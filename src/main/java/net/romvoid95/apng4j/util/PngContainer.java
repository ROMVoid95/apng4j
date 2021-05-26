package net.romvoid95.apng4j.util;

import java.util.ArrayList;
import java.util.List;

import net.romvoid95.apng4j.chunks.PngAnimationControl;
import net.romvoid95.apng4j.chunks.PngFrameControl;
import net.romvoid95.apng4j.chunks.PngGamma;
import net.romvoid95.apng4j.chunks.PngHeader;
import net.romvoid95.apng4j.chunks.PngPalette;
import net.romvoid95.apng4j.map.PngChunkMap;

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
