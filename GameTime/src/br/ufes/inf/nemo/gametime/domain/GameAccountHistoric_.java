package br.ufes.inf.nemo.gametime.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-05-04T19:09:02.981-0300")
@StaticMetamodel(GameAccountHistoric.class)
public class GameAccountHistoric_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<GameAccountHistoric, User> user;
	public static volatile SingularAttribute<GameAccountHistoric, Date> startDate;
	public static volatile SingularAttribute<GameAccountHistoric, Date> endDate;
	public static volatile SingularAttribute<GameAccountHistoric, GameAccount> gameAccount;
}
