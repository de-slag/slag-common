package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.Response;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.BasicWebTargetCallBuilder.HttpMethod;
import de.slag.basic.frontend.call.SaveEntityCall;
import de.slag.basic.model.EntityDto;

public class SaveEntityCallBuilder implements Builder<SaveEntityCall> {

	private static final String ENDPOINT = "/save";

	private String backendUrl;

	private EntityDto entityDto;

	private String token;

	public SaveEntityCallBuilder withToken(String token) {
		this.token = token;
		return this;
	}

	public SaveEntityCallBuilder withEntityDto(EntityDto entityDto) {
		this.entityDto = entityDto;
		return this;
	}

	public SaveEntityCallBuilder withBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
		return this;
	}

	@Override
	public SaveEntityCall build() {

		BasicWebTargetCall wtCall = new BasicWebTargetCallBuilder().withTarget(backendUrl).withEndpoint(ENDPOINT)
				.withMethod(HttpMethod.PUT).withEntity(entityDto).withToken(token).build();

		return new SaveEntityCall() {

			@Override
			public String call() throws Exception {
				final Response call = wtCall.call();
				if (200 != call.getStatus()) {
					throw new RuntimeException("something went wrong");
				}
				return "ok";
			}
		};

	}

}
