import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class InteractiveServer {
    public static void main(String []args) throws IOException {
        ServerSocket ss = new ServerSocket(1286);
        System.out.println("Listening on 1286...");

        while (true) {
            Socket s = ss.accept();
            Boolean loop = true;

            DataInputStream netInput = new DataInputStream(s.getInputStream());
            DataOutputStream netOutput = new DataOutputStream(s.getOutputStream());

            System.out.println(String.format("Client connected: %s", s.getRemoteSocketAddress()));

            while (loop) {
                String response = "Sorry, I don't understand";

                // baca request client
                try {
                    String request = netInput.readUTF();

                    // proses request
                    if (request.equals("stop") || request.equals("quit")) {
                        response = "Bye-bye";
                        loop = false;
                    } else if (request.equals("time") || request.equals("date")) {
                        response = new Date().toString();
                    }
                    // kirim response
                    netOutput.writeUTF(response);
                } catch (IOException e) {
                    // jika terjadi exception pada saat read/write
                    // asumsi client sudah terputus, keluar dari loop
                    break;
                }

            }

            System.out.println(String.format("Client disconnect: %s", s.getRemoteSocketAddress()));

            netInput.close();
            netOutput.close();
            s.close();
        }
    }
}
