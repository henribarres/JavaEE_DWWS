package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface GameDAO extends BaseDAO<Game>{
	public Game retrieveByNameAndManufacturer(String name,String manufacturer) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
