package de.slag.common.db;

import java.util.Collection;
import java.util.Optional;

import de.slag.common.db.hibernate.AbstractHibernateServiceImpl;
import de.slag.common.db.hibernate.HibernateService;

public class HibernateSupport {

	private HibernateService service;

	HibernateSupport(Collection<Class<?>> registerClasses) {
		service = new AbstractHibernateServiceImpl(registerClasses);
	}

	public void save(Object o) {
		service.save(o);
	}

	public <T> Optional<T> loadById(Long id, Class<T> persistentType) {
		return service.loadById(id, persistentType);
	}

	public Collection<Long> findAllIds(Class<?> persistentType) {
		return service.findAllIds(persistentType);
	}

}
