package br.ufes.inf.nemo.gametime.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;



@Local
public interface ManageGameAccountService extends CrudService<GameAccount>{

}
