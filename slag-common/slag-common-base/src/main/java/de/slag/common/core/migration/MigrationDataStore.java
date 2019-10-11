package de.slag.common.core.migration;

import java.util.Collection;

public interface MigrationDataStore<T> {
	
	Collection<String> getTables();
	
	Collection<Long> getIds(String table);
	
	T get(String table, Long id);
	
	void store(T o);
	
	void flush();

}
