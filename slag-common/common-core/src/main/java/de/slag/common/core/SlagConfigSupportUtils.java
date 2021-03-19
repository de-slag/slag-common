package de.slag.common.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import de.slag.common.base.BaseException;

public final class SlagConfigSupportUtils {

	private static final String DEFAULT_DATABASE_CONFIG = "DefaultDatabaseConfig";

	private static final String DEFAULT_FILE_CONFIG = "DefaultFileConfig";

	public static <T extends DatasourceConfig> T getDefault(final Class<T> configType) {

		if (configType == DatabaseConfig.class) {
			return configType.cast(defaultDatabase());
		}

		if (configType == FileStorageConfig.class) {
			return configType.cast(defaultFileStorage());
		}
		throw new BaseException("Not supported: " + configType);
	}

	private static FileStorageConfig defaultFileStorage() {
		final Optional<String> defaultFileconfig = SystemUtils.getSystemProperty(DEFAULT_FILE_CONFIG);
		if (!defaultFileconfig.isPresent()) {
			throw new BaseException("Property not set: '" + DEFAULT_FILE_CONFIG + "'");
		}
		final Properties properties = PropertyUtils.fromFile(defaultFileconfig.get());

		final String path = (String) Objects.requireNonNull(properties.get("path"), "'path' not found");

		return new FileStorageConfig() {

			@Override
			public String getPath() {
				return path;
			}
		};
	}

	private static DatabaseConfig defaultDatabase() {
		final Optional<String> defaultDatabaseConfig = SystemUtils.getSystemProperty(DEFAULT_DATABASE_CONFIG);
		if (!defaultDatabaseConfig.isPresent()) {
			throw new BaseException("Property not set: '" + DEFAULT_DATABASE_CONFIG + "'");
		}
		final Properties properties = PropertyUtils.fromFile(defaultDatabaseConfig.get());

		final String username = (String) Objects.requireNonNull(properties.get("username"), "'username' not found");
		final String url = (String) Objects.requireNonNull(properties.get("url"), "'url' not found");
		final String password = (String) Objects.requireNonNull(properties.get("password"), "'password' not found");
		final String driverClass = (String) Objects.requireNonNull(properties.get("driverClass"),
				"'driverClass' not found");
		final String dialect = (String) Objects.requireNonNull(properties.get("dialect"), "'dialect' not found");

		return new DatabaseConfig() {

			@Override
			public String getUsername() {
				return username;
			}

			@Override
			public String getUrl() {
				return url;
			}

			@Override
			public String getPassword() {
				return password;
			}

			@Override
			public String getDriverClass() {
				return driverClass;
			}

			@Override
			public String getDialect() {
				return dialect;
			}
		};
	}

	public static Properties getConfigProperties() {
		final File userHome = org.apache.commons.lang3.SystemUtils.getUserHome();
		String absolutePath = userHome.getAbsolutePath();
		String file = "slag.cfg";
		String cfgFile = absolutePath + "/" + file;

		if (!new File(cfgFile).exists()) {
			try {
				new File(cfgFile).createNewFile();
			} catch (IOException e) {
				throw new BaseException(e);
			}
		}

		final Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(cfgFile)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		return properties;
	}

}
