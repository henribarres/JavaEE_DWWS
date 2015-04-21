package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface ContaDAO extends BaseDAO<Conta>{

}
