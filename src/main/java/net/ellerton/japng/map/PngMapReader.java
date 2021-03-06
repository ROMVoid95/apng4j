package net.ellerton.japng.map;

import java.io.IOException;
import java.util.ArrayList;

import net.ellerton.japng.*;
import net.ellerton.japng.error.PngException;
import net.ellerton.japng.reader.PngReader;
import net.ellerton.japng.reader.PngSource;

/**
 * Simple processor that skips all chunk content and ignores checksums, with
 * sole objective of building a map of the contents of a PNG file.
 * <p>
 *     WARNING: not sure if this API will remain.
 * </p>
 */
public class PngMapReader implements PngReader<PngMap> {
    PngMap map;

    public PngMapReader(String sourceName) {
        map = new PngMap();
        map.source = sourceName;
        map.chunks = new ArrayList<>(4);
    }

    @Override
    public boolean readChunk(PngSource source, int code, int dataLength) throws PngException, IOException {
        int dataPosition = source.tell();
        source.skip(dataLength);
        int chunkChecksum = source.readInt();
        map.chunks.add(new PngChunkMap(PngChunkCode.from(code), dataPosition, dataLength, chunkChecksum));

        return code == PngConstants.IEND_VALUE;
    }

    @Override
    public void finishedChunks(PngSource source) throws PngException, IOException {
        // NOP
    }

    @Override
    public PngMap getResult() {
        return map;
    }
}
