package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.persistence.GameAccountDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ManageGameAccountServiceBean extends CrudServiceBean<GameAccount> implements ManageGameAccountService {

	private static final long serialVersionUID = 1L;

	@EJB
	private GameAccountDAO gameAccountDAO;
	
	@EJB
	private SessionService sessionService;
	
	
	
	@Override
	public BaseDAO<GameAccount> getDAO() {
		return gameAccountDAO ;
	}

	@Override
	protected GameAccount createNewEntity() {
		return new GameAccount();
	}

}
