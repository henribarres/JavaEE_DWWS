package br.ufes.inf.nemo.gametime.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.ufes.inf.nemo.gametime.application.ManageGroupGameService;
import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;
import br.ufes.inf.nemo.util.ejb3.controller.PrimefacesLazyEntityDataModel;


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
		return viewPath + "list.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	
	@Override
	protected CrudService<GroupGame> getCrudService() {
		return manageGroupGameService;
	}
	
	@SuppressWarnings("unchecked")
	public void teste(){
		// Checks if the last entity index is over the number of entities and correct it.
				if (lastEntityIndex > entityCount) lastEntityIndex = (int) entityCount;

				
					// There's not. Retrieve all entities within range.
					logger.log(Level.INFO, "Retrieving from the application layer {0} of a total of {1} entities: interval [{2}, {3})", new Object[] { (lastEntityIndex - firstEntityIndex), entityCount, firstEntityIndex, lastEntityIndex });
					entities = (List<GroupGame>) sessionController.getAuthenticatedUser().getAdministeredGroups();
				

				// Adjusts the last entity index.
				lastEntityIndex = firstEntityIndex + entities.size();
	}
	
	@Override
	public LazyDataModel<GroupGame> getLazyEntities() {
		
		if (lazyEntities == null) {
			count();
			lazyEntities = new PrimefacesLazyEntityDataModel<GroupGame>(getListingService().getDAO()) {
				/** Serialization id. */
				private static final long serialVersionUID = 1117380513193004406L;

				@Override
				public List<GroupGame> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
					firstEntityIndex = first;
					lastEntityIndex = first + pageSize;
					teste();
					return entities;
				}
			};
			lazyEntities.setRowCount((int) entityCount);
		}

		return lazyEntities;
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
