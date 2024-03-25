import java.io.*;
import java.net.*;

public class EClient {
    public static void main(String args[]) {
        Socket c = null;
        String line;
        BufferedReader userInput = null;
        PrintWriter outputToServer = null;
        BufferedReader inputFromServer = null;

        try {
            c = new Socket("localhost", 8080);
            userInput = new BufferedReader(new InputStreamReader(System.in));
            outputToServer = new PrintWriter(c.getOutputStream(), true);
            inputFromServer = new BufferedReader(new InputStreamReader(c.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            System.out.println("Enter text ('exit' to quit)");
            while (true) {
                line = userInput.readLine();
                if (line.equals("exit")) break;
                outputToServer.println(line);
                System.out.println("Server: " + inputFromServer.readLine());
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } finally {
            try {
                userInput.close();
                outputToServer.close();
                inputFromServer.close();
                c.close();
            } catch (IOException e) {
                System.out.println("Exception while closing resources: " + e.getMessage());
            }
        }
    }
}
