package de.slag.common.context.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.slag.common.context.SlagContext;

public class SlagContextTest {
	
	@BeforeClass
	public static void setup() {
		SlagContext.init();		
	}
	
	@Test
	public void testService() {
		Assert.assertNotNull(SlagContext.getBean(TestService.class));
	}
	
	@Test
	public void testRepo() {

		Assert.assertNotNull(SlagContext.getBean(TestRepository.class));
	}

}
