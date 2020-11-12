package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.MediaType;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.RunDefaultCall;

public class RunDefaultCallBuilder extends AbstractBasicCallBuilder implements Builder<RunDefaultCall> {

	public RunDefaultCallBuilder(PropertiesSupplier propertiesSupplier) {
		super(propertiesSupplier);
	}

	@Override
	public RunDefaultCall build() {
		final String backendUrl = getPropertiesSupplier().getBackendUrl();
		final String currentToken = getPropertiesSupplier().getCurrentToken();
		
		
		final BasicWebTargetCall call = new BasicWebTargetCallBuilder().withTarget(backendUrl)
				.withEndpoint("/rundefault").withToken(currentToken).withAcceptedResponseType(MediaType.APPLICATION_JSON)
				.build();
		return new RunDefaultCall() {

			@Override
			public String call() throws Exception {
				return call.call().readEntity(String.class);
			}
		};
	}

}
