package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;



@Stateless
public class RegistrationServiceBean implements RegistrationService {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	
	@Override
	public void register(User user) throws Exception  {
		try {
			User  teste = userDAO.retrieveByEmail(user.getEmail());
			if(teste!=null){
				throw new Exception();
			}
		} 
		catch (PersistentObjectNotFoundException e) {	}  
		catch (MultiplePersistentObjectsFoundException e) {
			throw new Exception();
		}
		userDAO.save(user);	
			
	}

	
}
