package br.ufes.inf.nemo.gametime.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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

	private static final long serialVersionUID = 1L;
	
	/* CRUDSERVICE DE GROUPGAME*/
	@EJB
	private ManageGroupGameService manageGroupGameService;
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO E TAMBÉM SABER QUEM É ESSE USUARIO*/
	@Inject
	private SessionController sessionController;
	
	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGroupGameController() {
	    viewPath = "/manageGroupGame/";
	    bundleName = "msgsGametime";
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
		super.prepEntity();
	}
	
	/* FUNCAO PARA IMPRIMIR INFORMACAO DO GRUPO */
	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.getName()+" com Administrador "+ selectedEntity.getAdminUser().getEmail();
	}
	
	/*FUNÇÃO QUE RETORNA OS GRUPOS DO USUARIO LOGADO*/
	@Override
	protected void retrieveEntities() {
		entities = ((GroupGameDAO) manageGroupGameService.getDAO()).findByAdmin(sessionController.getAuthenticatedUser());
		entities.addAll(((GroupGameDAO) manageGroupGameService.getDAO()).findByMember(sessionController.getAuthenticatedUser()));
	}
	
	
	

	
	
	/* ************ATRIBUTOS E FUNCOES PARA SELECIONAR GAME ****************************/
	
	/* DAO PARA BUSCAR A LISTA DE GAME DO BANCO DE DADOS */
	@EJB
	private GameDAO gameDAO;
	
	/* JSF Converter PARA OBJETOS GAME */
	private PersistentObjectConverterFromId<Game> gameConverter;
	
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
			gameConverter = new PersistentObjectConverterFromId<Game>(gameDAO);
		}
		return gameConverter;
	}
	
	
	
	
	
	
	
	
	/*  ************ ATRIBUTOS E FUNCOES PARA ADICIONAR E REMOVER USUARIOS A UM GRUPO *************** */
	
	/* DAO PARA BUSCAR USUARIO PARA ADD A UM GRUPO */
	@EJB 
	private UserDAO userDAO;
	
	/* VARIAVEL PARA VERIFICAR SE É PARA ADICIONAR UM USUARIO A UM GRUPO*/
	private boolean addUser = false;
	
	/* PARA ADIOCIONAR  UM NOVO USUARIO A UM GRUPO*/
	private User user;
	
	/* PARA REMOVER  UM NOVO USUARIO A UM GRUPO*/
	private User userRemove;
	
	/* JSF Converter PARA OBJETOS USER */
	private PersistentObjectConverterFromId<User> userConverter;
	
	/* FUNÇÃO PARA SUGERIR USUARIOS PARA ADICIONAR AO GRUPO*/
	public List<User> suggestUser(String query){
		if (query.length() > 0) {
			List<User> users = userDAO.findByEmailList(query);
			users.remove(sessionController.getAuthenticatedUser());
			users.removeAll(selectedEntity.getUsersMembers());
			return users;
		}
		return null;
	}
	
	/* GET PARA O CONVERTER DE USER*/
	public PersistentObjectConverterFromId<User> getUserConverter() {
		if (userConverter == null) {
			userConverter = new PersistentObjectConverterFromId<User>(userDAO);
		}
		return userConverter;
	}
	
	/* FUNCAO PARA LISTAR OS USUARIOS MEMBROS DE UM GRUPO */
	public List<User> getUserMembers(){
		return new ArrayList<User>(selectedEntity.getUsersMembers());
	}
	
	//public void setUserMembers(List<User> list){}
	
	/* FUNCAO GET PARA USUARIO A SER REMOVIDO A UM GRUPO*/
	public User getUserRemove() {
		return userRemove;
	}
	/* FUNCAO SET PARA USUARIO A SER REMOVIDO A UM GRUPO*/
	public void setUserRemove(User userRemove) {
		this.userRemove = userRemove;
	}
	
	/* FUNCAO GET PARA USUARIO A SER ADICIONADO A UM GRUPO*/
	public User getUser() {
		return user;
	}
	
	/* FUNCAO SET PARA USUARIO A SER ADICIONADO A UM GRUPO*/
	public void setUser(User user) {
		this.user = user;
	}
	
	/* GET PARA VER SE E PARA ADICIONAR USUARIOS AO GRUPO*/
	public boolean isAddUser() {
		return addUser;
	}

	/* FUNÇÃO INICIAL PARA ADICIONAR USUARIO AO GRUPO*/
	public String addUser(){
		user = null;
		userRemove = null;
		addUser = true;
		readOnly = true;
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}

	/* FUNCAO QUE ADICIONA O USUARIO AO GRUPO*/
	public String addUserInGroup(){
		if(user == null){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.add.user.fail", summarizeSelectedEntity());
			return null;
		}
		selectedEntity.getUsersMembers().add(user);
		GroupGame tmp = selectedEntity;
		super.save();
		selectedEntity = getCrudService().getDAO().refresh(tmp);
		user = null;
		return null;
	}
	
	/* FUNCAO QUE ADICIONA O USUARIO AO GRUPO*/
	public String removeUserInGroup(){
		if(userRemove == null){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.delete.user.fail", summarizeSelectedEntity());
			return null;
		}
		selectedEntity.getUsersMembers().remove(userRemove);
		GroupGame tmp = selectedEntity;
		super.save();
		selectedEntity = getCrudService().getDAO().refresh(tmp);
		userRemove = null;
		return null;
	}
	
	
	
}
