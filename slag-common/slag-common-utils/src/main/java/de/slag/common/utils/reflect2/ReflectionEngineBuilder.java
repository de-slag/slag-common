package de.slag.common.utils.reflect2;

import org.apache.commons.lang3.builder.Builder;

public class ReflectionEngineBuilder implements Builder<ReflectionEngine> {

	@Override
	public ReflectionEngine build() {
		return new ReflectionEngine();
	}

}
