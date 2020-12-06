package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTable {

    public static void main(String[] args) {

        GameEngine game = new GameEngine();
        Dealer dealer = new Dealer();
        Utility utility = new Utility();
        Community communityCards = new Community();
        Player player1 = new Player("Tony");
        Player player2 = new Player("Rogers");
        Player player3 = new Player("Thor");
        Player player4 = new Player("Natasha");
        Player player5 = new Player("Banner");
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1,player2,player3,player4,player5));

        game.startGame();
        dealer.dealsHoleCards(players);
        game.preFlop();
        game.showPlayersCards(players);

        game.flop(communityCards,players);

        game.river(communityCards, players);

        game.turn(communityCards, players);
    }
}
