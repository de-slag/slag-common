package de.slag.common.testsupport;

import java.util.Collection;

import org.mockito.Spy;

import de.slag.common.db.HibernateSupport;

/**
 * provides a in-memory based HibernateSupport named 'hibernateSupport'
 */

public abstract class AbstractHibernateSupportedTest extends AbstractMockitoRunnerTest {

	@Spy
	protected HibernateSupport hibernateSupport = new TestHibernateSupportBuilder()
			.registerClasses(getRegisterClasses()).build();

	protected abstract Collection<Class<?>> getRegisterClasses();
	
	protected HibernateSupport getHibernateSupport() {
		return hibernateSupport;
	}

}
