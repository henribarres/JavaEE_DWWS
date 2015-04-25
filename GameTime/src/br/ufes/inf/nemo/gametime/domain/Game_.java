package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Game.class)
public class Game_ {

	public static volatile SingularAttribute<Game, String> name;
	public static volatile SingularAttribute<Game, String> manufacturer;
	public static volatile SingularAttribute<Game, String> genre;
	public static volatile SingularAttribute<Game, String> requisitos_minimos;
	
}
