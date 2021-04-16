package de.slag.common.data;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.model.EntityBean;
import de.slag.common.model.EntityBeanUpdatedAtSetter;
import de.slag.common.model.EntityBeanValidUntilSetter;

public abstract class AbstractPersistService<E extends EntityBean> implements PersistService<E> {

	private static final Log LOG = LogFactory.getLog(AbstractPersistService.class);

	@Resource
	private EntityManager entityManager;

	protected abstract Class<E> getType();

	@PostConstruct
	public void init() {
		try {
			EntityBean.class.getDeclaredField("validUntil");
			EntityBean.class.getDeclaredField("updatedAt");
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	@Transactional
	public void save(E e) {
		new EntityBeanUpdatedAtSetter().accept(e, new Date());
		if (e.getId() != null) {
			entityManager.merge(e);
		} else {
			entityManager.persist(e);
		}
	}

	@Override
	@Transactional
	public void delete(E e) {
		new EntityBeanValidUntilSetter().accept(e, new Date());
		save(e);
	}

	@Override
	@Transactional
	public Optional<E> loadById(Long id) {
		final Class<E> type = getType();
		final CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(type);
		final Root<E> root = criteriaQuery.from(type);

		final ParameterExpression<Long> parameter = criteriaBuilder.parameter(Long.class);
		final javax.persistence.criteria.Predicate equal = criteriaBuilder.equal(root.get("id"), parameter);
		criteriaQuery.select(root).where(equal);

		final TypedQuery<E> typedQuery = createQuery(criteriaQuery);
		typedQuery.setParameter(parameter, id);
		final List<E> resultList = typedQuery.getResultList();

		if (resultList.isEmpty()) {
			return Optional.empty();
		}
		if (resultList.size() > 1) {
			throw new RuntimeException("more than one entry found for: " + id);
		}

		return Optional.of(resultList.get(0));
	}

	protected TypedQuery<E> createQuery(final CriteriaQuery<E> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery);
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	@Override
	@Transactional
	public Collection<Long> findAllIds() {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<E> root = criteriaQuery.from(getType());

		criteriaQuery.select(root.get("id"));
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<E> findBy(Predicate<E> filter) {
		return findAllIds().parallelStream().map(id -> loadById(id)).filter(o -> o.isPresent()).map(o -> o.get())
				.filter(filter).collect(Collectors.toList());
	}

	@Override
	public Optional<E> loadBy(Predicate<E> filter) {
		final long start = System.currentTimeMillis();
		Collection<E> findBy = findBy(filter);
		if (findBy.isEmpty()) {
			return Optional.empty();
		}
		if (findBy.size() > 1) {
			throw new RuntimeException("more than one result");
		}
		final Optional<E> findAny = findBy.stream().findAny();
		final long end = System.currentTimeMillis();
		LOG.info("load by predicate took (ms): " + (end - start));
		return findAny;
	}

	@Override
	public Collection<E> findAll() {
		return findAllIds().stream().map(id -> loadById(id)).map(o -> o.get()).collect(Collectors.toList());
	}

}
