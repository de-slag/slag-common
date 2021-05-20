package de.slag.basic.backend.simple;

import de.slag.basic.model.EntityDto;

public interface SimpleBasicBackendController {

	String getLogin(String identifier, String passcode);

	EntityDto create(String token, String type);

	EntityDto read(String token, String type, Long id);

	EntityDto update(String token, EntityDto dto);

	EntityDto delete(String token, String type, Long id);
	
	String ok();

}
