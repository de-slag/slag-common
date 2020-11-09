package de.slag.basic.backend.impl.repository;

import java.util.Collection;
import java.util.Map;

interface RepositorySupport {

	void save(Map<String, String> bean);

	void delete(Long id, String type);

	Map<String, String> loadById(Long id, String type);

	Collection<Long> findAllIds(String type);

}
