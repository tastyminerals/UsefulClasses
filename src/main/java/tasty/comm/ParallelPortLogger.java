package tasty.comm;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.ParallelPort;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.io.OutputStream;

import sun.io.CharToByteConverter;

/** Simple parallel port logger utilizing RXTX library.
 * operation. The script can work either in reading or writing mode.
 * This script reads or listens to parallel port and logs each successful
 * Each read/write operation is accompanied by a timestamp written to csv file.
 */
public class ParallelPortLogger {
    private static OutputStream outStream;
    private static ParallelPort pPort;
    private static CommPortIdentifier port;

    // CONSTANTS
    public static final String PARALLEL_PORT = "LPT1";
    public static final String[] PORT_TYPE = {"Serial Port", "Parallel Port"};

    // Data to transfer
    private static String message = "hello";

    public static void main(String [] args) {
        System.out.println("Checking LPT1...");

        try {
           // do stuff



        } catch (NoSuchPortException nspe) {
            System.out.println("NoSuchPortException! Parallel port LPT1 not found\n" + nspe);
        } catch (PortInUseException piue) {
            System.out.println("PortInUseException! Parallel port is in use\n" + piue);
        } catch (IOException ioe) {
            System.out.println("IOException! Failed to read/write on LPT1\n" + ioe);
        } catch (Exception e) {
            System.out.println("Failed to open parallel printer LPT1\n" + e);
        } finally {
            if (port != null && port.isCurrentlyOwned()) {
                port.close();
            }
            System.out.println("Communication successfully closed.");

        }
    }

}
