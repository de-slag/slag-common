package de.slag.common.base;

public class NotSupportedException extends BaseException {

	private static final long serialVersionUID = 1L;

	public NotSupportedException(Object o) {
		this(o == null ? "null" : o.toString());
	}

	public NotSupportedException(String s) {
		super("not supported: " + s);
	}

}
