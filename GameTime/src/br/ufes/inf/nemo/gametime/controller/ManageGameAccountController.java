package br.ufes.inf.nemo.gametime.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGameAccountService;
import br.ufes.inf.nemo.gametime.domain.GameAccount;
import br.ufes.inf.nemo.gametime.persistence.GameAccountDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;


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
	
	
	
	
	
	
	private int number;
	 
    public int getNumber() {
        return number;
    }
 
    public void increment() {
        number--;
    }
	
	
	
	private boolean jogando;
	public boolean isJogando() {
		return jogando;
	}
	public void setJogando(boolean jogando) {
		this.jogando = jogando;
	}
	
	public String iniciar(){
		jogando = true;
		number = 60;
		return null;
	}
	public String parar(){
		jogando = false;
		return null;
	}
	private GameAccount contaplay;
	public GameAccount getContaplay() { return contaplay; }
	public void setContaplay(GameAccount contaplay) { this.contaplay = contaplay; }
	public String jogar(){	  
		retrieveEntities();
		return  getViewPath() + "jogar.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	/* JSF Converter PARA OBJETOS  */
	private PersistentObjectConverterFromId<GameAccount> gameAccountConverter;
	/* GET PARA O CONVERTER DE  */
	public Converter getGameAccountConverter() {
		if (gameAccountConverter == null) {
			gameAccountConverter = new PersistentObjectConverterFromId<GameAccount>(getCrudService().getDAO());
		}
		return gameAccountConverter;
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
