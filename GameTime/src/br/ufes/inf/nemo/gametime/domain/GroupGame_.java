package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-26T07:32:06.489-0300")
@StaticMetamodel(GroupGame.class)
public class GroupGame_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<GroupGame, String> name;
	public static volatile SingularAttribute<GroupGame, String> descricao;
	public static volatile SingularAttribute<GroupGame, Boolean> isactive;
	public static volatile SingularAttribute<GroupGame, User> adminUser;
	public static volatile SetAttribute<GroupGame, User> usersMembers;
	public static volatile SetAttribute<GroupGame, GameAccount> gameAccounts;
}
