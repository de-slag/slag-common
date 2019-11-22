package de.slag.common.reflect;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public interface MethodFilters {

	public static Predicate<Method> NO_PARAMETER = m -> m.getParameterCount() == 0;
	public static Predicate<Method> WITH_RETURN_TYPE = m -> m.getReturnType() != null;
	public static Predicate<Method> GETTER_NAME = m -> m.getName().startsWith("get") || m.getName().startsWith("is");

}
