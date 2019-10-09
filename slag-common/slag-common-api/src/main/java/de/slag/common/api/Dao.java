package de.slag.common.api;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Dao<T> {

	void save(T t);

	Optional<T> loadById(Long id);

	Collection<Long> findAllIds();

	default Collection<T> findAll() {
		return findAllIds().stream().map(id -> loadById(id).get()).collect(Collectors.toList());
	}

}
