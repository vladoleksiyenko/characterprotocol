import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Character Access Protocol commands
 from a socket.
 */
public class CharacterService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private UserCharacters characters;

    /**
     Constructs a service object that processes commands
     from a socket for a character.
     @param aSocket the socket
     @param userCharacters the character container
     */
    public CharacterService(Socket aSocket, UserCharacters userCharacters)
    {
        s = aSocket;
        characters = userCharacters;
    }

    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }

            String command = in.next();

            if (command.equals("QUIT")) {
                return;
            } else {
                executeCommand(command);
            }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command)
    {
        int characterID = in.nextInt();
        if (command.equals("HEALTH_INCREASE")) {
            double amount = in.nextDouble();
            characters.HEALTH_INCREASE(characterID, amount);
        } else if (command.equals("HEALTH_REDUCE")) {
            double amount = in.nextDouble();
            characters.HEALTH_REDUCE(characterID, amount);
        } else if (command.equals("DAMAGE_INCREASE")) {
            double amount = in.nextDouble();
            characters.DAMAGE_INCREASE(characterID, amount);
        } else if (command.equals("DAMAGE_REDUCE")) {
            double amount = in.nextDouble();
            characters.DAMAGE_REDUCE(characterID, amount);
        } else if (!command.equals("GET_DAMAGE") && !command.equals("GET_HEALTH")) {
            out.println("INVALID COMMAND");
            out.flush();
        }

        out.println(characterID + " | HEALTH: " + characters.getHealth(characterID) + " | DAMAGE: " + characters.getDamage(characterID));
        out.flush();
    }
}