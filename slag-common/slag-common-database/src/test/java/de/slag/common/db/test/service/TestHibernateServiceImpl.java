package de.slag.common.db.test.service;

import java.util.Properties;

//import org.springframework.stereotype.Service;

import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.db.hibernate.AbstractHibernateServiceImpl;
import de.slag.common.db.hibernate.HibernateService;

//@Service
public class TestHibernateServiceImpl extends AbstractHibernateServiceImpl implements HibernateService {

	TestHibernateServiceImpl() {
		super(null);
	}

	@Override
	protected Properties getProperties() {
		return new InMemoryProperties();
	}

}
