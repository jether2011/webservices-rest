package br.unisal.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import br.unisal.api.model.City;

@Component
@SuppressWarnings("unchecked")
public class CityDAO extends HibernateDAO<City> {

	public CityDAO() {
		super(City.class);
	}

	public List<City> getCity() {
		List<City> cities = null;
		Query q = getSession().createQuery("from City order by nome asc");
		try {
			cities = q.list();
		} catch (NoResultException e) {
			cities = new ArrayList<>();
		}
		return cities;
	}

	public void salvar(City c) throws ConstraintViolationException{
		super.save(c);
	}
}
