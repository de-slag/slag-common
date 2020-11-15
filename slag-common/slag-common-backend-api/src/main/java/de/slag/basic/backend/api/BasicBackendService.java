package de.slag.basic.backend.api;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.LogFactory;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

public interface BasicBackendService {

	Token getLogin(String username, String password);

	BackendState putConfigProperty(String token, ConfigProperty configProperty);

	String runDefault(String token);

	public enum BackendState {
		OK,

		NOT_OK
	}

	default Collection<String> getDataTypes() {
		return Collections.emptyList();
	}

	default EntityDto getEntity(String type, Long id) {
		return null;
	}

	default BackendState save(EntityDto entityDto) {
		LogFactory.getLog(BasicBackendService.class).error("not implemented yet");
		return BackendState.NOT_OK;
	}
}
