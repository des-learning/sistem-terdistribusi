import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * thread untuk melayani user
 */
public class ServerThread extends Thread {
    // object untuk mengirimkan output ke client
    private final OutputStream socketOutStream;
    private final DataOutputStream dos;

    // object untuk membaca input dari client
    private final InputStream socketInStream;
    private final DataInputStream dis;

    // list perintah dan proses yang akan dijalankan
    private final Map<String, Command> commands;

    ServerThread(Socket s) throws IOException {
        // initialize output stream
        this.socketOutStream = s.getOutputStream();
        this.dos = new DataOutputStream(socketOutStream);

        // initialize input stream
        this.socketInStream = s.getInputStream();
        this.dis = new DataInputStream(socketInStream);

        // initialize commands
        this.commands = new HashMap<>();
        commands.put("time", () -> {
            writeStream(ZonedDateTime.now().toString() + "\n");
        });
        commands.put("who are you", () -> {
            writeStream("I am your servant, Master\n");
        });
        commands.put("memory", () -> {
            writeStream("Free memory: " + Runtime.getRuntime().freeMemory() + "\n");
        });
    }

    /**
     * menjalankan perintah yang dikirim oleh client/user.
     *
     * @param command perintah yang dikirim oleh client/user
     */
    private void doCommand(String command) {
        Command cmd = commands.get(command);

        if (cmd == null)
            writeStream(String.format("Master, you were saying: \"%s\"?\n", command));
        else
            cmd.execute();
    }

    /**
     * thread main program
     */
    @Override
    public void run() {

        Boolean loop = true;
        // main loop
        while(loop) {
            // baca perintah dari client
            final String input = readStream();

            // stop and disconnect
            if (input.equals("stop")) {
                writeStream("Bye-bye Master\n");
                loop = false;
            } else {
                doCommand(input);
            }
        }
        endConnection();
    }

    /**
     * mengirimkan pesan ke client
     * @param message pesan yang akan dikirimkan
     */
    private void writeStream(String message) {
        try {
            this.dos.writeUTF(message);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * membaca input dari client
     * @return String input yang dikirim dari client
     */
    private String readStream() {
        String result = "";
        try {
            result = dis.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * menutup stream dan putuskan koneksi
     */
    private void endConnection() {
        try {
            this.dis.close();
            this.dos.close();
            this.socketInStream.close();
            this.socketOutStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
