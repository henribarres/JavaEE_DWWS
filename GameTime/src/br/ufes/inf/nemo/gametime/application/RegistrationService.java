package br.ufes.inf.nemo.gametime.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.User;


@Local
public interface RegistrationService extends Serializable{

	public void register(User user);
}
