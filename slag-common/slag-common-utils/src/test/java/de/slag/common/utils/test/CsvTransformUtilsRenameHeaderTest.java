package de.slag.common.utils.test;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import de.slag.common.utils.CsvTransformUtils;
import de.slag.common.utils.CsvTransformUtils.CsvTransformException;
import de.slag.common.utils.CsvUtils;

public class CsvTransformUtilsRenameHeaderTest {

	@Test
	public void renameHeaderTest() throws CsvTransformException, FileNotFoundException {
		String inputFileName = CsvTransformUtilsTest.absolutePathFromResource(CsvTransformUtilsTest.CSV_UTILS_TEST_CSV);
		String outputFileName = CsvTransformUtilsTest.JAVA_IO_TEMPDIR + "rename_header"
				+ CsvTransformUtilsTest.CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "A-AXIS";
		CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo);
		
		final Collection<String> header = CsvUtils.getHeader(outputFileName);
		
		Assert.assertTrue(header.contains(headerTo));
		Assert.assertTrue(!header.contains(headerFrom));
	}

	@Test(expected = CsvTransformException.class)
	public void renameHeaderFailTest() throws CsvTransformException, FileNotFoundException {
		String inputFileName = CsvTransformUtilsTest.absolutePathFromResource(CsvTransformUtilsTest.CSV_UTILS_TEST_CSV);
		String outputFileName = CsvTransformUtilsTest.JAVA_IO_TEMPDIR + "rename_header"
				+ CsvTransformUtilsTest.CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "Y-AXIS";
		CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo);
		final Collection<String> header = CsvUtils.getHeader(outputFileName);
		Assert.assertTrue(header.contains(headerTo));
		Assert.assertTrue(!header.contains(headerFrom));
	}

}
