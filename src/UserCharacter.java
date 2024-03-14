import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A character has health and damage fields, which can be altered
 by using included methods
 */
public class UserCharacter {
    private int characterID;
    private double health;
    private double damage;
    private Lock characterLock;

    /**
     Constructs a character with 0 health, 0 damage and a random id
     */
    Random rand = new Random();
    public UserCharacter() {
        this.characterID = rand.nextInt(99999); // Not a great way to create unique id's, this is just temporary
        this.health = 0;
        this.damage = 0;
        characterLock = new ReentrantLock();
    }

    /**
     Constructs a character with a given health amount and damage amount.
     @param initialHealth the amount of health the character is created with
     @param initialDamage the amount of damage the character is created with
     */
    public UserCharacter(double initialHealth, double initialDamage) {
        this.characterID = rand.nextInt(99999); // Not a great way to create unique id's, this is just temporary
        this.health = initialHealth;
        this.damage = initialDamage;
        characterLock = new ReentrantLock();
    }

    /**
     Reduces the health of the character by given amount
     @param amount the amount of health that is reduced
     */
    public void HEALTH_REDUCE(double amount) {
        characterLock.lock();
        try {
            if (health == 0) {
                health = 0;
            } else {
                double newHealth = health - amount;
                health = newHealth;
            }
        }
        finally {
            characterLock.unlock();
        }
    }

    /**
     Increases the health by given amount
     @param amount the amount the health is increased by
     */
    public void HEALTH_INCREASE(double amount) {
        characterLock.lock();
        try {
            double newHealth = health + amount;
            health = newHealth;
        }
        finally {
            characterLock.unlock();
        }
    }

    /**
     * Increases the damage by given amount
     * @param amount the amount that damage is increased by
     */
    public void DAMAGE_INCREASE(double amount) {
        characterLock.lock();
        try {
            double newDamage = damage + amount;
            damage = newDamage;
        } finally {
            characterLock.unlock();
        }
    }

    /**
     * Reduces the damage by given amount
     * @param amount the amount that damage is reduces by
     */
    public void DAMAGE_REDUCE(double amount) {
        characterLock.lock();
        try {
            double newDamage = damage - amount;
            damage = newDamage;
        } finally {
            characterLock.unlock();
        }
    }

    /**
     Gets the current health amount of a character.
     @return the current amount
     */
    public double getHealth() {
        return health;
    }

    /**
     Gets the current damage amount of a character.
     @return the current amount
     */
    public double getDamage() {
        return damage;
    }
}