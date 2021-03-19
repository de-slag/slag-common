package de.slag.common.testsupport;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

public class AbstractMockitoRunnerTestTest extends AbstractMockitoRunnerTest {
	
	@Mock
	Object o;
	

	@Test
	public void test() {
		Assert.assertNotNull(o);
	}

}
