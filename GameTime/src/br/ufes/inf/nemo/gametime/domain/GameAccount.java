package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class GameAccount extends  PersistentObjectSupport implements Comparable<GameAccount>{

	private static final long serialVersionUID = 1L;
	
	/* NOME DA CONTA */
	@NotNull
	private String name;
	
	/* LOGIN DA CONTA UTILIZADO PARA ACESSAR O GAME ONLINE*/
	@NotNull
	private String login;
	
	/* SENHA DO LOGIN */
	@NotNull
	private String password;
	
	/* GRUPO QUE COMPARTILHA A CONTA  */
	@ManyToOne @NotNull
	private GroupGame groupGame;
	
	
	/* USUARIO  PROPRIETARIO DA CONTA */
	@ManyToOne @NotNull
	private User userOwner;
	
	
	
	@Override
	public int compareTo(GameAccount o) { return super.compareTo(o); }
	
	
	/*  GETS AND SETS*/
	public String getName() { return name; }
	public void setName(String name) { 	this.name = name; }

	public GroupGame getGroupGame() { return groupGame; }
	public void setGroupGame(GroupGame groupGame) { this.groupGame = groupGame; }


	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }


	public String getPassword() {	return password; }
	public void setPassword(String password) {  this.password = password; }


	public User getUserOwner() { return userOwner; 	}
	public void setUserOwner(User userOwner) { this.userOwner = userOwner;	}

}
