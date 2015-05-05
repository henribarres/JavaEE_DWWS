package br.ufes.inf.nemo.gametime.application;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.persistence.GameAccountDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;



@Stateless
public class ManageGameAccountServiceBean extends CrudServiceBean<GameAccount> implements ManageGameAccountService {

	private static final long serialVersionUID = 1L;

	/* DAO PARA OPERAÇÕES NO BANCO DE DADOS*/
	@EJB
	private GameAccountDAO gameAccountDAO;
	
	
	/* METODO OBRIGATORIO*/
	@Override
	public BaseDAO<GameAccount> getDAO() {
		return gameAccountDAO ;
	}

	/* METODO OBRIGATORIO*/
	@Override
	protected GameAccount createNewEntity() {
		return new GameAccount();
	}

	@Override
	public void validateCreate(GameAccount entity) throws CrudException {
		CrudException crudException = null;
		String name = entity.getName();
		String crudExceptionMessage = "The Account \"" + entity.getName() + " cannot be created, validation errors.";
		if (name != null && name.length() > 0) {
			try {
				List<GameAccount> anotherEntity  = gameAccountDAO.retrieveByGameAccount(entity);
				if (anotherEntity != null) {
					if(anotherEntity.isEmpty()) return ; 
					crudException = addValidationError(crudException, crudExceptionMessage, "login", "manageGameAccount.error.repeated");
					crudException.addValidationError("password", "manageGameAccount.error.repeated", null);
				}	
			} 
			catch (PersistentObjectNotFoundException e) { return;}
		}
		if (crudException != null) throw crudException;
	}

	
	
	
	
	
	
	
	
	
	

}

