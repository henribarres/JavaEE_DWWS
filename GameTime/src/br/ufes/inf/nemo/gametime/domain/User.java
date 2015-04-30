package br.ufes.inf.nemo.gametime.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends  PersistentObjectSupport{
	
	private static final long serialVersionUID = 1L;

	@NotNull 
	private String email;
	
	@NotNull
	private String name;
	
	@NotNull 
	private String password;

	private boolean admin;
	
	@OneToMany(mappedBy="adminUser")
	private Set<GroupGame> administeredGroups;
	
	@ManyToMany
	@JoinTable( name="GroupGame_User",
			joinColumns = {@JoinColumn(name="USER_ID" , referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="GROUPGAME_ID" , referencedColumnName="ID")})
	private Set<GroupGame> memberGroups;
	
	
	/*  GETS AND SETS*/
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password;}

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Set<GroupGame> getAdministeredGroups() { return administeredGroups; }
	public void setAdministeredGroups(Set<GroupGame> administeredGroups) {this.administeredGroups = administeredGroups;}
	
	public boolean isAdmin() { return admin; }
	public void setAdmin(boolean admin) { this.admin = admin; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Set<GroupGame> getMemberGroups() { return memberGroups; }
	public void setMemberGroups(Set<GroupGame> memberGroups) { 	this.memberGroups = memberGroups; }
	
}
