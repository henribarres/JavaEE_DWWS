package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-06-10T21:22:31.884-0300")
@StaticMetamodel(Game.class)
public class Game_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Game, String> name;
	public static volatile SingularAttribute<Game, String> manufacturer;
	public static volatile SingularAttribute<Game, String> genre;
	public static volatile SingularAttribute<Game, String> requisitos_minimos;
	public static volatile SingularAttribute<Game, String> uri;
}
