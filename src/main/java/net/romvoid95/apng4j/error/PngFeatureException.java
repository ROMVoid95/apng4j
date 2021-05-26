package net.romvoid95.apng4j.error;

import net.romvoid95.apng4j.PngConstants;

/**
 * A PngFeatureException is thrown when a feature used in a specific file
 * is not supported by the pipeline being used. For example, at the time
 * of writing, interlaced image reading is not supported.
 */
public class PngFeatureException extends PngException {

	private static final long serialVersionUID = -8066160894756528149L;

	public PngFeatureException(String message) {
		super(PngConstants.ERROR_FEATURE_NOT_SUPPORTED, message);
	}
}
