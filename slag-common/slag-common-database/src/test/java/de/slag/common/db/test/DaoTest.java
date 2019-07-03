package de.slag.common.db.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.slag.common.context.SlagContext;
import de.slag.common.db.test.dao.TestDao;

public class DaoTest {

	private TestDao testDao;

	@Before
	public void setup() {
		//SlagContext.init();
		testDao = SlagContext.getBean(TestDao.class);
		Assert.assertNotNull(testDao);
	}

	@Test
	@Ignore("fix in vacation")
	public void test() {		
		final TestEntity testEntity = createTestEntity();
		testDao.save(testEntity);
		Assert.assertNotNull(testEntity.getId());
	}
	
	public TestEntity createTestEntity() {
		final TestEntity testEntity = new TestEntity();
		testEntity.setName("dao test");
		return testEntity;
	}

}
