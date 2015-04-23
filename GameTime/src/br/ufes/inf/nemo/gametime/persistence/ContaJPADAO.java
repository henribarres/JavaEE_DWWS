package br.ufes.inf.nemo.gametime.persistence;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class ContaJPADAO extends BaseJPADAO<Conta> implements ContaDAO{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ContaJPADAO.class.getCanonicalName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Conta> getDomainClass() {
		return Conta.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
