package de.slag.common.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Dao<T> {

	HibernateSupport getHibernateSupport();

	Class<T> getPersistentType();

	default void save(T t) {
		getHibernateSupport().save(t);
	}

	default Optional<T> loadById(Long id) {
		return getHibernateSupport().loadById(id, getPersistentType());
	}

	default Collection<Long> findAllIds() {
		return getHibernateSupport().findAllIds(getPersistentType());
	}

	default Collection<T> findAll() {
		return findAllIds().stream().map(id -> loadById(id).get()).collect(Collectors.toList());
	}

	/**
	 * CAUTION! may be slow
	 */
	default Collection<T> findBy(Predicate<T> filter) {
		return new ArrayList<>(findAll().stream().filter(filter).collect(Collectors.toList()));
	}

}
