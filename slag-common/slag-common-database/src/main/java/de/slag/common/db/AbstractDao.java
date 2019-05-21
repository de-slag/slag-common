package de.slag.common.db;

import java.util.Collection;
import java.util.Optional;

import de.slag.common.context.SlagContext;
import de.slag.common.db.hibernate.HibernateService;

public abstract class AbstractDao<T> implements Dao<T> {

	private HibernateService hibernateService = SlagContext.getBean(HibernateService.class);

	protected abstract Class<T> getPersistentType();

	public void save(T t) {
		hibernateService.save(t);
	}

	@Override
	public Optional<T> loadById(Long id) {
		return hibernateService.loadById(id, getPersistentType());
	}
	
	@Override
	public Collection<Long> findAllIds() {
		return hibernateService.findAllIds(getPersistentType());
	}	
}
