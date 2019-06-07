package de.slag.common.utils.migration;

import java.util.function.Function;

import de.slag.common.base.migration.MigrationDataStore;
import de.slag.common.utils.migration.DataMigrator.Case;

public class DataMigrationUtils {

	public static <F, T> void migrate(MigrationDataStore<F> from, MigrationDataStore<T> to,
			Function<F, T> transformator) {
		
		final DataMigrator<F, T> dataMigrator = new DataMigrator<F, T>();
		final Case<F, T> createCase = dataMigrator.createCase(from, to, transformator);
		dataMigrator.migrate(createCase);
		
	}
}
