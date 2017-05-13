package br.unisal.api.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.unisal.api.dao.FreteDAO;
import br.unisal.api.model.Frete;

@Component
public class FreteService {
	
	@Autowired
	private FreteDAO db;

	public List<Frete> getFrete() {
		return db.getFrete();
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean save(Frete frete) throws ConstraintViolationException{
		db.saveOrUpdate(frete);
		return true;
	}

}
