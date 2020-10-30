package de.slag.common.core.dao;

import java.util.Collection;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import de.slag.common.core.datasource.DataSource;
import de.slag.common.core.datasource.DataSourceBuilder;
import de.slag.common.model.EntityBean;
import de.slag.common.model.EntityBeanUtils;

public abstract class AbstractDao<E extends EntityBean> implements Dao<E> {

	private static DataSource<EntityBean> dataSource;

	protected static Supplier<Collection<Class<? extends EntityBean>>> registeredEntitiesSupplier;

	@PostConstruct
	public void init() {
		dataSource = new DataSourceBuilder().withRegisteredClasses(registeredEntitiesSupplier.get()).build();
	}

	@Override
	public E save(E e) {
		if (e.getId() == null) {
			dataSource.create(e);
		}
		dataSource.update(e);
		return e;
	}

	@Override
	public void delete(E e) {
		EntityBeanUtils.setDelete(e);
		save(e);
	}

}
