package br.unisal.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import br.unisal.api.model.Frete;

@Component
@SuppressWarnings("unchecked")
public class FreteDAO extends HibernateDAO<Frete> {

	public FreteDAO() {
		super(Frete.class);
	}

	public List<Frete> getFrete() {
		List<Frete> fretes = null;
		Query q = getSession().createQuery("from Frete order by id asc");
		try {
			fretes = q.list();
		} catch (NoResultException e) {
			fretes = new ArrayList<>();
		}
		return fretes;
	}

	public void salvar(Frete f) throws ConstraintViolationException{
		super.save(f);
	}
}
