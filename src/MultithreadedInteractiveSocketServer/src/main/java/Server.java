import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String []args) throws IOException {
       final ServerSocket ss = new ServerSocket(1286);

       System.out.println("Listening on 1286...");

       while (true) {
           Socket s = ss.accept();

           System.out.println(String.format("Client connected from %s", s.getRemoteSocketAddress()));

           new ThreadedServer(s).start();

           // workaround for unreachable code
           if (false) break;
       }

       ss.close();
    }
}
