package net.romvoid95.apng4j.util;

/**
 * Returns the PngContainer built by the PngContainerProcessor.
 * WARNING: this may be removed.
 */
public class PngContainerBuilder extends PngContainerProcessor<PngContainer> {

//    @Override
//    public PngAnimationType chooseApngImageType(PngAnimationType type, PngFrameControl currentFrame) throws PngException {
//        return type;
//    }

    @Override
    public PngContainer getResult() {
        return getContainer();
    }
}
