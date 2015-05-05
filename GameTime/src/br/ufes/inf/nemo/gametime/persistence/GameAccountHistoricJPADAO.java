package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.domain.GameAccountHistoric;
import br.ufes.inf.nemo.gametime.domain.GameAccountHistoric_;
import br.ufes.inf.nemo.gametime.domain.GameAccount_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@Stateless
public class GameAccountHistoricJPADAO extends BaseJPADAO<GameAccountHistoric> implements GameAccountHistoricDAO{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Class<GameAccountHistoric> getDomainClass() {
		return GameAccountHistoric.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<GameAccount> retrieveByAccount(GameAccount account) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GameAccountHistoric> cq = cb.createQuery(GameAccountHistoric.class);
		Root<GameAccountHistoric> root = cq.from(GameAccountHistoric.class);
		
		Join<GameAccountHistoric,GameAccount> join = root.join(GameAccountHistoric_.gameAccount);
		
		
		cq.where(	
				cb.equal(join.get(GameAccount_.groupGame), account.getGroupGame()),
				cb.isNull(root.get(GameAccountHistoric_.endDate))
						
			);
		
		//List<GameAccount> result =   entityManager.createQuery(cq).getResultList();
		
		return null;
	}

	/*
	@Override
	public List<GameAccount> retrieveByAccount(GameAccount account) {
		
		
		
	
		return result;
		
	}

	*/

}
