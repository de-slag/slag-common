package de.slag.common.base.adm;

import java.util.Optional;

import de.slag.common.base.BaseException;

public interface AdmService {

	Optional<String> get(String key);
	
	default String getSafe(String key) {
		final Optional<String> optional = get(key);
		if(!optional.isPresent()) {
			throw new BaseException(String.format("adm property not found: '%s'", key));
		}
		return optional.get();
	}
	
}
