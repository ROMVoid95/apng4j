package net.ellerton.japng.error;

/**
 * All exceptions in the library are a PngException or subclass of it.
 */
public class PngException extends Exception {

	private static final long serialVersionUID = 781929404755272089L;
	int code;

	public PngException(int code, String message) {
		super(message);
		this.code = code;
	}

	public PngException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
}
