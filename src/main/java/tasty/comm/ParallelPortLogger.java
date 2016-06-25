package tasty.comm; // comment this line out to run standalone

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.ParallelPort;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Simple parallel port logger utilizing RXTX library.
 * The script can work either in reading or writing mode.
 * This script reads or listens to parallel port and logs each successful operation.
 * Each read/write operation is accompanied by a timestamp written to csv file.
 * The script runs for 3h (can be changed) unless terminated manually.
 */
public class ParallelPortLogger {
    private static FileWriter writer;
    private static OutputStream outStream;
    private static InputStream inputStream;
    private static ParallelPort pport;
    private static CommPortIdentifier port;
    public static Map<String, String> pports = new HashMap<>();

    // CONSTANTS
    private static final DateFormat DATEF = new SimpleDateFormat("dd/MM/yy,HH:mm:ss");
    private static final Date DATE = new Date();
    // Script Timer: 10800000 millisec == 3h, 3600000 millisec == 1h
    private static final long ENDTIME = System.currentTimeMillis() + 3600000;
    // Set logging interval in millisec
    private static final int LOGINTERVAL = 500;

    public static final String[] PORT_TYPE = {"Serial Port", "Parallel Port"};
    // Data to transfer
    private static final String MESSAGE = "hello";


    public static void write(String message) throws IOException {
        try {
            // port writing
            outStream = pport.getOutputStream();
            byte[] byteArray = message.getBytes();
            System.out.println("Sending hello...");
            outStream.write(byteArray);
            outStream.flush();
        } finally {
            outStream.close();
        }
    }

    public static int[] read() throws IOException{
        int b;
        int i = 0;
        int[] ints = null;
        try {
            // port reading
            inputStream = pport.getInputStream();
            while ((b = inputStream.read()) != -1) {
                ints[i] = b;
                i++;
            }
        } finally {
            inputStream.close();
        }
        return ints;
    }

    public static void main(String [] args) {
        System.out.println("Checking ports...");
        pports.put("Windows", "LPT1"); // Windows parallel port
        pports.put("Linux", "/dev/lp0"); // Linux parallel port
        try {
            // get connected parallel port name and type
            port = CommPortIdentifier.getPortIdentifier(pports.get(System.getProperty("os.name")));
            System.out.println("Name:" + port.getName());
            System.out.println("Type:" + PORT_TYPE[port.getPortType() - 1]);
            // open parallel port
            pport = (ParallelPort) port.open("CommTest", 50);
            if (args[0].equals("write")) {
                writer = new FileWriter("LPT1-write.csv");
                while (System.currentTimeMillis() < ENDTIME) {
                    // construct time stamp
                    writer.append("WRITE," + DATEF.format(DATE) + "," + System.currentTimeMillis());
                    Thread.sleep(500);
                }
                writer.close();

            } else if (args[0].equals("read")) {
                writer = new FileWriter("LPT1-read.csv");
                while (System.currentTimeMillis() < ENDTIME) {
                    // construct time stamp
                    writer.append("WRITE," + DATEF.format(DATE) + "," + System.currentTimeMillis());
                    System.out.println("RECEIVED:" + Arrays.toString(ParallelPortLogger.read()));
                    Thread.sleep(500);
                }
                writer.close();
            }

        } catch (NoSuchPortException nspe) {
            System.out.println("NoSuchPortException! Parallel port LPT1 not found\n");
        } catch (PortInUseException piue) {
            System.out.println("PortInUseException! Parallel port is in use\n");
        } catch (IOException ioe) {
            System.out.println("IOException! Failed to read/write on LPT1\n" );
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("Error! Specify \"read\" or \"write\" mode");
        } catch (InterruptedException ex) {
            Logger.getLogger(ParallelPortLogger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (port != null && port.isCurrentlyOwned()) {
                pport.close();
            }
            System.out.println("Communication successfully closed.");
        }
    }
}
