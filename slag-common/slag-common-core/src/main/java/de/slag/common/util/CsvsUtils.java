package de.slag.common.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

public class CsvsUtils {

	public static Map<String, Collection<CSVRecord>> get(File baseDir) {
		return get(baseDir.getAbsolutePath());
	}

	public static Map<String, Collection<CSVRecord>> get(String baseDir) {
		final HashMap<String, Collection<CSVRecord>> result = new HashMap<>();
		final File file = new File(baseDir);
		final File[] listFiles = file.listFiles();
		Arrays.asList(listFiles).forEach(f -> {

			final String absolutePath = f.getAbsolutePath();
			final Collection<CSVRecord> records = CsvUtils.getRecords(absolutePath);
			result.put(absolutePath, records);
			
		});

		return result;

	}

}
