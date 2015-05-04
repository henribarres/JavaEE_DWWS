package br.ufes.inf.nemo.gametime.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGameAccountService;
import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.persistence.GameAccountDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class ManageGameAccountController extends CrudController<GameAccount>{

	private static final long serialVersionUID = 1L;
	
	/* CRUDSERVICE DE GAMEACCOUNT */
	@EJB
	private ManageGameAccountService manageGameAccountService;
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO E TAMBÉM SABER QUEM É ESSE USUARIO*/
	@Inject
	private SessionController sessionController;
	
	@Inject
	private ManageGroupGameController manageGroupGameController;
	
	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGameAccountController() {
	    viewPath = "/manageGameAccount/";
	    bundleName = "msgsGametime";
	}
	
	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<GameAccount> getCrudService() {
		return manageGameAccountService;
	}

	/* METODO OBRIGATORIO */
	@Override
	protected GameAccount createNewEntity() {
		GameAccount newEntity = new GameAccount();
		return newEntity;
	}
	
	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		// SEM FILTROS
	}

	/* FUNCAO PARA IMPRIMIR INFORMACAO DO CONTA */
	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.toString();
	}
	
	/* FUNÇÃO QUE RETORNA AS CONTAS DO GRUPO SELECIONADO */
	@Override
	protected void retrieveEntities() {
		entities =( (GameAccountDAO) manageGameAccountService.getDAO()).findByGroup(manageGroupGameController.getSelectedEntity());
	}

	/* FUNCÃO QUE PREPARA UMA CONTA ANTES DE SALVA-LA */
	@Override
	protected void prepEntity() {
		selectedEntity.setGroupGame(manageGroupGameController.getSelectedEntity());
		selectedEntity.setUserOwner(sessionController.getAuthenticatedUser());
		super.prepEntity();
	}
	
}
