package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.domain.GameAccount_;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

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
	

}
