import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection() throws IOException {
        clientSocket = new Socket("localhost", 1998);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
