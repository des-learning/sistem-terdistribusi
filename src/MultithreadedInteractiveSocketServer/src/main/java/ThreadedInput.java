import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class ThreadedInput extends Thread {
    private final DataInputStream inputStream;
    private final Message request;
    private final Socket socket;

    ThreadedInput(Socket socket, Message request) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.request = request;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = this.inputStream.readUTF();

                if (! command.equals("")) {
                    synchronized (this.request) {
                        this.request.add(command);
                        this.request.notify();
                    }
                }
            } catch (IOException e) {
                // client disconnect
                System.out.println(String.format("Client disconnect from %s", socket.getRemoteSocketAddress()));
                break;
            }
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
