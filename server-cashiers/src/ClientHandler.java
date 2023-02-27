import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    break;
                }
                System.out.println(inputLine);
            }

            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception catched on client handler: " + e.getMessage());
        }
    }

}
