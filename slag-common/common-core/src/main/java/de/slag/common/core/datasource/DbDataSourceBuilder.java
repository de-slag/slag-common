package de.slag.common.core.datasource;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.Builder;

import de.slag.common.db.HibernateSupport;
import de.slag.common.db.HibernateSupportBuilder;
import de.slag.common.model.EntityBean;

class DbDataSourceBuilder implements Builder<DataSource<EntityBean>> {

	private String driver;
	private String password;
	private Collection<Class<?>> registeredClasses = new ArrayList<Class<?>>();
	private String hibernateDialect;
	private String url;
	private String user;

	public DbDataSourceBuilder withUser(String user) {
		this.user = user;
		return this;
	}
	
	public DbDataSourceBuilder withUrl(String url) {
		this.url = url;
		return this;
	}
	
	public DbDataSourceBuilder withHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
		return this;
	}
	
	public DbDataSourceBuilder withRegisteredClasses(Collection<Class<? extends EntityBean>> registeredClasses) {
		this.registeredClasses.addAll(registeredClasses);
		return this;
	}

	public DbDataSourceBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public DbDataSourceBuilder withDriver(String driver) {
		this.driver = driver;
		return this;
	}

	@Override
	public DataSource<EntityBean> build() {
		
		final HibernateSupportBuilder hibernateSupportBuilder = new HibernateSupportBuilder();
		final HibernateSupport hibernateSupport = hibernateSupportBuilder.driver(driver).password(password)
				.registerClasses(registeredClasses).hibernateDialect(hibernateDialect).url(url).user(user).build();

		return new DbDataSource(hibernateSupport);
	}

}
