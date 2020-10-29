package de.slag.common.reflect;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ReflectionUtils2Test {

	@Disabled
	@Test
	void determineGetterTest() {
		Collection<PropertyGetter> determineGetter = ReflectionUtils2.determineGetter(new ReflectionUtils2TestEntity());
		List<String> collect = determineGetter.stream().map(p -> p.getName()).collect(Collectors.toList());
		assertThat(Arrays.asList("id", "number", "valid"), containsInAnyOrder(collect));
	}
	

	@Disabled
	@Test
	void determinePropertyNamesTest() {
		Collection<String> determinePropertyNames = ReflectionUtils2.determinePropertyNames(new ReflectionUtils2TestEntity());
		List<String> expected = Arrays.asList("id","number","valid");
		assertTrue(determinePropertyNames.containsAll(expected));
		assertTrue(expected.containsAll(determinePropertyNames));
	}
	

	@Disabled
	@Test
	void determineSetterTest() {
		Collection<PropertySetter> determineSetter = ReflectionUtils2.determineSetter(new ReflectionUtils2TestEntity());
		List<String> collect = determineSetter.stream().map(p -> p.getName()).collect(Collectors.toList());
		assertThat(Arrays.asList("id", "number", "valid"), containsInAnyOrder(collect));
	}

}
