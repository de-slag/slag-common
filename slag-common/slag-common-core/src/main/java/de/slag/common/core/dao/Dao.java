package de.slag.common.core.dao;

import java.util.Collection;
import java.util.Optional;

import de.slag.common.model.EntityBean;

public interface Dao<E extends EntityBean> {
	
	E save (E e);
	
	void delete(E e);
	/**
	 * 
	 * use 'loadById' instead
	 */
	@Deprecated
	Optional<E> load(Long id);
	
	Optional<E> loadById(Long id);
	
	Collection<Long> findAllIds();
	

}
