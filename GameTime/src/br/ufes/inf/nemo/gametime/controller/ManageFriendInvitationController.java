package br.ufes.inf.nemo.gametime.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageFriendInvitationService;
import br.ufes.inf.nemo.gametime.domain.Invitation;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@SessionScoped
public class ManageFriendInvitationController extends JSFController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageFriendInvitationService InviteService;
	
	private Invitation invite;
	
	public Invitation getInvite() {
		return invite;
	}

	public void setInvite(Invitation invite) {
		this.invite = invite;
	}

	public String register(){
		InviteService.register(invite);
		boolean reply = sendEmail(invite.getEmail());
		if (reply) {
			return  "/manageFriendInvitation/success.xhtml?faces-redirect=true" ;
		}
		else{
			return  "/manageFriendInvitation/fail.xhtml?faces-redirect=true" ;
		}
	}
	
	public String begin(){
		invite = new Invitation();
		return "/manageFriendInvitation/invite.xhtml?faces-redirect=true";
	}

	public boolean sendEmail(String email) {
		final String username = "gametimebrapp@gmail.com";
	    final String password = "wow123$%";

	    Properties props = new Properties();
	    props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(username, password);
            	}
          	});

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gametimebrapp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Bem Vindo ao GameTime");
            message.setText("Este e-mail foi enviado pelos seus amigos através do nosso sistema." + "\n\n Junte-se a nós para ter a melhor experiencia em jogos online, compartilhando contas, sem se preocupar, e fazendo novas amizades!" + "\n\n Acesse o site localhost:8080/GameTime e comece a jogar agora mesmo!" );

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            return false;
        }
	}
}
