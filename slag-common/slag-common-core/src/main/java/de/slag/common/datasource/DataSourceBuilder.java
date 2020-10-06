package de.slag.common.datasource;

import org.apache.commons.lang3.builder.Builder;

import de.slag.common.model.EntityBean;

public class DataSourceBuilder implements Builder<DataSource<EntityBean>> {
	
	private DataSourceType type;
	
	public DataSourceBuilder withType(DataSourceType type) {
		this.type = type;
		return this;
	}

	@Override
	public DataSource<EntityBean> build() {
		return null;
	}

}
