package de.slag.common.base.function;

@FunctionalInterface
public interface TriConsumer<T, U, R> {

	void accept(T t, U u, R r);

}
