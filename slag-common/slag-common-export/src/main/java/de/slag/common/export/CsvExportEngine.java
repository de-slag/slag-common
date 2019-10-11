package de.slag.common.export;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class CsvExportEngine {

	private CsvExportServiceImpl service;

	CsvExportEngine() {
		service = new CsvExportServiceImpl();
	}

	void export(File toFile, List<String> attributes, Collection<?> data) {
		service.export(toFile, attributes, data);
	}

}
