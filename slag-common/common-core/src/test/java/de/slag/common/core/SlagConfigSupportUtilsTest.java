package de.slag.common.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.base.BaseException;
import de.slag.common.util.ResourceUtils;

class SlagConfigSupportUtilsTest {

	@BeforeEach
	public void setUp() {
		File testDbProperties = ResourceUtils.getFileFromResources("core/test-databaseconfig.properties");
		System.setProperty("DefaultDatabaseConfig", testDbProperties.getAbsolutePath());

		File testFileconfigProperties = ResourceUtils.getFileFromResources("core/test-fileconfig.properties");
		System.setProperty("DefaultFileConfig", testFileconfigProperties.getAbsolutePath());
	}

	@Test
	void testGetDefaultDatabase() {
		assertThrows(BaseException.class, () -> SlagConfigSupportUtils.getDefault(null));
		DatasourceConfig dsConfig = SlagConfigSupportUtils.getDefault(DatabaseConfig.class);
		assertNotNull(dsConfig);
	}

	@Test
	void testGetDefaultFilestorage() {
		assertThrows(BaseException.class, () -> SlagConfigSupportUtils.getDefault(null));
		DatasourceConfig dsConfig = SlagConfigSupportUtils.getDefault(FileStorageConfig.class);
		assertNotNull(dsConfig);
	}

	@Test
	void testGetConfigProperties() {
		Properties configProperties = SlagConfigSupportUtils.getConfigProperties();
		assertNotNull(configProperties);
	}

}
