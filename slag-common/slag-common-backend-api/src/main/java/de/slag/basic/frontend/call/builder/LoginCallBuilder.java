package de.slag.basic.frontend.call.builder;

import java.util.Objects;

import javax.ws.rs.core.MediaType;

import de.slag.basic.frontend.BasicWebTargetCall;
import de.slag.basic.frontend.BasicWebTargetCallBuilder;
import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.LoginCall;
import de.slag.basic.model.Token;

public class LoginCallBuilder extends AbstractBasicCallBuilder implements Builder<LoginCall> {

	String backendUrl;
	
	String user;
	
	String password;
	
	/**
	 * use default constructor and 'with...()' methods instead
	 * @param propertiesSupplier
	 */
	@Deprecated
	public LoginCallBuilder(PropertiesSupplier propertiesSupplier) {
		super(propertiesSupplier);
		withPassword(propertiesSupplier.getPassword());
		withUser(propertiesSupplier.getUser());
		withBackendUrl(propertiesSupplier.getBackendUrl());
	}
	
	public LoginCallBuilder() {
		super(null);
	}
	
	public LoginCallBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public LoginCallBuilder withUser(String user) {
		this.user = user;
		return this;
	}

	public LoginCallBuilder withBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
		return this;
	}

	public LoginCall build() {
		backendUrl = getPropertiesSupplier().getBackendUrl();
		Objects.requireNonNull(backendUrl,
				String.format("property not setted: '%s'", PropertiesSupplier.FRONTEND_BACKEND_URL));
		
		user = getPropertiesSupplier().getUser();
		Objects.requireNonNull(user,
				String.format("property not setted: '%s'", PropertiesSupplier.FRONTEND_USER));
		
		password = getPropertiesSupplier().getPassword();
		Objects.requireNonNull(password,
				String.format("property not setted: '%s'", PropertiesSupplier.FRONTEND_PASSWORD));

		final BasicWebTargetCall loginCall = new BasicWebTargetCallBuilder().withTarget(backendUrl)
				.withEndpoint("/login").withAcceptedResponseType(MediaType.APPLICATION_JSON)
				.addQueryParam("username", user).addQueryParam("password", password).build();

		return new LoginCall() {

			@Override
			public Token call() throws Exception {
				return loginCall.call().readEntity(Token.class);
			}
		};
	}
}
