package de.slag.common.config;

public interface FileStorageConfig extends DatasourceConfig {

	String getPath();

	@Override
	default String getIdentifier() {
		return FileStorageConfig.class.getSimpleName() + "-" + getPath();
	}

}
