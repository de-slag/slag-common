package de.slag.common.utils;

import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

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
		
		Assert.assertEquals(propertiesAsString, from2);
	}

}
