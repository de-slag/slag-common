package de.slag.common.testsupport.repo;

import de.slag.common.testsupport.model.TestEntity;

public interface TestRepo {

	void save(TestEntity e);

	<T> T loadById(Long id, Class<T> type);

}
