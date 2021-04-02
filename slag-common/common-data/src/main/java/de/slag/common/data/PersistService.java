package de.slag.common.data;

import java.util.Collection;
import java.util.Optional;

import de.slag.common.model.EntityBean;

public interface PersistService<E extends EntityBean> {

	void save(E e);

	void delete(E e);

	Optional<E> loadById(Long id);

	Collection<Long> findAllIds();
}
