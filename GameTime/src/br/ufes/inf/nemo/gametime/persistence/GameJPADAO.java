package br.ufes.inf.nemo.gametime.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.gametime.domain.Game;
import br.ufes.inf.nemo.gametime.domain.Game_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;


@Stateless
public class GameJPADAO extends BaseJPADAO<Game> implements GameDAO{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(GameJPADAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Game> getDomainClass() {
		return Game.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	
	@Override
	public List<Game> findByName(String name) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Game> cq = cb.createQuery(Game.class);
		Root<Game> root = cq.from(Game.class);

		cq.where(cb.like(cb.lower(root.get(Game_.name)), name.toLowerCase() + "%"));
		cq.orderBy(cb.asc(root.get(Game_.name)));

		List<Game> result = entityManager.createQuery(cq).getResultList();
		return result;
	}
	
	/* 
	 * FUNCAO PARA RETORNAR GAME COM NOME E EMPRESA EXASTOS
	 * UTILIZADO PARA NAO PERMIRTIR CADASTRO DE UM GAME COM MESMO NOME E EMPRESA DE OUTRO
	 */
	@Override
	public Game retrieveByNameAndManufacturer(String name, String manufacturer) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException{
		
		logger.log(Level.INFO, "RETORNANDO O GAME COM NOME = \"{0}\" E COM EMPRESA = \"{1}\" ",new Object[]{ name, manufacturer});
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Game> cq = cb.createQuery(Game.class);
		Root<Game> root = cq.from(Game.class);
		cq.where( cb.and(  cb.equal(root.get(Game_.name), name), cb.equal(root.get(Game_.manufacturer), manufacturer)));
		Game result = executeSingleResultQuery(cq, name , manufacturer);
		logger.log(Level.INFO, "BUSCA COM SUCESSO DO GAME COM NOME E EMPRESA =  \"{0}\" ", name + " - " + manufacturer);
		return result;
	}

}
