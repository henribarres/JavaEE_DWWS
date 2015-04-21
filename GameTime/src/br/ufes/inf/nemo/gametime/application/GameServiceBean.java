package br.ufes.inf.nemo.gametime.application;

import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Stateless
public class GameServiceBean extends CrudServiceBean<Game> implements GameService {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDAO<Game> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Game createNewEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
