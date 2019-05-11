package de.slag.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.base.BaseException;

public class CsvUtils {

	private static final Log LOG = LogFactory.getLog(CsvUtils.class);

	private static final char DEFAULT_DELIMITER = ';';

	public static Collection<Collection<String>> toLines(Collection<String> header, Collection<CSVRecord> records) {
		return records.stream().map(r -> header.stream().map(h -> r.get(h)).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	public static void write(final File file, Collection<String> header, Collection<Collection<String>> lines)
			throws IOException {
		write(header, lines, Paths.get(file.getAbsolutePath()));
	}

	public static void write(final String filename, Collection<String> header, Collection<Collection<String>> lines)
			throws IOException {
		write(header, lines, Paths.get(filename));
	}
	
	public static void write(Collection<Collection<String>> allLines, final String filename)
			throws IOException {
		write(allLines, Paths.get(filename));
		
	}
	
	public static void write(Collection<Collection<String>> allLines, final Path path)
			throws IOException {
		List<Collection<String>> asList = new ArrayList<>(allLines);
		Collection<String> header = asList.get(0);
		asList.remove(0);
		ArrayList<Collection<String>> otherLines = new ArrayList<Collection<String>>(asList);
		
		write(header, otherLines, path);
		
	}

	public static void write(Collection<String> header, Collection<Collection<String>> lines, final Path path)
			throws IOException {

		final BufferedWriter writer = Files.newBufferedWriter(path);
		final CSVFormat format = CSVFormat.newFormat(DEFAULT_DELIMITER).withHeader(header.toArray(new String[0]))
				.withRecordSeparator("\r\n");
		final CSVPrinter csvPrinter = new CSVPrinter(writer, format);
		for (Collection<String> collection : lines) {
			csvPrinter.printRecord(collection);
		}
		csvPrinter.flush();
		csvPrinter.close();
	}

	public static Collection<CSVRecord> getRecords(final String filename, Collection<String> header)
			throws IOException {
		return getRecords(filename, header.toArray(new String[0]));
	}

	/**
	 * ...first record as header
	 */

	public static Collection<CSVRecord> getRecords(final String filename) throws IOException {
		return getRecords(filename, new String[0]);
	}

	public static Collection<CSVRecord> getRecords(final String filename, String... header) throws IOException {
		final Path path = Paths.get(filename);
		if (!Files.exists(path)) {
			throw new BaseException("file not exists: " + filename);
		}

		final BufferedReader in = Files.newBufferedReader(path);
		final CSVFormat format;
		if (header != null && header.length > 0) {
			format = CSVFormat.newFormat(DEFAULT_DELIMITER).withHeader(header);
		} else {
			format = CSVFormat.newFormat(DEFAULT_DELIMITER).withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
		}

		final CSVParser parse = format.parse(in);
		final List<CSVRecord> records = parse.getRecords();
		validate(records, header);
		return records;
	}

	public static void validate(final List<CSVRecord> records, String... header) {
		final Collection<String> s = new ArrayList<>();
		for (int record = 0; record < records.size(); record++) {
			for (int col = 0; col < header.length; col++) {
				final String columnName = header[col];
				final CSVRecord csvRecord = records.get(record);
				try {
					csvRecord.get(columnName);
				} catch (IllegalArgumentException e) {
					LOG.debug(e);
					s.add("record: " + record + ", col: " + col + ": " + e.getMessage());
				}
			}
		}
		if (s.isEmpty()) {
			return;
		}
		throw new RuntimeException(String.join("\n", s));
	}

	public static Collection<String> getHeader(String filename) throws FileNotFoundException {
		final Path path = Paths.get(filename);
		if (!Files.exists(path)) {
			throw new FileNotFoundException(filename);
		}
		final BufferedReader in;
		try {
			in = Files.newBufferedReader(path);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		final CSVFormat format = CSVFormat.newFormat(DEFAULT_DELIMITER);
		CSVParser parse;
		try {
			parse = format.parse(in);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		List<CSVRecord> records;
		try {
			records = parse.getRecords();
		} catch (IOException e) {
			throw new BaseException(e);
		}
		
		final CSVRecord csvRecord = records.get(0);
		final Collection<String> header = new ArrayList<String>();
		csvRecord.forEach(field -> header.add(field));
		return header;
		
		
	}
}
