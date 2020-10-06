package de.slag.common.datasource;

public interface DataSource<T> {
	
	void create(T t);
	
	T read(Class<? extends T> type, Long id);
	
	T update(T t);
	
	void delete(T t);

}
