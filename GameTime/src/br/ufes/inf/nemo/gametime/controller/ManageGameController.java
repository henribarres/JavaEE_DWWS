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
	
	
	/* CRUDSERVICE*/
	@EJB
	private ManageGameService manageGameService;
	
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO */
	@Inject
	private SessionController sessionController;
	
	
	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGameController() {
	    viewPath = "/manageGame/";
	    bundleName = "msgsGametime";
	}
	
	public String begin(){
		return  viewPath +"list.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	@Override
	public CrudService<Game> getCrudService() {
		return manageGameService;
	}
	

	@Override
	protected Game createNewEntity() {
		logger.log(Level.INFO, "INICIALIZANDO NOVO GAME");
		return new Game();
	}

	
	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.getName()+" da empresa "+selectedEntity.getManufacturer();
	}

	
	/* FILTROS CRIADOS PARA GAME POR NOME OU EMPRESA */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageGame.filter.byName", "name", getI18nMessage(bundleName, "manageGame.text.filter.byName")));
		addFilter(new LikeFilter("manageGame.filter.byManufacturer", "manufacturer", getI18nMessage(bundleName, "manageGame.text.filter.byManufacturer")));
	}
	
	
	
	
	
	
	
	/*  FUNCOES QUE VERIFICAM SE O USUARIO ESTA LOGADO PARA CONTINUAR E SE ELE E ADMIN*/
	@Override
	public String create() {
		if(sessionController.isLoggedIn() && sessionController.getAuthenticatedUser().isAdmin()){
			return super.create();
		}
		logger.log(Level.INFO, " NAO FOI POSSIVEL CRIAR GAME, USUARIO NAO LOGADO OU NÃO É ADMINISTRADOR");
		addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.create","");
		return null;
	}
	
	
	/* PARA CONSULTAR UM GAME TEM QUE ESTAR LOGADO */
	@Override
	public String retrieve() {
		if(sessionController.isLoggedIn()){
			return super.retrieve();
		}
		else{
			logger.log(Level.INFO, " NAO FOI POSSIVEL CONSULTAR GAME, USUARIO NAO LOGADO");
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.retrieve", summarizeSelectedEntity());
			return null;
		}
	}
	
	
	/* PARA MOVER UM GAME PARA LIXEIRA TEM QUE SER UM USUARIO ADMINISTRADOR*/
	@Override
	public void trash() {
		if(sessionController.isLoggedIn() && sessionController.getAuthenticatedUser().isAdmin()){
			super.trash();
		}
		else{
			logger.log(Level.INFO, " NAO FOI POSSIVEL DELETAR GAME, USUARIO NAO LOGADO OU NÃO É ADMINISTRADOR");
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.delete", summarizeSelectedEntity());
		}
	}

	
	/* PARA ATUALIZAR UM GAME TEM QUE SER UM USUARIO ADMINISTRADOR*/
	@Override
	public String update() {
		if(sessionController.isLoggedIn() && sessionController.getAuthenticatedUser().isAdmin()){
			return super.update();
		}
		logger.log(Level.INFO, " NAO FOI POSSIVEL ATUALIZAR GAME, USUARIO NAO LOGADO OU NÃO É ADMINISTRADOR");
		addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".forbiden.update", summarizeSelectedEntity());
		return null;
	}
	
	
	/* PARA DELETAR UM GAME TEM QUE SER UM USUARIO ADMINISTRADOR */
	@Override
	public String delete() {
		if(sessionController.isLoggedIn() && sessionController.getAuthenticatedUser().isAdmin()){
			return super.delete();
		}
		return "/error-permissao.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	/* PARA SALVAR UMA GAME TEM QUE SER UM USUARIO ADMINISTRADOR CASO CONTRARIO VOLTAR PARA UMA PAGINA DE ERRO*/
	@Override
	public String save() {
		if(sessionController.isLoggedIn() && sessionController.getAuthenticatedUser().isAdmin()){
			return super.save();
		}
		return "/error-permissao.xhtml?faces-redirect=" + getFacesRedirect();
	}
}
