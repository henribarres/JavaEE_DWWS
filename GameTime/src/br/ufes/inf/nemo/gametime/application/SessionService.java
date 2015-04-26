package br.ufes.inf.nemo.gametime.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.User;


@Local
public interface SessionService extends Serializable{

	public User getAuthenticatedUser();
	
	void login(String email, String password) throws Exception;
}
