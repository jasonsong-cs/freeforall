package ui;

import classes.FreeForAll;
import classes.Player;
import exception.MatchEmptyException;

import java.util.InputMismatchException;
import java.util.Scanner;

// Free For All Simulation
public class Console {
    private FreeForAll newFreeForAll;
    private Scanner userInput;

    // runs the free for all match
    public Console() {
        userInput = new Scanner(System.in);
        runMatch();
    }

    // loop to run the console until the user wants to exit
    private void runMatch() {
        boolean running = true;
        int input;
        userInput = new Scanner(System.in);

        newFreeForAll = new FreeForAll();

        while (running) {
            options();
            input = userInput.nextInt();

            if (input == 0) {
                running = false;
            } else {
                runCommand(input);
            }
        }
    }

    // displays list of options/commands the user can use
    private void options() {
        System.out.println("Your Options: ");
        System.out.println("[1] Create Player");
        System.out.println("[2] Remove Player");
        System.out.println("[3] Get Player Information");
        System.out.println("[4] Edit Player");
        System.out.println("[5] Get All Player Names");
        System.out.println("[6] Get FreeForAll Status");
        System.out.println("[7] Start a match (winner will be removed from the match)");
        System.out.println("[8] Reset");
        System.out.println("[0] Exit");
    }

    // runs the command given by the user
    private void runCommand(int input) {
        if (input == 1) {
            createPlayer();
        } else if (input == 2) {
            removePlayer();
        } else if (input == 3) {
            getPlayerInfo();
        } else if (input == 4) {
            editPlayer();
        } else if (input == 5) {
            printAllPlayerName();
        } else if (input == 6) {
            getFreeForAllStatus();
        } else if (input == 7) {
            startMatch();
        } else if (input == 8) {
            reset();
        } else {
            System.out.println("Invalid command (Enter 0-6)");
        }
    }


    // creates a player and adds it to the free for all match
    private void createPlayer() {
        System.out.println("Enter the name of the player: ");
        String name = userInput.next();
        name += userInput.nextLine();
        try {
            System.out.println("Enter the health of the player: ");
            int health = userInput.nextInt();
            System.out.println("Enter the strength of the player: ");
            int strength = userInput.nextInt();
            System.out.println("Enter the speed of the player: ");
            int speed = userInput.nextInt();

            Player add = new Player(name, health, strength, speed);
            newFreeForAll.addPlayer(add);

            System.out.println("Added the player with the given details");
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid integer!");
            userInput.next();
        }

    }

    // removes all players with the given name
    private void removePlayer() {
        System.out.println("Enter the name of the player to remove: ");
        String name = userInput.next();
        name += userInput.nextLine();
        newFreeForAll.removePlayerByName(name);

        System.out.println("Removed all players with the matching name");
    }

    // prints the player's statistics
    private void getPlayerInfo() {

        try {
            System.out.println("Which player would you like to get the information of? (Enter the index): ");
            int index = userInput.nextInt();
            System.out.println("Name: " + newFreeForAll.getPlayerByIndex(index).getName());
            System.out.println("Health: " + newFreeForAll.getPlayerByIndex(index).getHealth());
            System.out.println("Strength: " + newFreeForAll.getPlayerByIndex(index).getStrength());
            System.out.println("Speed: " + newFreeForAll.getPlayerByIndex(index).getSpeed());
            System.out.println("Tiredness: " + newFreeForAll.getPlayerByIndex(index).getTiredness());
        } catch (InputMismatchException e) {
            System.out.println("Enter an integer for the index!");
            userInput.next();
        } catch (IndexOutOfBoundsException f) {
            System.out.println("Invalid index");
        }

    }

    // edits player's statistics by using index
    private void editPlayer() {

        try {
            System.out.println("Which player would you like to edit? (Enter the index): ");
            int index = userInput.nextInt();
            String playerName = newFreeForAll.getPlayerByIndex(index).getName();
            System.out.println("Editing: " + playerName);
            System.out.println("What would you like to edit? (1 for name, 2 for health, 3 for strength, 4 for speed): ");
            int choice = userInput.nextInt();

            if (choice == 1) {
                updateName(index);
            } else if (choice == 2) {
                updateHealth(index);
            } else if (choice == 3) {
                updateStrength(index);
            } else {
                updateSpeed(index);
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter an integer for the index!");
            userInput.next();
        } catch (IndexOutOfBoundsException f) {
            System.out.println("Invalid index");
        }
    }

    // updates name of the player
    private void updateName(int index) {
        System.out.println("Enter the new name of the player: ");
        String name = userInput.next();
        newFreeForAll.getPlayerByIndex(index).setName(name);
    }

    // updates health of the player
    private void updateHealth(int index) {
        System.out.println("Enter the new health of the player: ");
        int health = userInput.nextInt();
        newFreeForAll.getPlayerByIndex(index).setHealth(health);
    }

    //  updates strength of the player
    private void updateStrength(int index) {
        System.out.println("Enter the new strength of the player: ");
        int strength = userInput.nextInt();
        newFreeForAll.getPlayerByIndex(index).setStrength(strength);
    }

    //  updates speed of the player
    private void updateSpeed(int index) {
        System.out.println("Enter the new speed of the player: ");
        int speed = userInput.nextInt();
        newFreeForAll.getPlayerByIndex(index).setSpeed(speed);
    }

    // prints all names of the players in the match
    private void printAllPlayerName() {
        System.out.println("Name(s) of the player(s) in the list");
        for (int i = 0; i < newFreeForAll.getSize(); i++) {
            System.out.println(newFreeForAll.getPlayerByIndex(i).getName());
        }
    }

    // gets the status of the free for all match (number of players and number of matches)
    private void getFreeForAllStatus() {
        System.out.println("Number of players: " + newFreeForAll.getSize());
        System.out.println("Number of matches so far: " + newFreeForAll.getMatches());
    }

    // Picks winner, tires losing players, shows winning percentages and removes winner from match
    private void startMatch() {
        try {
            double totalMatchStats = newFreeForAll.getTotalMatchStat();
            Player winner = newFreeForAll.pickWinner();
            String winner_name = winner.getName();
            double winning = winner.getTotalStats();
            System.out.println("Player " + winner_name + " won with a chance of " + (winning * 100 / totalMatchStats) + "%!");
            newFreeForAll.increaseMatch();
        } catch (MatchEmptyException e) {
            System.out.println("No players are in the match, add a player before starting!");
        }
    }

    // resets the free for all match
    private void reset() {
        newFreeForAll = new FreeForAll();
    }

}
