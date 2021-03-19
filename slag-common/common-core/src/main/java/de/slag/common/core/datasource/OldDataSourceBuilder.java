package de.slag.common.core.datasource;

import java.util.Optional;

import org.apache.commons.lang3.builder.Builder;

import de.slag.common.base.BaseException;
import de.slag.common.base.datasource.DataSource;
import de.slag.common.base.datasource.DataSourceType;
import de.slag.common.model.EntityBean;

@Deprecated
public class OldDataSourceBuilder implements Builder<DataSource<EntityBean>> {

	private DataSourceType type = DataSourceType.FILE_SYSTEM;
	private String path;

	public OldDataSourceBuilder withType(DataSourceType type) {
		this.type = type;
		return this;
	}

	public OldDataSourceBuilder whithPath(String path) {
		this.path = path;
		return this;
	}

	@Override
	public DataSource<EntityBean> build() {
		DataSourceType currentType = Optional.ofNullable(type).orElseThrow(() -> new BaseException("type is null"));
		switch (currentType) {
		case FILE_SYSTEM:
			return buildFileSystemDataSource();
		default:
			throw new BaseException("type not supported: " + type);
		}
	}

	private DataSource<EntityBean> buildFileSystemDataSource() {
		String currentPath = Optional.ofNullable(path).orElseThrow(() -> new BaseException("path is null"));
		return new DataSource<EntityBean>() {

			@Override
			public void create(EntityBean t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public EntityBean read(Class<? extends EntityBean> type, Long id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EntityBean update(EntityBean t) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void delete(EntityBean t) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
