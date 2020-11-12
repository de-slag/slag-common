package de.slag.basic.frontend;

import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.slag.basic.model.ConfigProperty;
import de.slag.basic.model.Token;

public class BasicWebguiRunner implements Runnable {

	private Properties backendProperties = new Properties();

	private Properties frontendProperties = new Properties();

	private WebTarget loginWt;
	private WebTarget configWt;
	private WebTarget defaultWt;

	private String token;

	private String result;

	public BasicWebguiRunner(Properties properties) {
		final List<String> allKeys = properties.keySet().stream().filter(key -> key instanceof String)
				.map(key -> (String) key).collect(Collectors.toList());

		final List<String> frontendKeys = allKeys.stream().filter(key -> key.startsWith("frontend."))
				.collect(Collectors.toList());

		final List<String> backendKeys = allKeys.stream().filter(key -> !key.startsWith("frontend."))
				.collect(Collectors.toList());

		frontendKeys.forEach(key -> frontendProperties.put(key, properties.get(key)));
		backendKeys.forEach(key -> backendProperties.put(key, properties.get(key)));

		final String backendUrl = Objects.requireNonNull(frontendProperties.getProperty("frontend.backend.url"));

		loginWt = ClientBuilder.newClient().target(backendUrl + "/login");
		configWt = ClientBuilder.newClient().target(backendUrl + "/configproperty");
		defaultWt = ClientBuilder.newClient().target(backendUrl + "/rundefault");
	}

	@Override
	public void run() {
		runLogin();
		runConfigProperties();
		runDefault();

	}

	private void runDefault() {
		Builder request = defaultWt.queryParam("token", token).request(MediaType.APPLICATION_JSON);
		Response response = request.get();
		result = response.readEntity(String.class);
	}

	private void runConfigProperties() {
		backendProperties.keySet().forEach(key -> {
			String value = backendProperties.getProperty((String) key);
			ConfigProperty configProperty = new ConfigProperty();
			configProperty.setKey((String) key);
			configProperty.setValue(value);

			Response put = configWt.queryParam("token", token).request()
					.put(Entity.entity(configProperty, MediaType.APPLICATION_JSON));
			assert put.getStatus() == 200;
		});

	}

	private void runLogin() {
		String username = frontendProperties.getProperty("frontend.username");
		String password = frontendProperties.getProperty("frontend.password");

		Response response = loginWt.queryParam("username", username).queryParam("password", password)
				.request(MediaType.APPLICATION_JSON).get();

		token = response.readEntity(Token.class).getTokenString();
	}

	public String getResult() {
		return result;
	}

}
