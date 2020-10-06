package de.slag.common.db.hibernate;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import de.slag.common.base.SlagProperties;

public class AbstractHibernateServiceImpl implements HibernateService, Closeable {

	private SessionFactory sessionFactory;

	public AbstractHibernateServiceImpl(Collection<Class<?>> registerClasses) {
		init(registerClasses);
	}

	AbstractHibernateServiceImpl() {
		init();
	}

	public void init() {
		init(null);
	}

	public void init(Collection<Class<?>> registerClasses) {
		sessionFactory = SessionFactoryUtils.createSessionFactory(getProperties(), new ArrayList<>(registerClasses));
	}

	protected Properties getProperties() {
		return SlagProperties.getConfigProperties();
	}

	@Override
	public void save(Object o) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			s.saveOrUpdate(o);
			tx.commit();
		}
	}

	@Override
	public <T> Optional<T> loadById(Long id, Class<T> clazz) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final T entity = s.get(clazz, id);
			tx.commit();
			return Optional.of(entity);
		}
	}

	@Override
	public Collection<Long> findAllIds(Class<?> clazz) {
		final Collection<Long> results = new ArrayList<Long>();
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final Query<Long> createQuery = s.createQuery("select id from " + clazz.getName(), Long.class);
			results.addAll(createQuery.getResultList());
			tx.commit();
		}
		return results;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void close() throws IOException {
		sessionFactory.close();
	}

	@Override
	public Properties getDbProperties() {
		return getProperties();
	}

}
