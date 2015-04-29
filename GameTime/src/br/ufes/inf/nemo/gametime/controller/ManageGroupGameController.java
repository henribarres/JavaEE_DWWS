package br.ufes.inf.nemo.gametime.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.ufes.inf.nemo.gametime.application.ManageGroupGameService;
import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.persistence.GameDAO;
import br.ufes.inf.nemo.gametime.persistence.GroupGameDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.util.ejb3.controller.PrimefacesLazyEntityDataModel;


@Named
@SessionScoped
public class ManageGroupGameController extends CrudController<GroupGame>{

	private static final Logger logger = Logger.getLogger(ManageGroupGameController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageGroupGameService manageGroupGameService;
	
	/** The DAO for game objects. */
	@EJB
	private GameDAO gameDAO;
	
	
	@EJB
	private GroupGameDAO groupGameDAO;
	
	/* JSF Converter for Game objects. */
	private PersistentObjectConverterFromId<Game> gameConverter;
	
	/* CONTROLLER PARA VERIFICAR SE O USUARIO ESTA LOGADO */
	@Inject
	private SessionController sessionController;

	
	/*   CONSTRUTOR  DA CLASSE */
	public ManageGroupGameController() {
	    viewPath = "/manageGroupGame/";
	    bundleName = "msgsGametime";
	}
	
	
	
	
	
	@Override
	public LazyDataModel<GroupGame> getLazyEntities() {
		if (lazyEntities == null) {
			count();
			lazyEntities = new PrimefacesLazyEntityDataModel<GroupGame>(getListingService().getDAO()) {
				
				private static final long serialVersionUID = 1117380513193004406L;

				@Override
				public List<GroupGame> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
					firstEntityIndex = first;
					lastEntityIndex = first + pageSize;
					retrieveEntities();
					return entities;
				}
			};
			lazyEntities.setRowCount((int) entityCount);
		}

		return lazyEntities;
	}
	
	@Override
	protected void retrieveEntities() {
		if (lastEntityIndex > entityCount) lastEntityIndex = (int) entityCount;

		// There's not. Retrieve all entities within range.
		logger.log(Level.INFO, "Retrieving from the application layer {0} of a total of {1} entities: interval [{2}, {3})", new Object[] { (lastEntityIndex - firstEntityIndex), entityCount, firstEntityIndex, lastEntityIndex });
		
		entities = new ArrayList<GroupGame>(sessionController.getAuthenticatedUser().getAdministeredGroups());
		
		entities.addAll(groupGameDAO.findByMember(sessionController.getAuthenticatedUser()));
		
		lastEntityIndex = firstEntityIndex + entities.size();
		
	}
	
	private List<GroupGame> teste;
	
	public List<GroupGame> getTeste() {
		if(teste == null)
			teste = groupGameDAO.findByAdmin(sessionController.getAuthenticatedUser());
		return teste;
	}

	public void setTeste(List<GroupGame> teste) {
		this.teste = teste;
	}
	
	
	
	
	
	
	
	
	@Override
	protected CrudService<GroupGame> getCrudService() {
		return manageGroupGameService;
	}
	
	@Override
	protected void prepEntity() {
		selectedEntity.setAdminUser(sessionController.getAuthenticatedUser());
		selectedEntity.setIsactive(true);
		super.prepEntity();
	}
	
	
	public String begin(){
		return viewPath + "list.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	

	@Override
	protected GroupGame createNewEntity() {
		return new GroupGame();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
	}

	@Override
	public String save() {
		 super.save();
		 return "index.xhtml?faces-redirect=" + getFacesRedirect();
	}


	public List<Game> suggestGame(String query) {
		if (query.length() > 0) {
			List<Game> cities = gameDAO.findByName(query);
			return cities;
		}
		return null;
	}
	
	/** Getter for gameConverter. */
	public Converter getGameConverter() {
		// Lazily create the converter.
		if (gameConverter == null) {
			logger.log(Level.FINEST, "Creating a city converter...");
			gameConverter = new PersistentObjectConverterFromId<Game>(gameDAO);
		}
		return gameConverter;
	}






	
}
