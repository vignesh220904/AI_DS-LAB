import java.io.*;
import java.net.*;

public class EServer {
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader inputFromClient = null;
        PrintWriter outputToClient = null;

        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server is running...");
        } catch (IOException e) {
            System.out.println("Error creating server socket: " + e.getMessage());
            System.exit(1);
        }

        try {
            clientSocket = serverSocket.accept();
            inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            while ((line = inputFromClient.readLine()) != null) {
                System.out.println("Received from client: " + line);
                outputToClient.println(line);
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } finally {
            try {
                inputFromClient.close();
                outputToClient.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Exception while closing resources: " + e.getMessage());
            }
        }
    }
}
