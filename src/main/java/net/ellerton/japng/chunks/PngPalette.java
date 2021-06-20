package net.ellerton.japng.chunks;

import java.util.Arrays;

import net.ellerton.japng.error.PngException;
import net.ellerton.japng.error.PngIntegrityException;

/**
 * A PngPalette object represents an ordered array of RGB (888) colour tuples
 * derived from a PLTE chunk.
 * <p>
 *     WARNING: this class may not remain in the API.
 *     When implementing the Argb decoders it seems clear that every output
 *     format will benefit from a specific palette implementation, so this attempt
 *     at a generic palette may be removed.
 *
 * @see net.ellerton.japng.argb8888.Argb8888Palette
 */
public class PngPalette {
    // TODO: should include alpha here? Can then store as int32s?
    public final byte[] rgb888;
    public final int[] rgba; // Including this duplicate for now. Not sure if will keep it.
    public final int numColours;

    public static final int LENGTH_RGB_BYTES = 3;
    public static final int BYTE_INITIAL_ALPHA = 0xff;

    public PngPalette(byte[] rgb888, int[] rgba) {
        this.rgb888 = rgb888;
        this.rgba = rgba;
        this.numColours = rgb888.length / 3;
    }

    public static PngPalette from(byte[] source, int first, int length) throws PngException {
        if (length % LENGTH_RGB_BYTES != 0) {
            throw new PngIntegrityException(String.format("Invalid palette data length: %d (not a multiple of 3)", length));
        }

        return new PngPalette(
                Arrays.copyOfRange(source, first, first+length),
                rgbaFrom(source, first, length)
        );
    }

    private static int[] rgbaFrom(byte[] source, int first, int length) {
        int last = first+length;
        int numColours = length / 3;
        int[] rgba = new int[numColours];
        int j=0;
        for (int i = first; i < last; i += LENGTH_RGB_BYTES) {
            rgba[j] = source[i] << 24 | source[i+1] << 16 | source[i+2] << 8 | BYTE_INITIAL_ALPHA;
            j++;
        }
        return rgba;
    }

}
