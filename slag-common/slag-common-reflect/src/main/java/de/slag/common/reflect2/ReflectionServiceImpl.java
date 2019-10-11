package de.slag.common.reflect2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import de.slag.common.base.BaseException;

public class ReflectionServiceImpl {
	
	ReflectionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	<T> Optional<T> getValue(Object o, String attributeName, Class<T> returnType) {
		final Object invoke = getValue(o, attributeName);
		T cast = returnType.cast(invoke);
		return Optional.of(cast);
	}

	Object getValue(Object o, String attributeName) {
		final Method getter = getter(o, attributeName);
		final Object invoke;
		try {
			invoke = getter.invoke(o);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
		return invoke;
	}

	private Method getter(Object obj, String attributeName) {
		final Optional<Method> determineGetter = ReflectionUtils.determineGetter(obj.getClass(), attributeName);
		if (!determineGetter.isPresent()) {
			throw new BaseException("no getter found for " + obj.getClass() + ", " + attributeName);
		}
		return determineGetter.get();
	}

	void setValue(Object obj, String attributeName, Object value) {
		Method setter = setter(obj, attributeName);

		try {
			setter.invoke(obj, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
	}

	private Method setter(Object obj, String attributeName) {
		final Optional<Method> determineSetter = ReflectionUtils.determineSetter(obj.getClass(), attributeName);
		if (!determineSetter.isPresent()) {
			throw new BaseException("no setter found for " + obj.getClass() + ", " + attributeName);
		}
		return determineSetter.get();
	}

	Class<?> getType(Object obj, String attribute) {
		Method getter = getter(obj, attribute);
		Method setter = setter(obj, attribute);

		Class<?> returnType = getter.getReturnType();

		Class<?> parameterType = setter.getParameterTypes()[0];

		if (!returnType.equals(parameterType)) {
			throw new BaseException(
					String.format("returnType and parameterType are not equals: %s, %s", returnType, parameterType));
		}
		return returnType;
	}

}
