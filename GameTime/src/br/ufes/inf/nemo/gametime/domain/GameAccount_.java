package br.ufes.inf.nemo.gametime.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-25T19:28:15.025-0300")
@StaticMetamodel(GameAccount.class)
public class GameAccount_ {
	public static volatile SingularAttribute<GameAccount, String> name;
	public static volatile SingularAttribute<GameAccount, GroupGame> groupGame;
	public static volatile SingularAttribute<GameAccount, Game> game;
}
