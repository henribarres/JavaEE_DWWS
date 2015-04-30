package br.ufes.inf.nemo.gametime.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageGroupGameService;
import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.persistence.GameDAO;
import br.ufes.inf.nemo.gametime.persistence.GroupGameDAO;
import br.ufes.inf.nemo.gametime.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;


@Named
@SessionScoped
public class ManageGroupGameController extends CrudController<GroupGame>{

	private static final Logger logger = Logger.getLogger(ManageGroupGameController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	/* CRUDSERVICE DE GROUPGAME*/
	@EJB
	private ManageGroupGameService manageGroupGameService;
	
	/* DAO PARA BUSCAR A LISTA DE GAME DO BANCO DE DADOS */
	@EJB
	private GameDAO gameDAO;
	
	/* DAO PARA BUSCAR USUARIO PARA ADD A UM GRUPO */
	@EJB
	private UserDAO userDAO;
	
	/* VARIAVEL PARA VERIFICAR SE É PARA ADICIONAR UM USUARIO A UM GRUPO*/
	private boolean addUser = false;
	
	/* PARA ADIOCIONAR  UM NOVO USUARIO A UM GRUPO*/
	private User UserAdd ;
	
	/* JSF Converter PARA OBJETOS GAME */
	private PersistentObjectConverterFromId<Game> gameConverter;
	
	/* JSF Converter PARA OBJETOS USER */
	private PersistentObjectConverterFromId<User> userConverter;
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO E TAMBÉM SABER QUEM É ESSE USUARIO*/
	@Inject
	private SessionController sessionController;

	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGroupGameController() {
	    viewPath = "/manageGroupGame/";
	    bundleName = "msgsGametime";
	}
	
	/* FUNÇÃO INICIAL PARA O LINK GRUPO */
	public String begin(){
		return viewPath + "list.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	/* METODO OBRIGATORIO */
	@Override
	protected GroupGame createNewEntity() {
		return new GroupGame();
	}

	/* METODO OBRIGATORIO */
	@Override
	protected void initFilters() {
		// SEM FILTROS
	}

	/* METODO OBRIGATORIO */
	@Override
	protected CrudService<GroupGame> getCrudService() {
		return manageGroupGameService;
	}
	
	/* METODO UTILIZADO ANTES DE SALVAR UM GROUPGAME SETA O ADMINISTRADOR E O TORNA ATIVO*/
	@Override
	protected void prepEntity() {
		selectedEntity.setAdminUser(sessionController.getAuthenticatedUser());
		selectedEntity.setIsactive(true);
		super.prepEntity();
	}
	
	/* FUNÇÃO PARA SUGERIR GAMES PARA O GRUPO A SER CRIADO*/
	public List<Game> suggestGame(String query) {
		if (query.length() > 0) {
			List<Game> cities = gameDAO.findByName(query);
			return cities;
		}
		return null;
	}
	
	/* GET PARA O CONVERTER DE GAME */
	public Converter getGameConverter() {
		if (gameConverter == null) {
			logger.log(Level.FINEST, "Creating a city converter...");
			gameConverter = new PersistentObjectConverterFromId<Game>(gameDAO);
		}
		return gameConverter;
	}
	
	/* FUNÇÃO PARA SUGERIR USUARIOS PARA ADICIONAR AO GRUPO*/
	public List<User> suggestUser(String query){
		if (query.length() > 0) {
			List<User> users = userDAO.findByEmailList(query);
			users.remove(sessionController.getAuthenticatedUser());
			return users;
		}
		return null;
	}
	
	/* GET PARA O CONVERTER DE USER*/
	public PersistentObjectConverterFromId<User> getUserConverter() {
		if (userConverter == null) {
			logger.log(Level.FINEST, "Creating a user converter...");
			userConverter = new PersistentObjectConverterFromId<User>(userDAO);
		}
		return userConverter;
	}
	
	
	
	
	
	
	
	
	
	/* FUNÇÃO INICIAL PARA ADICIONAR USUARIO AO GRUPO*/
	public String addUser(){
		addUser = true;
		UserAdd = new User();
		super.update();
		readOnly = true;
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	/* GET PARA VER SE E PARA ADICIONAR USUARIOS AO GRUPO*/
	public boolean isAddUser() {
		return addUser;
	}
	
	/* FUNCAO O USUARIO A SER ADICIONADO COMMO MEBRO DO GRUPO*/
	public User getUserAdd() {
		return UserAdd;
	}
	
	/* FUNCAO QUE ADICIONA O USUARIO AO GRUPO*/
	public String addUserInGroup(){
		//selectedEntity = manageGroupGameService.getDAO().refresh(selectedEntity);
		selectedEntity.getUsersMembers().add(UserAdd);
		logger.log(Level.INFO, "GROUPO COM ID ======= {0} " , selectedEntity.getId());
		return super.save();
	}
	
	
	/*FUNÇÃO QUE RETORNA OS GRUPOS*/
	@Override
	protected void retrieveEntities() {
		
		entities = ((GroupGameDAO) manageGroupGameService.getDAO()).findByAdmin(sessionController.getAuthenticatedUser());
			
		entities.addAll(((GroupGameDAO) manageGroupGameService.getDAO()).findByMember(sessionController.getAuthenticatedUser()));
				
	}
	
	
	
	
	
	public String addConta(){
		return null;
	}
	
	

	
}
