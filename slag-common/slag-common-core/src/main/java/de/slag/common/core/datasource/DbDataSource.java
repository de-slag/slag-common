package de.slag.common.core.datasource;

import java.util.Optional;

import de.slag.common.db.HibernateSupport;
import de.slag.common.model.EntityBean;

class DbDataSource implements DataSource<EntityBean> {

	private HibernateSupport hibernateSupport;

	public DbDataSource(HibernateSupport hibernateSupport) {
		super();
		this.hibernateSupport = hibernateSupport;
	}

	@Override
	public void create(EntityBean t) {
		hibernateSupport.save(t);

	}

	@Override
	public EntityBean read(Class<? extends EntityBean> type, Long id) {
		Optional<? extends EntityBean> loadById = hibernateSupport.loadById(id, type);
		if (loadById.isEmpty()) {
			return null;
		}
		return loadById.get();
	}

	@Override
	public EntityBean update(EntityBean t) {
		hibernateSupport.save(t);
		return t;
	}

	@Override
	public void delete(EntityBean t) {
		throw new UnsupportedOperationException("delete");

	}

}
