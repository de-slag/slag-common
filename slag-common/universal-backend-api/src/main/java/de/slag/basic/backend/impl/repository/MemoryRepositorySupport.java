package de.slag.basic.backend.impl.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class MemoryRepositorySupport implements RepositorySupport {

	private Map<MemoryEntityBean, Map<String, String>> entityMap = new HashMap<>();

	@Override
	public void save(Map<String, String> bean) {
		MemoryEntityBean memoryEntityBean = memoyEntityBeanOf(bean);
		entityMap.put(memoryEntityBean, bean);
		// TODO Auto-generated method stub

	}

	private MemoryEntityBean memoyEntityBeanOf(Map<String, String> bean) {
		String string = bean.get("type");
		String string2 = bean.get("id");
		Long id = Long.valueOf(string2);
		MemoryEntityBean memoryEntityBean = new MemoryEntityBean(string, id);
		return memoryEntityBean;
	}

	@Override
	public void delete(Long id, String type) {
		MemoryEntityBean memoryEntityBean = new MemoryEntityBean(type, id);
		entityMap.remove(memoryEntityBean);

	}

	@Override
	public Map<String, String> loadById(Long id, String type) {
		MemoryEntityBean memoryEntityBean = new MemoryEntityBean(type, id);
		return entityMap.get(memoryEntityBean);
	}

	@Override
	public Collection<Long> findAllIds(String type) {
		return entityMap.keySet()
				.stream()
				.filter(b -> b.getType()
						.equals(type))
				.map(b -> b.getId())
				.collect(Collectors.toList());
	}

	private class MemoryEntityBean {

		private String type;
		private Long id;

		public MemoryEntityBean(String type, Long id) {
			this.type = type;
			this.id = id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MemoryEntityBean other = (MemoryEntityBean) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}

		private MemoryRepositorySupport getEnclosingInstance() {
			return MemoryRepositorySupport.this;
		}

		public String getType() {
			return type;
		}

		public Long getId() {
			return id;
		}

	}

}
