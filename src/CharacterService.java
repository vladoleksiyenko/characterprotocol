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
            receivingOutput(characterID);
        } else if (command.equals("HEALTH_REDUCE")) {
            double amount = in.nextDouble();
            characters.HEALTH_REDUCE(characterID, amount);
            receivingOutput(characterID);
        } else if (command.equals("DAMAGE_INCREASE")) {
            double amount = in.nextDouble();
            characters.DAMAGE_INCREASE(characterID, amount);
            receivingOutput(characterID);
        } else if (command.equals("DAMAGE_REDUCE")) {
            double amount = in.nextDouble();
            characters.DAMAGE_REDUCE(characterID, amount);
            receivingOutput(characterID);
        } else if (command.equals("GET_HEALTH")) {
            out.println("CURRENT HEALTH: " + characters.getHealth(characterID));
            out.flush();
        } else if (command.equals("GET_DAMAGE")) {
            out.println("CURRENT DAMAGE: " + characters.getDamage(characterID));
            out.flush();
        }
    }

    public void receivingOutput(int characterID) {
        out.println("Character ID: " + characterID + " | Character Health: " + characters.getHealth(characterID)
                + " | Character Damage: " + characters.getDamage(characterID));
        out.flush();
    }
}