package de.slag.basic.backend.api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;

@RestController
public class BasicBackendControllerImpl implements BasicBackendController {

	private static final String AUTHENTICATION_FAILED = "authentication failed";

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
		try {
			return basicBackendService.getLogin(username, password);
		} catch (Exception e) {
			LOG.error(String.format("error get login with username '%s' and password '%s'", username, "SECRET"), e);
		}
		return null;
	}

	@Override
	@PutMapping(path = "/configproperty", produces = MediaType.TEXT_PLAIN)
	public Response putConfigProperty(@RequestParam(required = false) String token,
			@RequestBody ConfigProperty configProperty) {
		// TODO: assert token

		final BasicBackendServiceReturnValue putConfigProperty;
		try {
			putConfigProperty = basicBackendService.putConfigProperty(token, configProperty);
		} catch (Exception e) {
			LOG.error(String.format("error put proerty key '%s', value '%s'", configProperty.getKey(),
					configProperty.getValue(), "SECRET"), e);
			return Response.serverError().build();
		}

		return Response.ok(putConfigProperty.getValue()).build();
	}

	@PutMapping(path = "/save", produces = MediaType.TEXT_PLAIN)
	public Response saveEntity(@RequestParam String token, @RequestBody EntityDto entityDto) {
		handleException(() -> basicBackendService.authenticate(Token.of(token)), () -> AUTHENTICATION_FAILED);

		return Response.ok().build();
	}

	@Override
	@GetMapping(path = "/rundefault", produces = MediaType.APPLICATION_JSON)
	public String runDefault(@RequestParam String token) {
		handleException(() -> basicBackendService.authenticate(Token.of(token)), () -> AUTHENTICATION_FAILED);
		return handleException(() -> basicBackendService.runDefault(token), () -> "error run default");
	}

	@GetMapping(path = "/demo/ok", produces = MediaType.TEXT_PLAIN)
	public String getOk() {
		final String backendServiceSimpleName = basicBackendService.getClass().getSimpleName();
		return "ok, " + backendServiceSimpleName + ", " + LocalDateTime.now();
	}

	@Override
	@GetMapping(path = "/types", produces = MediaType.APPLICATION_JSON)
	public String getTypes(String token) {
		handleException(() -> basicBackendService.authenticate(Token.of(token)), () -> AUTHENTICATION_FAILED);

		Collection<String> types = handleException(() -> basicBackendService.getDataTypes(),
				() -> "error getting types.");

		return String.join(";", types);
	}

	@Override
	@GetMapping(path = "/entity", produces = MediaType.APPLICATION_JSON)
	public EntityDto getEntity(@RequestParam String token, @RequestParam String type,
			@RequestParam(required = false) Long id) {

		handleException(() -> basicBackendService.authenticate(Token.of(token)), () -> AUTHENTICATION_FAILED);

		final String msg = String.format("error getting entity type: %s, id: %s", type, id);
		return handleException(() -> basicBackendService.getEntity(type, id), () -> msg);

	}

	private <T> T handleException(Callable<T> call, Supplier<String> messageSupplier) {
		try {
			return call.call();
		} catch (Exception e) {
			throw new RuntimeException(messageSupplier.get(), e);
		}
	}
}
