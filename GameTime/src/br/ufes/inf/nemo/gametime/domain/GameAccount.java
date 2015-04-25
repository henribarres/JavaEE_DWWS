package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class GameAccount extends  PersistentObjectSupport{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	
	@ManyToOne
	private GroupGame groupGame;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
