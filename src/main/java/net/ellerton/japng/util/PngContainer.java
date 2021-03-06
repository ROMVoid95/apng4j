package net.ellerton.japng.util;

import java.util.ArrayList;
import java.util.List;

import net.ellerton.japng.chunks.PngAnimationControl;
import net.ellerton.japng.chunks.PngFrameControl;
import net.ellerton.japng.chunks.PngGamma;
import net.ellerton.japng.chunks.PngHeader;
import net.ellerton.japng.chunks.PngPalette;
import net.ellerton.japng.map.PngChunkMap;

/**
 * A PngContainer represents the parsed content of a PNG file.
 * <p>
 * The original idea was that all implementations can use this as a "container"
 * for representing the data, but I think it is too generic to be useful.
 * <p>
 *     WARNING: not sure if this API will remain.
 * </p>
 */
public class PngContainer {
	public List<PngChunkMap> chunks = new ArrayList<>(4);
	public PngHeader header;
	public PngGamma gamma;
	public PngPalette palette;
	public PngAnimationControl animationControl;
	public List<PngFrameControl> animationFrames;
	public PngFrameControl currentFrame;
	public boolean hasDefaultImage = false;

	public PngHeader getHeader() {
		return header;
	}

	public PngGamma getGamma() {
		return gamma;
	}

	public boolean isAnimated() {
		return animationControl != null;
	}
}
