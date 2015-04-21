package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends  PersistentObjectSupport{
	
	private static final long serialVersionUID = 1L;

	@NotNull 
	private String username;
	
	@NotNull 
	private String password;
	
	
}
