package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface UserDAO extends BaseDAO<User>{
	public User retrieveByUsername(String username)throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
