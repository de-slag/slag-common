package de.slag.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvUtilsTest {
	
	private Collection<CSVRecord> records = new ArrayList<>();
	private Set<String> header;
	
	@BeforeEach
	public void setUp() throws IOException {
		File fileFromResources = ResourceUtils.getFileFromResources("core/util/csv-utils-test-valid.csv");
		assertTrue(fileFromResources.exists());
		Charset charset = Charset.forName("UTF-8");
		CSVParser parse = CSVParser.parse(fileFromResources.toPath(),charset, CSVFormat.DEFAULT);
		records.addAll(parse.getRecords());
		Map<String, Integer> headerMap = parse.getHeaderMap();
		header = headerMap.keySet();
	}

	@Test
	void testToLines() {
		Collection<Collection<String>> lines = CsvUtils.toLines(header, records);
		assertNotNull(lines);
	}

	@Test
	void testWriteFileCollectionOfStringCollectionOfCollectionOfString() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteStringCollectionOfStringCollectionOfCollectionOfString() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteCollectionOfCollectionOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteCollectionOfCollectionOfStringPath() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteCollectionOfStringCollectionOfCollectionOfStringPath() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecordsStringCollectionOfStringBoolean() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecordsStringCollectionOfString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecordsString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecordsStringStringArray() {
		fail("Not yet implemented");
	}

	@Test
	void testValidate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHeader() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteListOfListOfStringPath() {
		fail("Not yet implemented");
	}

}
