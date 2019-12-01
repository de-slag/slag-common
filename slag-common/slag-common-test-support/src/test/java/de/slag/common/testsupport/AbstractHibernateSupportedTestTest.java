package de.slag.common.testsupport;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import de.slag.common.testsupport.model.TestEntity;
import de.slag.common.testsupport.repo.TestRepo;
import de.slag.common.testsupport.repo.TestRepoImpl;

public class AbstractHibernateSupportedTestTest extends AbstractHibernateSupportedTest {
	
	@InjectMocks
	private TestRepo testRepo = new TestRepoImpl();

	@Override
	protected Collection<Class<?>> getRegisterClasses() {
		return Arrays.asList(TestEntity.class);
	}
	
	@Before
	public void setUp() {
		TestEntity e = new TestEntity();
		e.setName("test0815");
		testRepo.save(e);
		Assert.assertTrue(Long.valueOf(1).equals(e.getId()));
	}
	
	@Test
	public void test() {
		TestEntity entity = testRepo.loadById(1l, TestEntity.class);
		Assert.assertNotNull(entity);
		Assert.assertTrue("test0815".equals(entity.getName()));
	}


}
