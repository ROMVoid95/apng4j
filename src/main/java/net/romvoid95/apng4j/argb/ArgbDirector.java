package net.romvoid95.apng4j.argb;

import net.romvoid95.apng4j.PngScanlineBuffer;
import net.romvoid95.apng4j.chunks.PngAnimationControl;
import net.romvoid95.apng4j.chunks.PngFrameControl;
import net.romvoid95.apng4j.chunks.PngHeader;
import net.romvoid95.apng4j.error.PngException;

/**
 * ArgbDirector implementations "direct" a given ArgbProcessor how to
 * control the output. This allows the ArgbProcessor to transform pixels into
 * ARGB format while allowing for radically different final output objects,
 * e.g. a single bitmap, a sequence of bitamps, an Android View or Drawable, etc.
 *
 * TODO: not sure if this will stay in this form. Needs refinement.
 */
public interface ArgbDirector<ResultT> {

    void receiveHeader(PngHeader header, PngScanlineBuffer buffer) throws PngException;

    void receivePalette(ArgbPalette palette);

    void processTransparentPalette(byte[] bytes, int position, int length) throws PngException;

    void processTransparentGreyscale(byte k1, byte k0) throws PngException;

    void processTransparentTruecolour(byte r1, byte r0, byte g1, byte g0, byte b1, byte b0) throws PngException;

    boolean wantDefaultImage();

    boolean wantAnimationFrames();

    ScanlineProcessor beforeDefaultImage();

    void receiveDefaultImage(ArgbBitmap bitmap);

    void receiveAnimationControl(PngAnimationControl control);

    ScanlineProcessor receiveFrameControl(PngFrameControl control);

    void receiveFrameImage(ArgbBitmap bitmap);

    ResultT getResult();
}
