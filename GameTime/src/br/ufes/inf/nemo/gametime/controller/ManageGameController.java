package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
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
	
	@Inject
	private SessionController sessionController;
	
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
	
	
	/*  FUNCOES QUE VERIFICAM SE O USUARIO ESTA LOGADO PARA CONTINUAR*/
	@Override
	public String create() {
		if(sessionController.isLoggedIn()){
			return super.create();
		}
		
		logger.log(Level.INFO, " NAO FOI POSSIVEL CRIAR USUARIO NAO LOGADO");
		addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.create","");
		return null;
		
	}
	
	@Override
	public String retrieve() {
		if(sessionController.isLoggedIn()){
			return super.retrieve();
		}
		else{
			logger.log(Level.INFO, " NAO FOI POSSIVEL CONSULTAR USUARIO NAO LOGADO");
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.retrieve", summarizeSelectedEntity());
			return null;
		}
	}
	
	@Override
	public void trash() {
		if(sessionController.isLoggedIn()){
			super.trash();
		}
		else{
			logger.log(Level.INFO, " NAO FOI POSSIVEL DELETAR USUARIO NAO LOGADO");
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.delete", summarizeSelectedEntity());
		}
	}

	@Override
	public String update() {
		if(sessionController.isLoggedIn()){
			return super.update();
		}
		logger.log(Level.INFO, " NAO FOI POSSIVEL ATUALIZAR USUARIO NAO LOGADO");
		addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.update", summarizeSelectedEntity());
		return null;
	}
	
	@Override
	public String delete() {
		if(sessionController.isLoggedIn()){
			return super.delete();
		}
		return "/error-permissao.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	@Override
	public String save() {
		if(sessionController.isLoggedIn()){
			return super.save();
		}
		return "/error-permissao.xhtml?faces-redirect=" + getFacesRedirect();
	}
}
