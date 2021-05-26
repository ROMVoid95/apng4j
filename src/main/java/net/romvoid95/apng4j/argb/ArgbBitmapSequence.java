package net.romvoid95.apng4j.argb;

import java.util.ArrayList;
import java.util.List;

import net.romvoid95.apng4j.chunks.PngAnimationControl;
import net.romvoid95.apng4j.chunks.PngFrameControl;
import net.romvoid95.apng4j.chunks.PngHeader;

/**
 * An ArgbBitmapSequence object represents all bitmaps in a single PNG file,
 * whether it has one and only one default image, any number of frames in an animation
 * or a default image and a separate set of animation frames.
 * <p>
 * Note that instances of this class will hold an individual bitmap for every frame
 * and does <em>not</em> do composition of images in any way. Composition is done in
 * the japng_android library using an Android Canvas. This class is not used for that,
 * as the intermediate ArgbBitmap objects are not required, only one bitmap and
 * the output buffer is required during composition.
 */
public class ArgbBitmapSequence {

    public final PngHeader header;
    public final ArgbBitmap defaultImage;

    protected boolean defaultImageIsSet = false;
    protected PngAnimationControl animationControl;
    protected List<Frame> animationFrames;

    public ArgbBitmapSequence(PngHeader header) {
        this.header = header;
        this.defaultImage = new ArgbBitmap(header.width, header.height);
    }

    public void receiveAnimationControl(PngAnimationControl animationControl) {
        this.animationControl = animationControl;
        this.animationFrames = new ArrayList<>(animationControl.numFrames);
    }

    public void receiveDefaultImage(ArgbBitmap bitmap) {
        defaultImageIsSet = true;
    }

    public boolean hasDefaultImage() {
        return defaultImageIsSet;
    }

    public boolean isAnimated() {
        return null != animationControl && animationControl.numFrames > 0;
    }

    public PngAnimationControl getAnimationControl() {
        return animationControl;
    }

    public List<Frame> getAnimationFrames() {
        return animationFrames;
    }


    public static class Frame {
        public final PngFrameControl control;
        public final ArgbBitmap bitmap;

        public Frame(PngFrameControl control, ArgbBitmap bitmap) {
            this.control = control;
            this.bitmap = bitmap;
        }
    }
}
