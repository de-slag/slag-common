package de.slag.basic.backend.simple;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.slag.basic.model.EntityDto;

@RestController
public class SimpleBasicBackendControllerImpl implements SimpleBasicBackendController {

	private static final Log LOG = LogFactory.getLog(SimpleBasicBackendControllerImpl.class);

	@Resource
	private SimpleBasicBackendService simpleBasicBackendService;

	@PostConstruct
	public void init() {
		LOG.warn("initialized");
	}

//	@Override
	@GetMapping(path = "/login", produces = MediaType.TEXT_PLAIN)
	public String getLogin(@RequestParam String identifier, @RequestParam String passcode) {
		return simpleBasicBackendService.getLogin(identifier, passcode).getTokenString();
	}

//	@Override
	@GetMapping(path = "/create", produces = MediaType.APPLICATION_JSON)
	public EntityDto create(@RequestParam String token, @RequestParam String type) {
		return simpleBasicBackendService.create(type);
	}

//	@Override
	@GetMapping(path = "/read", produces = MediaType.APPLICATION_JSON)
	public EntityDto read(@RequestParam String token, @RequestParam String type, @RequestParam Long id) {
		return simpleBasicBackendService.load(type, id);
	}

//	@Override
	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON)
	public EntityDto update(@RequestParam String token, @RequestBody EntityDto dto) {
		return simpleBasicBackendService.save(dto);
	}

//	@Override
	@DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON)
	public EntityDto delete(@RequestParam String token, @RequestParam String type, @RequestParam Long id) {
		return simpleBasicBackendService.delete(type, id);
	}

//	@Override
	@GetMapping(path = "/ok", produces = MediaType.TEXT_PLAIN)
	public String ok() {
		return "OK";
	}

}
