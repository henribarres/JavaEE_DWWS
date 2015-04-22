package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.GameService;
import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.LikeFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class GameController extends CrudController<Game>{

	private static final Logger logger = Logger.getLogger(GameController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GameService gameService;
	
	
	
	public String cadastraGame(){
		try {	
			logger.log(Level.INFO, "CADASTRO DE GAME");
			return "/cadastro/game/success.xhtml?faces-redirect=true";
		}
		catch (Exception e) {
			return "/cadastro/game/underage.xhtml?faces-redirect=true";
		}
	}
	private Game game = new Game();
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	protected void prepEntity() {
		logger.log(Level.FINER, "Preparing spiritist for storage ({0})...", selectedEntity);
		selectedEntity = createNewEntity();
		selectedEntity.setName("game");
		selectedEntity.setManufacturer("manufacturer");
	}
	
	@Override
	public CrudService<Game> getCrudService() {
		gameService.authorize();
		return gameService;
	}


	@Override
	protected Game createNewEntity() {
		logger.log(Level.INFO, "INICIALIZANDO NOVO GAME");
		Game newEntity = new Game();
		return newEntity;
	}

	
	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.getName()+" da empresa "+selectedEntity.getManufacturer();
	}

	@Override
	protected void initFilters() {
		// One can filter spiritists by name or e-mail.
		addFilter(new LikeFilter("manageSpiritists.filter.byName", "name", getI18nMessage("msgsCore", "manageSpiritists.text.filter.byName")));
		addFilter(new LikeFilter("manageSpiritists.filter.byEmail", "email", getI18nMessage("msgsCore", "manageSpiritists.text.filter.byEmail")));
	}
	
	@Override
	public String save() {
		try {	
			super.save();
			logger.log(Level.INFO, "CADASTRO DE GAME");
			return "/cadastro/game/success.xhtml";
		}
		catch (Exception e) {
			return "/cadastro/game/underage.xhtml?faces-redirect=true";
		}
	}
}
