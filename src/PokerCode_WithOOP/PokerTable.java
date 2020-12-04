package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTable {

    public static void main(String[] args) {

        GameEngine Game = new GameEngine();
        Dealer dealer = new Dealer();
        Utility utility = new Utility();
        Community communityCards = new Community();
        Player Tony = new Player();
        Player Rogers = new Player();
        Player Thor = new Player();
        Player Natasha = new Player();
        Player Banner = new Player();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(Tony,Rogers,Thor,Natasha,Banner));

        Game.startGame();
        dealer.dealsHoleCards(players);
        Game.preFlop();
        Game.showPlayersCards(players);

        Game.flop(communityCards);
        Game.showPlayersCards(players);
        System.out.println(utility.findRank(Tony));
        System.out.println(utility.findRank(Rogers));
        System.out.println(utility.findRank(Thor));
        System.out.println(utility.findRank(Natasha));
        System.out.println(utility.findRank(Banner));


        Game.river(communityCards);
        Game.showPlayersCards(players);


        Game.turn(communityCards);
        Game.showPlayersCards(players);
    }
}
