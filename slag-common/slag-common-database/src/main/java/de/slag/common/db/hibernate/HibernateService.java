package de.slag.common.db.hibernate;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.SessionFactory;

public interface HibernateService {
	
	SessionFactory getSessionFactory();

	void save(Object o);

	<T> Optional<T> loadById(Long id, Class<T> persistentType);

	Collection<Long> findAllIds(Class<?> persistentType);

}
