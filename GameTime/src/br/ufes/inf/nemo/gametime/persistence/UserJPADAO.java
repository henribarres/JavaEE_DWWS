package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public Class<User> getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

}