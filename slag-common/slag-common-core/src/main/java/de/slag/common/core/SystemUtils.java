package de.slag.common.core;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Optional;

import de.slag.common.base.BaseException;

public class SystemUtils {

	public static String getUserHome() {
		final Optional<String> userHome = getSystemProperty("user.home");
		if (!userHome.isPresent()) {
			throw new BaseException("Property 'user.home' not found.");
		}
		return userHome.get();
	}

	public static Date getStartTime() {
		return new Date(ManagementFactory.getRuntimeMXBean().getStartTime());
	}

	public static Optional<String> getSystemProperty(String propertyName) {
		final String property = System.getProperty(propertyName);
		if (property == null) {
			return Optional.empty();
		}
		return Optional.of(property);
	}

}
