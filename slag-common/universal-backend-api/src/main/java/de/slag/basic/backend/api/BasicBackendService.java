package de.slag.basic.backend.api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

public interface BasicBackendService {

	Token getLogin(String username, String password) throws Exception;
	
	default Optional<Object> authenticate(Token token) throws Exception {
		return Optional.empty();
	}

	BasicBackendServiceReturnValue putConfigProperty(String token, ConfigProperty configProperty) throws Exception;

	String runDefault(String token) throws Exception;

	default Collection<String> getDataTypes() throws Exception {
		return Collections.emptyList();
	}

	default EntityDto getEntity(String type, Long id) throws Exception {
		return null;
	}

	default BasicBackendServiceReturnValue save(EntityDto entityDto) throws Exception {
		throw new RuntimeException("not implemented yet");
	}
}
