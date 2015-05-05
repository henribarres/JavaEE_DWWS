package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.domain.GameAccountHistoric;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Local
public interface GameAccountHistoricDAO extends BaseDAO<GameAccountHistoric>{

	
	public List<GameAccount> retrieveByAccount(GameAccount account);
}
