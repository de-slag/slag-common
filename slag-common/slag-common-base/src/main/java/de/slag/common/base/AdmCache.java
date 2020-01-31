package de.slag.common.base;

import java.util.Optional;

public interface AdmCache {

	default Optional<String> getValue(String key) {
		final String string = get(key);
		if (string != null) {
			return Optional.of(string);
		}
		return Optional.empty();
	}

	String get(String key);

}
