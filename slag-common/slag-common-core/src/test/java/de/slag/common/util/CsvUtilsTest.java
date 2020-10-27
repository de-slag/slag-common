package de.slag.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CsvUtilsTest {

	private Collection<CSVRecord> records = new ArrayList<>();
	private Set<String> header;

	@BeforeEach
	public void setUp() throws IOException {
		File fileFromResources = ResourceUtils.getFileFromResources("core/util/csv-utils-test-valid.csv");
		assertTrue(fileFromResources.exists());
		Charset charset = Charset.forName("UTF-8");
		CSVParser parse = CSVParser.parse(fileFromResources.toPath(), charset, CSVFormat.DEFAULT);
		records.addAll(parse.getRecords());
		Map<String, Integer> headerMap = parse.getHeaderMap();
		header = headerMap.keySet();
	}

	@Disabled
	@Test
	void testToLines() {
		Collection<Collection<String>> lines = CsvUtils.toLines(header, records);
		assertNotNull(lines);
	}

	@Disabled
	@Test
	void testWriteFileCollectionOfStringCollectionOfCollectionOfString() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testWriteStringCollectionOfStringCollectionOfCollectionOfString() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testWriteCollectionOfCollectionOfStringString() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testWriteCollectionOfCollectionOfStringPath() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testWriteCollectionOfStringCollectionOfCollectionOfStringPath() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetRecordsStringCollectionOfStringBoolean() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetRecordsStringCollectionOfString() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetRecordsString() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetRecordsStringStringArray() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testValidate() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetHeader() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testWriteListOfListOfStringPath() {
		fail("Not yet implemented");
	}

}
