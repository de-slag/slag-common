package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.Response;

import de.slag.basic.frontend.PropertiesSupplier;

public abstract class AbstractBasicCallBuilder {

	private PropertiesSupplier propertiesSupplier;

	public AbstractBasicCallBuilder(PropertiesSupplier propertiesSupplier) {
		super();
		this.propertiesSupplier = propertiesSupplier;
	}

	protected PropertiesSupplier getPropertiesSupplier() {
		return propertiesSupplier;
	}
}
