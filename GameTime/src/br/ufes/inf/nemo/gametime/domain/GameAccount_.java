package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-28T10:57:19.792-0300")
@StaticMetamodel(GameAccount.class)
public class GameAccount_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<GameAccount, String> name;
	public static volatile SingularAttribute<GameAccount, String> login;
	public static volatile SingularAttribute<GameAccount, String> senha;
	public static volatile SingularAttribute<GameAccount, GroupGame> groupGame;
}
