package de.slag.common.exception;


public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException(Throwable t) {
		super(t);
	}
	
	public BaseException(String s) {
		super(s);
	}
}
