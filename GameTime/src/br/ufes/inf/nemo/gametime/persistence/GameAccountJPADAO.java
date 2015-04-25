package br.ufes.inf.nemo.gametime.persistence;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class GameAccountJPADAO extends BaseJPADAO<GameAccount> implements GameAccountDAO{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(GameAccountJPADAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<GameAccount> getDomainClass() {
		return GameAccount.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
