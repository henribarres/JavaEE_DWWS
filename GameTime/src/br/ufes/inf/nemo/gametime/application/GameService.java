package br.ufes.inf.nemo.gametime.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;



@Local
public interface GameService extends CrudService<Game>{

}
