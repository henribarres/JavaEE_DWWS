package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface GameAccountDAO extends BaseDAO<GameAccount>{

}
