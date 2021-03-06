package de.slag.common.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import de.slag.common.db.hibernate.SessionFactoryUtils;

public class HibernateSupportImpl implements HibernateSupport {

	private static final Log LOG = LogFactory.getLog(HibernateSupport.class);

	private SessionFactory sessionFactory;

	HibernateSupportImpl(Collection<Class<?>> registerClasses, Properties props) {
		sessionFactory = SessionFactoryUtils.createSessionFactory(props, registerClasses);
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
	public <T> Optional<T> loadById(Long id, Class<T> persistentType) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(persistentType);
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final T entity = s.get(persistentType, id);
			tx.commit();
			if (entity == null) {
				return Optional.empty();
			}
			return Optional.of(entity);
		}
	}

	@Override
	public Collection<Long> findAllIds(Class<?> clazz) {
		return findBy(Long.class, "select id from " + clazz.getName());
	}

	@Override
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
