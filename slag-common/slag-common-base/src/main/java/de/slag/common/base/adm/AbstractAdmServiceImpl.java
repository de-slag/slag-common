package de.slag.common.base.adm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractAdmServiceImpl implements AdmService {

	private Map<String, String> map = new HashMap<>();

	public AbstractAdmServiceImpl() {
		this(() -> new HashMap<>());
	}

	public AbstractAdmServiceImpl(Supplier<Map<String, String>> admSuplier) {
		this.map.putAll(admSuplier.get());
	}

	@Override
	public Optional<String> get(String key) {
		if (!map.containsKey(key)) {
			return Optional.empty();
		}
		return Optional.of(map.get(key));
	}

}
