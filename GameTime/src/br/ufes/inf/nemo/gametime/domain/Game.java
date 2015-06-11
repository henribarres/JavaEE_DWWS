package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Game extends  PersistentObjectSupport implements Comparable<Game>{

	private static final long serialVersionUID = 1L;
	
	/* NOME DO GAME NAO PODE SE NULO*/
	@NotNull
	private String name;
	
	/* EMPRESA CRIADORA DO GAME NAO PODE SER NULA*/
	@NotNull
	private String manufacturer;

	/* GENERO DO GAME */
	private String genre;
	
	/* REQUISITOS MINIMOS PARA O GAME FUNCIONAR */
	private String requisitos_minimos;

	
	private String uri;
	
	
	
	
	/* FUNCAO PARA COMPARAR USANDO UUID*/
	@Override
	public int compareTo(Game o) { 	return super.compareTo(o); }
	
	
	
	/*  GETS AND SETS*/
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getManufacturer() { return manufacturer; }
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

	public String getGenero() { return genre; }
	public void setGenero(String genero) { this.genre = genero; }

	public String getRequisitos_minimos() { return requisitos_minimos; }
	public void setRequisitos_minimos(String requisitos) { this.requisitos_minimos = requisitos; }



	public String getUri() {
		return uri;
	}



	public void setUri(String uri) {
		this.uri = uri;
	}
	

}
