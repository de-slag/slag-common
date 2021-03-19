package de.slag.common.base;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ServiceException(String s, Throwable t) {
		super(s, t);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(String s) {
		super(s);
	}

}
