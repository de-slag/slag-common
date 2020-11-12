package de.slag.basic.frontend.call.builder;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.BasicWebTargetCallBuilder.HttpMethod;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.ConfigCall;
import de.slag.basic.model.ConfigProperty;

public class ConfigCallBuilder extends AbstractBasicCallBuilder implements Builder<ConfigCall> {

	public ConfigCallBuilder(PropertiesSupplier propertiesSupplier) {
		super(propertiesSupplier);
	}

	@Override
	public ConfigCall build() {
		final String backendUrl = getPropertiesSupplier().getBackendUrl();
		final String currentToken = getPropertiesSupplier().getCurrentToken();

		final Properties properties = getPropertiesSupplier().get();

		final List<BasicWebTargetCall> calls = properties.keySet().stream().filter(key -> key instanceof String)
				.map(key -> (String) key).filter(key -> !key.startsWith("frontend.")).map(key -> {

					final ConfigProperty configProperty = new ConfigProperty();
					configProperty.setKey(key);
					configProperty.setValue(properties.getProperty(key));

					BasicWebTargetCall build = new BasicWebTargetCallBuilder().withTarget(backendUrl).withEndpoint("/configproperty")
							.withMethod(HttpMethod.PUT).withEntity(configProperty).withToken(currentToken).build();
					return build;
				}).collect(Collectors.toList());

		return new ConfigCall() {

			@Override
			public String call() throws Exception {
				calls.forEach(call -> {
					Response call2;
					try {
						call2 = call.call();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					if (call2.getStatus() != 200) {
						throw new RuntimeException("something went wrong");
					}
				});
				return "ok";
			}
		};
	}

}
