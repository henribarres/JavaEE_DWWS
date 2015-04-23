package br.ufes.inf.nemo.gametime.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.persistence.GameDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;


@Stateless
public class ManageGameServiceBean extends CrudServiceBean<Game> implements ManageGameService {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ManageGameServiceBean.class.getCanonicalName());
	
	@EJB
	private GameDAO gameDAO;
	
	@Override
	public BaseDAO<Game> getDAO() {
		return gameDAO;
	}

	@Override
	public Game createNewEntity() {
		return new Game();
	}
	
	/*
	 * PARA CRIAR UM GAME NÃO PODE JA TER UM COM O MESMO NOME E EMPRESA CADASTRADO.
	 */
	@Override
	public void validateCreate(Game entity) throws CrudException {
		CrudException crudException = null;
		String name = entity.getName();
		String manufacturer = entity.getManufacturer();
		String crudExceptionMessage = "The Game \"" + entity.getName() + " cannot be created, validation errors.";
		
		if (name != null && name.length() > 0 && manufacturer !=null && manufacturer.length()>0) 
			try {
				Game anotherEntity = gameDAO.retrieveByNameAndManufacturer(name,manufacturer);
				if (anotherEntity != null) {
					logger.log(Level.INFO, "CRIAÇÃO DO GAME \"{0}\" VIOLOU A VALIDAÇÃO POR JÁ EXISTIR GAME COM MESMO NOME E EMPRESA",  name );
					crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGame.error.repeated");
					crudException.addValidationError("manufacturer", "manageGame.error.repeated", null);
				}
			}
		catch (PersistentObjectNotFoundException e) {
			logger.log(Level.INFO, "VALIDAÇÃO  OK - O GAME: {0} VAI SER CADASTRADO", name);
			return;
		}
		catch (MultiplePersistentObjectsFoundException e) {
			logger.log(Level.INFO, "CRIAÇÃO DO GAME  \"" + name + "\" TEVE MULTIPLOS RESULTADOS COM O MESMO NOME E EMPRESA", e);
			crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGame.error.multipleInstancesError");
			crudException.addValidationError("manufacturer", "manageGame.error.multipleInstancesError", null);
		}
		if (crudException != null) throw crudException;
	}

	
	/*
	 * PARA ATUALIZAR UM GAME NÃO SE PODE MODIFICAR O NOME E EMPRESA PARA UM IGUAL JA CADASTRADO
	 * */
	@Override
	public void validateUpdate(Game entity) throws CrudException {
		CrudException crudException = null;
		String name = entity.getName();
		String manufacturer = entity.getManufacturer();
		String crudExceptionMessage = "The Game \"" + entity.getName() + " não ser atualizado, validation errors.";
		
		if (name != null && name.length() > 0 && manufacturer !=null && manufacturer.length()>0) 
			try {
				Game anotherEntity = gameDAO.retrieveByNameAndManufacturer(name,manufacturer);
				if ((anotherEntity != null) && (!anotherEntity.getId().equals(entity.getId()))) {
					logger.log(Level.INFO, "ATUALIZAÇÃO DO GAME \"{0}\" VIOLOU A VALIDAÇÃO POR JÁ EXISTIR GAME COM MESMO NOME E EMPRESA",  name );
					crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGame.error.repeated");
					crudException.addValidationError("manufacturer", "manageGame.error.repeated", null);
				}
			}
		catch (PersistentObjectNotFoundException e) {
			logger.log(Level.INFO, "VALIDAÇÃO  OK - O GAME: {0} VAI SER CADASTRADO", name);
			return;
		}
		catch (MultiplePersistentObjectsFoundException e) {
			logger.log(Level.INFO, "ATUALIZAÇÃO DO GAME  \"" + name + "\" TEVE MULTIPLOS RESULTADOS COM O MESMO NOME E EMPRESA", e);
			crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGame.error.multipleInstancesError");
			crudException.addValidationError("manufacturer", "manageGame.error.multipleInstancesError", null);
		}
		if (crudException != null) throw crudException;
	}

}
