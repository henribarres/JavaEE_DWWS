package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;



@StaticMetamodel(GroupGame.class)
public class GroupGame_ {
	public static volatile SingularAttribute<GroupGame, User> adminUser;
	public static volatile SetAttribute<GroupGame, User> usersMembers;
	public static volatile SetAttribute<GroupGame, GameAccount> gameAccounts;
}
