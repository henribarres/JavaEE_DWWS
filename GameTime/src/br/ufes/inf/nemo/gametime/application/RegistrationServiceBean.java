package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.persistence.UserDAO;



@Stateless
public class RegistrationServiceBean implements RegistrationService {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	
	@Override
	public void register(User user) {
		userDAO.save(user);		
	}

	
}
