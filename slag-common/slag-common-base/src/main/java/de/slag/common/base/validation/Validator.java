package de.slag.common.base.validation;

public interface Validator<T> {

	default void validate(T t) throws ValidationException {
		if (!isValid(t)) {
			throw new ValidationException("not valid: " + t);
		}
	}

	boolean isValid(T t);

}
