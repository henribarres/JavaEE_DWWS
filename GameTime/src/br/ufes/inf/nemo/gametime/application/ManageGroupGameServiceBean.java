package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.persistence.GroupGameDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ManageGroupGameServiceBean extends CrudServiceBean<GroupGame> implements ManageGroupGameService {

	private static final long serialVersionUID = 1L;

	@EJB
	private GroupGameDAO groupGgameDAO;
	
	@Override
	public BaseDAO<GroupGame> getDAO() {
		return groupGgameDAO;
	}

	@Override
	protected GroupGame createNewEntity() {
		return new GroupGame();
	}
	
	

}
