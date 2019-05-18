package de.slag.common.db.test.dao;

import org.springframework.stereotype.Repository;

import de.slag.common.db.AbstractDao;
import de.slag.common.db.test.TestEntity;

@Repository
public class TestDaoImpl extends AbstractDao<TestEntity> implements TestDao {

	public TestDaoImpl() {
		super();
	}

	@Override
	protected Class<TestEntity> getPersistentType() {
		return TestEntity.class;
	}
}
