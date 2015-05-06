package br.ufes.inf.nemo.gametime.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Invitation;

@Local
public interface ManageFriendInvitationService extends Serializable {
	
	public void register(Invitation invite);

}
