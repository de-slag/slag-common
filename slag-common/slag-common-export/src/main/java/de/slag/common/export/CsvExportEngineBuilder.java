package de.slag.common.export;

import org.apache.commons.lang3.builder.Builder;

public class CsvExportEngineBuilder implements Builder<CsvExportEngine> {

	@Override
	public CsvExportEngine build() {
		return new CsvExportEngine();
	}

}
