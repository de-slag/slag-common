package de.slag.common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.slag.common.base.BaseException;
import de.slag.common.util.CsvTransformUtils;
import de.slag.common.util.CsvTransformUtils.CsvTransformException;
import de.slag.common.util.CsvUtils;

public class CsvTransformUtilsRenameHeaderTest {

	@Test
	public void renameHeaderTest() throws CsvTransformException, FileNotFoundException {
		String inputFileName = CsvTransformUtilsTest.absolutePathFromResource(CsvTransformUtilsTest.CSV_UTILS_TEST_CSV);
		String outputFileName = CsvTransformUtilsTest.JAVA_IO_TEMPDIR + "/rename_header"
				+ CsvTransformUtilsTest.CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "other-AXIS";
		CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo);

		final Collection<String> header = CsvUtils.getHeader(outputFileName);

		assertTrue(header.contains(headerTo));
		assertTrue(!header.contains(headerFrom));
	}

	@Test
	public void renameHeaderFailTest() throws CsvTransformException, FileNotFoundException {
		String inputFileName = CsvTransformUtilsTest.absolutePathFromResource(CsvTransformUtilsTest.CSV_UTILS_TEST_CSV);
		String outputFileName = CsvTransformUtilsTest.JAVA_IO_TEMPDIR + "rename_header"
				+ CsvTransformUtilsTest.CSV_UTILS_TEST_CSV;
		String headerFrom = "X-AXIS";
		String headerTo = "Y-AXIS";
		assertThrows(BaseException.class,
				() -> CsvTransformUtils.renameHeader(inputFileName, outputFileName, headerFrom, headerTo));
	}

}
