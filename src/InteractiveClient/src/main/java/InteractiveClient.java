import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class InteractiveClient {
    public static void main(String []args) throws IOException {
        Socket s = new Socket("localhost", 1286);
        Scanner input = new Scanner(System.in);

        DataInputStream netInput = new DataInputStream(s.getInputStream());
        DataOutputStream netOutput = new DataOutputStream(s.getOutputStream());

        System.out.println("Connected...");

        while (true) {
            try {
                // baca input dari keyboard
                String command = input.nextLine();
                // kirim request ke server
                netOutput.writeUTF(command);
                // baca response dari server
                String response = netInput.readUTF();
                // tampilkan ke layar monitor
                System.out.println("Response from server: " + response);
            } catch (IOException e) {
                // jika terjadi exception asumsi koneksi sudah terputus
                // keluar dari loop
                break;
            }
        }

        netInput.close();
        netOutput.close();
        s.close();
    }
}
