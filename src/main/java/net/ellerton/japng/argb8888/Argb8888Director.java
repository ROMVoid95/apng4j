package net.ellerton.japng.argb8888;

import net.ellerton.japng.PngScanlineBuffer;
import net.ellerton.japng.chunks.PngAnimationControl;
import net.ellerton.japng.chunks.PngFrameControl;
import net.ellerton.japng.chunks.PngHeader;
import net.ellerton.japng.error.PngException;

/**
 * ArgbDirector implementations "direct" a given ArgbProcessor how to
 * control the output. This allows the ArgbProcessor to transform pixels into
 * ARGB format while allowing for radically different final output objects,
 * e.g. a single bitmap, a sequence of bitamps, an Android View or Drawable, etc.
 *
 * TODO: not sure if this will stay in this form. Needs refinement.
 */
public interface Argb8888Director<ResultT> {

    void receiveHeader(PngHeader header, PngScanlineBuffer buffer) throws PngException;

    void receivePalette(Argb8888Palette palette);

    void processTransparentPalette(byte[] bytes, int position, int length) throws PngException;

    void processTransparentGreyscale(byte k1, byte k0) throws PngException;

    void processTransparentTruecolour(byte r1, byte r0, byte g1, byte g0, byte b1, byte b0) throws PngException;

    boolean wantDefaultImage();

    boolean wantAnimationFrames();

    Argb8888ScanlineProcessor beforeDefaultImage();

    void receiveDefaultImage(Argb8888Bitmap bitmap);

    void receiveAnimationControl(PngAnimationControl control);

    Argb8888ScanlineProcessor receiveFrameControl(PngFrameControl control);

    void receiveFrameImage(Argb8888Bitmap bitmap);

    ResultT getResult();
}
