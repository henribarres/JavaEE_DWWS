package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(GameAccount.class)
public class GameAccount_ {

	public static volatile SingularAttribute<GameAccount, String> name;
	public static volatile SingularAttribute<GameAccount, GroupGame> groupGame;
}
