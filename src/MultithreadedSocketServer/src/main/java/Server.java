import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server yang menerima koneksi dari client
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1286);
        System.out.println(String.format("Listening on %d...", 1286));
        // main loop forever
        while (true) {
            // setelah menerima koneksi dari client, bentuk thread untuk
            // melayani user
            Socket s = ss.accept();
            new ServerThread(s).start();
            if (false) break;
        }
        ss.close();
    }
}
