package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGameAccountService;
import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.LikeFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class ManageGameAccountController extends CrudController<GameAccount>{

	private static final Logger logger = Logger.getLogger(ManageGameAccountController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageGameAccountService manageGameAccountService;
	
	
	public ManageGameAccountController() {
	    viewPath = "/manageGameAccount/";
	    bundleName = "msgsGametime";
	}
	
	@Override
	protected CrudService<GameAccount> getCrudService() {
		manageGameAccountService.authorize();
		return manageGameAccountService;
	}

	@Override
	protected GameAccount createNewEntity() {
		logger.log(Level.INFO, "INICIALIZANDO NOVO CONTA");
		GameAccount newEntity = new GameAccount();
		return newEntity;
	}

	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.toString();
	}
	
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageGameAccount.filter.byName", "name", getI18nMessage("msgsGametime", "manageGameAccount.text.filter.byName")));
		
	}

}
