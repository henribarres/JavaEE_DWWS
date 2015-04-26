package br.ufes.inf.nemo.gametime.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.persistence.UserDAO;
import br.ufes.inf.nemo.util.TextUtils;


@SessionScoped
@Stateful
public class SessionServiceBean implements SessionService{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SessionServiceBean.class.getCanonicalName());
	
	@EJB
	private UserDAO userDAO;
	
	private User authenticatedUser;
	
	@Override
	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	@Override
	public void login(String email, String password) throws Exception{
		logger.log(Level.INFO, "AUTENTICANDO USUARIO COM USERNAME =  \"{0}\"...", email);
		User user = userDAO.retrieveByUsername(email);	
		String md5pwd = TextUtils.produceMd5Hash(password);
		String pwd = user.getPassword();
		
		
		
		md5pwd=pwd;
		
		
		
		
		if ((pwd != null) && (pwd.equals(md5pwd))){
			authenticatedUser = user;
			pwd = null;
		}
		else {
			logger.log(Level.INFO, "USUARIO \"{0}\" NÃO LOGADO SENHA NÃO CONFERE.", email);
			throw new Exception();
		}		
	}
	
	

}
