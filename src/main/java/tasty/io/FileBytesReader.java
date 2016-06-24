package tasty.io;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** This class uses RandomAccessFile + FileChannel + ByteBuffer + byte array
 * to efficiently read file data into buffered byte arrays. Byte array is then
 * written to ByteArrayOutputStream and converted to String.
 * This method is the fastest in ioclasses.*/
public final class FileBytesReader {
    private final FileChannel fileChannel;
    private final ByteBuffer bbuffer;
    // default value is 8192
    private final int BUFFERSIZE = 8192;
    private ByteArrayOutputStream baos = null;

    public FileBytesReader(String filepath) throws FileNotFoundException {
        RandomAccessFile rndFile = new RandomAccessFile(filepath, "r");
        bbuffer = ByteBuffer.allocateDirect(BUFFERSIZE);
        fileChannel = rndFile.getChannel();
        baos = new ByteArrayOutputStream();
    }

    public String read() throws IOException {
        byte[] bytes = new byte[BUFFERSIZE];
        while (fileChannel.read(bbuffer) > 0) {
            bbuffer.flip(); // reseting byte buffer pointer
            for (int i = 0; i < bbuffer.limit(); i++) {
                bytes[i] = bbuffer.get();
            }
            baos.write(bytes); // writing data to growable byte array
            bbuffer.clear();
        }
        fileChannel.close();
        //return baos.toString("UTF-8");
        return baos.toString();
    }
}
