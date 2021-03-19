package de.slag.core;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.slag.common.core.PropertyUtils;

public class PropertyUtilsTest {
	
	private String propertiesAsString = "a=1\nb=2";

	@Test
	public void testFromString() {
		Properties properties = PropertyUtils.from(propertiesAsString);
		assertThat(properties.get("a"), Matchers.is("1"));
		assertThat(properties.get("b"), Matchers.is("2"));
	}

	@Test
	public void testFromProperties() {
		Properties from = PropertyUtils.from(propertiesAsString);
		String from2 = PropertyUtils.from(from);
		
		assertEquals(propertiesAsString, from2);
	}

}
