package net.ellerton.japng.error;

import net.ellerton.japng.PngConstants;

/**
 * A PngIntegrityException is thrown when some aspect of the PNG file being
 * loaded is invalid or unacceptable according to the PNG Specification.
 * <p>
 * For example, requesting a 16-bit palette image is invalid, or a colour type
 * outside of the allowed set.
 */
public class PngIntegrityException extends PngException {

	private static final long serialVersionUID = 7953448562726787565L;

	public PngIntegrityException(String message) {
		super(PngConstants.ERROR_INTEGRITY, message);
	}
}
