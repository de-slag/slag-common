package de.slag.common.db.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import de.slag.common.context.SlagContext;
import de.slag.common.db.test.dao.TestDao;

public class DaoTest {

	private TestDao testDao;

	@BeforeEach
	public void setup() {
//		SlagContext.init();
//		testDao = SlagContext.getBean(TestDao.class);
		assertNotNull(testDao);
	}

	@Test
	public void test() {
		final TestEntity testEntity = createTestEntity();
		testDao.save(testEntity);
		assertNotNull(testEntity.getId());
	}

	public TestEntity createTestEntity() {
		final TestEntity testEntity = new TestEntity();
		testEntity.setName("dao test");
		return testEntity;
	}

}
