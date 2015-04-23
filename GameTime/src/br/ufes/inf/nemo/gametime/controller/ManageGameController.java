package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGameService;
import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.LikeFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named
@SessionScoped
public class ManageGameController extends CrudController<Game>{

	private static final Logger logger = Logger.getLogger(ManageGameController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageGameService manageGameService;
	
	public ManageGameController() {
	    viewPath = "/manageGame/";
	    bundleName = "msgsGametime";
	}
	
	@Override
	protected void prepEntity() {
		logger.log(Level.INFO, "PREPARANDO PARA SALVAR O GAME ({0})...", selectedEntity.getName());
	}
	
	@Override
	public CrudService<Game> getCrudService() {
		manageGameService.authorize();
		return manageGameService;
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
		addFilter(new LikeFilter("manageGame.filter.byName", "name", getI18nMessage("msgsGametime", "manageGame.text.filter.byName")));
		addFilter(new LikeFilter("manageGame.filter.byManufacturer", "manufacturer", getI18nMessage("msgsGametime", "manageGame.text.filter.byManufacturer")));
	}
}
