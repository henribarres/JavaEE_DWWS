package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface GroupGameDAO extends BaseDAO<GroupGame>{

	List<GroupGame> findByAdmin(User admin);

	List<GroupGame> findByMember(User member);

	GroupGame retrieveByNameAndAdmin(String name, User admin) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	
}
