package br.ufes.inf.nemo.gametime.domain;


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Invitation extends PersistentObjectSupport implements Comparable<Invitation>{
	
	private static final long serialVersionUID = 1L;

	/* EMAIL DO CONVIDADO*/
	@NotNull 
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int compareTo(Invitation o) {
		return super.compareTo(o);
	}
	
	
		
}
