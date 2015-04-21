package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Stateless
public class UserServiceBean extends CrudServiceBean<User> implements UserService {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}

	@Override
	protected User createNewEntity() {
		return new User();
	}

}
