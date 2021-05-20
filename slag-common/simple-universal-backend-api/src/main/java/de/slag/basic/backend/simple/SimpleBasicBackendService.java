package de.slag.basic.backend.simple;

import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

public interface SimpleBasicBackendService {

	Token getLogin(String identifier, String passcode);
	
	void validateLogin(Token token);

	EntityDto create(String type);

	EntityDto load(String type, Long id);

	EntityDto save(EntityDto dto);

	EntityDto delete(String type, Long id);

}