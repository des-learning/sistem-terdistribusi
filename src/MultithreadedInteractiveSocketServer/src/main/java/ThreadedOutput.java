import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ThreadedOutput extends Thread {
    private final DataOutputStream outputStream;
    private final Message response;
    private final Socket socket;

    ThreadedOutput(Socket socket, Message response) throws IOException {
        this.socket = socket;
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.response = response;
    }

    @Override
    public void run() {
        while (true) {
            String command;
            synchronized (this.response) {
                command = this.response.get();
                if (command == null) {
                    try {
                        this.response.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace(System.err);
                        break;
                    }
                }
                command = this.response.get();
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
