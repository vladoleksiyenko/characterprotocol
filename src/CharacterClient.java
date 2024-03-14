import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 This program tests the character server.
 */
public class CharacterClient
{
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8888;

        // localhost means we are connecting to our own device
        // that we are currently running on (loopback to ourselves)
        // localhost is a name for IP address 127.0.0.1
        try (Socket s = new Socket("localhost", SBAP_PORT))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            String health_command = "HEALTH_INCREASE 1 500000.0";
            System.out.println("Sending: " + health_command);
            out.print(health_command + "\n");
            out.flush();
            String response = in.nextLine();
            System.out.println("Receiving: " + response);

            System.out.println("");

            String damage_command = "DAMAGE_INCREASE 1 500000.0";
            System.out.println("Sending: " + damage_command);
            out.print(damage_command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            System.out.println("");

            health_command = "HEALTH_REDUCE 1 500000.0";
            System.out.println("Sending: " + health_command);
            out.print(health_command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            System.out.println("");

            damage_command = "DAMAGE_REDUCE 1 500000.0";
            System.out.println("Sending: " + damage_command);
            out.print(damage_command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            System.out.println();

            health_command = "GET_HEALTH 1";
            System.out.println("Sending: " + health_command);
            out.print(health_command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            damage_command = "GET_DAMAGE 1";
            System.out.println("Sending: " + damage_command);
            out.print(damage_command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            damage_command = "INVALID COMMAND";
            System.out.println("Sending: " + damage_command);
            out.print(damage_command);
            out.flush();

            String quit_command = "QUIT";
            System.out.println("Sending: " + quit_command);
            out.print(quit_command + "\n");
            out.flush();
        }
    }
}