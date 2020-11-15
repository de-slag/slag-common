package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.BasicWebTargetCallBuilder.HttpMethod;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.call.DemoCall;

public class DemoCallBuilder implements Builder<DemoCall> {

	private String target = "http://localhost:18080/invest-backend";

	private String endpoint = "demo/notok";

	private HttpMethod httpMethod = HttpMethod.GET;

	private String acceptedResponseType = MediaType.TEXT_PLAIN;

	/**
	 * 
	 * @param target - a target url like 'http://host:port/path/'
	 * @return {@link DemoCallBuilder}
	 */
	public DemoCallBuilder withTarget(String target) {
		this.target = target;
		return this;
	}

	@Override
	public DemoCall build() {
		if (target == null) {
			throw new RuntimeException("target not setted");
		}
		
		final BasicWebTargetCall targetCall = new BasicWebTargetCallBuilder()
				.withEndpoint(endpoint)
				.withTarget(target)
				.withMethod(httpMethod)
				.withAcceptedResponseType(acceptedResponseType)
				.build();
		return new DemoCall() {

			@Override
			public String call() throws Exception {
				final Response call = targetCall.call();
				return call.readEntity(String.class);
			}
		};
	}

}
