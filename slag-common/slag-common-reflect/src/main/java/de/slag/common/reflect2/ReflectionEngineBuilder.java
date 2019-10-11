package de.slag.common.reflect2;

import org.apache.commons.lang3.builder.Builder;

public class ReflectionEngineBuilder implements Builder<ReflectionEngine> {

	@Override
	public ReflectionEngine build() {
		return new ReflectionEngine();
	}

}
