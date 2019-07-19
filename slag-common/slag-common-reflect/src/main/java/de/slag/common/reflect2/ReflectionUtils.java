package de.slag.common.reflect2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import de.slag.common.base.BaseException;

public class ReflectionUtils {

	private static final String IS = "is";

	private static final String GET = "get";

	private static final String SET = "set";

	private static Predicate<Method> FILTER_GETTER = m -> {
		String methodName = m.getName();
		boolean get = methodName.startsWith(GET);
		boolean is = methodName.startsWith(IS);
		if (!get && !is) {
			return false;
		}
		if ("getClass".equals(m.getName())) {
			return false;
		}
		return m.getParameterCount() == 0;
	};

	private static Predicate<Method> FILTER_SETTER = m -> {
		String methodName = m.getName();
		boolean set = methodName.startsWith(SET);
		if (!set) {
			return false;
		}
		boolean oneParameter = m.getParameterCount() == 1;
		if (!oneParameter) {
			return false;
		}

		return m.getReturnType() != null;
	};

	public static Collection<String> determineAccessibleProperties(Class<?> c) {
		return determineSetters(c).stream().map(m -> setterNameToPropertyName(m.getName())).filter(o -> o.isPresent())
				.map(o -> o.get()).collect(Collectors.toList());
	}

	public static Optional<Method> determineSetter(Class<?> c, String attributeName) {
		final String setterName = SET + upperFirstCharacter(attributeName);
		return determineMethods(c, m -> setterName.equals(m.getName())).stream().findAny();
	}

	public static Optional<Method> determineGetter(Class<?> c, String attributeName) {
		final String getterName = GET + upperFirstCharacter(attributeName);
		final String isName = IS + upperFirstCharacter(attributeName);
		return determineMethods(c, m -> {
			String name = m.getName();
			return getterName.equals(name) || isName.equals(name);
		}).stream().findAny();
	}

	public static Optional<String> setterNameToPropertyName(String setterName) {
		if (!setterName.startsWith(SET)) {
			return Optional.empty();
		}
		return Optional.of(lowerFirstCharacter(setterName.substring(3)));
	}

	public static Optional<String> getterNameToPropertyName(String getterName) {
		if (getterName.startsWith(GET)) {
			return Optional.of(lowerFirstCharacter(getterName.substring(3)));
		}
		if (getterName.startsWith(IS)) {
			return Optional.of(lowerFirstCharacter(getterName.substring(2)));
		}
		return Optional.empty();
	}

	public static Collection<Method> determineSetters(Class<?> c) {
		return determineMethods(c, FILTER_SETTER);
	}

	/**
	 * ..without Object.getClass()
	 * 
	 * @param o
	 * @return
	 */
	public static Collection<Method> determineGetters(Class<?> c) {
		return determineMethods(c, FILTER_GETTER);
	}

	private static Collection<Method> determineMethods(Class<?> c, Predicate<Method> filter) {
		return Arrays.stream(c.getMethods()).filter(filter).collect(Collectors.toList());
	}

	private static String lowerFirstCharacter(String s) {
		return modifyFirstCharacter(s, first -> first.toLowerCase());
	}

	private static String upperFirstCharacter(String s) {
		return modifyFirstCharacter(s, first -> first.toUpperCase());
	}

	private static String modifyFirstCharacter(String s, Function<String, String> modifier) {
		if (StringUtils.isAllBlank(s)) {
			return StringUtils.EMPTY;
		}
		final int length = s.length();
		if (length == 1) {
			return s.toLowerCase();
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(modifier.apply(s.substring(0, 1)));
		sb.append(s.substring(1, length));
		return sb.toString();
	}

	/**
	 * ...without Object.class.
	 * 
	 * @param class1
	 * @return
	 */
	public static Collection<Class<?>> determineClasses(Class<?> class1) {
		final Collection<Class<?>> result = new ArrayList<>();
		result.add(class1);
		Class<?>[] interfaces = class1.getInterfaces();
		result.addAll(Arrays.asList(interfaces));

		Class<?> superclass = class1.getSuperclass();
		if (superclass != null && !Object.class.equals(superclass)) {
			result.addAll(determineClasses(superclass));
		}
		return result;
	}

	public static void invokeSetter(Method method, Object object, Object value) {
		try {
			method.invoke(object, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
	}

	public static Object invokeGetter(Method method, Object object) {
		try {
			return method.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BaseException(e);
		}
	}

}
