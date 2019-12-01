package de.slag.common.testsupport.repo;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import de.slag.common.db.HibernateSupport;
import de.slag.common.testsupport.model.TestEntity;

@Repository
public class TestRepoImpl implements TestRepo {
	
	@Resource
	private HibernateSupport hibernateSupport;
	
	@Override
	public void save(TestEntity e) {
		hibernateSupport.save(e);
	}

	@Override
	public <T> T loadById(Long id, Class<T> type) {
		return type.cast(hibernateSupport.loadById(id, type).get());
	}

}
