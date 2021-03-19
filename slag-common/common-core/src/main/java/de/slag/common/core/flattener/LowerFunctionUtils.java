package de.slag.common.core.flattener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import de.slag.common.base.BaseException;
import de.slag.common.core.DefaultDateFormat;

class LowerFunctionUtils {

	@SuppressWarnings("unchecked")
	static <T> T convertFromString(Class<T> t, String value) {
		if (String.class.isAssignableFrom(t)) {
			return (T) value;
		}
		if (Integer.class.isAssignableFrom(t)) {
			return (T) Integer.valueOf(value);
		}
		if (Boolean.class.isAssignableFrom(t)) {
			return (T) Boolean.valueOf(value);
		}
		throw new BaseException("no supported type: " + t.getName());
	}

	static String convertToString(Object returnValue) {
		if (returnValue == null) {
			return StringUtils.EMPTY;
		}
		Class<?> returnValueType = returnValue.getClass();
		if (String.class.isAssignableFrom(returnValueType)) {
			return (String) returnValue;
		}
		if (Boolean.class.isAssignableFrom(returnValueType)) {
			return Boolean.toString((Boolean) returnValue);
		}
		if (Integer.class.isAssignableFrom(returnValueType)) {
			return Integer.toString((Integer) returnValue);
		}

		if (Long.class.isAssignableFrom(returnValueType)) {
			return Long.toString((Long) returnValue);
		}

		if (Date.class.isAssignableFrom(returnValueType)) {
			return new SimpleDateFormat(DefaultDateFormat.ISO_8601).format(returnValue);
		}

		throw new BaseException("no supported type: " + returnValueType.getName());
	}

}
