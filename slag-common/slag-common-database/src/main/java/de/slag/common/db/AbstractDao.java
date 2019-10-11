package de.slag.common.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import de.slag.common.base.BaseException;
import de.slag.common.db.hibernate.HibernateResource;
import de.slag.common.model.EntityBean;
import de.slag.common.model.EntityBeanUtils;

public abstract class AbstractDao<T> implements Dao<T> {

	private static final Log LOG = LogFactory.getLog(AbstractDao.class);

	@Resource
	private HibernateResource hibernateResource;

	protected abstract Class<T> getPersistentType();
	
	public void remove(T t) {
		if(!(t instanceof EntityBean)) {
			throw new BaseException("cannot remove non-EntityBeans");
		}
		final EntityBean bean = (EntityBean) t;
		EntityBeanUtils.setDelete(bean);
		save(t);
	}

	public void save(T t) {

		// TODO hacky
//		if (t instanceof EntityBean) {
//			final EntityBean eb = (EntityBean) t;
//			if (eb.getId() == null) {
//				HackyIdAllocator.allocateId(eb);
//			}
//		}

		final Supplier<Session> sessionSupplier = hibernateResource.getSessionSupplier();

		try (final Session s = sessionSupplier.get()) {
			final Transaction tx = s.beginTransaction();
			s.saveOrUpdate(t);
			tx.commit();
		}
		LOG.debug(String.format("saved '%s'", t));
	}

	@Override
	public Optional<T> loadById(Long id) {
		final Supplier<Session> sessionSupplier = hibernateResource.getSessionSupplier();
		try (final Session s = sessionSupplier.get()) {
			final Transaction tx = s.beginTransaction();
			final T entity = s.get(getPersistentType(), id);
			tx.commit();
			return Optional.of(entity);
		}

	}

	@Override
	public Collection<Long> findAllIds() {
		final Supplier<Session> sessionSupplier = hibernateResource.getSessionSupplier();

		final Collection<Long> results = new ArrayList<Long>();
		try (final Session s = sessionSupplier.get()) {
			final Transaction tx = s.beginTransaction();
			final Query<Long> createQuery = s.createQuery("select id from " + getPersistentType().getName(),
					Long.class);
			results.addAll(createQuery.getResultList());
			tx.commit();
		}
		return results;
	}

}