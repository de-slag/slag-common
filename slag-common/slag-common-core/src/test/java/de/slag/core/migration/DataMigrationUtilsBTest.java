package de.slag.core.migration;

import static org.hamcrest.MatcherAssert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.slag.common.core.migration.DataMigrationUtils;
import de.slag.common.core.migration.MigrationDataStore;

public class DataMigrationUtilsBTest {

	private MigrationDataStore<TestEntityA> from;

	private MigrationDataStore<String> to;

	private Function<TestEntityA, String> transformator;

	private StringProcessor processor;

	@Test
	public void test() {
		DataMigrationUtils.migrate(from, to, transformator);
		assertThat(processor.get(), Matchers.is("TestEntityA [name=Mr.X]"));
	}

	@BeforeEach
	public void setUp() throws Exception {
		from = new MigrationDataStore<DataMigrationUtilsBTest.TestEntityA>() {

			@Override
			public Collection<String> getTables() {
				return Collections.singletonList("user");
			}

			@Override
			public Collection<Long> getIds(String table) {
				return Collections.singletonList(1l);
			}

			@Override
			public TestEntityA get(String table, Long id) {
				return new TestEntityA("Mr.X");
			}

			@Override
			public void store(TestEntityA o) {
				throw new UnsupportedOperationException();

			}

			@Override
			public void flush() {
				throw new UnsupportedOperationException();
			}
		};

		to = new MigrationDataStore<String>() {

			private StringBuffer sb = new StringBuffer();

			@Override
			public Collection<String> getTables() {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Long> getIds(String table) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String get(String table, Long id) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void store(String o) {
				sb.append(o);

			}

			@Override
			public void flush() {
				processor.accept(sb.toString());
			}
		};

		transformator = a -> a.toString();

		processor = new StringProcessor();

	}

	private class TestEntityA {

		private String name;

		public TestEntityA(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "TestEntityA [name=" + name + "]";
		}
	}

	private class StringProcessor implements Consumer<String>, Supplier<String> {

		private String t;

		@Override
		public String get() {
			return t;
		}

		@Override
		public void accept(String t) {
			this.t = t;

		}

	}

}
