package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface UserDAO extends BaseDAO<User>{

}
