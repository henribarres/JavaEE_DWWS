package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Invitation;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface InvitationDAO extends BaseDAO<Invitation>{

}
