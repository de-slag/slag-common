package de.slag.basic.frontend;

import java.util.Properties;
import java.util.function.Supplier;

public interface PropertiesSupplier extends Supplier<Properties> {

	public static final String FRONTEND_CURRENT_TOKEN = "frontend.current.token";
	public static final String FRONTEND_PASSWORD = "frontend.password";
	public static final String FRONTEND_USER = "frontend.user";
	public static final String FRONTEND_BACKEND_URL = "frontend.backend.url";

	default String getBackendUrl() {
		return get().getProperty(FRONTEND_BACKEND_URL);
	}

	default String getUser() {
		return get().getProperty(FRONTEND_USER);
	}

	default String getPassword() {
		return get().getProperty(FRONTEND_PASSWORD);
	}

	default String getCurrentToken() {
		return get().getProperty(FRONTEND_CURRENT_TOKEN);
	}

}
