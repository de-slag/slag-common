package de.slag.common.core.adm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractAdmServiceImpl implements AdmService {

	private Map<String, String> map = new HashMap<>();

	private Function<String, String> admFunction;

	public AbstractAdmServiceImpl() {
		this(() -> new HashMap<>());
	}

	public AbstractAdmServiceImpl(Supplier<Map<String, String>> admSuplier) {
		this(key -> admSuplier.get().get(key));
	}

	public AbstractAdmServiceImpl(Function<String, String> admFunction) {
		this.admFunction = admFunction;
	}

	@Override
	public Optional<String> get(String key) {
		if (!map.containsKey(key)) {
			final String value = admFunction.apply(key);
			if(value != null) {
				map.put(key, value);
			}			
		}
		
		if (!map.containsKey(key)) {
			return Optional.empty();
		}
		return Optional.of(map.get(key));
	}

}
