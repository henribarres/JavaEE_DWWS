package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class GameAccount extends  PersistentObjectSupport implements Comparable<GameAccount>{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String name;
	
	@NotNull
	private String login;
	
	@NotNull
	private String senha;
	
	@ManyToOne @NotNull
	private GroupGame groupGame;
	
	@ManyToOne @NotNull
	private User userOwner;
	
	@Override
	public int compareTo(GameAccount o) {
		return name.compareToIgnoreCase(o.name);
	}
	
	
	/*  GETS AND SETS*/
	public String getName() { return name; }
	public void setName(String name) { 	this.name = name; }

	public GroupGame getGroupGame() { return groupGame; }
	public void setGroupGame(GroupGame groupGame) { this.groupGame = groupGame; }


	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }

	public String getSenha() { 	return senha; }
	public void setSenha(String senha) { this.senha = senha; }
	
	

}
