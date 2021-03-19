package de.slag.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import de.slag.common.util.CsvUtils;
import de.slag.common.util.CsvUtilsOption;
import de.slag.common.util.ResourceUtils;

public class CsvUtilsTest {

	private static final String CSV_UTILS_TEST_CSV = "csv-utils-test.csv";

	private Collection<String> expected = Arrays.asList("X-AXIS", "Y-AXIS", "Z-AXIS");

	@Test
	public void readRecordsNoHeaderIgnoreEmptyLines() {
		final String csvFileName = getAbsolutePathOfResource("csv-utils-test-ends-with-empty-line.csv");
		Collection<CSVRecord> records = CsvUtils.readRecords(csvFileName);
		assertNotNull(records);
		assertEquals(3, records.size());
		records.forEach(rec -> {
			rec.forEach(field -> {
				assertFalse(expected.contains(field));
			});
		});
	}

	@Test
	public void testGetHeader() throws FileNotFoundException {

		final String absolutePath = getAbsolutePathOfResource(CSV_UTILS_TEST_CSV);

		final Collection<String> header = CsvUtils.getHeader(absolutePath);

		assertTrue(expected.size() == header.size());

		for (String columnHeader : header) {
			assertTrue(expected.contains(columnHeader));
		}
	}

	@Test
	public void getRecordsTest() throws IOException {
		String absolutePathOfResource = getAbsolutePathOfResource(CSV_UTILS_TEST_CSV);
		Collection<CSVRecord> records = CsvUtils.getRecords(absolutePathOfResource, expected);
		assertEquals(4, records.size());
	}

	@Test
	public void getRecordsIgnoreHeaderTest() throws IOException {
		String absolutePathOfResource = getAbsolutePathOfResource(CSV_UTILS_TEST_CSV);
		Collection<CSVRecord> records = CsvUtils.getRecords(absolutePathOfResource, expected, true);
		assertEquals(3, records.size());
	}

	private String getAbsolutePathOfResource(String filename) {
		final String absolutePath = ResourceUtils.getFileFromResources(filename).getAbsolutePath();
		return absolutePath;
	}

}
