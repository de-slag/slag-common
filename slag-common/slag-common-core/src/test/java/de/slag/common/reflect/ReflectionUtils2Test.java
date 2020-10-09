package de.slag.common.reflect;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ReflectionUtils2Test {

	@Test
	void test() {
		Collection<PropertyGetter> determineGetter = ReflectionUtils2.determineGetter(new ReflectionUtils2TestEntity());
		List<String> collect = determineGetter.stream().map(p -> p.getName()).collect(Collectors.toList());
		assertThat(Arrays.asList("id", "number", "valid"), containsInAnyOrder(collect));
	}

}
