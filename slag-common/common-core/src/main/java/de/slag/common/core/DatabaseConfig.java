package de.slag.common.core;

public interface DatabaseConfig extends DatasourceConfig {

	String getDriverClass();

	String getUrl();

	String getUsername();

	String getPassword();

	String getDialect();
	
	@Override
	default String getIdentifier() {
		return DatabaseConfig.class.getSimpleName() + "-" + getUrl();
	}
}