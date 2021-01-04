package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.MediaType;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.RunDefaultCall;
import de.slag.basic.model.Token;

public class RunDefaultCallBuilder extends AbstractBasicCallBuilder implements Builder<RunDefaultCall> {

	private String backendUrl;
	private String currentToken;

	/**
	 * use default constructor and 'with...' methods instead
	 * 
	 * @param propertiesSupplier
	 */
	@Deprecated
	public RunDefaultCallBuilder(PropertiesSupplier propertiesSupplier) {
		super(null);
		backendUrl = getPropertiesSupplier().getBackendUrl();
		currentToken = getPropertiesSupplier().getCurrentToken();
	}

	public RunDefaultCallBuilder() {
		super(null);
	}

	public RunDefaultCallBuilder withTarget(String target) {
		this.backendUrl = target;
		return this;
	}

	public RunDefaultCallBuilder withToken(String token) {
		this.currentToken = token;
		return this;
	}

	@Override
	public RunDefaultCall build() {

		final BasicWebTargetCall call = new BasicWebTargetCallBuilder().withTarget(backendUrl)
				.withEndpoint("/rundefault")
				.withToken(currentToken)
				.withAcceptedResponseType(MediaType.APPLICATION_JSON)
				.build();
		return new RunDefaultCall() {

			@Override
			public String call() throws Exception {
				return call.call().readEntity(String.class);
			}
		};
	}

}
