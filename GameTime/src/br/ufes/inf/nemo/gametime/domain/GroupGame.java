package br.ufes.inf.nemo.gametime.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class GroupGame extends  PersistentObjectSupport implements Comparable<GroupGame>{
	
	private static final long serialVersionUID = 1L;
	
	/*   NOME DO GRUPO  */
	@NotNull
	private String name;
	
	/*  DESCRIÇÃO DO GRUPO NÃO OBRIGATORIO  */
	private String descricao;
	
	/*  ADMINISTRADOR DO GRUPO  */
	@ManyToOne 	@NotNull
	private User adminUser;
	
	/* LISTA DE USUARIOS PERTENCENTES AOS GRUPOS  */
	@ManyToMany (fetch=FetchType.EAGER )
	private Set<User> usersMembers;
	
	/* CONTAS PARA SEREM USADAS PELOS USUARIOS DESSE GRUPO  */
	@OneToMany(mappedBy="groupGame", cascade=CascadeType.ALL )
	private Set<GameAccount>  gameAccounts;

	/* O GAME QUE O USUARIOS DO GRUPO IRÃO JOGAR */
	@ManyToOne @NotNull
	private Game game;
	
	
	
	/* FUNCAO QUE USA O UUID PARA COMPARAR OBJETOS GROUPGAME */
	@Override
	public int compareTo(GroupGame o) { return super.compareTo(o); }	
	

	/*  GETS AND SETS*/
	public User getAdminUser() { return adminUser; }
	public void setAdminUser(User adminUser) { 	this.adminUser = adminUser; }

	public Set<User> getUsersMembers() { return usersMembers;  }
	public void setUsersMembers(Set<User> usersMembers) {  this.usersMembers = usersMembers; }

	public Set<GameAccount> getGameAccounts() { return gameAccounts;  }
	public void setGameAccounts(Set<GameAccount> gameAccounts) { this.gameAccounts = gameAccounts; }
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;	}
	
	public String getDescricao() { return descricao;}
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Game getGame() {return game; }
	public void setGame(Game game) { this.game = game; }
	
}
