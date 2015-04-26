package br.ufes.inf.nemo.gametime.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends  PersistentObjectSupport{
	
	private static final long serialVersionUID = 1L;

	@NotNull 
	private String email;
	
	@NotNull 
	private String password;

	@OneToMany(mappedBy="adminUser")
	private Set<GroupGame> administeredGroups;
	
	

	
	
	/*  GETS AND SETS*/
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password;}

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Set<GroupGame> getAdministeredGroups() { return administeredGroups; }
	public void setAdministeredGroups(Set<GroupGame> administeredGroups) {this.administeredGroups = administeredGroups;}
	
	
}
