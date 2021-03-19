package de.slag.basic.frontend.call.builder;

import java.util.Arrays;
import java.util.Collection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.RecieveTypesCall;

public class RecieveTypesCallBuilder extends AbstractBasicCallBuilder implements Builder<RecieveTypesCall> {

	public RecieveTypesCallBuilder(PropertiesSupplier propertiesSupplier) {
		super(propertiesSupplier);
	}

	@Override
	public RecieveTypesCall build() {
		final String backendUrl = getPropertiesSupplier().getBackendUrl();
		final String currentToken = getPropertiesSupplier().getCurrentToken();

		final BasicWebTargetCall call = new BasicWebTargetCallBuilder().withTarget(backendUrl).withEndpoint("/types")
				.withToken(currentToken).withAcceptedResponseType(MediaType.APPLICATION_JSON).build();
		return new RecieveTypesCall() {

			@Override
			public Collection<String> call() throws Exception {
				final Response response = call.call();
				final String readEntity = response.readEntity(String.class);
				final String[] split = readEntity.split(";");
				return Arrays.asList(split);
			}
		};

	}

}
