package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.EntityCall;
import de.slag.basic.model.EntityDto;

public class EntityCallBuilder implements Builder<EntityCall> {

	private final PropertiesSupplier propertiesSupplier;

	private String type;

	private Long id;

	private String currentToken;

	private String backendUrl;

	public EntityCallBuilder withBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
		return this;
	}

	public EntityCallBuilder withCurrentToken(String currentToken) {
		this.currentToken = currentToken;
		return this;
	}

	public EntityCallBuilder withType(String type) {
		this.type = type;
		return this;
	}

	public EntityCallBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public EntityCallBuilder(PropertiesSupplier propertiesSupplier) {
		super();
		this.propertiesSupplier = propertiesSupplier;
		currentToken = propertiesSupplier.getCurrentToken();
		backendUrl = propertiesSupplier.getBackendUrl();
	}

	public EntityCallBuilder() {
		this.propertiesSupplier = null;
	}

	@Override
	public EntityCall build() {

		final BasicWebTargetCall webTargetCall = new BasicWebTargetCallBuilder().withEndpoint("/entity")
				.withTarget(backendUrl)
				.withAcceptedResponseType(MediaType.APPLICATION_JSON)
				.withToken(currentToken)
				.addQueryParam("type", type)
				.addQueryParam("id", id != null ? String.valueOf(id) : null)
				.build();

		return new EntityCall() {

			@Override
			public EntityDto call() throws Exception {
				final EntityCall call = new EntityCall() {

					@Override
					public EntityDto call() throws Exception {
						final Response call = webTargetCall.call();
						return call.readEntity(EntityDto.class);
					}
				};
				return call.call();
			}
		};

	}

}
