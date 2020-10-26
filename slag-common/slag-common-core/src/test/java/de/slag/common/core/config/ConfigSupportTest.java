package de.slag.common.core.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.util.ResourceUtils;

class ConfigSupportTest {

	private String testConfigFileName;

	@BeforeEach
	void setUp() throws URISyntaxException {
		testConfigFileName = ResourceUtils.getFileFromResources("config/testconfig.properties").getAbsolutePath();
	}

	@Test
	void correctSystemPropertyTest() {
		System.setProperty("config.file", testConfigFileName);
		ConfigSupport configSupport = new ConfigSupport();
		Optional<String> value = configSupport.getValue("test");
		assertEquals(Optional.of("this is a test"), value);
	}

	@Test
	void wrongSystemPropertyTest() {
		System.setProperty("config.file", "something-wrong");
		new ConfigSupport();
	}

	@Test
	void noSystemPropertyTest() {
		ConfigSupport configSupport = new ConfigSupport();
	}

}
