package br.ufes.inf.nemo.gametime.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.gametime.application.SessionService;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;


@Named
@SessionScoped
public class SessionController extends JSFController{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	@EJB
	private SessionService sessionService;
	
	private String email;

	private String password;
	
	
	
	public String login(){
		try {
			logger.log(Level.FINEST, "USUARIO TENTANDO LOGIN COM USERNAME = \"{0}\"...", email);
			sessionService.login(email, password);
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			logger.log(Level.INFO, "FALHA DE LOGIN PARA USERNAME \"{0}\".", email);
			addGlobalI18nMessage("msgsGametime", FacesMessage.SEVERITY_ERROR, "error.login.summary", "error.login.detail");
			return "index.xhtml?faces-redirect=true";
		}
	}
	
	
	
	
	public String logout(){
		FacesContext fc = FacesContext.getCurrentInstance();     
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);     
		session.invalidate(); 
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
		} catch (IOException e) {} 
		return "login.xhtml?faces-redirect=true";
	}
	
	/* FUNCAO QUE RETORNA TRUE SE O USUARIO FEZ LOGIN OU FALSE CASO CONTRARIO*/
	public boolean isLoggedIn() { return sessionService.getAuthenticatedUser() != null; }
	
	/* FUNCAO QUE RETORNA O USUARIO ATENTICADO, ISTO É, QUE JA REALIZOU O LOGIN*/
	public User getAuthenticatedUser() { return sessionService.getAuthenticatedUser();  }

	
	/*  GETS AND SETS*/
	public String getEmail() { return email; }
	public void setEmail(String email) { 	this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { 	this.password = password; }
	
	
}
