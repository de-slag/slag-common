package de.slag.common.api;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface CsvExportService {

	void export(File toFile, List<String> attributes, Collection<?> data);

}
