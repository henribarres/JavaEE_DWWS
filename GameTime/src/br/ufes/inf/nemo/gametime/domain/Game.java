package br.ufes.inf.nemo.gametime.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Game extends  PersistentObjectSupport{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String name;
	
	@NotNull
	private String manufacturer;

	private String genero;
	
	private String requisitos_minimos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getRequisitos_minimos() {
		return requisitos_minimos;
	}

	public void setRequisitos_minimos(String requisitos_minimos) {
		this.requisitos_minimos = requisitos_minimos;
	}
	
	
	
	
	/*
	empresa_fabricante,genero (MMRPG, FPS), versão (patch 1.9), requisitos_minimos (memoria, hd e etc)
	isso ta bom ja
	*/
	
}