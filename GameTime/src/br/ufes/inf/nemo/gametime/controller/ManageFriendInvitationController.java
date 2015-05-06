package br.ufes.inf.nemo.gametime.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.gametime.application.ManageFriendInvitationService;
import br.ufes.inf.nemo.gametime.domain.Invitation;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;

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
		return  "/manageFriendInvitation/success.xhtml?faces-redirect=true" ;
	}
	
	public String begin(){
		invite = new Invitation();
		return "/manageFriendInvitation/invite.xhtml?faces-redirect=true";
	}
	
	
}
