package de.slag.common.utils.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import de.slag.common.base.BaseException;
import de.slag.common.utils.CsvTransformUtils;
import de.slag.common.utils.CsvUtils;
import de.slag.common.utils.ResourceUtils;

public class CsvTransformUtilsTest {

	private static final String CSV_UTILS_TEST_CSV = "csv-utils-test.csv";

	private static final String JAVA_IO_TEMPDIR = System.getProperty("java.io.tmpdir");

	private static final Function<String, String> NO_TRANSFORMATION_FUNCTION = string -> {
		return string;
	};

	@Test
	public void transformTest() throws FileNotFoundException {

		final String tmpFile = JAVA_IO_TEMPDIR + CSV_UTILS_TEST_CSV;

		final File fileFromResources = ResourceUtils.getFileFromResources(CSV_UTILS_TEST_CSV);
		final Function<String, String> umformatFunction = value -> {
			return "transformedValue: " + value;
		};
		CsvTransformUtils.umformat(fileFromResources.getAbsolutePath(), tmpFile, "X-AXIS", umformatFunction);
		Assert.assertTrue("kommt noch Mist bei raus", false);
	}

	private String absolutePathFromResource(String filename) {
		return ResourceUtils.getFileFromResources(filename).getAbsolutePath();
	}

	@Test(expected = BaseException.class)
	public void columnNotFoundTest() throws FileNotFoundException {
		CsvTransformUtils.umformat(ResourceUtils.getFileFromResources(CSV_UTILS_TEST_CSV).getAbsolutePath(),
				"COLUMN_NOT_TO_FIND", "NO_MATTER_IN_THIS_TEST", NO_TRANSFORMATION_FUNCTION);
	}

	@Test
	public void renameHeaderTest() throws FileNotFoundException {
		String inputFileName = absolutePathFromResource(CSV_UTILS_TEST_CSV);
		String outputFileName = JAVA_IO_TEMPDIR + "rename_header" + CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "A-AXIS";
		CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo);
		final Collection<String> header = CsvUtils.getHeader(outputFileName);
		Assert.assertTrue(header.contains(headerTo));
		Assert.assertTrue(!header.contains(headerFrom));
	}

	@Test(expected = BaseException.class)
	public void renameHeaderFailTest() throws FileNotFoundException {
		String inputFileName = absolutePathFromResource(CSV_UTILS_TEST_CSV);
		String outputFileName = JAVA_IO_TEMPDIR + "rename_header" + CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "Y-AXIS";
		CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo);
		final Collection<String> header = CsvUtils.getHeader(outputFileName);
		Assert.assertTrue(header.contains(headerTo));
		Assert.assertTrue(!header.contains(headerFrom));
	}

}
