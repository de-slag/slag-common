package de.slag.common.core.dao;

import java.util.Optional;

import de.slag.common.model.EntityBean;

public interface Dao<E extends EntityBean> {
	
	E save (E e);
	
	void delete(E e);
	
	Optional<E> load(Long id);

}
