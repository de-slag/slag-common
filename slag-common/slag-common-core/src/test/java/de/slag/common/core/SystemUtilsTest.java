package de.slag.common.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SystemUtilsTest {

	@BeforeEach
	public void setUp() {
		System.setProperty("test-property", "test-value");
	}

	@Test
	void testGetUserHome() {
		String userHome = SystemUtils.getUserHome();
		assertTrue(new File(userHome).isDirectory());
	}

	@Test
	void testGetStartTime() {
		Date startTime = SystemUtils.getStartTime();
		assertNotNull(startTime);
		assertTrue(new Date(0).compareTo(startTime) < 1);
	}

	@Test
	void testGetSystemProperty() {
		Optional<String> systemProperty = SystemUtils.getSystemProperty("test-property");
		assertTrue(systemProperty.isPresent());
		assertEquals("test-value", systemProperty.get());

		assertTrue(SystemUtils.getSystemProperty("invalid-test-property").isEmpty());

	}

}
