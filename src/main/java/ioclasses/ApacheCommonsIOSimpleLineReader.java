package ioclasses;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* This is simple and short file reader implementation using Apache Commons */
public final class ApacheCommonsIOSimpleLineReader {
    private final List lines;

    public ApacheCommonsIOSimpleLineReader(File f) throws IOException {
        lines = FileUtils.readLines(f, "UTF-8");
    }

    public String read() {
        return lines.toString();
    }
}
