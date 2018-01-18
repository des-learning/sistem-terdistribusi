import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ThreadedOutputStream extends Thread {
    private final Socket socket;
    private final DataOutputStream outputStream;

    ThreadedOutputStream(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String request = input.nextLine();

            try {
                outputStream.writeUTF(request);
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
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
