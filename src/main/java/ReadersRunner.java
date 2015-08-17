import com.google.common.base.Charsets;
import com.google.common.io.Files;
import ioclasses.ApacheCommonsIOReader;
import ioclasses.ApacheCommonsIOSimpleLineReader;
import ioclasses.FileBytesReader;
import ioclasses.FileLinesReader;
import ioclasses.GuavaBufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** simple ReadersRunner testing which runs various classes from ioclasses */
public class ReadersRunner {
    public static void main(String[] args) {
        // some arbitrary test file path
        final String filepath = "/home/tastyminerals/Public/dev/huge.txt";
        final File f = new File("/home/tastyminerals/Public/dev/huge.txt");
        // tryFileLinesReader(filepath);
        // tryFileBytesReader(filepath);
        // tryApacheCommonsIOReader(f);
        // tryApacheCommonsIOSimpleLineReader(f);
        // tryGuavaBufferedReader(f);
    }

    public static void tryGuavaBufferedReader(File f) {
        GuavaBufferedReader reader = null;
        String text = "";
        try {
            reader = new GuavaBufferedReader(f);
            text = reader.read();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
            throw new RuntimeException(ex);
        }
        //System.out.println(text);
    }

    public static void tryApacheCommonsIOSimpleLineReader(File f) {
        ApacheCommonsIOSimpleLineReader reader = null;
        String text = "";
        try {
            reader = new ApacheCommonsIOSimpleLineReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
            throw new RuntimeException(ex);
        }
        text = reader.read();

        //System.out.println(text);
    }

    public static void tryApacheCommonsIOReader(File f) {
        ApacheCommonsIOReader reader = null;
        String text = "";
        try {
            reader = new ApacheCommonsIOReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
            throw new RuntimeException(ex);
        }
        text = reader.read();

        //System.out.println(text);
    }

    public static void tryFileBytesReader(String fpath) {
        FileBytesReader reader = null;
        String text = "";
        try {
            reader = new FileBytesReader(fpath);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
            throw new RuntimeException(ex);
        }
        try {
            text = reader.read();
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
            throw new RuntimeException(ex);
        }

        //System.out.println(text);
    }


    public static void tryFileLinesReader(String fpath) {
        FileLinesReader reader = null;
        String text = "";
        try {
            reader = new FileLinesReader(fpath);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
            throw new RuntimeException(ex);
        }
        try {
            text = reader.read();
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
            throw new RuntimeException(ex);
        }

        //System.out.println(text);
    }
}
