package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTable {

    public static void main(String[] args) {

        GameEngine newGame = new GameEngine();
        Deck deckOfCard = new Deck();
        Dealer dealer = new Dealer();
        Community communityCards = new Community();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1,player2,player3,player4,player5));

        newGame.startGame();
        dealer.dealsHoleCards(players);
        newGame.preFlop();
        newGame.showPlayersCards(players);

        newGame.flop(communityCards);
        newGame.showPlayersCards(players);

        newGame.river(communityCards);
        newGame.showPlayersCards(players);


        newGame.turn(communityCards);
        newGame.showPlayersCards(players);
    }
}
