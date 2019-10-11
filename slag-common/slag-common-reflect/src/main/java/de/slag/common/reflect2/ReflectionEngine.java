package de.slag.common.reflect2;

import java.util.Optional;

public class ReflectionEngine {

	private ReflectionServiceImpl service;

	ReflectionEngine() {
		service = new ReflectionServiceImpl();
	}

	public <T> Optional<T> getValue(Object o, String attributeName, Class<T> returnType) {
		return service.getValue(o, attributeName, returnType);
	}

	public Object getValue(Object o, String attributeName) {
		return service.getValue(o, attributeName);
	}

	public void setValue(Object obj, String attributeName, Object value) {
		service.setValue(obj, attributeName, value);
	}

	public Class<?> getType(Object obj, String attribute) {
		return service.getType(obj, attribute);
	}

}
