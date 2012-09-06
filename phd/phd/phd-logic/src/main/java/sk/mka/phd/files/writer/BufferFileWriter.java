package sk.mka.phd.files.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public class BufferFileWriter {

    public static BufferedWriter openFileForWriting(final String filename) throws IOException {
        FileWriter ofstream = null;
        //open the same file
        BufferedWriter out = null;
        ofstream = new FileWriter(filename);
        out = new BufferedWriter(ofstream);
        return out;
    }
}
