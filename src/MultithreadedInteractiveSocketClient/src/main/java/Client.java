import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String []args) throws IOException {
        final Socket socket = new Socket("localhost", 1286);

        System.out.println("Connected to the server");

        // start input and output thread
        final ThreadedInputStream inputStream = new ThreadedInputStream(socket);
        final ThreadedOutputStream outputStream = new ThreadedOutputStream(socket);

        inputStream.start();
        outputStream.start();

        while (inputStream.isAlive() && outputStream.isAlive());

        System.out.println("Disconnected from server");
        socket.close();
    }
}
