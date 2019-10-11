package de.slag.common.utils.reflect2;

import java.lang.reflect.Method;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReflectionUtilsTest {
	
	private TestEntity testEntity;
	
	@Before
	public void setUp() {
		testEntity = new TestEntity();
	}

	@Test
	public void testDetermineAccessibleProperties() {
		Assert.assertEquals(ReflectionUtils.determineAccessibleProperties(testEntity.getClass()).size(), 1);
	}

	@Test
	public void testDetermineSetterClassOfQString() {
		Collection<Method> determineSetters = ReflectionUtils.determineSetters(testEntity.getClass());
		Assert.assertEquals(1, determineSetters.size());
	}

	@Test
	public void testDetermineGetterClassOfQString() {
		Collection<Method> determineGetters = ReflectionUtils.determineGetters(testEntity.getClass());
		Assert.assertEquals(2,determineGetters.size());
	}

	@Test
	public void testSetterNameToPropertyName() {
		Assert.assertEquals("xyz", ReflectionUtils.setterNameToPropertyName("setXyz").get());
	}

	@Test
	public void testGetterNameToPropertyName() {
		Assert.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("getXyz").get());
		Assert.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("isXyz").get());
	}

	@Test
	public void testDetermineSetterClassOfQ() {
		Assert.assertTrue(ReflectionUtils.determineSetter(testEntity.getClass(), "name").isPresent());
	}

	@Test
	public void testDetermineGetterClassOfQ() {
		Assert.assertTrue(ReflectionUtils.determineGetter(testEntity.getClass(), "name").isPresent());
	}
	
	@Test
	public void testDetermineClasses() {
		Assert.assertEquals(2, ReflectionUtils.determineClasses(testEntity.getClass()).size());
	}
}
