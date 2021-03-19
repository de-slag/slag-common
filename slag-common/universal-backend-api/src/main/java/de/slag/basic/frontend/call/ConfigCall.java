package de.slag.basic.frontend.call;

import java.util.concurrent.Callable;

public interface ConfigCall extends Callable<String>{
	
	default boolean isSuccessful(String string) {
		String call;
		try {
			call = call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return call.endsWith("ok");
	}

}
