package de.slag.common.core.datasource;

import java.util.Collection;

import de.slag.common.model.EntityBean;

public interface DataSource<T> {
	
	void create(T t);
	
	T read(Class<? extends T> type, Long id);
	
	T update(T t);
	
	void delete(T t);
	
	Collection<Long> findAllIds(Class<? extends EntityBean> type);

}
