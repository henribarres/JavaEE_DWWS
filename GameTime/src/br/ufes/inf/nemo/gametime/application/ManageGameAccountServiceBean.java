package br.ufes.inf.nemo.gametime.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.persistence.GameAccountDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ManageGameAccountServiceBean extends CrudServiceBean<GameAccount> implements ManageGameAccountService {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ManageGameAccountServiceBean.class.getCanonicalName());
	
	@EJB
	private GameAccountDAO gameAccountDAO;
	
	@Override
	public BaseDAO<GameAccount> getDAO() {
		return gameAccountDAO;
	}

	@Override
	protected GameAccount createNewEntity() {
		return new GameAccount();
	}

	@Override
	public void validateCreate(GameAccount entity) throws CrudException {
		logger.log(Level.INFO, "VALIDAÇÃO PARA CONTA ");
		super.validateCreate(entity);
	}
	
	
	
}
