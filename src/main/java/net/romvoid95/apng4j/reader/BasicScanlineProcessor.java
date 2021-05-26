package net.romvoid95.apng4j.reader;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.zip.Inflater;

import net.romvoid95.apng4j.util.PartialInflaterInputStream;

/**
 * A BasicScanlineProcessor manages a re-entrant java.util.zip.Inflater object and
 * basic bytesPerLine management.
 */
public abstract class BasicScanlineProcessor implements PngScanlineProcessor {
	protected final int bytesPerLine;
	protected Inflater inflater = new Inflater();

	public BasicScanlineProcessor(int bytesPerScanline) {
		this.bytesPerLine = bytesPerScanline;
	}

	@Override
	public FilterInputStream makeInflaterInputStream(InputStream inputStream) {
		return new PartialInflaterInputStream(inputStream, inflater);
	}

	@Override
	public int getBytesPerLine() {
		return bytesPerLine;
	}

	@Override
	public boolean isFinished() {
		return inflater.finished();
	}

	@Override
	public void processTransparentGreyscale(byte k1, byte k0) {
		// NOP by default
	}

	@Override
	public void processTransparentTruecolour(byte r1, byte r0, byte g1, byte g0, byte b1, byte b0) {
		// NOP by default
	}
}
