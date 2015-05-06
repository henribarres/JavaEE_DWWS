package br.ufes.inf.nemo.gametime.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;


@Entity
public class GameAccountHistoric extends PersistentObjectSupport implements Comparable<GameAccountHistoric>{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User user;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date endDate;
	
	@ManyToOne
	private GameAccount gameAccount;
	
	
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public Date getStartDate() { return startDate; }
	public void setStartDate(Date startDate) { this.startDate = startDate; }

	public Date getEndDate() {return endDate; }
	public void setEndDate(Date endDate) { this.endDate = endDate;}

	
	public GameAccount getGameAccount() {
		return gameAccount;
	}
	public void setGameAccount(GameAccount gameAccount) {
		this.gameAccount = gameAccount;
	}
	@Override
	public int compareTo(GameAccountHistoric o) {
		return super.compareTo(o);
	}

}
