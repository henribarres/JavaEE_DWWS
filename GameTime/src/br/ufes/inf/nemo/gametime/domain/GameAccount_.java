package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-26T07:32:06.471-0300")
@StaticMetamodel(GameAccount.class)
public class GameAccount_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<GameAccount, String> name;
	public static volatile SingularAttribute<GameAccount, GroupGame> groupGame;
	public static volatile SingularAttribute<GameAccount, Game> game;
}
