package de.slag.common;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public interface Dao<T> {
	
	void save(T t);
	
	Optional<T> loadById(Long id);

	Collection<Long> findAllIds();

}
