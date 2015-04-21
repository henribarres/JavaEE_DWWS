package br.ufes.inf.nemo.gametime.application;

import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ContaServiceBean extends CrudServiceBean<Conta> implements ContaService {

	private static final long serialVersionUID = 1L;

	@Override
	public BaseDAO<Conta> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Conta createNewEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
