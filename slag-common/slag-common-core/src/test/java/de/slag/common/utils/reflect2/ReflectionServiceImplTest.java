package de.slag.common.utils.reflect2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReflectionServiceImplTest {

	ReflectionServiceImpl reflectionServiceImpl = new ReflectionServiceImpl();

	TestPojo testPojo;

	@BeforeEach
	public void init() {
		testPojo = new TestPojo();
		testPojo.setSvar("TesT");
	}

	@Test
	public void testGetValue() {
		Optional<String> value = reflectionServiceImpl.getValue(testPojo, "svar", String.class);
		assertTrue(value.isPresent());
		assertEquals("TesT", value.get());
	}

	@Test
	public void testSetValue() {
		Objects.requireNonNull(testPojo, "TestPojo is null");
		reflectionServiceImpl.setValue(testPojo, "svar", "z-test");
		assertEquals("z-test", testPojo.getSvar());
	}

	@Test
	public void testGetType() {
		Class<?> type = reflectionServiceImpl.getType(testPojo, "svar");
		assertEquals(String.class, type);
	}

	private class TestPojo {
		private String svar;

		public String getSvar() {
			return svar;
		}

		public void setSvar(String svar) {
			this.svar = svar;
		}
	}

}
