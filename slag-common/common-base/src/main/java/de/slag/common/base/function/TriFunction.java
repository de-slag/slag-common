package de.slag.common.base.function;

@FunctionalInterface
public interface TriFunction<Y, T, U, R>  {

	R apply(Y y, T t, U u);

}
