package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTable {

    public static void main(String[] args) throws InterruptedException {

        GameEngine game = new GameEngine();
        Community communityCards = new Community();

        ArrayList<Player> players = new ArrayList<>(Arrays.asList(
                new Player("Tony"), new Player("Rogers"), new Player("Thor"),
                new Player("Natasha"), new Player("Banner")));

        game.startGame();

        game.preFlop(players);

        game.flop(communityCards, players);

        game.river(communityCards, players);

        game.turn(communityCards, players);

        game.displayWinner(players, communityCards);
    }
}
