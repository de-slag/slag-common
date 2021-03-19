package de.slag.common.core.datasource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.slag.common.core.datasource.DataSource;
import de.slag.common.core.datasource.DataSourceBuilder;
import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.model.EntityBean;

class DataSourceBuilderTest {

	@Test
	void integrationTest() {
		DataSourceBuilder dataSourceBuilder = new DataSourceBuilder()
		.withHibernateDialect(InMemoryProperties.DIALECT)
		.withUser(InMemoryProperties.USER)
		.withPassword(InMemoryProperties.PASSWORD)
		.withUrl(InMemoryProperties.URL)
		.withDriver(InMemoryProperties.DRIVER)
		.withRegisteredClasses(Collections.singleton(TestEntity47.class));	
		
		DataSource<EntityBean> dataSource = dataSourceBuilder.build();
		assertNotNull(dataSource);
		
		TestEntity47 testEntity = new TestEntity47();
		dataSource.create(testEntity);
		testEntity.getId();
		
	}

}
