package de.slag.basic.backend.api;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.slag.basic.backend.api.BasicBackendService.BackendState;
import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

/**
 * This is a Template for a Spring Boot web application. Copy this template to
 * the maven project.
 *
 */
@RestController
public class BasicBackendControllerImpl implements BasicBackendController {

	private static final Log LOG = LogFactory.getLog(BasicBackendControllerImpl.class);

	@Resource
	private BasicBackendService basicBackendService;

	@PostConstruct
	public void init() {
		LOG.info("initialized");
	}

	@Override
	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON)
	public Token getLogin(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {
		return basicBackendService.getLogin(username, password);
	}

	@Override
	@PutMapping(path = "/configproperty", produces = MediaType.TEXT_PLAIN)
	public Response putConfigProperty(@RequestParam(required = false) String token,
			@RequestBody ConfigProperty configProperty) {

		final BackendState putConfigProperty = basicBackendService.putConfigProperty(token, configProperty);
		if (putConfigProperty == BackendState.OK) {
			return null;
		}
		return Response.status(500).build();
	}

	@Override
	@GetMapping(path = "/rundefault", produces = MediaType.APPLICATION_JSON)
	public String runDefault(@RequestParam String token) {
		return basicBackendService.runDefault(token);
	}

	@GetMapping(path = "/demo/ok", produces = MediaType.TEXT_PLAIN)
	public String getOk() {
		return "ok, " + LocalDateTime.now();
	}

	@Override
	@GetMapping(path = "/types", produces = MediaType.APPLICATION_JSON)
	public String getTypes(String token) {
		// TODO: assert token
		Collection<String> types = basicBackendService.getDataTypes();
		return String.join(";", types);
	}

	@Override
	@GetMapping(path = "/entity", produces = MediaType.APPLICATION_JSON)
	public EntityDto getEntity(@RequestParam String token, @RequestParam String type,
			@RequestParam(required = false) Long id) {
		// TODO: assert token
		return basicBackendService.getEntity(type, id);
	}

}
