package de.slag.common.testsupport;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import de.slag.common.db.HibernateSupport;
import de.slag.common.testsupport.model.TestEntity;
import de.slag.common.testsupport.repo.TestRepo;
import de.slag.common.testsupport.repo.TestRepoImpl;

public class IntegrationTest extends AbstractMockitoRunnerTest {

	@InjectMocks
	TestRepo testRepo = new TestRepoImpl();

	@Spy
	HibernateSupport hibernateSupport = new TestHibernateSupportBuilder()
			.registerClasses(Arrays.asList(TestEntity.class)).build();

	@Test
	public void it() {
		Assert.assertNotNull(testRepo);
		TestEntity e = new TestEntity();
		testRepo.save(e);
		Assert.assertNotNull(e.getId());
	}

}
