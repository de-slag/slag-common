package de.slag.common.base.function;

@FunctionalInterface
public interface QuadFunction<X, Y, T, U, R>  {

	R apply(X x, Y y, T t, U u);

}
