package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.ufes.inf.nemo.gametime.controller.ManageGroupGameController;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.domain.GroupGame_;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class GroupGameJPADAO extends BaseJPADAO<GroupGame> implements GroupGameDAO{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(GroupGameJPADAO.class.getCanonicalName());
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<GroupGame> getDomainClass() {
		return GroupGame.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<GroupGame> findByAdmin(User admin) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupGame> cq = cb.createQuery(GroupGame.class);
		Root<GroupGame> root = cq.from(GroupGame.class);

		cq.where(cb.equal(root.get(GroupGame_.adminUser), admin));
		
		List<GroupGame> result = entityManager.createQuery(cq).getResultList();
		return result;
	}
	
	@Override
	public List<GroupGame> findByMember(User member) {
		
		logger.log(Level.INFO, " FIND BY MEMBER INIT ID {0} E {1} " , new Object[]{member.getId(), member.getName() } );
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupGame> cq = cb.createQuery(GroupGame.class);
		
		Root<GroupGame> root = cq.from(GroupGame.class);
		cq.select(root);
		
		Subquery<GroupGame> cqs = cq.subquery(GroupGame.class);
		
		Root<GroupGame> subroot = cqs.from(GroupGame.class);
		
		cqs.select(subroot);
		
		cqs.where(cb.equal(subroot.get(GroupGame_.adminUser), member));	
		
		cq.where(cb.isMember(member, root.get(GroupGame_.usersMembers)));
				//,cb.not(cb.exists(cqs)));
	
		List<GroupGame> result = entityManager.createQuery(cq).getResultList();
		
		logger.log(Level.INFO, " RETORNANDO {0} GROUP GAME MEMBER ",  result.size() );
		
		
		return result;
	}
	

}
