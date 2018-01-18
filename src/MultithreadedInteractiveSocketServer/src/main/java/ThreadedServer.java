import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ThreadedServer extends Thread {
    private final Socket socket;
    private final ThreadedInput inputThread;
    private final ThreadedOutput outputThread;
    private final Message requests;
    private final Message responses;

    ThreadedServer(Socket s) throws IOException {
        this.socket = s;
        this.requests = new Message();
        this.responses = new Message();
        this.inputThread = new ThreadedInput(this.socket, this.requests);
        this.outputThread = new ThreadedOutput(this.socket, this.responses);
    }

    @Override
    public void run() {
        boolean loop = true;

        this.inputThread.start();
        this.outputThread.start();

        while (loop) {
            // baca dari request, kalau ada proses request
            // tambahkan ke response, notify
            // wait for request
            synchronized (this.requests) {
                String command = this.requests.get();

                if (command != null) {
                    synchronized (this.responses) {
                        switch(command) {
                            case "stop":
                                this.responses.add("bye-bye");
                                loop = false;
                                break;
                            case "hello":
                                this.responses.add("Hello world");
                                break;
                            case "time":
                                this.responses.add(new Date().toString());
                                break;
                            default:
                                this.responses.add("sorry I don't understand");
                        }
                        this.responses.notify();
                    }
                }

                if (!loop) break;

                try {
                    this.requests.wait();
                } catch (InterruptedException e) {
                }
            }
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}


