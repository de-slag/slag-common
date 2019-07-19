package de.slag.common.reflect2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import de.slag.common.api.ReflectionService;
import de.slag.common.base.BaseException;

public class ReflectionServiceImpl implements ReflectionService {

	@Override
	public <T> Optional<T> getValue(Object o, String attributeName, Class<T> returnType) {
		final Object invoke = getValue(o, attributeName);
		T cast = returnType.cast(invoke);
		return Optional.of(cast);
	}

	@Override
	public Object getValue(Object o, String attributeName) {
		final Optional<Method> determineGetter = ReflectionUtils.determineGetter(o.getClass(), attributeName);
		if (determineGetter.isEmpty()) {
			throw new BaseException("no getter found for " + o.getClass() + ", " + attributeName);
		}
		final Method getter = determineGetter.get();
		final Object invoke;
		try {
			invoke = getter.invoke(o);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
		return invoke;
	}

	@Override
	public void setValue(Object o, String attributeName, Object value) {
		Optional<Method> determineSetter = ReflectionUtils.determineSetter(o.getClass(), attributeName);
		if (determineSetter.isEmpty()) {
			throw new BaseException("no setter found for " + o.getClass() + ", " + attributeName);
		}
		Method setter = determineSetter.get();
		
		try {
			setter.invoke(o, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
	}

}
