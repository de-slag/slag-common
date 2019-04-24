package de.slag.common.db;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateSupport {

	private SessionFactory sessionFactory;

	HibernateSupport(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void save(Object o) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			s.save(o);
			tx.commit();
		}
	}
	
	public static Collection<Long> selectAllIds(Session session, Class beanClass) {
		return selectIds(session, beanClass, StringUtils.EMPTY);
	}

	public static Collection<Long> selectIds(Session session, Class beanClass, String where) {
		final String sql = "select id from " + beanClass.getName() + (StringUtils.isBlank(where) ? "" : (" " + where));
		final Query<Long> query = session.createQuery(sql, Long.class);
		final List<Long> list = query.list();
		return list;
	}

	public <T> Optional<T> loadById(Long id, Class<T> clazz) {
		try (final Session s = sessionFactory.openSession()) {
			final Transaction tx = s.beginTransaction();
			final T entity = s.get(clazz, id);
			tx.commit();
			return Optional.of(entity);
		}
	}
}
