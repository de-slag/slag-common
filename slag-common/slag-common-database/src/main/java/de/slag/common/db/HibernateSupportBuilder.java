package de.slag.common.db;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.Builder;

public class HibernateSupportBuilder implements Builder<HibernateSupport> {

	private Collection<Class<?>> registerClasses = new ArrayList<Class<?>>();

	public HibernateSupportBuilder registerClasses(Collection<Class<?>> registerClasses) {
		this.registerClasses.addAll(registerClasses);
		return this;
	}

	@Override
	public HibernateSupport build() {
		if (registerClasses.isEmpty()) {
			throw new IllegalStateException("no registered classes");
		}
		return new HibernateSupport(registerClasses);
	}

}
