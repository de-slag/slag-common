package de.slag.common.model;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodGetterFunctions {

	public static final Function<EntityBean, Collection<Method>> GETTER = bean -> {
		final List<Method> declaredMethods = Arrays.asList(bean.getClass().getDeclaredMethods());
		return declaredMethods.stream()
			.filter(m -> m.getName().startsWith("get") || m.getName().startsWith("is"))
			.filter(m -> m.getParameterCount() == 0)
			.filter(m -> m.getReturnType() != null)
			.collect(Collectors.toList());
	};
	
	public static final Function<EntityBean, Collection<Method>> SETTER = bean -> {
		final List<Method> declaredMethods = Arrays.asList(bean.getClass().getDeclaredMethods());
		return declaredMethods.stream()
			.filter(m -> m.getName().startsWith("set"))
			.filter(m -> m.getParameterCount() == 1)
			.filter(m -> m.getReturnType() == null)
			.collect(Collectors.toList());
	};

}
