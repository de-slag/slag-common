package de.slag.common.utils.migration;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import de.slag.common.core.migration.MigrationDataStore;

public class DataMigrator<F, T> {

	public Case<F, T> createCase(MigrationDataStore<F> from, MigrationDataStore<T> to, Function<F, T> transformator) {
		return new Case<F, T>() {

			@Override
			public Function<F, T> transformator() {
				return transformator;

			}

			@Override
			public MigrationDataStore<T> toStore() {

				return to;
			}

			@Override
			public MigrationDataStore<F> fromStore() {
				return from;

			}
		};
	}

	public void migrate(Case<F, T> migCase) {

		Objects.requireNonNull(migCase);
		Objects.requireNonNull(migCase.fromStore());
		Objects.requireNonNull(migCase.toStore());
		Objects.requireNonNull(migCase.transformator());

		final Collection<String> tables = migCase.fromStore().getTables();
		tables.forEach(table -> migrate(table, migCase));

		migCase.toStore().flush();
	}

	private void migrate(String table, Case<F, T> migCase) {
		final Collection<Long> ids = migCase.fromStore().getIds(table);
		ids.forEach(id -> migrate(id, table, migCase));
	}

	private void migrate(Long id, String table, Case<F, T> migCase) {
		final F fromObject = migCase.fromStore().get(table, id);
		final T toObject = migCase.transformator().apply(fromObject);
		migCase.toStore().store(toObject);
	}

	public interface Case<F, T> {

		MigrationDataStore<F> fromStore();

		MigrationDataStore<T> toStore();

		Function<F, T> transformator();
	}
}
