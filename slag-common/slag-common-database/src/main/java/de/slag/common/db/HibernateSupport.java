package de.slag.common.db;

import java.io.Closeable;
import java.util.Collection;
import java.util.Optional;

public interface HibernateSupport extends Closeable {

	void save(Object o);

	<T> Optional<T> loadById(Long id, Class<T> persistentType);

	Collection<Long> findAllIds(Class<?> clazz);

	<T> Collection<T> findBy(Class<T> type, String sql);


}
