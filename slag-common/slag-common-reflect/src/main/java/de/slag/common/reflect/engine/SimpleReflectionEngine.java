package de.slag.common.reflect.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.slag.common.base.BaseException;
import de.slag.common.reflect.MethodFilters;

public class SimpleReflectionEngine {

	private static final List<String> IGNORED_GETTER_METHODS = Arrays.asList("GETCLASS");

	public void mapValues(Object source, Object target) {
		final Map<String, Object> values = getValues(source);
		setValues(target, values);
	}

	private void setValues(Object target, Map<String, Object> values) {
		final Set<String> keySet = values.keySet();
		keySet.forEach(attributeName -> {
			Object value = values.get(attributeName);
			Method setter = determineSetter(target.getClass(), attributeName);
			try {
				setter.invoke(target, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new BaseException("error setting property: " + value, e);
			}

		});

		// TODO Auto-generated method stub
	}

	private Method determineSetter(Class<?> type, String attributeName) {
		return Arrays.asList(type.getMethods()).stream()
				.filter(m -> ("SET" + attributeName.toUpperCase()).equals(m.getName().toUpperCase())).findAny()
				.orElseThrow(() -> new BaseException("setter not found for: " + attributeName + " in type: " + type));
	}

	private Map<String, Object> getValues(Object o) {

		final List<Method> allMethods = Arrays.asList(o.getClass().getMethods());
		List<Method> getters = allMethods.stream().filter(MethodFilters.NO_PARAMETER)
				.filter(MethodFilters.WITH_RETURN_TYPE)
				.filter(MethodFilters.GETTER_NAME)
				.filter(m -> !IGNORED_GETTER_METHODS.contains(m.getName().toUpperCase()))
				.collect(Collectors.toList());

		final Map<String, Object> valueMap = new HashMap<>();

		getters.forEach(getterMethod -> {
			Object value;
			try {
				value = getterMethod.invoke(o);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new BaseException("error invoking method: " + getterMethod, e);
			}
			String propertyName = determineAttributeName(getterMethod).toUpperCase();
			valueMap.put(propertyName, value);
		});

		return valueMap;
	}

	private String determineAttributeName(Method getterMethod) {
		String methodName = getterMethod.getName();
		if (methodName.startsWith("get")) {
			return methodName.substring(3, methodName.length());
		}
		if (methodName.startsWith("is")) {
			return methodName.substring(2, methodName.length());
		}
		throw new BaseException("cannot determine protperty name from getter method " + getterMethod);
	}

}
