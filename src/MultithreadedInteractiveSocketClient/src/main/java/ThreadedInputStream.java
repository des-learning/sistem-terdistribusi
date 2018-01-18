import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

class ThreadedInputStream extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;

    ThreadedInputStream(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String response = inputStream.readUTF();
                System.out.println(response);
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                break;
            }
        }

        try {
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
