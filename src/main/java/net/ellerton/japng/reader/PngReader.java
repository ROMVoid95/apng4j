package net.ellerton.japng.reader;

import java.io.IOException;

import net.ellerton.japng.error.PngException;

/**
 * All PngReader implementations need to read a specific single chunk and to return
 * a result of some form.
 */
public interface PngReader<ResultT> {
    boolean readChunk(PngSource source, int code, int dataLength) throws PngException, IOException;
    void finishedChunks(PngSource source) throws PngException, IOException;
    ResultT getResult();
}
