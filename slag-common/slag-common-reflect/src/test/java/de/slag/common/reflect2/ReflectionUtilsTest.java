package de.slag.common.reflect2;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReflectionUtilsTest {
	
	private TestEntity testEntity;
	
	@BeforeEach
	void setUp() {
		testEntity = new TestEntity();
	}

	@Test
	void testDetermineAccessibleProperties() {
		Assertions.assertEquals(ReflectionUtils.determineAccessibleProperties(testEntity.getClass()).size(), 1);
	}

	@Test
	void testDetermineSetterClassOfQString() {
		Collection<Method> determineSetters = ReflectionUtils.determineSetters(testEntity.getClass());
		Assertions.assertEquals(1, determineSetters.size());
	}

	@Test
	void testDetermineGetterClassOfQString() {
		Collection<Method> determineGetters = ReflectionUtils.determineGetters(testEntity.getClass());
		Assertions.assertEquals(2,determineGetters.size());
	}

	@Test
	void testSetterNameToPropertyName() {
		Assertions.assertEquals("xyz", ReflectionUtils.setterNameToPropertyName("setXyz").get());
	}

	@Test
	void testGetterNameToPropertyName() {
		Assertions.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("getXyz").get());
		Assertions.assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("isXyz").get());
	}

	@Test
	void testDetermineSetterClassOfQ() {
		Assertions.assertTrue(ReflectionUtils.determineSetter(testEntity.getClass(), "name").isPresent());
	}

	@Test
	void testDetermineGetterClassOfQ() {
		Assertions.assertTrue(ReflectionUtils.determineGetter(testEntity.getClass(), "name").isPresent());
	}
	
	@Test
	void testDetermineClasses() {
		Assertions.assertEquals(2, ReflectionUtils.determineClasses(testEntity.getClass()).size());
	}
}
