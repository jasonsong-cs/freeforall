package classes;

public class Player {
    private String name;
    private int health;
    private int strength;
    private int speed;
    private double tiredness;

    // Constructor for player
    public Player(String name, int health, int strength, int speed) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
        this.tiredness = 1;
    }

    // Setter method to change name
    public void setName(String name) {
        this.name = name;
    }

    // Setter method to change health
    public void setHealth(int health) {
        this.health = health;
    }

    // Setter method to change strength
    public void setStrength(int strength) {
        this.strength = strength;
    }

    // Setter method to change speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Setter method to change tiredness (multiplying a multiplier)
    public void setTiredness(double tired) {
        this.tiredness *= tired;
    }

    // Getter method to get name
    public String getName() {
        return name;
    }

    // Getter method to get health
    public int getHealth() {
        return health;
    }

    // Getter method to get strength
    public int getStrength() {
        return strength;
    }

    // Getter method to get speed
    public int getSpeed() {
        return speed;
    }

    // Getter method to get tiredness
    public double getTiredness() {
        return tiredness;
    }

    public double getTotalStats() {
        return getTiredness() * (getHealth() + getStrength() + getSpeed());
    }
}
