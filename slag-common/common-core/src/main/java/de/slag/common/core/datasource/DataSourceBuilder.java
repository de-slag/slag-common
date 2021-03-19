package de.slag.common.core.datasource;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.Builder;

import de.slag.common.base.BaseException;
import de.slag.common.model.EntityBean;

public class DataSourceBuilder implements Builder<DataSource<EntityBean>> {

	private DataSourceType type = DataSourceType.DATABASE;

	private String driver;
	private String password;
	private Collection<Class<? extends EntityBean>> registeredClasses = new ArrayList<>();
	private String hibernateDialect;
	private String url;
	private String user;

	public DataSourceBuilder withUser(String user) {
		this.user = user;
		return this;
	}

	public DataSourceBuilder withUrl(String url) {
		this.url = url;
		return this;
	}

	public DataSourceBuilder withHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
		return this;
	}

	public DataSourceBuilder withRegisteredClasses(Collection<Class<? extends EntityBean>> registeredClasses) {
		this.registeredClasses.addAll(registeredClasses);
		return this;
	}

	public DataSourceBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public DataSourceBuilder withDriver(String driver) {
		this.driver = driver;
		return this;
	}

	@Override
	public DataSource<EntityBean> build() {
		if (DataSourceType.DATABASE == type) {
			return createDbDataSource();
		}
		throw new BaseException("not supported type: " + type);
	}

	private DataSource<EntityBean> createDbDataSource() {
		return new DbDataSourceBuilder()
				.withRegisteredClasses(registeredClasses)
				.withHibernateDialect(hibernateDialect)
				.withUrl(url)
				.withUser(user)
				.withPassword(password)
				.withDriver(driver)
				.build();
	}

}
