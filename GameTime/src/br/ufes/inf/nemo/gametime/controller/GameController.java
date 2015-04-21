package br.ufes.inf.nemo.gametime.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;


@Named
@SessionScoped
public class GameController extends JSFController{

	private static final long serialVersionUID = 1L;
	
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
