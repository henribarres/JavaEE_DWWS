package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface UserDAO extends BaseDAO<User>{
	public User retrieveByEmail(String email)throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	List<User> findByEmailList(String email);
	
	
}
