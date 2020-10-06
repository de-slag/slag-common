package de.slag.common.reflect.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.base.BaseException;
import de.slag.common.reflect.MethodFilters;

public class SimpleReflectionEngine {
	
	private static final Log LOG = LogFactory.getLog(SimpleReflectionEngine.class);

	public void mapValues(Object source, Object target) {
		mapValues(source, target, Collections.emptyList());
	}

	public void mapValues(Object source, Object target, Collection<String> ignoredAttributes) {
		final Map<String, Object> values = getValues(source, ignoredAttributes);
		
		LOG.debug("set values: "+ String.join("; ", values.keySet()));

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

	private Map<String, Object> getValues(Object o, Collection<String> ignoredAttributes) {
		
		final Collection<String> ignoredAttributes0 = new ArrayList<String>(ignoredAttributes);
		ignoredAttributes0.add("CLASS");

		final List<Method> allMethods = Arrays.asList(o.getClass().getMethods());
		List<Method> getters = allMethods.stream().filter(MethodFilters.NO_PARAMETER)
				.filter(MethodFilters.WITH_RETURN_TYPE).filter(MethodFilters.GETTER_NAME)
				.collect(Collectors.toList());

		final Map<String, Object> valueMap = new HashMap<>();

		getters.forEach(getterMethod -> {
			String attributeName = determineAttributeName(getterMethod).toUpperCase();
			if (ignoredAttributes0.contains(attributeName)) {
				LOG.debug(String.format("ignoredAttributes contains: '%s'", attributeName));
				return;
			}

			Object value;
			try {
				value = getterMethod.invoke(o);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new BaseException("error invoking method: " + getterMethod, e);
			}

			valueMap.put(attributeName, value);
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
