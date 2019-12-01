package de.slag.common.testsupport;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestHibernateSupportBuilderTest {

	@Test
	public void testBuild() {
		TestHibernateSupportBuilder builder = new TestHibernateSupportBuilder();
		builder.registerClasses(Arrays.asList(TestEntity.class));
		Assert.assertNotNull(builder.build());
	}

}
