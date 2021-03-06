package net.ellerton.japng;

import java.io.InputStream;

import net.ellerton.japng.argb8888.Argb8888Bitmap;
import net.ellerton.japng.argb8888.Argb8888BitmapSequence;
import net.ellerton.japng.argb8888.Argb8888BitmapSequenceDirector;
import net.ellerton.japng.argb8888.Argb8888Processor;
import net.ellerton.japng.argb8888.DefaultImageArgb8888Director;
import net.ellerton.japng.error.PngException;
import net.ellerton.japng.map.PngMap;
import net.ellerton.japng.map.PngMapReader;
import net.ellerton.japng.reader.DefaultPngChunkReader;
import net.ellerton.japng.reader.PngChunkProcessor;
import net.ellerton.japng.reader.PngReadHelper;
import net.ellerton.japng.reader.PngReader;
import net.ellerton.japng.util.PngContainer;
import net.ellerton.japng.util.PngContainerBuilder;

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

    public static Argb8888Bitmap readArgb8888Bitmap(InputStream is) throws PngException {
        Argb8888Processor<Argb8888Bitmap> processor = new Argb8888Processor<>(new DefaultImageArgb8888Director());
        return PngReadHelper.read(is, new DefaultPngChunkReader<>(processor));
    }

    public static Argb8888BitmapSequence readArgb8888BitmapSequence(InputStream is) throws PngException {
        Argb8888Processor<Argb8888BitmapSequence> processor = new Argb8888Processor<>(new Argb8888BitmapSequenceDirector());
        return PngReadHelper.read(is, new DefaultPngChunkReader<>(processor));
    }
}
