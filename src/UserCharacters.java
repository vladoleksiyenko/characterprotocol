/**
 An array that contains characters
 */
public class UserCharacters
{
    private UserCharacter[] userCharacters;

    /**
     Creates a user character array that contains user characters
     @param size the array size (how many characters it'll contain)
     */
    public UserCharacters(int size)
    {
        userCharacters = new UserCharacter[size];
        for (int i = 0; i < userCharacters.length; i++)
        {
            userCharacters[i] = new UserCharacter(500.0, 500.0);
        }
    }

    /**
     Increases the health amount of a character
     @param characterID the character id
     @param amount the amount of increase to the health
     */
    public void HEALTH_INCREASE(int characterID, double amount) {
        UserCharacter character = userCharacters[characterID];
        character.HEALTH_INCREASE(amount);
    }

    /**
     Increases the health amount of a character
     @param characterID the character id
     @param amount the amount of reduction to the health
     */
    public void HEALTH_REDUCE(int characterID, double amount) {
        UserCharacter character = userCharacters[characterID];
        character.HEALTH_REDUCE(amount);
    }

    /**
     Increases the damage amount of a character
     @param characterID the character id
     @param amount the amount of increase to the damage
     */
    public void DAMAGE_INCREASE(int characterID, double amount) {
        UserCharacter character = userCharacters[characterID];
        character.DAMAGE_INCREASE(amount);
    }

    /**
     Reduces the damage amount of a character
     @param characterID the character id
     @param amount the amount of reduction to the damage
     */
    public void DAMAGE_REDUCE(int characterID, double amount) {
        UserCharacter character = userCharacters[characterID];
        character.DAMAGE_REDUCE(amount);
    }

    /**
     Returns the health for given character id
     @param characterID the character id
     @param characterID the character id that is used to return health
     */
    public double getHealth(int characterID) {
        UserCharacter character = userCharacters[characterID];
        return character.getHealth();
    }

    /**
     Returns the damage for given character id
     @param characterID the character id
     @param characterID the character id that is used to return damage
     */
    public double getDamage(int characterID) {
        UserCharacter character = userCharacters[characterID];
        return character.getDamage();
    }
}