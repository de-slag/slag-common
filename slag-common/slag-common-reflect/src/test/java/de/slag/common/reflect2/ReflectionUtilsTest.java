package de.slag.common.reflect2;

import java.lang.reflect.Method;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class ReflectionUtilsTest {
	
	private TestEntity testEntity;
	
	@Before
	void setUp() {
		testEntity = new TestEntity();
	}

	@Test
	void testDetermineAccessibleProperties() {
		Assert.assertEquals(ReflectionUtils.determineAccessibleProperties(testEntity.getClass()).size(), 1);
	}

	@Test
	void testDetermineSetterClassOfQString() {
		Collection<Method> determineSetters = ReflectionUtils.determineSetters(testEntity.getClass());
		Assert.assertEquals(1, determineSetters.size());
	}

	@Test
	void testDetermineGetterClassOfQString() {
		Collection<Method> determineGetters = ReflectionUtils.determineGetters(testEntity.getClass());
		Assert.assertEquals(2,determineGetters.size());
	}

	@Test
	void testSetterNameToPropertyName() {
		Assert.assertEquals("xyz", ReflectionUtils.setterNameToPropertyName("setXyz").get());
	}

	@Test
	void testGetterNameToPropertyName() {
		Assert.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("getXyz").get());
		Assert.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("isXyz").get());
	}

	@Test
	void testDetermineSetterClassOfQ() {
		Assert.assertTrue(ReflectionUtils.determineSetter(testEntity.getClass(), "name").isPresent());
	}

	@Test
	void testDetermineGetterClassOfQ() {
		Assert.assertTrue(ReflectionUtils.determineGetter(testEntity.getClass(), "name").isPresent());
	}
	
	@Test
	void testDetermineClasses() {
		Assert.assertEquals(2, ReflectionUtils.determineClasses(testEntity.getClass()).size());
	}
}
