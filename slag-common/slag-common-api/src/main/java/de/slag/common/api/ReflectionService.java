package de.slag.common.api;

import java.util.Optional;

public interface ReflectionService {
	
	<T> Optional<T> getValue(Object o, String attribute, Class<T> returnType);
	
	void setValue(Object o, String attribute, Object value);
	
	

}
