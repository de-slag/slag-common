package de.slag.common.reflect2;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class ReflectionServiceImplTest {

	ReflectionServiceImpl reflectionServiceImpl = new ReflectionServiceImpl();

	TestPojo testPojo;

	@Before
	public void init() {
		testPojo = new TestPojo();
		testPojo.setSvar("TesT");
	}

	@Test
	void testGetValue() {
		Optional<String> value = reflectionServiceImpl.getValue(testPojo, "svar", String.class);
		Assert.assertTrue(value.isPresent());
		Assert.assertEquals("TesT", value.get());
	}

	@Test
	void testSetValue() {
		reflectionServiceImpl.setValue(testPojo, "svar", "z-test");
		Assert.assertEquals("z-test", testPojo.getSvar());
	}

	@Test
	void testGetType() {
		Class<?> type = reflectionServiceImpl.getType(testPojo, "svar");
		Assert.assertEquals(String.class, type);
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
