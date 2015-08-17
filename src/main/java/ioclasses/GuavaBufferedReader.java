package ioclasses;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/* This is a file reader implementation using Guava.
Second fastest in ioclasses. */
public final class GuavaBufferedReader {
    private final CharSource charSource;
    private final BufferedReader buffReader;
    private final StringBuilder strBuilder;

    public GuavaBufferedReader(File f) throws IOException {
        charSource = Files.asCharSource(f, Charsets.UTF_8);
        buffReader = charSource.openBufferedStream();
        strBuilder = new StringBuilder();
    }

    public String read() throws IOException {
        String line;
        while ((line = buffReader.readLine()) != null) {
            strBuilder.append(line);
        }
        buffReader.close();
        return strBuilder.toString();
    }
}
