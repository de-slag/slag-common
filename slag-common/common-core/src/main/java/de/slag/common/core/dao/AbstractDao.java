package de.slag.common.core.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import de.slag.common.core.datasource.DataSource;
import de.slag.common.core.datasource.DataSourceBuilder;
import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.model.EntityBean;
import de.slag.common.model.EntityBeanUtils;

public abstract class AbstractDao<E extends EntityBean> implements Dao<E> {

	private static DataSource<EntityBean> dataSource;

	protected static Supplier<Collection<Class<? extends EntityBean>>> registeredEntitiesSupplier;

	@PostConstruct
	public void init() {

		// TODO configured datasource
		dataSource = new DataSourceBuilder().withHibernateDialect(InMemoryProperties.DIALECT)
				.withUser(InMemoryProperties.USER).withPassword(InMemoryProperties.PASSWORD)
				.withUrl(InMemoryProperties.URL).withDriver(InMemoryProperties.DRIVER)
				.withRegisteredClasses(registeredEntitiesSupplier.get()).build();
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

	@Override
	public Optional<E> load(Long id) {
		return loadById(id);
	}

	@Override
	public Optional<E> loadById(Long id) {
		@SuppressWarnings("unchecked")
		E e = (E) dataSource.read(getType(), id);
		if (e == null) {
			return Optional.empty();
		}
		return Optional.of(e);
	}

	@Override
	public Collection<Long> findAllIds() {
		return dataSource.findAllIds(getType());
	}

	@Override
	public Collection<E> findAll() {
		return findAllIds().stream()
				.map(id -> loadById(id))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
	/**
	 * use 'findBy(Predicate)' instead
	 */
	@Deprecated
	@Override
	public Collection<E> findAllBy(Predicate<E> filter) {
		return findBy(filter);
	}

	@Override
	public Collection<E> findBy(Predicate<E> filter) {
		return findAll().stream()
				.filter(e -> filter.test(e))
				.collect(Collectors.toList());
	}

	protected abstract Class<? extends EntityBean> getType();

}
