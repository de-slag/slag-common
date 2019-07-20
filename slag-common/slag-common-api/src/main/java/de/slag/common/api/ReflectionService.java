package de.slag.common.api;

import java.util.Optional;

public interface ReflectionService {

	<T> Optional<T> getValue(Object obj, String attribute, Class<T> returnType);

	Object getValue(Object obj, String attributeName);

	Class<?> getType(Object obj, String attribute);

	void setValue(Object obj, String attribute, Object value);

}
