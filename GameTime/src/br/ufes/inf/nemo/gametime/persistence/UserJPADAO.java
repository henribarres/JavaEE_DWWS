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

import br.ufes.inf.nemo.gametime.domain.User;
import br.ufes.inf.nemo.gametime.domain.User_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UserJPADAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public List<User> findByEmailList(String email) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);

		cq.where(cb.like(cb.lower(root.get(User_.email)), email.toLowerCase() + "%"));
		cq.orderBy(cb.asc(root.get(User_.email)));

		List<User> result = entityManager.createQuery(cq).getResultList();
		return result;
	}

	@Override
	public User retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException{
		logger.log(Level.INFO, "RETORNANDO O USURIO COM USERNAME = \"{0}\". ",email);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(  cb.equal(root.get(User_.email), email));
		User result = executeSingleResultQuery(cq, email);
		logger.log(Level.INFO, "BUSCA COM SUCESSO DO USUARIO COM USERNAME =  \"{0}\" ", email);
		return result;
	}

}
