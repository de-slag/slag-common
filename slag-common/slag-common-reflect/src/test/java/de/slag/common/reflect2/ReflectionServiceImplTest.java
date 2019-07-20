package de.slag.common.reflect2;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReflectionServiceImplTest {

	ReflectionServiceImpl reflectionServiceImpl = new ReflectionServiceImpl();

	TestPojo testPojo;

	@BeforeEach
	public void init() {
		testPojo = new TestPojo();
		testPojo.setSvar("TesT");
	}

	@Test
	void testGetValue() {
		Optional<String> value = reflectionServiceImpl.getValue(testPojo, "svar", String.class);
		Assertions.assertTrue(value.isPresent());
		Assertions.assertEquals("TesT", value.get());
	}

	@Test
	void testSetValue() {
		reflectionServiceImpl.setValue(testPojo, "svar", "z-test");
		Assertions.assertEquals("z-test", testPojo.getSvar());
	}

	@Test
	void testGetType() {
		Class<?> type = reflectionServiceImpl.getType(testPojo, "svar");
		Assertions.assertEquals(String.class, type);
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
