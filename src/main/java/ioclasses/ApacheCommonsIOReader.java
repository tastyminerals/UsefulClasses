package ioclasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/* LineIterator reader using Apache Commons IO */
public final class ApacheCommonsIOReader {
    private final LineIterator lit;
    private StringBuilder builder;

    public ApacheCommonsIOReader(File file) throws FileNotFoundException, IOException {
        lit = FileUtils.lineIterator(file, "UTF-8");
    }

    public String read() {
        builder = new StringBuilder();
        try {
            while (lit.hasNext()) {
                builder.append(lit.nextLine());
                builder.append(System.getProperty("line.separator"));
            }
        } finally {
            lit.close();
        }
        return builder.toString();
    }
}
