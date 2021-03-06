package net.ellerton.japng.argb8888;

import net.ellerton.japng.chunks.PngHeader;
import net.ellerton.japng.reader.BasicScanlineProcessor;

/**
 * Base implementation that transforms scanlines of source pixels into scanlines of
 * ARGB (32-bit integer) pixels in a destination ArgbBitmap object.
 *
 * Note: I wonder if a better name is ScanlineConverter or PixelTransformer.
 *
 * @see Argb8888Processors
 */
public abstract class Argb8888ScanlineProcessor extends BasicScanlineProcessor {
	protected Argb8888Bitmap bitmap;
	protected Argb8888Palette palette;
	protected int y=0;

	public Argb8888ScanlineProcessor(int bytesPerScanline, Argb8888Bitmap bitmap) {
		super(bytesPerScanline);
		this.bitmap = bitmap;
	}

	public Argb8888Bitmap getBitmap() {
		return bitmap;
	}

	public Argb8888Palette getPalette() {
		return palette;
	}

	public void setPalette(Argb8888Palette palette) {
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
	public Argb8888ScanlineProcessor cloneWithNewBitmap(PngHeader header) {
		Argb8888ScanlineProcessor cloned = clone(header.bytesPerRow, new Argb8888Bitmap(header.width, header.height));
		if(this.palette != null){
			cloned.setPalette(new Argb8888Palette(palette.argbArray));
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
	public Argb8888ScanlineProcessor cloneWithSharedBitmap(PngHeader header) {
		Argb8888ScanlineProcessor cloned = clone(header.bytesPerRow, bitmap.makeView(header.width, header.height));
		if(this.palette != null){
			cloned.setPalette(new Argb8888Palette(palette.argbArray));
		}
		return cloned;

	}

	abstract Argb8888ScanlineProcessor clone(int bytesPerRow, Argb8888Bitmap bitmap);
}
