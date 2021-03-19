package de.slag.basic.frontend.call.builder;

import java.util.Optional;

import de.slag.basic.frontend.Builder;
import de.slag.basic.frontend.PropertiesSupplier;
import de.slag.basic.frontend.call.LoginCall;
import de.slag.basic.frontend.call.SecureLoginCall;
import de.slag.basic.model.Token;

public class SecureLoginCallBuilder implements Builder<SecureLoginCall> {

	private final PropertiesSupplier propertiesSupplier;

	public SecureLoginCallBuilder(PropertiesSupplier propertiesSupplier) {
		super();
		this.propertiesSupplier = propertiesSupplier;
	}

	@Override
	public SecureLoginCall build() {
		final LoginCall build = new LoginCallBuilder(propertiesSupplier).build();
		return new SecureLoginCall() {

			@Override
			public Optional<Token> call() throws Exception {
				final Token token = build.call();
				if (token == null || token.getTokenString() == null) {
					return Optional.empty();
				}

				return Optional.of(token);
			}
		};
	}

}
