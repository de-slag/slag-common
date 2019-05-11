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
			Function<String, String> umformat) throws CsvTransformException {
		Collection<String> header;
		try {
			header = CsvUtils.getHeader(inputFileName);
		} catch (FileNotFoundException e) {
			throw new CsvTransformException(e);
		}
		if (!header.contains(column)) {
			throw new CsvTransformException("column not found in header: " + column + "(" + header + ")");
		}
		Collection<CSVRecord> records = getRecords(inputFileName);

		Collection<Collection<String>> lines = new ArrayList<>();
		for (CSVRecord csvRecord : records) {
			final List<String> currentLine = new ArrayList<>();
			for (String currentColumn : header) {
				final String cellValue = csvRecord.get(currentColumn);
				if (!currentColumn.equals(column)) {
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
			Collection<Collection<String>> lines) throws CsvTransformException {
		try {
			CsvUtils.write(header, lines, Paths.get(outputFileName));
		} catch (IOException e) {
			throw new CsvTransformException(e);
		}
	}

	private static Collection<CSVRecord> getRecords(String inputFileName) throws CsvTransformException {
		Collection<CSVRecord> records;
		try {
			records = CsvUtils.getRecords(inputFileName);
		} catch (IOException e) {
			throw new CsvTransformException(e);
		}
		return records;
	}

	public static void renameHeader(String inputFileName, String outputFileName, String headerFrom, String headerTo)
			throws CsvTransformException {

		final Collection<String> header = getHeader(inputFileName);

		if (header.contains(headerTo)) {
			throw new CsvTransformException("header cannot be renamed. colum already exists: " + headerTo);
		}

		final List<CSVRecord> records = new ArrayList<>();
		try {
			records.addAll(CsvUtils.getRecords(inputFileName));
		} catch (IOException e) {
			throw new CsvTransformException(e);
		}

		final Collection<Collection<String>> newContent = new ArrayList<>();
		CSVRecord csvRecord = records.get(0);
		final ArrayList<String> newHeadline = new ArrayList<>();
		for (String oldColumnHead : csvRecord) {
			if (!oldColumnHead.equals(headerFrom)) {
				newHeadline.add(oldColumnHead);
				return;
			}
			newHeadline.add(headerTo);
		}
		newContent.add(newHeadline);

		for (int line = 1; line < records.size(); line++) {
			final CSVRecord csvLine = records.get(line);
			final Collection<String> arrayList = new ArrayList<>();
			//csvLine.forEach(current);
		}
		
		throw new BaseException("");

	}

	private static Collection<String> getHeader(String inputFileName) throws CsvTransformException {
		try {
			return CsvUtils.getHeader(inputFileName);
		} catch (FileNotFoundException e) {
			throw new CsvTransformException(e);
		}
	}

	public static class CsvTransformException extends Exception {

		private static final long serialVersionUID = 1L;

		public CsvTransformException(String string) {
			super(string);
		}

		public CsvTransformException(Throwable e) {
			super(e);
		}
	}

	public static List<List<String>> copyAllExcept(List<CSVRecord> records, Integer skipCol, Integer skipLine) {
		return null;
	}

	public List<List<String>> toStringList(List<CSVRecord> records) {
		final List<List<String>> list = new ArrayList<>();
		for (int line = 0; line < records.size(); line++) {

		}

		return list;
	}
}
