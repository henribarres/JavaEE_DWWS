package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@Stateless
public class GameJPADAO extends BaseJPADAO<Game> implements GameDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Game> getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
