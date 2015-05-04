package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface GameDAO extends BaseDAO<Game>{
	
	
	public List<Game> findByName(String name);
	
	/* 
	 * FUNCAO PARA RETORNAR GAME COM NOME E EMPRESA EXASTOS
	 * UTILIZADO PARA NAO PERMIRTIR CADASTRO DE UM GAME COM MESMO NOME E EMPRESA DE OUTRO
	 */
	public Game retrieveByNameAndManufacturer(String name,String manufacturer) 
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}