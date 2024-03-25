import java.net.*;
import java.io.*;

public class TCPclient1 {
    public static void main(String arg[]) {
        Socket c = null;
        String line;
        DataInputStream is, is1;
        PrintStream os;

        try {
            c = new Socket("10.0.200.36", 9999);
            os = new PrintStream(c.getOutputStream());
            is = new DataInputStream(System.in);
            is1 = new DataInputStream(c.getInputStream());
            
            do {
                System.out.print("Client: ");
                line = is.readLine();
                os.println(line);
                System.out.println("Server: " + is1.readLine());
            } while (!line.equalsIgnoreCase("quit"));
            
            is.close();
            os.close();
            c.close();
        } catch (IOException e) {
            System.out.println("Socket Closed! Message Passing is over");
        }
    }
}
