package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class GroupGameJPADAO extends BaseJPADAO<GroupGame> implements GroupGameDAO{

	private static final long serialVersionUID = 1L;
	
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

	

}
