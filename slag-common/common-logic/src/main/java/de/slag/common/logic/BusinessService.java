package de.slag.common.logic;

import java.util.Optional;

import de.slag.common.model.EntityBean;

public interface BusinessService<E extends EntityBean> {

	void save(E e);

	void delete(E e);

	Optional<E> loadById(Long id);

	E create();

}
