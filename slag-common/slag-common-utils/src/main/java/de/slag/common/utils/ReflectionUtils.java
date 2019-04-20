package de.slag.common.utils;

import java.lang.reflect.Field;

import de.slag.common.exception.BaseException;

public class ReflectionUtils {

	public static Field field(Class c, String name) {
		try {
			return c.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new BaseException(e);
		}
	}

}
