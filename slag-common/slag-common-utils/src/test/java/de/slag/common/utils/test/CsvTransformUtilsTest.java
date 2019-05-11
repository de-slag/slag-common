package de.slag.common.utils.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

import org.apache.commons.csv.CSVRecord;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import de.slag.common.utils.CsvTransformUtils;
import de.slag.common.utils.CsvTransformUtils.CsvTransformException;
import de.slag.common.utils.CsvUtils;
import de.slag.common.utils.ResourceUtils;

public class CsvTransformUtilsTest {

	public static final String CSV_UTILS_TEST_CSV = "csv-utils-test.csv";

	public static final String JAVA_IO_TEMPDIR = System.getProperty("java.io.tmpdir");

	private static final Function<String, String> NO_TRANSFORMATION_FUNCTION = string -> {
		return string;
	};

	@Test
	public void transformTest() throws CsvTransformException, IOException {

		final String tmpFile = JAVA_IO_TEMPDIR + CSV_UTILS_TEST_CSV;

		final File fileFromResources = ResourceUtils.getFileFromResources(CSV_UTILS_TEST_CSV);
		final Function<String, String> umformatFunction = value -> {
			return "transformedValue: " + value;
		};
		CsvTransformUtils.umformat(fileFromResources.getAbsolutePath(), tmpFile, "X-AXIS", umformatFunction);
		
		// 
		
		final Collection<String> header = CsvUtils.getHeader(tmpFile);
		Assert.assertTrue(header.size() == 3);
		Assert.assertTrue(header.contains("X-AXIS"));
		Assert.assertTrue(header.contains("Y-AXIS"));
		Assert.assertTrue(header.contains("Z-AXIS"));
		
		
		
		
		final Collection<CSVRecord> records = CsvUtils.getRecords(tmpFile, header);
		Assert.assertThat(records.size(), is(4));
		Assert.assertTrue("extend this test", false);
		
	}
	
	private <T> Matcher<T> is(T value) {
		return Matchers.is(value);
	}

	public static String absolutePathFromResource(String filename) {
		return ResourceUtils.getFileFromResources(filename).getAbsolutePath();
	}

	@Test(expected = CsvTransformException.class)
	public void columnNotFoundTest() throws CsvTransformException {
		
		CsvTransformUtils.umformat(ResourceUtils.getFileFromResources(CSV_UTILS_TEST_CSV).getAbsolutePath(),
				"COLUMN_NOT_TO_FIND", "NO_MATTER_IN_THIS_TEST", NO_TRANSFORMATION_FUNCTION);
	}

	@Test
	public void xTest() throws CsvTransformException {
		CsvTransformUtils.renameHeader("", "", "", "");
	}
	
	

}
