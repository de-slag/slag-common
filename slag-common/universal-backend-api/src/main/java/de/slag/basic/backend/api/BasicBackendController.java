package de.slag.basic.backend.api;

import javax.ws.rs.core.Response;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

public interface BasicBackendController {

	/**
	 * Implement as http-get<br>
	 * path = "/login", produces = MediaType.APPLICATION_JSON
	 * 
	 * 
	 * @param username as request parameter
	 * @param password as request parameter
	 * @return {@link Token}
	 */
	Token getLogin(String username, String password);

	/**
	 * implement as http-get<br>
	 * path = "/rundefault", produces = MediaType.APPLICATION_JSON
	 * 
	 * @param token as request parameter
	 * @return messages as string
	 */
	String runDefault(String token);

	/**
	 * Puts a single config Implement as http-put<br>
	 * path = "/configproperty", produces = MediaType.TEXT_PLAIN
	 * 
	 * 
	 * @param token          as request parameter
	 * @param configProperty as request body
	 * @return
	 */
	Response putConfigProperty(String token, ConfigProperty configProperty);

	/**
	 * implement as http-get<br>
	 * path="/types", produces = MediaType.APPLICATION_JSON
	 * 
	 * @param token
	 * @return
	 */
	String getTypes(String token);

	/**
	 * implement as http-get path="/entity", produces = MediaType.APPLICATION_JSON
	 */
	EntityDto getEntity(String token, String type, Long id);

}
