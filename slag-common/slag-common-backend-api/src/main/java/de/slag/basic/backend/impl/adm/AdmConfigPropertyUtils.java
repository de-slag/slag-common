package de.slag.basic.backend.impl.adm;

import java.util.Collection;
import java.util.stream.Collectors;

public class AdmConfigPropertyUtils {

	private static final String DOT = ".";

	public static Collection<Long> findIdsFor(String keyPrefix, Collection<String> keys) {
		return keys.stream()
				.filter(key -> key.startsWith(keyPrefix))
				.map(key -> key.substring(keyPrefix.length() + DOT.length()))
				.map(subkey -> subkey.split("\\."))
				.map(splittedSubkey -> splittedSubkey[0])
				.map(idAsString -> Long.valueOf(idAsString))
				.collect(Collectors.toSet());
	}

}
