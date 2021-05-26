package net.romvoid95.apng4j.argb;

import net.romvoid95.apng4j.chunks.PngHeader;
import net.romvoid95.apng4j.reader.BasicScanlineProcessor;

/**
 * Base implementation that transforms scanlines of source pixels into scanlines of
 * ARGB (32-bit integer) pixels in a destination ArgbBitmap object.
 *
 * Note: I wonder if a better name is ScanlineConverter or PixelTransformer.
 *
 * @see ArgbProcessors
 */
public abstract class ScanlineProcessor extends BasicScanlineProcessor {
	protected ArgbBitmap bitmap;
	protected ArgbPalette palette;
	protected int y=0;

	public ScanlineProcessor(int bytesPerScanline, ArgbBitmap bitmap) {
		super(bytesPerScanline);
		this.bitmap = bitmap;
	}

	public ArgbBitmap getBitmap() {
		return bitmap;
	}

	public ArgbPalette getPalette() {
		return palette;
	}

	public void setPalette(ArgbPalette palette) {
		this.palette = palette;
	}

	/**
	 * When processing a sequence of frames a caller may want to write to a completely
	 * new bitmap for every frame or re-use the bytes in an existing bitmap.
	 *
	 * @param header of image to use as basis for calculating image size.
	 *
	 * @return processor that will write to a new bitmap.
	 */
	public ScanlineProcessor cloneWithNewBitmap(PngHeader header) {
		ScanlineProcessor cloned = clone(header.bytesPerRow, new ArgbBitmap(header.width, header.height));
		if(this.palette != null){
			cloned.setPalette(new ArgbPalette(palette.argbArray));
		}
		return cloned;

	}

	/**
	 * When processing a sequence of frames a caller may want to write to a completely
	 * new bitmap for every frame or re-use the bytes in an existing bitmap.
	 *
	 * @param header of image to use as basis for calculating image size.
	 *
	 * @return processor that will write to the same bitmap as the current processor.
	 */
	public ScanlineProcessor cloneWithSharedBitmap(PngHeader header) {
		ScanlineProcessor cloned = clone(header.bytesPerRow, bitmap.makeView(header.width, header.height));
		if(this.palette != null){
			cloned.setPalette(new ArgbPalette(palette.argbArray));
		}
		return cloned;

	}

	abstract ScanlineProcessor clone(int bytesPerRow, ArgbBitmap bitmap);
}
