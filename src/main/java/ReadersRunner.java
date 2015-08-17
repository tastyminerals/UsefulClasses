

import ioclasses.ApacheCommonsIOReader;
import ioclasses.ApacheCommonsIOSimpleLineReader;
import ioclasses.FileBytesReader;
import ioclasses.FileLinesReader;
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
    }

    public static void tryApacheCommonsIOSimpleLineReader(File f) {
        ApacheCommonsIOSimpleLineReader ar = null;
        String text = "";
        try {
            ar = new ApacheCommonsIOSimpleLineReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
        }
        text = ar.read();

        //System.out.println(text);
    }

    public static void tryApacheCommonsIOReader(File f) {
        ApacheCommonsIOReader ar = null;
        String text = "";
        try {
            ar = new ApacheCommonsIOReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
        }
        text = ar.read();

        //System.out.println(text);
    }

    public static void tryFileBytesReader(String fpath) {
        FileBytesReader fr = null;
        String text = "";
        try {
            fr = new FileBytesReader(fpath);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
        }
        try {
            text = fr.read();
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
        }

        //System.out.println(text);
    }


    public static void tryFileLinesReader(String fpath) {
        FileLinesReader fr = null;
        String text = "";
        try {
            fr = new FileLinesReader(fpath);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!: " + ex);
        }
        try {
            text = fr.read();
        } catch (IOException ex) {
            System.out.println("IOException!: " + ex);
        }

        //System.out.println(text);
    }
}
