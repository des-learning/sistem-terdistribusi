import java.io.IOException;
import java.net.Socket;

public class ThreadedServer extends Thread {
    private final Socket socket;
    private final ThreadedInput inputThread;
    private final ThreadedOutput outputThread;

    ThreadedServer(Socket s) throws IOException {
        this.socket = s;
        Request request = new Request();
        this.inputThread = new ThreadedInput(this.socket, request);
        this.outputThread = new ThreadedOutput(this.socket, request);
    }

    @Override
    public void run() {

        this.inputThread.start();
        this.outputThread.start();

        while (true) {
            if (false) break;
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}


