package de.slag.common.db;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
	
	void save(T t);
	
	Optional<T> loadById(Long id);

	Collection<Long> findAllIds();

}
