package net.romvoid95.apng4j.argb;

import net.romvoid95.apng4j.PngScanlineBuffer;
import net.romvoid95.apng4j.chunks.PngAnimationControl;
import net.romvoid95.apng4j.chunks.PngFrameControl;
import net.romvoid95.apng4j.chunks.PngHeader;
import net.romvoid95.apng4j.error.PngException;

/**
 * ArgbBitmapSequenceDirector instances direct an ArgbProcessor to build all frames
 * of an animation into an ArgbBitmapSequence object.
 */
public class ArgbBitmapSequenceDirector extends BasicArgbDirector<ArgbBitmapSequence> {
	ArgbBitmapSequence bitmapSequence = null;
	PngFrameControl currentFrame = null;
	private PngHeader header;

	@Override
	public void receiveHeader(PngHeader header, PngScanlineBuffer buffer) throws PngException {
		this.header = header;
		this.bitmapSequence = new ArgbBitmapSequence(header);
		this.scanlineProcessor = ArgbProcessors.from(header, buffer, this.bitmapSequence.defaultImage);
	}

	@Override
	public boolean wantDefaultImage() {
		return true;
	}

	@Override
	public boolean wantAnimationFrames() {
		return true;
	}

	@Override
	public ScanlineProcessor beforeDefaultImage() {
		return scanlineProcessor;
	}

	@Override
	public void receiveDefaultImage(ArgbBitmap bitmap) {
		this.bitmapSequence.receiveDefaultImage(bitmap);
	}

	@Override
	public void receiveAnimationControl(PngAnimationControl control) {
		this.bitmapSequence.receiveAnimationControl(control);
	}

	@Override
	public ScanlineProcessor receiveFrameControl(PngFrameControl control) {
		currentFrame = control;

		return scanlineProcessor.cloneWithNewBitmap(header.adjustFor(control)); // TODO: is this going to be a problem?
	}

	@Override
	public void receiveFrameImage(ArgbBitmap bitmap) {
		if (null == currentFrame) {
			throw new IllegalStateException("Received a frame image with no frame control in place");
		}
		if (null == bitmapSequence.animationFrames) {
			throw new IllegalStateException("Received a frame image without animation control (or frame list?) in place");
		}
		bitmapSequence.animationFrames.add(new ArgbBitmapSequence.Frame(currentFrame, bitmap));
		currentFrame = null;
	}

	@Override
	public ArgbBitmapSequence getResult() {
		return bitmapSequence;
	}
}
