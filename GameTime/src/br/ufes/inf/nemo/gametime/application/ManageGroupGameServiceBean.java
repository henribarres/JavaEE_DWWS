package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.persistence.GroupGameDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;



@Stateless
public class ManageGroupGameServiceBean extends CrudServiceBean<GroupGame> implements ManageGroupGameService {

	private static final long serialVersionUID = 1L;

	
	/* DAO PARA OPERAÇÕES NO BANCO DE DADOS*/
	@EJB
	private GroupGameDAO groupGameDAO;
	
	
	/* METODO OBRIGATORIO*/
	@Override
	public BaseDAO<GroupGame> getDAO() {
		return groupGameDAO;
	}

	/* METODO OBRIGATORIO*/
	@Override
	protected GroupGame createNewEntity() {
		return new GroupGame();
	}
	
	
	/* FUNCAO PARA VALIDAR CRIAÇÃO DE GRUPOS ,  UM USUARIO NÃO PODE TER DOIS GRUPOS COM MESMO NOME */
	@Override
	public void validateCreate(GroupGame entity) throws CrudException {
		CrudException crudException = null;
		String name = entity.getName();
		String crudExceptionMessage = "The Group \"" + entity.getName() + " cannot be created, validation errors.";
		if (name != null && name.length() > 0) {
			try {
				GroupGame anotherEntity  = groupGameDAO.retrieveByNameAndAdmin(name,entity.getAdminUser());
				if (anotherEntity != null) {
					crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGroupGame.error.repeated");
				}		
			} 
			catch (PersistentObjectNotFoundException e) { return;	}
			catch (MultiplePersistentObjectsFoundException e) {
				crudException = addValidationError(crudException, crudExceptionMessage, "name", "manageGroupGame.error.multipleInstancesError");
			}
		}
		if (crudException != null) throw crudException;
	}
	
	
	

}
