package de.slag.common.db.hibernate;

import java.util.Collection;
import java.util.Optional;

public interface HibernateService extends HibernateResource {

	void save(Object o);

	<T> Optional<T> loadById(Long id, Class<T> persistentType);

	Collection<Long> findAllIds(Class<?> persistentType);

}
