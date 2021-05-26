package net.romvoid95.apng4j;

import java.io.InputStream;

import net.romvoid95.apng4j.argb.*;
import net.romvoid95.apng4j.error.PngException;
import net.romvoid95.apng4j.map.PngMap;
import net.romvoid95.apng4j.map.PngMapReader;
import net.romvoid95.apng4j.reader.*;
import net.romvoid95.apng4j.util.PngContainer;
import net.romvoid95.apng4j.util.PngContainerBuilder;

/**
 * Convenient one liners for loading PNG images.
 */
public class Png {

    /**
     * Read the provided stream and produce a PngMap of the data.
     *
     * @param is stream to read from
     * @param sourceName optional name, mainly for debugging.
     * @return PngMap of the data.
     * @throws PngException
     */
    public static PngMap readMap(InputStream is, String sourceName) throws PngException {
        return PngReadHelper.read(is, new PngMapReader(sourceName));
    }

    public static PngContainer readContainer(InputStream is) throws PngException {
        return PngReadHelper.read(is, new DefaultPngChunkReader<PngContainer>(new PngContainerBuilder()));
    }

    public static<ResultT> ResultT read(InputStream is, PngReader<ResultT> reader) throws PngException {
        return PngReadHelper.read(is, reader);
    }

    public static<ResultT> ResultT read(InputStream is, PngChunkProcessor<ResultT> processor) throws PngException {
        return PngReadHelper.read(is, new DefaultPngChunkReader<ResultT>(processor));
    }

    public static ArgbBitmap readArgbBitmap(InputStream is) throws PngException {
        ArgbProcessor<ArgbBitmap> processor = new ArgbProcessor<>(new DefaultImageArgbDirector());
        return PngReadHelper.read(is, new DefaultPngChunkReader<>(processor));
    }

    public static ArgbBitmapSequence readArgbBitmapSequence(InputStream is) throws PngException {
        ArgbProcessor<ArgbBitmapSequence> processor = new ArgbProcessor<>(new ArgbBitmapSequenceDirector());
        return PngReadHelper.read(is, new DefaultPngChunkReader<>(processor));
    }
}
