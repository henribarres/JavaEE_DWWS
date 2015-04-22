package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@Stateless
public class GameJPADAO extends BaseJPADAO<Game> implements GameDAO{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Game> getDomainClass() {
		return Game.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
