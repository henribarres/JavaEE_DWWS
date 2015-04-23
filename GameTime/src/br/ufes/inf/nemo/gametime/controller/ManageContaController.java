package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageContaService;
import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.LikeFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class ManageContaController extends CrudController<Conta>{

	private static final Logger logger = Logger.getLogger(ManageContaController.class.getCanonicalName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageContaService manageContaService;
	
	
	public ManageContaController() {
	    viewPath = "/manageConta/";
	    bundleName = "msgsGametime";
	}
	
	@Override
	protected CrudService<Conta> getCrudService() {
		manageContaService.authorize();
		return manageContaService;
	}

	@Override
	protected Conta createNewEntity() {
		logger.log(Level.INFO, "INICIALIZANDO NOVO CONTA");
		Conta newEntity = new Conta();
		return newEntity;
	}

	@Override
	protected String summarizeSelectedEntity() {
		return (selectedEntity == null) ? "" : selectedEntity.toString();
	}
	
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageConta.filter.byName", "name", getI18nMessage("msgsGametime", "manageConta.text.filter.byName")));
		
	}

}
