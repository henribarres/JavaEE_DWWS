package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-27T15:55:38.322-0300")
@StaticMetamodel(User.class)
public class User_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, GroupGame> administeredGroups;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SetAttribute<User, GroupGame> memberGroups;
	public static volatile SingularAttribute<User, Boolean> admin;
}
