package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.persistence.GameDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Stateless
public class GameServiceBean extends CrudServiceBean<Game> implements GameService {

	private static final long serialVersionUID = 1L;

	@EJB
	private GameDAO gameDAO;
	
	@Override
	public BaseDAO<Game> getDAO() {
		return gameDAO;
	}

	@Override
	protected Game createNewEntity() {
		return new Game();
	}

}
