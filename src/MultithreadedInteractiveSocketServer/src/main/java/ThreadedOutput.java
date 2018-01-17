import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ThreadedOutput extends Thread {
    private final DataOutputStream outputStream;
    private final Request request;
    private final Socket socket;

    ThreadedOutput(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.request = request;
    }

    @Override
    public void run() {
        while (true) {
            String command;
            synchronized (this.request) {
                command = this.request.get();
                if (command == null) {
                    try {
                        this.request.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace(System.err);
                        break;
                    }
                    command = this.request.get();
                }
            }

            try {
                outputStream.writeUTF(command);
            } catch (IOException e) {
                System.out.println(String.format("Client disconnect from %s", this.socket.getRemoteSocketAddress()));
                break;
            }
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
