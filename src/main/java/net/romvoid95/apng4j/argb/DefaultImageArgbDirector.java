package net.romvoid95.apng4j.argb;

import net.romvoid95.apng4j.PngScanlineBuffer;
import net.romvoid95.apng4j.chunks.PngAnimationControl;
import net.romvoid95.apng4j.chunks.PngFrameControl;
import net.romvoid95.apng4j.chunks.PngHeader;
import net.romvoid95.apng4j.error.PngException;

/**
 * This will build a single bitmap: the default image within the PNG file.
 * Any animation data (or any other data) will not be processed.
 */
public class DefaultImageArgbDirector extends BasicArgbDirector<ArgbBitmap> {

    protected ArgbBitmap defaultImage;

    @Override
    public void receiveHeader(PngHeader header, PngScanlineBuffer buffer) throws PngException {
        defaultImage = new ArgbBitmap(header.width, header.height);
        scanlineProcessor = ArgbProcessors.from(header, buffer, defaultImage);
    }

    @Override
    public boolean wantDefaultImage() {
        return true;
    }

    @Override
    public boolean wantAnimationFrames() {
        return false;
    }

    @Override
    public ScanlineProcessor beforeDefaultImage() {
        return scanlineProcessor;
    }

    @Override
    public void receiveDefaultImage(ArgbBitmap bitmap) {

    }

    @Override
    public void receiveAnimationControl(PngAnimationControl control) {

    }

    @Override
    public ScanlineProcessor receiveFrameControl(PngFrameControl control) {
        return null;
    }

    @Override
    public void receiveFrameImage(ArgbBitmap bitmap) {

    }

    @Override
    public ArgbBitmap getResult() {
        return defaultImage;
    }
}
