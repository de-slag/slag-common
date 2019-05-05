package de.slag.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.base.BaseException;

public class CsvTransformUtils {
	
	private static final Log LOG = LogFactory.getLog(CsvTransformUtils.class);

	public static void umformat(String inputFileName, String outputFileName, String column,
			Function<String, String> umformat) throws FileNotFoundException {
		final Collection<String> header = CsvUtils.getHeader(inputFileName);
		if (!header.contains(column)) {
			throw new BaseException("column not found in header: " + column + "(" + header + ")");
		}
		Collection<CSVRecord> records = getRecords(inputFileName);

		Collection<Collection<String>> lines = new ArrayList<>();
		for (CSVRecord csvRecord : records) {
			final List<String> currentLine = new ArrayList<>();
			for (String currentColumn : header) {
				final String cellValue = csvRecord.get(currentColumn);
				if(!currentColumn.equals(column)) {
					currentLine.add(cellValue);
					continue;
				}
				
				currentLine.add(umformat.apply(cellValue));
				
				lines.add(currentLine);
			}
		}

		write(outputFileName, header, lines);
	}

	private static void write(String outputFileName, final Collection<String> header,
			Collection<Collection<String>> lines) {
		try {
			CsvUtils.write(header, lines, Paths.get(outputFileName));
		} catch (IOException e) {
			throw new BaseException(e);
		}
	}

	private static Collection<CSVRecord> getRecords(String inputFileName) {
		Collection<CSVRecord> records;
		try {
			records = CsvUtils.getRecords(inputFileName);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		return records;
	}

	public static void renameHeader(String inputFileName, String outputFileName, String headerFrom, String headerTo) {
		// TODO Auto-generated method stub
		
	}

}
