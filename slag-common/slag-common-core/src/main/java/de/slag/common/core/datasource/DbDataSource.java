package de.slag.common.core.datasource;

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

	

}
