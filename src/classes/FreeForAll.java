package classes;

import exception.MatchEmptyException;

import java.util.ArrayList;

public class FreeForAll {
    private ArrayList<Player> players;
    private int matches;

    // Constructor for game with initializing list of players and match count
    public FreeForAll() {
        players = new ArrayList<>();
        matches = 0;
    }

    // Method to add player
    public void addPlayer(Player p) {
        players.add(p);
    }

    // Method to remove player by name
    public void removePlayerByName(String name) {
        players.removeIf(player -> player.getName().equals(name));
    }

    // Method to return how many players are in the match
    public int getSize() {
        return players.size();
    }

    // Getting player by index
    public Player getPlayerByIndex(int i) {
        return players.get(i);
    }

    // increase the matches by 1
    public void increaseMatch() {
        matches++;
    }

    // returns the number of matches
    public int getMatches() {
        return matches;
    }

    // get total match stats (addition of all player stats)
    public double getTotalMatchStat() {
        double stat = 0;
        for (Player p: players) {
            stat = stat + p.getTotalStats();
        }
        return stat;
    }

    // runs the game, picks the winner (returns as well) and tires the rest of the players, throws exception if there are no players
    public Player pickWinner() throws MatchEmptyException {
        if (players.size() == 0) {
            throw new MatchEmptyException();
        }

        // randomly choosing a winner, creating a copy of the winner and deleting the winner from list
        double total = getTotalMatchStat();
        double random = Math.random() * (total);
        double checking = 0;
        Player winner = null;
        for (Player p: players) {
            checking = checking + p.getTotalStats();
            if (checking <= random) {
                continue;
            } else {
                winner = new Player(p.getName(), p.getHealth(), p.getStrength(), p.getSpeed());
                players.remove(p);
                break;
            }
        }

        // tiring every player that's left
        for (Player p: players) {
            double tiredness = Math.random() * (1);
            p.setTiredness(tiredness);
        }
        return winner;
    }
}
