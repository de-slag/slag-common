package de.slag.basic.backend.impl.repository;

import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;

public abstract class BasicRepositoryImpl implements BasicRepository {

	private RepositorySupport repositorySupport;

	@PostConstruct
	public void init() {
		// TODO extend supporting "file" and "db"
		repositorySupport = new MemoryRepositorySupport();
	}

	@Override
	public void save(Map<String, String> bean) {
		repositorySupport.save(bean);

	}

	@Override
	public void delete(Long id, String type) {
		repositorySupport.delete(id, type);

	}

	@Override
	public Map<String, String> loadById(Long id, String type) {
		return repositorySupport.loadById(id, type);
	}

	@Override
	public Collection<Long> findAllIds(String type) {
		return repositorySupport.findAllIds(type);
	}

}
