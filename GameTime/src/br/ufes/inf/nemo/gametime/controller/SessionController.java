package br.ufes.inf.nemo.gametime.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.SessionService;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;


@Named
@SessionScoped
public class SessionController extends JSFController{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	@EJB
	private SessionService sessionService;
	
	private String username;

	private String password;
	
	
	
	public String login(){
		try {
			logger.log(Level.FINEST, "USUARIO TENTANDO LOGIN COM USERNAME = \"{0}\"...", username);
			sessionService.login(username, password);
			return "";
		} catch (Exception e) {
			logger.log(Level.INFO, "FALHA DE LOGIN PARA USERNAME \"{0}\".", username);
			addGlobalI18nMessage("msgsGametime", FacesMessage.SEVERITY_ERROR, "error.login.summary", "error.login.detail");
			return null;
		}
		
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
