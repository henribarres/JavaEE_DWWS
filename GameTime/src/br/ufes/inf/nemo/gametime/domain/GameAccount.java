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
	
	@ManyToOne
	private Game game;
	
	
	
	
	/*  GETS AND SETS*/
	public String getName() { return name; }
	public void setName(String name) { 	this.name = name; }

	public GroupGame getGroupGame() { return groupGame; }
	public void setGroupGame(GroupGame groupGame) { this.groupGame = groupGame; }

	public Game getGame() { return game; }
	public void setGame(Game game) { this.game = game; }

}
