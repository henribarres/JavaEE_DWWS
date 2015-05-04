package br.ufes.inf.nemo.gametime.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends  PersistentObjectSupport implements Comparable<User>{
	
	private static final long serialVersionUID = 1L;

	/* EMAIL DO USUARIO, USADO PARA FAZER LOGIN */
	@NotNull 
	private String email;
	
	/* NOME DO USUARIO */
	@NotNull
	private String name;
	
	/* SENHA PARA ACESSAR */
	@NotNull 
	private String password;

	/* BOLEANO PARA DIZER SE O USUARIO E UM ADMINISTRADOR OU NAO */
	private boolean admin;
	
	
	/* LISTA DE GRUPOS QUE O USUARIO ADMINISTRA*/
	@OneToMany(mappedBy="adminUser")
	private Set<GroupGame> administeredGroups;
	
	/* LISTA  DE GRUPOS QUE O USUARIO E MEMBRO */
	@ManyToMany(mappedBy="usersMembers")
	private Set<GroupGame> memberGroups;
	
	
	
	
	@Override
	public int compareTo(User o) { return super.compareTo(o); }
	
	
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
