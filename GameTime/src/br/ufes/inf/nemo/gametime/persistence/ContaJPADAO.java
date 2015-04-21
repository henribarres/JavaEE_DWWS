package br.ufes.inf.nemo.gametime.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class ContaJPADAO extends BaseJPADAO<Conta> implements ContaDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Conta> getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
