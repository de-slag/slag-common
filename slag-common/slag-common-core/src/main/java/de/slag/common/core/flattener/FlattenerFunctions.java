package de.slag.common.core.flattener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import de.slag.common.base.BaseException;
import de.slag.common.base.function.TriConsumer;

public class FlattenerFunctions {

	private static Predicate<Method> NO_PARAMETER = m -> m.getParameterCount() == 0;
	private static Predicate<Method> ONE_PARAMETER = m -> m.getParameterCount() == 1;
	private static Predicate<Method> WITH_RETURN_TYPE = m -> m.getReturnType() != null;
	private static Predicate<Method> WITHOUT_RETURN_TYPE = m -> {
		Class<?> returnType = m.getReturnType();
		if (returnType == null) {
			return true;
		}
		return returnType.getSimpleName().equals("void");
	};
	private static Predicate<Method> GETTER_NAME = m -> m.getName().startsWith("get") || m.getName().startsWith("is");
	private static Predicate<Method> SETTER_NAME = m -> {
		String name = m.getName();
		return name.startsWith("set");
	};
	private static final Function<Method, String> DETERMINE_ATTRIBUTE_NAME = m -> {
		String name = m.getName();
		if (name.startsWith("get")) {
			return name.substring(3);
		}
		if (name.startsWith("is")) {
			return name.substring(2);
		}
		if (name.startsWith("set")) {
			return name.substring(3);
		}
		return StringUtils.EMPTY;
	};

	public static final BiFunction<Object, String, String> GET_VALUE = (object, attributeName) -> {
		Method[] declaredMethods = object.getClass().getMethods();
		Optional<Method> findAny = Arrays.asList(declaredMethods).stream().filter(m -> {
			String upperCase = m.getName().toUpperCase();
			if (upperCase.equals("GET" + attributeName)) {
				return true;
			}
			return upperCase.equals("IS" + attributeName);
		}).findAny();
		if (findAny.isEmpty()) {
			return StringUtils.EMPTY;
		}
		Method method = findAny.get();
		Object returnValue;
		try {
			returnValue = method.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
		try {
			return LowerFunctionUtils.convertToString(returnValue);
		} catch (Exception e) {
			final String msg = String.format("error getting '%s' from class ''", attributeName,
					object.getClass().getName());
			throw new BaseException(msg, e);
		}

	};

	public static final TriConsumer<Object, String, String> SET_VALUE_0 = (object, attribute, value) -> {
		Method[] declaredMethods = object.getClass().getMethods();
		Optional<Method> findAny = Arrays.asList(declaredMethods).stream()
				.filter(m -> m.getName().toUpperCase().equals("SET" + attribute)).findAny();
		if (findAny.isEmpty()) {
			return;
		}
		Method method = findAny.get();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Class<?> type = parameterTypes[0];
		Object convert = LowerFunctionUtils.convertFromString(type, value);
		try {
			method.invoke(object, convert);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
	};

	public static final BiConsumer<Object, AttributeValue> SET_VALUE = (object, attributeValue) -> {
		SET_VALUE_0.accept(object, attributeValue.getAttribute(), attributeValue.getValue());
	};

	public static final Function<Object, Collection<String>> DETERMINE_ATTRIBUTES = o -> {
		Class<?> type = o.getClass();
		final Collection<Method> attributeMethods = new ArrayList<>();
		attributeMethods.addAll(determineGetter(type));
		attributeMethods.addAll(determineSetter(type));
		attributeMethods.removeAll(Arrays.asList(Object.class.getMethods()));
		return attributeMethods.stream().map(DETERMINE_ATTRIBUTE_NAME).map(s -> s.toUpperCase())
				.collect(Collectors.toSet());
	};

	private static Collection<Method> determineGetter(Class<?> type) {
		return Arrays.asList(type.getMethods()).stream().filter(GETTER_NAME).filter(WITH_RETURN_TYPE)
				.filter(NO_PARAMETER).collect(Collectors.toList());
	}

	static Collection<Method> determineSetter(Class<?> type) {
		return Arrays.asList(type.getMethods()).stream().filter(SETTER_NAME).filter(WITHOUT_RETURN_TYPE)
				.filter(ONE_PARAMETER).collect(Collectors.toList());
	}

}
