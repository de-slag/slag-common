package de.slag.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.csv.CSVRecord;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import de.slag.common.util.CsvTransformUtils;
import de.slag.common.util.CsvUtils;
import de.slag.common.util.ResourceUtils;
import de.slag.common.util.CsvTransformUtils.CsvTransformException;

public class CsvTransformUtilsTest {

	public static final String CSV_UTILS_TEST_CSV = "csv-utils-test.csv";

	public static final String JAVA_IO_TEMPDIR = System.getProperty("java.io.tmpdir");

	private static final Function<String, String> NO_TRANSFORMATION_FUNCTION = string -> {
		return string;
	};

	@Test
	public void transformTest() throws CsvTransformException, IOException {

		final String tmpFile = JAVA_IO_TEMPDIR + "/"+ CSV_UTILS_TEST_CSV;

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

		final List<CSVRecord> recordsList = new ArrayList<>();
		recordsList.addAll(CsvUtils.getRecords(tmpFile, header));
		Assert.assertThat(CsvUtils.getRecords(tmpFile, header).size(), is(4));

		final CSVRecord csvRecord0 = recordsList.get(0);
		Assert.assertThat(csvRecord0.get(0), is("X-AXIS"));
		Assert.assertThat(csvRecord0.get(1), is("Y-AXIS"));
		Assert.assertThat(csvRecord0.get(2), is("Z-AXIS"));

		final CSVRecord csvRecord1 = recordsList.get(1);
		Assert.assertThat(csvRecord1.get(0), is("transformedValue: 11"));
		Assert.assertThat(csvRecord1.get(1), is("12"));
		Assert.assertThat(csvRecord1.get(2), is("13"));

		final CSVRecord csvRecord2 = recordsList.get(2);
		Assert.assertThat(csvRecord2.get(0), is("transformedValue: 14"));
		Assert.assertThat(csvRecord2.get(1), is("15"));
		Assert.assertThat(csvRecord2.get(2), is("16"));

		final CSVRecord csvRecord3 = recordsList.get(3);
		Assert.assertThat(csvRecord3.get(0), is("transformedValue: 17"));
		Assert.assertThat(csvRecord3.get(1), is("18"));
		Assert.assertThat(csvRecord3.get(2), is("19"));
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
	
	

}
