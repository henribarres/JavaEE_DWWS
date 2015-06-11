package br.ufes.inf.nemo.gametime.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

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
	
	
	
	
	
	
	
	private Game game;
	
	private List<Game> lista = new ArrayList<Game>();
	public List<Game> getLista() {
		return lista;
	}
	public void setLista(List<Game> lista) {
		this.lista = lista;
	}
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
	
	
	
	
	public void dbpedia() {
		
		lista = new ArrayList<Game>();

		String format1 = " select ?node ?name ?genre ?fabricante ?homepage ?requisito "
				+ "where { "
				+ "		?node <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/VideoGame> ; "
				+ " 	<http://xmlns.com/foaf/0.1/name> ?name ;"
				+ "		<http://dbpedia.org/ontology/developer> ?fabricante ;"
				+ "		<http://dbpedia.org/ontology/computingPlatform> ?requisito ;"
				+ "		<http://dbpedia.org/ontology/genre> ?genre ."
				+ "		filter regex(?name , \"%s\") "
				+ ""
				+ "		OPTIONAL { ?node <http://xmlns.com/foaf/0.1/homepage> ?homepage . }"
				+ "}"
				+ "limit 10";
		
		String format = String.format(format1, selectedEntity.getName());
		
		System.out.println(format);

		Query query = QueryFactory.create(format);

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		
		RDFNode qRes;
		String qName;
		String qOfficial;
		String qComment;
		String qArea;
		String qPopulation;
		String qThumb;
		
		
		try {
			ResultSet rs = qexec.execSelect();
			
			Game game ;
			
			for ( ; rs.hasNext() ; ) {
				
				game = new Game();
				
				QuerySolution querySolution = rs.nextSolution();
			    
				RDFNode rdfNode = querySolution.get("?node");
			    
				game.setName(querySolution.getLiteral("?name").getString());
				
				selectedEntity.setGenero(querySolution.getResource("?genre").getLocalName()); //getProperty(new Property()).getString() {
					
				selectedEntity.setManufacturer(querySolution.getResource("?fabricante").getLocalName());
				
				selectedEntity.setUri(querySolution.getResource("?homepage").getURI());
				
				selectedEntity.setRequisitos_minimos(querySolution.getResource("?requisito").getLocalName());
				
				
				lista.add(game);
			    
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			qexec.close();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
