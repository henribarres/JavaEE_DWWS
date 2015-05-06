package br.ufes.inf.nemo.gametime.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Invitation;
import br.ufes.inf.nemo.gametime.persistence.InvitationDAO;

@Stateless
public class ManageFriendInvitationServiceBean implements ManageFriendInvitationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private InvitationDAO inviteDAO;
	
	@Override
	public void register(Invitation invite) {
		inviteDAO.save(invite);		
	}
}
