package br.ufes.inf.nemo.started;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.ufes.inf.nemo.gametime.application.RegistrationService;
import br.ufes.inf.nemo.gametime.application.RegistrationServiceBean;
import br.ufes.inf.nemo.gametime.domain.User;

@Singleton
@Startup
public class GameStarted{

	private static final long serialVersionUID = -718214408262760803L;

	private RegistrationServiceBean registrationService;

	@PostConstruct
	public void init() {
		try {
			System.out.println("PASSEI INIT");
			GameStarted gameTime = new GameStarted();
			gameTime.inicializarGameTime();
		} catch (Exception e) {
			System.out.println("Erro ao inicializar os dados básicos do GameTime.");
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa o banco de dados do GameTime com informações básicas.
	 * 
	 * @throws Exception
	 */
	public void inicializarGameTime() throws Exception {
		inicializarGames();
		//inicializarUsers();
	}

	private void inicializarGames() throws Exception
	    {
	    	User usuario_1 = new User();
	    	usuario_1.setAdmin(true);
	    	usuario_1.setName("nome_teste");
	    	usuario_1.setEmail("email@gmail.com");
	    	usuario_1.setPassword("123");
	    	
	    	

		//Persiste.
		try
		{
			registrationService.register(usuario_1);
			System.out.println("registrou");
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
	    }
	    }

	private static void inicializarUsers() throws Exception {

	}

}
