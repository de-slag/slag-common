package de.slag.common.core;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * 
 * use java.util.concurrent.Executors instead
 *
 */
@Deprecated
public class SlagScheduledThreadPoolExecutorSupplier implements Supplier<ScheduledThreadPoolExecutor> {

	@Override
	public ScheduledThreadPoolExecutor get() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		return new ScheduledThreadPoolExecutor(availableProcessors - 1);
	}

}
