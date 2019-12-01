package de.slag.common.testsupport;

import java.util.Collection;

import org.apache.commons.lang3.builder.Builder;

import de.slag.common.db.HibernateSupport;
import de.slag.common.db.HibernateSupportBuilder;
import de.slag.common.db.h2.InMemoryProperties;

public class TestHibernateSupportBuilder implements Builder<HibernateSupport>{	
	
	private final HibernateSupportBuilder builder;
	
	public TestHibernateSupportBuilder() {
		builder = new HibernateSupportBuilder();
	}

	public HibernateSupport build() {
		builder.driver(InMemoryProperties.DRIVER);
		builder.hibernateDialect(InMemoryProperties.DIALECT);
		builder.password(InMemoryProperties.PASSWORD);
		builder.url(InMemoryProperties.URL);
		builder.user(InMemoryProperties.USER);
		return builder.build();
	}
	
	public TestHibernateSupportBuilder registerClasses(Collection<Class<?>> registerClasses) {
		builder.registerClasses(registerClasses);
		return this;
	}

}
