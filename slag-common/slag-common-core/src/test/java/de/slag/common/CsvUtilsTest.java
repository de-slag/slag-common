package de.slag.common;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import de.slag.common.util.CsvUtils;
import de.slag.common.util.ResourceUtils;

public class CsvUtilsTest {

	private Collection<String> expected = Arrays.asList("X-AXIS","Y-AXIS","Z-AXIS");

	@Test
	public void testGetHeader() throws FileNotFoundException {
		
		final String absolutePath = ResourceUtils.getFileFromResources("csv-utils-test.csv").getAbsolutePath();
		
		final Collection<String> header = CsvUtils.getHeader(absolutePath);
		
		Assert.assertTrue(expected.size() == header.size());
		
		for (String columnHeader : header) {
			Assert.assertTrue(expected.contains(columnHeader));
		}
	}

}
