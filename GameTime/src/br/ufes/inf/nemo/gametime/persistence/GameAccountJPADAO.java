package br.ufes.inf.nemo.gametime.persistence;

import java.util.Calendar;
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
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.domain.GroupGame_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Stateless
public class GameAccountJPADAO extends BaseJPADAO<GameAccount> implements GameAccountDAO{

	private static final long serialVersionUID = 1L;

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
	
	@Override
	public List<GameAccount> findByGroup(GroupGame group) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GameAccount> cq = cb.createQuery(GameAccount.class);
		Root<GameAccount> root = cq.from(GameAccount.class);

		cq.where(cb.equal(root.get(GameAccount_.groupGame), group));
		cq.orderBy(cb.asc(root.get(GameAccount_.name)));
		
		List<GameAccount> result = entityManager.createQuery(cq).getResultList();
		return result;
	}

	
	
	@Override
	public List<GameAccount> retrieveByGameAccount(GameAccount entity) throws PersistentObjectNotFoundException {
			
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GameAccount> cq = cb.createQuery(GameAccount.class);
		
		Root<GameAccount> root = cq.from(GameAccount.class);
		
		Join<GameAccount, GroupGame> join = root.join(GameAccount_.groupGame);
	
		cq.where(	cb.equal(root.get(GameAccount_.login), entity.getLogin()),
					//cb.equal(root.get(GameAccount_.password), entity.getPassword()),
					cb.equal( join.get(GroupGame_.game),entity.getGroupGame().getGame() )
				);
		
		List<GameAccount> result =   entityManager.createQuery(cq).getResultList();
	
		return result;
	}
	
	
	
	@Override
	public List<GameAccount> retrieveByPlaying(GroupGame group) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GameAccount> cq = cb.createQuery(GameAccount.class);
		Root<GameAccountHistoric> root = cq.from(GameAccountHistoric.class);
		Join<GameAccountHistoric,GameAccount> join = root.join(GameAccountHistoric_.gameAccount);
	
		Calendar teste =  Calendar.getInstance();
		teste.set(11, teste.get(11)-1);
		
		cq.select(join);
		cq.where(	
				cb.equal(join.get(GameAccount_.groupGame), group) ,
				cb.isNull(root.get(GameAccountHistoric_.endDate)),
				cb.greaterThanOrEqualTo(root.get(GameAccountHistoric_.startDate), teste.getTime())
			);
		
		List<GameAccount> result =   entityManager.createQuery(cq).getResultList();
		return result;
		
	}
	
	

}
