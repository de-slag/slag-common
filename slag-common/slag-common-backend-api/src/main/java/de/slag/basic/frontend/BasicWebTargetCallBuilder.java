package de.slag.basic.frontend;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BasicWebTargetCallBuilder implements Builder<BasicWebTargetCall> {

	private String acceptedResponseType = MediaType.TEXT_PLAIN;

	private String requestMediaType = MediaType.APPLICATION_JSON;

	private HttpMethod httpMethod = HttpMethod.GET;

	private String target;

	private String endpoint;

	private Object entity;

	private Map<String, String> queryParams = new HashMap<>();

	public BasicWebTargetCallBuilder addQueryParam(String key, String value) {
		queryParams.put(key, value);
		return this;
	}

	public BasicWebTargetCallBuilder withMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
		return this;
	}

	public BasicWebTargetCallBuilder withToken(String token) {
		queryParams.put("token", token);
		return this;
	}

	public BasicWebTargetCallBuilder withEntity(Object entity) {
		this.entity = entity;
		return this;
	}

	public BasicWebTargetCallBuilder withTarget(String target) {
		this.target = target;
		return this;
	}

	public BasicWebTargetCallBuilder withEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	public BasicWebTargetCallBuilder withAcceptedResponseType(String acceptedResponseType) {
		this.acceptedResponseType = acceptedResponseType;
		return this;
	}

	public BasicWebTargetCall build() {

		Objects.requireNonNull(target, "target not setted");
		Objects.requireNonNull(endpoint, "endpoint not setted");

		String uri = target + endpoint;

		final WebTarget webTarget = addQueryParams(ClientBuilder.newClient().target(uri));

		return new BasicWebTargetCall() {

			@Override
			public Response call() throws Exception {

				switch (httpMethod) {
				case GET:
					return get();
				case PUT:
					return put();

				default:
					throw new RuntimeException("not supported: " + httpMethod);
				}
			}

			private Response put() {
				return interceptException(
						() -> webTarget.request(acceptedResponseType).put(Entity.entity(entity, requestMediaType)));
			}

			private Response get() {
				return interceptException(() -> webTarget.request(acceptedResponseType).get());
			}

			private Response interceptException(Callable<Response> c) {
				try {
					return c.call();
				} catch (Exception e) {
					throw new RuntimeException("uri: " + uri, e);
				}
			}
		};
	}

	private WebTarget addQueryParams(WebTarget webTarget) {
		WebTarget currentWebTarget = webTarget;
		final Set<String> keySet = queryParams.keySet();
		for (String key : keySet) {
			currentWebTarget = currentWebTarget.queryParam(key, queryParams.get(key));
		}
		return currentWebTarget;
	}

	public enum HttpMethod {
		GET,

		PUT
	}
}
