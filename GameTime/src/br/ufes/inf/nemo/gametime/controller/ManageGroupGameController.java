package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGroupGameService;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class ManageGroupGameController extends CrudController<GroupGame>{

	private static final Logger logger = Logger.getLogger(ManageGroupGameController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageGroupGameService manageGroupGameService;
	
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO */
	@Inject
	private SessionController sessionController;

	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGroupGameController() {
	    viewPath = "/manageGroupGame/";
	    bundleName = "msgsGametime";
	}
	
	
	public String begin(){
		return "/error-permissao.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	@Override
	protected CrudService<GroupGame> getCrudService() {
		return manageGroupGameService;
	}

	@Override
	protected GroupGame createNewEntity() {
		return new GroupGame();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
	}

}
