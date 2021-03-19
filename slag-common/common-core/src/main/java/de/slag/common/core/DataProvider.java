package de.slag.common.core;

import java.util.Properties;

public interface DataProvider<T> {
	
	T supply(Properties properties);
	
	void ship(T t, Properties properties);

}
