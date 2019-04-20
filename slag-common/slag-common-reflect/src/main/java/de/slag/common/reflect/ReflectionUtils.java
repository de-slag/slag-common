package de.slag.common.reflect;

import java.lang.reflect.Field;

public class ReflectionUtils {

	public static Field field(Class c, String name) {
		try {
			return c.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectException(e);
		}
	}

}
