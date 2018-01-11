import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
    public static void main(String[] args) throws IOException {
        // server mendengarkan koneksi pada port 1286
        ServerSocket ss = new ServerSocket(1286);
        System.out.println(String.format("Listening on %d...", 1286));
        // main loop
        while (true) {
            // terima koneksi dari client
            Socket s = ss.accept();

            // prepare output stream
            OutputStream socketOutStream = s.getOutputStream();
            DataOutputStream socketDOS = new DataOutputStream(socketOutStream);

            // send to client
            socketDOS.writeUTF("Hello World!");

            // close stream and client connection
            socketDOS.close();
            socketOutStream.close();
            s.close();
            if (!true)
                break;
        }
        ss.close();
    }
}
