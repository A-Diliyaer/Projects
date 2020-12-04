package PokerCode_WithOOP;

import java.util.ArrayList;

public class GameEngine {

    public Deck deck = new Deck();
    public Dealer dealer = new Dealer();
    public ArrayList<Player> players = new ArrayList<>();


    public void startGame() {

        Deck.deckOfCards = deck.createDeckOfCard();
        System.out.println(Deck.deckOfCards); // Only for testing purposes
        deck.shuffle(Deck.deckOfCards);
        System.out.println(Deck.deckOfCards); // Only for testing purposes
    }

    public void preFlop() {
        dealer.dealsHoleCards(this.players);
    }

    public void showPlayersCards(ArrayList<Player> players) {
        for (Player each : players) {
            System.out.println("Player" + (players.indexOf(each) + 1) + "'s cards: " + each);
        }
    }

    public void flop(Community communityCards) {
        dealer.dealsFlop(communityCards);
        showCommunityCards(communityCards);
    }

    public void showCommunityCards(Community communityCards) {
        System.out.println("Community cards are: " + communityCards);
    }

    public void river(Community communityCards) {
        dealer.dealsRiver(communityCards);
        showCommunityCards(communityCards);
    }

    public void turn(Community communityCards) {
        dealer.dealsRiver(communityCards);
        showCommunityCards(communityCards);
    }



}
