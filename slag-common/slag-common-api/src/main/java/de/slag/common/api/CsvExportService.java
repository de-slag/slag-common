package de.slag.common.api;

import java.io.File;
import java.util.Collection;

public interface CsvExportService {

	void export(File toFile, Collection<String> attributes, Collection<?> data);

}
