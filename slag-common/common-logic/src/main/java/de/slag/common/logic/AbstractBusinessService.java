package de.slag.common.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import de.slag.common.data.PersistService;
import de.slag.common.model.EntityBean;

public abstract class AbstractBusinessService<E extends EntityBean> implements BusinessService<E> {

	protected abstract PersistService<E> getPersistService();

	@Override
	public void delete(E e) {
		getPersistService().delete(e);
	}

	@Override
	public void save(E e) {
		getPersistService().save(e);
	}

	@Override
	public Optional<E> loadById(Long id) {
		return getPersistService().loadById(id);
	}

	protected Optional<E> pickUnique(Supplier<Collection<E>> supplier) {
		if (supplier == null) {
			return Optional.empty();
		}
		final Collection<E> collection = supplier.get();
		if (collection == null) {
			return Optional.empty();
		}
		if (collection.size() > 1) {
			throw new RuntimeException("not unique");
		}
		return Optional.of(new ArrayList<>(collection).get(0));
	}
}
