package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface GameAccountDAO extends BaseDAO<GameAccount>{

	List<GameAccount> findByGroup(GroupGame group);

	List<GameAccount> retrieveByGameAccount(GameAccount entity)throws PersistentObjectNotFoundException;

}
