package br.ufes.inf.nemo.started;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.ufes.inf.nemo.gametime.application.ManageGameService;
import br.ufes.inf.nemo.gametime.application.RegistrationService;
import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;

@Singleton
@Startup
public class GameStarted{

	@EJB
	private RegistrationService registrationService;
	
	@EJB
	private ManageGameService manageGameService;
	
	
	@PostConstruct
	public void init() {
		inicializarUsers();
		inicializarGames();
	}

	private void inicializarGames(){
		Game game = new Game();
		game.setName("Dama");
		game.setManufacturer("Tabuleiro S/A");
		game.setGenero("tabuleiro");
		game.setRequisitos_minimos("tabuleiro");
		try {
			manageGameService.validateCreate(game);
			manageGameService.create(game);
		} catch (CrudException e) {
			System.out.println("Não foi possivel criar game");
		}
		catch (Exception e) {
			System.out.println("excessao para criar game");
		}
		
		
		game = new Game();
		game.setName("Xadrez");
		game.setManufacturer("Tabuleiro S/A");
		game.setGenero("tabuleiro");
		game.setRequisitos_minimos("tabuleiro");
		try {
			manageGameService.validateCreate(game);
			manageGameService.create(game);
		} catch (CrudException e) {
			System.out.println("Não foi possivel criar game");
		}
		catch (Exception e) {
			System.out.println("excessao para criar game");
		}
		
		
		
		game = new Game();
		game.setName("Xadrez");
		game.setManufacturer("Tabuleiro S/A");
		game.setGenero("tabuleiro");
		game.setRequisitos_minimos("tabuleiro");
		try {
			manageGameService.validateCreate(game);
			manageGameService.create(game);
		} catch (CrudException e) {
			System.out.println("Não foi possivel criar game");
		}
		catch (Exception e) {
			System.out.println("excessao para criar game");
		}
		
		
		game = new Game();
		game.setName("Bisca");
		game.setManufacturer("Cartas S/A");
		game.setGenero("Carta");
		game.setRequisitos_minimos("Baralho");
		try {
			manageGameService.validateCreate(game);
			manageGameService.create(game);
		} catch (CrudException e) {
			System.out.println("Não foi possivel criar game");
		}
		catch (Exception e) {
			System.out.println("excessao para criar game");
		}
		
		
		game = new Game();
		game.setName("Poker");
		game.setManufacturer("Cartas S/A");
		game.setGenero("Carta");
		game.setRequisitos_minimos("Baralho");
		try {
			manageGameService.validateCreate(game);
			manageGameService.create(game);
		} catch (CrudException e) {
			System.out.println("Não foi possivel criar game");
		}
		catch (Exception e) {
			System.out.println("excessao para criar game");
		}
		
		
	}
	
	
	private void inicializarUsers()  {
			
		System.out.println("INICIALIZANDO Users");
			
		/* USUARIO ADMINISTRADOR */
		User usuario_1 = new User();
	    usuario_1.setAdmin(true);
	    usuario_1.setName("Bruno Manzoli");
	    usuario_1.setEmail("manzoli2122@gmail.com");
	    usuario_1.setPassword("bruno");
	    try {
			registrationService.register(usuario_1);
		} catch (Exception e) {
			System.out.println("Usuario ja cadastrado ");
		}
	    
	    /* USUARIO ADMINISTRADOR */
		usuario_1 = new User();
	    usuario_1.setAdmin(true);
	    usuario_1.setName("Henrique");
	    usuario_1.setEmail("henrique@gmail.com");
	    usuario_1.setPassword("henrique");
	    try {
			registrationService.register(usuario_1);
		} catch (Exception e) {
			System.out.println("Usuario ja cadastrado ");
		}
	    
	    
	    /* USUARIO NORMAL*/
		usuario_1 = new User();
	    usuario_1.setAdmin(false);
	    usuario_1.setName("roberto");
	    usuario_1.setEmail("roberto@gmail.com");
	    usuario_1.setPassword("roberto");
	    try {
			registrationService.register(usuario_1);
		} catch (Exception e) {
			System.out.println("Usuario ja cadastrado ");
		}
	    
		
	    	
		
		
	 }
	
	
}
