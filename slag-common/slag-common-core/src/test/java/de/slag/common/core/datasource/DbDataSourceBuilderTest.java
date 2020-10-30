package de.slag.common.core.datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.slag.common.core.datasource.DataSource;
import de.slag.common.core.datasource.DbDataSourceBuilder;
import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.model.EntityBean;

class DbDataSourceBuilderTest {

	@Test
	void integrationTest() {
		DataSource<EntityBean> dbDataSource = new DbDataSourceBuilder()
				.withRegisteredClasses(Collections.singleton(TestEntity.class))
				.withHibernateDialect(InMemoryProperties.DIALECT)
				.withUser(InMemoryProperties.USER)
				.withPassword(InMemoryProperties.PASSWORD)
				.withUrl(InMemoryProperties.URL)
				.withDriver(InMemoryProperties.DRIVER)
				.build();
		assertNotNull(dbDataSource);
	}
	
	private class TestEntity extends EntityBean {
		
	}

}
