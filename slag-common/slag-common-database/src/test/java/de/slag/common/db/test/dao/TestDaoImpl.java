package de.slag.common.db.test.dao;

import de.slag.common.db.AbstractDao;
import de.slag.common.db.HibernateSupport;
import de.slag.common.db.test.TestEntity;

//@Repository
public class TestDaoImpl extends AbstractDao<TestEntity> implements TestDao {

	public TestDaoImpl() {
		super();
	}

	@Override
	public Class<TestEntity> getPersistentType() {
		return TestEntity.class;
	}

	@Override
	public HibernateSupport getHibernateSupport() {
		// TODO Auto-generated method stub
		return null;
	}
}
