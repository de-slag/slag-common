package de.slag.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CsvUtilsTest {

	private static final Function<String, String> CSV_TEMP_FILE_DEPOSER = csvFileInResources -> {
		final Path tmpFile;
		try {
			tmpFile = Files.createTempFile("csv-utils-test", ".csv");
		} catch (IOException e) {
			throw new RuntimeException();
		}

		final File file = tmpFile.toFile();

		final InputStream is = CsvUtilsTest.class.getClassLoader().getResourceAsStream("csv/simple.csv");
		try (final FileOutputStream target = new FileOutputStream(file)) {
			byte[] buf = new byte[8192];
			int length;
			while ((length = is.read(buf)) > 0) {
				target.write(buf, 0, length);
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return file.getAbsolutePath();
	};

	private static String simpleCsvFileName;

	@BeforeAll
	static void setUpTempCsvFile() throws IOException {
		simpleCsvFileName = CSV_TEMP_FILE_DEPOSER.apply("csv/simple.csv");
	}

	@AfterAll
	static void shutDown() {
		new File(simpleCsvFileName).delete();
	}

	private Collection<CSVRecord> records = new ArrayList<>();

	private Set<String> header;

	public void setUp() throws IOException {
		File fileFromResources = ResourceUtils.getFileFromResources("core/util/csv-utils-test-valid.csv");
		assertTrue(fileFromResources.exists());
		Charset charset = Charset.forName("UTF-8");
		CSVParser parse = CSVParser.parse(fileFromResources.toPath(), charset, CSVFormat.DEFAULT);
		records.addAll(parse.getRecords());
		Map<String, Integer> headerMap = parse.getHeaderMap();
		header = headerMap.keySet();
	}

	@Test
	void testRemoveHeadLine() throws IOException {
		Collection<CSVRecord> csvRecords = CsvUtils.getRecords(simpleCsvFileName, Arrays.asList("NUMBER", "NAME"),
				true);
		assertTrue(Integer.valueOf(3).equals(csvRecords.size()));
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
