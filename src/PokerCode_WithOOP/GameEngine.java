package PokerCode_WithOOP;

import java.util.ArrayList;

public class GameEngine {

    public Deck deck = new Deck();
    public Dealer dealer = new Dealer();
    public ArrayList<Player> players = new ArrayList<>();
    public Utility utility = new Utility();


    public void startGame() {

        Deck.deckOfCards = deck.createDeckOfCard();
        System.out.println(Deck.deckOfCards); // Only for testing purposes
        deck.shuffle(Deck.deckOfCards);
        System.out.println(Deck.deckOfCards); // Only for testing purposes
    }

    public void preFlop() {
        dealer.dealsHoleCards(this.players);
        showPlayersCards(players);
    }

    public void showPlayersCards(ArrayList<Player> players) {
        for (Player each : players) {
            System.out.println(each.name + "'s cards: " + each);
        }
        System.out.println();
    }

    public void showRankAnd5Cards(ArrayList<Player> players, Community community) {
        for (Player player : players) {
            System.out.print(player.name + " has: ");
            System.out.println(utility.findRank(player,community) + " " + utility.find5Cards(player, community,utility.findRank(player,community)));
        }
        System.out.println();
    }

    public void flop(Community communityCards, ArrayList<Player> players) {
        dealer.dealsFlop(communityCards);
        showCommunityCards(communityCards);
        showRankAnd5Cards(players, communityCards);
    }

    public void showCommunityCards(Community communityCards) {
        System.out.println("Community cards are: " + communityCards);
        System.out.println();
    }

    public void river(Community communityCards, ArrayList<Player> players) {
        dealer.dealsRiver(communityCards);
        showCommunityCards(communityCards);
        showRankAnd5Cards(players, communityCards);
    }

    public void turn(Community communityCards, ArrayList<Player> players) {
        dealer.dealsRiver(communityCards);
        showCommunityCards(communityCards);
        showRankAnd5Cards(players, communityCards);
    }



}
