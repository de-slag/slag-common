package de.slag.common.db;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import de.slag.common.db.hibernate.SessionFactoryUtils;

public class HibernateSupport implements Closeable {

	private static final Log LOG = LogFactory.getLog(HibernateSupport.class);

	private SessionFactory sessionFactory;

	HibernateSupport(Collection<Class<?>> registerClasses, Properties props) {
		sessionFactory = SessionFactoryUtils.createSessionFactory(props, registerClasses);
	}

	public void save(Object o) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			s.saveOrUpdate(o);
			tx.commit();
		}
	}

	public <T> Optional<T> loadById(Long id, Class<T> persistentType) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final T entity = s.get(persistentType, id);
			tx.commit();
			return Optional.of(entity);
		}
	}

	public Collection<Long> findAllIds(Class<?> clazz) {
		return findBy(Long.class, "select id from " + clazz.getName());
	}

	public <T> Collection<T> findBy(Class<T> type, String sql) {
		final Collection<T> results = new ArrayList<>();
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final Query<T> createQuery = s.createQuery(sql, type);
			results.addAll(createQuery.getResultList());
			tx.commit();
		}
		return results;
	}

	@Override
	public void close() throws IOException {
		sessionFactory.close();
		LOG.info(this.getClass().getName() + " closed.");
	}
}
