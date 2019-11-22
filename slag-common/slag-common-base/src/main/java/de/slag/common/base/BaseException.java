package de.slag.common.base;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException(String s, Throwable t) {
		super(s, t);
	}

	public BaseException(Throwable t) {
		super(t);
	}

	public BaseException(String s) {
		super(s);
	}
<<<<<<< HEAD

=======
	
>>>>>>> branch 'master' of https://github.com/de-slag/slag-common.git
	public BaseException(String format, Object... objects) {
		super(String.format(format, objects));
	}
}
