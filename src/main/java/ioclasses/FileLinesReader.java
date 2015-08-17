//this package contains various useful IO utils
package ioclasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** This class uses FileReader + BufferedReader + StringBuilder to read
* lines (not bytes!), concatenate lines and return text.
* FileReader reads bytes from an internal FileInputStream and decodes them as
* UTF-8 multi-byte characters. BufferedReader does the same thing by wrapping a
* Reader and adding an internal buffer. Character decoding takes time, giving
* worse performance than simpler byte-reading classes.*/
public final class FileLinesReader {
    private String line;
    private BufferedReader buffer;
    private StringBuilder lineArray;

    public FileLinesReader(String filePath) throws FileNotFoundException {
        FileReader reader = new FileReader(filePath);
        buffer = new BufferedReader(reader);
    }

    public String read() throws IOException {
        lineArray = new StringBuilder();
        while ((line = buffer.readLine()) != null){
            lineArray.append(line);
            lineArray.append(System.getProperty("line.separator"));
        }
        return lineArray.toString();
    }
}





