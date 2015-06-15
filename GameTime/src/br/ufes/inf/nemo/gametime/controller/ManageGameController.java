package br.ufes.inf.nemo.gametime.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

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
	
	
	
	
	
	private Converter gameConverter ;
		
	public Converter getGameConverter(){
		
	
		if(gameConverter==null){
			gameConverter = new Converter() {
				
				@Override
				public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
					if ((value != null) && (value.getClass().equals(Game.class))) {
						Game game = (Game) value;
						return game.getUuid().toString();
					}
					return "";
				}
		
		
				@Override
				public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
					Game entity;
					if ((value != null) && (value.trim().length() > 0)) {
						try {
							if(lista!=null){
								Iterator<Game> ite = lista.iterator();
								while(ite.hasNext()){
									entity = ite.next();
									if(entity.getUuid().equals(value))
										return entity;
								}
							}
						}
						catch (Exception e) {
							return null;
						}
					}
					return null;
				}
			};
		}
			return gameConverter;
		}
	
	private Game game;
	
	private List<Game> lista ;
	
	public List<Game> getLista() { 	return lista; }
	public void setLista(List<Game> lista) { this.lista = lista; }
	
	public Game getGame() { return game; }
	public void setGame(Game game) { this.game = game; }
	
	Filter<Statement> filter_en = new Filter<Statement>() {
		@Override
		public boolean accept(Statement o) {
			if(o.getLanguage().equals("en")){
				return true;
			}
			return false;
		}
	};
	
	public void atualizarGame() { 
		selectedEntity = game;
		if(game == null)
			selectedEntity =  new Game();
		
	}
	
	public void dbpedia() {
		createNewEntity();
		lista = new ArrayList<Game>();
		Game game ;
		
		/* CRIA O MODELO DA DBPEDIA E AS PROPRIEDADES QUE SERÃO USADAS */
		Model dbpediaModel = ModelFactory.createDefaultModel();
		String dbpediaOwlNS = "http://dbpedia.org/ontology/";
		dbpediaModel.setNsPrefix("dbpedia-owl", dbpediaOwlNS);
		Property dbp_genre = ResourceFactory.createProperty(dbpediaOwlNS + "genre");
		Property dbp_developer = ResourceFactory.createProperty(dbpediaOwlNS + "developer");
		Property dbp_computingPlatform = ResourceFactory.createProperty(dbpediaOwlNS + "computingPlatform");
		
		
		/*	CRIANDO A QUERY */
		String format1 = " select ?node ?name ?homepage "
				+ "where { "
				+ "		?node <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/VideoGame> ; "
				+ " 	<http://xmlns.com/foaf/0.1/name> ?name ;"
				+ "		filter regex(?name , \"%s\") "
				+ "		OPTIONAL { ?node <http://xmlns.com/foaf/0.1/homepage> ?homepage . } "
				+ "}"
				+ "limit 2";
		String format = String.format(format1, selectedEntity.getName());
		Query query = QueryFactory.create(format);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		

		try{
			
			/*	EXECUTANDO A QUERY */
			ResultSet rs = qexec.execSelect();
			
			/* PERCORRE OS RESULTADOS */
			for ( ; rs.hasNext() ; ) {
				
				/* CRIA O RECURSO DO GAME ENCONTRADO */
				QuerySolution querySolution = rs.nextSolution();
				RDFNode jogoRdf = querySolution.getResource("?node");
				Model jogoModel = ModelFactory.createDefaultModel();
				jogoModel.read(jogoRdf.toString());
			    Resource jogoRecurso = jogoModel.getResource(jogoRdf.toString());
				
			    
			    game = new Game();
				
				/* PEGA O PARAMENTRO NOME DA EXECUÇÃO DA QUERY  */
				String name = querySolution.getLiteral("?name").getString();
				game.setName(name);
				
				
			    
			    /* PEGA OS  GENEROS DO RECURSO CRIADO ACIMA */
			    Iterator<Statement> jogoPropriedades = jogoRecurso.listProperties(dbp_genre).toList().iterator(); 
			    while(jogoPropriedades.hasNext()){
			    	Statement jogoStatement = jogoPropriedades.next();
			    	Model generoModel = ModelFactory.createDefaultModel();
				    generoModel.read(jogoStatement.getObject().toString());
				    Resource generoRecurso = generoModel.getResource(jogoStatement.getObject().toString());
				    /* LABEL DO GENERO */			   
				    Iterator<Statement> generoPropriedades = generoRecurso.listProperties(RDFS.label).filterKeep(filter_en).toList().iterator();
				    Statement generoStatement = generoPropriedades.next();
				    String genre = generoStatement.getObject().toString();
				    /* ADICIONA O LABEL NA ENTIDADE GAME */
				    if(game.getGenero()!=null){
				    	game.setGenero(game.getGenero() + " ; " + genre.substring(0, genre.length()-3) ); }
				    else {game.setGenero( genre.substring(0, genre.length()-3) );}
			    }
			    
			    
			    /* EMPRESA */  
			    jogoPropriedades = jogoRecurso.listProperties(dbp_developer).toList().iterator(); 
			    while(jogoPropriedades.hasNext()){
			    	Statement jogoStatement = jogoPropriedades.next();
			    	Model empresaModel = ModelFactory.createDefaultModel();
		    		empresaModel.read(jogoStatement.getObject().toString());
				    Resource empresaRecurso = empresaModel.getResource(jogoStatement.getObject().toString());
				    /* LABEL DA EMPRESA */
				    Iterator<Statement> empresaPropriedades = empresaRecurso.listProperties(RDFS.label).filterKeep(filter_en).toList().iterator(); 
				    Statement empresaStatement = empresaPropriedades.next();
			    	String empresa = empresaStatement.getObject().toString();
				    if(game.getManufacturer()!=null)
				    	game.setManufacturer(game.getManufacturer() + " ; " + empresa.substring(0, empresa.length()-3) );
				    else game.setManufacturer( empresa.substring(0, empresa.length()-3) );
			    } 
			    	
			    	

			    /* REQUISITOS  */
			    jogoPropriedades = jogoRecurso.listProperties(dbp_computingPlatform).toList().iterator(); 
			    while(jogoPropriedades.hasNext()){
			    	Statement jogoStatement = jogoPropriedades.next();
			    	Model requisitoModel = ModelFactory.createDefaultModel();
		    		requisitoModel.read(jogoStatement.getObject().toString());
				    Resource requisitoRecurso = requisitoModel.getResource(jogoStatement.getObject().toString());
				    /* LABEL DOS REQUISITOS*/
				    Iterator<Statement> requisitoPropriedades = requisitoRecurso.listProperties(RDFS.label).filterKeep(filter_en).toList().iterator(); 
				    Statement requisitoStatement = requisitoPropriedades.next();
			    	String requisito = requisitoStatement.getObject().toString();
				    if(game.getRequisitos_minimos()!=null)
				    	game.setRequisitos_minimos(game.getRequisitos_minimos() + " ; " + requisito.substring(0, requisito.length()-3) );
				    else game.setRequisitos_minimos( requisito.substring(0, requisito.length()-3) );
			    }
			    
			    
			    /* HOMEPAGE*/
			    try{ game.setUri(querySolution.getResource("?homepage").getURI()); }
				catch(Exception e){}
			       
			    /* ADICIONA O GAME NA LISTA DOS GAMES ENCONTRADOS */
			    lista.add(game);
			    			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			qexec.close();
		}
				
	}
	
	
public void dbpedia1() {
		
		lista = new ArrayList<Game>();
		
		
		/*	CRIANDO A QUERY */
		String format1 = " select ?node ?name ?homepage "
				+ "where { "
				+ "		?node <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/VideoGame> ; "
				+ " 	<http://xmlns.com/foaf/0.1/name> ?name ;"
				+ "		filter regex(?name , \"%s\") "
				+ ""
				+ "		OPTIONAL { ?node <http://xmlns.com/foaf/0.1/homepage> ?homepage . }"
				+ "}"
				+ "limit 5";
		String format = String.format(format1, selectedEntity.getName());
		//System.out.println(format);
		Query query = QueryFactory.create(format);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

		
		/*	EXECUTANDO A QUERY */
		try{
			ResultSet rs = qexec.execSelect();
			
			Game game ;
			
			for ( ; rs.hasNext() ; ) {
				
				game = new Game();
				QuerySolution querySolution = rs.nextSolution();
				Model jogoModel = ModelFactory.createDefaultModel();
				
				/* NOME  */
				String name = querySolution.getLiteral("?name").getString();
				game.setName(name);
				
				RDFNode jogoRdf = querySolution.getResource("?node");
				jogoModel.read(jogoRdf.toString());
			    Resource jogoRecurso = jogoModel.getResource(jogoRdf.toString());
				
			    Iterator<Statement> jogoPropriedades = jogoRecurso.listProperties().toList().iterator(); 
			    
			    while(jogoPropriedades.hasNext()){
			    	
			    	
			    	Statement jogoStatement = jogoPropriedades.next();
			    	
			    	
			    	/* GENERO */
			    	if(jogoStatement.getPredicate().toString().equals("http://dbpedia.org/ontology/genre")){	
			    		Model generoModel = ModelFactory.createDefaultModel();
					    generoModel.read(jogoStatement.getObject().toString());
					    Resource generoRecurso = generoModel.getResource(jogoStatement.getObject().toString());
					    Iterator<Statement> generoPropriedades = generoRecurso.listProperties().toList().iterator(); 
					    while(generoPropriedades.hasNext()){
					    	Statement generoStatement = generoPropriedades.next();
					    	if(generoStatement.getPredicate().equals(RDFS.label)){
					    		if(generoStatement.getLanguage().equals("en")){
						    		String genre = generoStatement.getObject().toString();
						    		if(game.getGenero()!=null)
						    			game.setGenero(game.getGenero() + " ; " + genre.substring(0, genre.length()-3) );
						    		else game.setGenero( genre.substring(0, genre.length()-3) );
					    		} 
					    	}
					    }
			    	}
			    	
			    	
			    	/* EMPRESA  */
			    	else if(jogoStatement.getPredicate().toString().equals("http://dbpedia.org/ontology/developer")) {
			    		Model empresaModel = ModelFactory.createDefaultModel();
			    		empresaModel.read(jogoStatement.getObject().toString());
					    Resource empresaRecurso = empresaModel.getResource(jogoStatement.getObject().toString());
					    Iterator<Statement> empresaPropriedades = empresaRecurso.listProperties().toList().iterator(); 
					    while(empresaPropriedades.hasNext()){
					    	Statement empresaStatement = empresaPropriedades.next();
					    	if(empresaStatement.getPredicate().equals(RDFS.label)){
					    		if(empresaStatement.getLanguage().equals("en")){
						    		String empresa = empresaStatement.getObject().toString();
						    		if(game.getManufacturer()!=null)
						    			game.setManufacturer(game.getManufacturer() + " ; " + empresa.substring(0, empresa.length()-3) );
						    		else game.setManufacturer( empresa.substring(0, empresa.length()-3) );
					    		} 
					    	}
					    }
			    	}
			    	
			    	
			    	/* REQUISITOS  */
			    	else if(jogoStatement.getPredicate().toString().equals("http://dbpedia.org/ontology/computingPlatform")) {
			    		Model requisitoModel = ModelFactory.createDefaultModel();
			    		requisitoModel.read(jogoStatement.getObject().toString());
					    Resource requisitoRecurso = requisitoModel.getResource(jogoStatement.getObject().toString());
					    Iterator<Statement> requisitoPropriedades = requisitoRecurso.listProperties().toList().iterator(); 
					    while(requisitoPropriedades.hasNext()){
					    	Statement requisitoStatement = requisitoPropriedades.next();
					    	if(requisitoStatement.getPredicate().equals(RDFS.label)){
					    		if(requisitoStatement.getLanguage().equals("en")){
						    		String requisito = requisitoStatement.getObject().toString();
						    		if(game.getRequisitos_minimos()!=null)
						    			game.setRequisitos_minimos(game.getRequisitos_minimos() + " ; " + requisito.substring(0, requisito.length()-3) );
						    		else game.setRequisitos_minimos( requisito.substring(0, requisito.length()-3) );
					    		} 
					    	}
					    }
			    	}
			    	
			    }
			    
			   
			    /* HOMEPAGE*/
			    try{ game.setUri(querySolution.getResource("?homepage").getURI()); }
				catch(Exception e){}
			        
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
		lista = null;
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
