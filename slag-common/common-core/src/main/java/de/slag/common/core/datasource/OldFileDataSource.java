package de.slag.common.core.datasource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;

import de.slag.common.base.BaseException;
import de.slag.common.base.datasource.DataSource;
import de.slag.common.util.CsvUtils;

@Deprecated
class OldFileDataSource implements DataSource<Map<String, String>> {

	private String path;

	private Long maxId;

	OldFileDataSource(String path) {
		File file = new File(path);
		if (!file.exists()) {
			throw new BaseException("file does not exists");
		}
		if (!file.isDirectory()) {
			throw new BaseException("file is not a directory");
		}

		this.path = path;

		final List<File> csvFiles = Arrays.asList(file.listFiles()).stream().filter(f -> f.getName().endsWith(".csv"))
				.collect(Collectors.toList());

		final Collection<Long> allIds = new TreeSet<>();

		csvFiles.forEach(csv -> {
			Collection<String> header = CsvUtils.getHeader(csv.getAbsolutePath());
			if (!header.contains("ID")) {
				return;
			}
			Collection<CSVRecord> records;
			try {
				records = CsvUtils.getRecords(csv.getAbsolutePath(), header);
			} catch (IOException e) {
				throw new BaseException("error reading csv records: " + csv.getAbsolutePath());
			}
			records.size();
			records.forEach(csvRecord -> {
				String idAsString = csvRecord.get("ID");
				if ("ID".equals(idAsString)) {
					return;
				}
				Long valueOf = Long.valueOf(idAsString);
				allIds.add(valueOf);
			});
		});

		if (allIds.isEmpty()) {
			maxId = 0L;
		} else {
			ArrayList<Long> arrayList = new ArrayList<Long>(allIds);
			Collections.sort(arrayList);
			Collections.reverse(arrayList);
			maxId = arrayList.get(0);
		}
	}

	Long getMaxId() {
		return maxId;
	}

	String getFileName(Map<String, String> valueMap) {
		String type = valueMap.get("TYPE");
		return path + "/" + type + ".csv";
	}

	File getFile(Map<String, String> valueMap) {
		final String fileName = getFileName(valueMap);
		final File file = new File(fileName);
		Set<String> keySet = valueMap.keySet();
		if (file.exists()) {
			Collection<String> header = CsvUtils.getHeader(fileName);
			if (!header.containsAll(keySet)) {
				throw new BaseException("file header does not match with keys");
			}
			return file;
		}
		try {
			CsvUtils.write(file, keySet, Collections.emptyList());
		} catch (IOException e) {
			throw new BaseException(e);
		}
		return file;

	}

	@Override
	public void create(Map<String, String> valueMap) {
		File file = getFile(valueMap);
		Collection<String> header = CsvUtils.getHeader(file.getAbsolutePath());
		Collection<CSVRecord> records;
		try {
			records = CsvUtils.getRecords(file.getAbsolutePath(), header, true);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		records.size();
//		CsvUtils.asList(records, header);
	}

	@Override
	public Map<String, String> read(Class<? extends Map<String, String>> type, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> update(Map<String, String> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Map<String, String> t) {
		// TODO Auto-generated method stub

	}

}
