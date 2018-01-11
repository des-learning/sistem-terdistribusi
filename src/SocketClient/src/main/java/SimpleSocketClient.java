import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String []args) throws IOException {
        // connect to localhost 1286
        Socket s = new Socket("localhost", 1286);
        System.out.println("Connected....");

        // input stream
        InputStream inputStream = s.getInputStream();
        DataInputStream socketDIS = new DataInputStream(inputStream);

        // read response from server
        String testString = socketDIS.readUTF();
        System.out.println(testString);

        // close stream and connection
        socketDIS.close();
        inputStream.close();
        s.close();
    }
}
