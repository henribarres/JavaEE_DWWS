package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.GroupGame;
import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface GroupGameDAO extends BaseDAO<GroupGame>{

	List<GroupGame> findByAdmin(User admin);

	List<GroupGame> findByMember(User member);

	
}
