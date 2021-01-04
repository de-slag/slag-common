package de.slag.common.core.eop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EopEntity {

	private EopType type;

	private Long id;

	private final Map<String, String> attributes = new HashMap<>();

	EopEntity(EopType type, Long id) {
		super();
		this.type = type;
		this.id = id;
	}

	public EopType getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public String put(String key, String value) {
		return attributes.put(key, value);
	}

	public String get(String key) {
		return attributes.get(key);
	}

	public Collection<String> getAttributeKeys() {
		return attributes.keySet();
	}

	@Override
	public String toString() {
		return "EopEntity [type=" + type + ", id=" + id + ", attributes=" + attributes + "]";
	}

}
