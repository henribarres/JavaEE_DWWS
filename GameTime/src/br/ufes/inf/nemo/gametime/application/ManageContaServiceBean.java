package br.ufes.inf.nemo.gametime.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.gametime.domain.Conta;
import br.ufes.inf.nemo.gametime.persistence.ContaDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ManageContaServiceBean extends CrudServiceBean<Conta> implements ManageContaService {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ManageContaServiceBean.class.getCanonicalName());
	
	@EJB
	private ContaDAO contaDAO;
	
	@Override
	public BaseDAO<Conta> getDAO() {
		return contaDAO;
	}

	@Override
	protected Conta createNewEntity() {
		return new Conta();
	}

	@Override
	public void validateCreate(Conta entity) throws CrudException {
		logger.log(Level.INFO, "VALIDAÇÃO PARA CONTA ");
		super.validateCreate(entity);
	}
	
}
