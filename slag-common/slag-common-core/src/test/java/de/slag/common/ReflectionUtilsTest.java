package de.slag.common;

import java.lang.reflect.Method;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import de.slag.common.utils.reflect2.ReflectionUtils;

public class ReflectionUtilsTest {

	private TestEntity testEntity;

	@BeforeEach
	public void setUp() {
		testEntity = new TestEntity();
	}

	@Test
	public void testDetermineAccessibleProperties() {
		assertEquals(ReflectionUtils.determineAccessibleProperties(testEntity.getClass()).size(), 1);
	}

	@Test
	public void testDetermineSetterClassOfQString() {
		Collection<Method> determineSetters = ReflectionUtils.determineSetters(testEntity.getClass());
		assertEquals(1, determineSetters.size());
	}

	@Test
	public void testDetermineGetterClassOfQString() {
		Collection<Method> determineGetters = ReflectionUtils.determineGetters(testEntity.getClass());
		assertEquals(2, determineGetters.size());
	}

	@Test
	public void testSetterNameToPropertyName() {
		assertEquals("xyz", ReflectionUtils.setterNameToPropertyName("setXyz").get());
	}

	@Test
	public void testGetterNameToPropertyName() {
		assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("getXyz").get());
		assertEquals("xyz", ReflectionUtils.getterNameToPropertyName("isXyz").get());
	}

	@Test
	public void testDetermineSetterClassOfQ() {
		assertTrue(ReflectionUtils.determineSetter(testEntity.getClass(), "name").isPresent());
	}

	@Test
	public void testDetermineGetterClassOfQ() {
		assertTrue(ReflectionUtils.determineGetter(testEntity.getClass(), "name").isPresent());
	}

	@Test
	public void testDetermineClasses() {
		assertEquals(2, ReflectionUtils.determineClasses(testEntity.getClass()).size());
	}
}
