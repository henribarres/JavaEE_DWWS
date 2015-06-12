package br.ufes.inf.nemo.gametime.application;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.persistence.GameDAO;



@WebServlet(urlPatterns = { "/data/game/*" })
public class ListGamesInRdfServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GameDAO gameDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		Long id = null;
		String dbpediaOwlNS = "http://dbpedia.org/ontology/";
		String myNS = "http://localhost:8080/GameTime/data/game/";
		
		try{ id = Long.valueOf(req.getPathInfo().substring(1)); }
		catch(Exception e){}
		
		
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("dbpedia-owl", dbpediaOwlNS);
		model.setNsPrefix("foaf", FOAF.getURI());
		
		
		Model dbpediaModel = ModelFactory.createDefaultModel();
		
		dbpediaModel.setNsPrefix("dbpedia-owl", dbpediaOwlNS);
		Property dbp_genre = ResourceFactory.createProperty(dbpediaOwlNS + "genre");
		Property dbp_developer = ResourceFactory.createProperty(dbpediaOwlNS + "developer");
		Property dbp_computingPlatform = ResourceFactory.createProperty(dbpediaOwlNS + "computingPlatform");
		Property dbp_VideoGame = ResourceFactory.createProperty(dbpediaOwlNS + "VideoGame");
		
		
		if(id!=null){
			Game game = gameDAO.retrieveById(id);
			if(game!=null){
				
				Resource jogo = model.createResource(myNS + game.getId())
				
										.addProperty(RDF.type, dbp_VideoGame)
				
										.addProperty(FOAF.name, game.getName())
										
										.addProperty(RDFS.label, game.getName());
				
				
				String genero = game.getGenero();
				String[] generos = genero.split(";");
				for(int i =0;i<generos.length;i++){
					jogo.addProperty(dbp_genre, generos[i]);
				}
					
				String empresa = game.getManufacturer();
				String[] empresas = empresa.split(";");
				for(int i =0;i<empresas.length;i++){
					jogo.addProperty(dbp_developer,  empresas[i]);
				}
				
				String requisito = game.getRequisitos_minimos();
				String[] requisitos = requisito.split(";");
				for(int i =0;i<requisitos.length;i++){
					jogo.addProperty(dbp_computingPlatform,  requisitos[i]);
				}
				
				if(game.getUri()!=null){
					jogo.addProperty(FOAF.homepage, game.getUri() );
				}
				
				
			}
			try (PrintWriter out = resp.getWriter()) {
				//model.write(out);
				model.write(out, "RDF/XML");
			}
			return;
		}
		
		
		
		
		
		for (Game game : gameDAO.retrieveAll()) {
	
			Resource jogo = model.createResource(myNS + game.getId())
					
					.addProperty(RDF.type, dbp_VideoGame)

					.addProperty(FOAF.name, game.getName())
					
					.addProperty(RDFS.label, game.getName());


			String genero = game.getGenero();
			String[] generos = genero.split(";");
			for(int i =0;i<generos.length;i++){
			jogo.addProperty(dbp_genre, generos[i]);
			}

			String empresa = game.getManufacturer();
			String[] empresas = empresa.split(";");
			for(int i =0;i<empresas.length;i++){
			jogo.addProperty(dbp_developer,  empresas[i]);
			}

			String requisito = game.getRequisitos_minimos();
			String[] requisitos = requisito.split(";");
			for(int i =0;i<requisitos.length;i++){
			jogo.addProperty(dbp_computingPlatform,  requisitos[i]);
			}
			
			if(game.getUri()!=null){
			jogo.addProperty(FOAF.homepage, game.getUri() );
			}

		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
		
	}
	

}
