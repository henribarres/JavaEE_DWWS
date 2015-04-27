package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.RegistrationService;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;



@Named
@SessionScoped
public class RegistrationController extends JSFController{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(RegistrationController.class.getCanonicalName());

	@EJB
	private RegistrationService registrationService;
	
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String register(){
		logger.log(Level.INFO, "USUARIO COM NOME \"{0}\" SE CADASTRANDO ", user.getName());
		user.setAdmin(false);
		registrationService.register(user);
		return  "/index.xhtml?faces-redirect=true" ;
	}
	
	public String begin(){
		user = new User();
		return "/registration/index.xhtml?faces-redirect=true";
	}
	
	
}
