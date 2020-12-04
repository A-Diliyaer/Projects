package PokerCode_WithOOP;

import java.util.ArrayList;

public class Dealer {

    public Deck deck = new Deck();
    public ArrayList<Player> players = new ArrayList<>();

    public void dealsHoleCards(ArrayList<Player> players) {

        for (Player each : players) {
            each.addCard(deck.dealACard(Deck.deckOfCards));
            each.addCard(deck.dealACard(Deck.deckOfCards));
        }
    }

    public void dealsFlop(Community communityCards) {
        for (int i = 0; i < 3; i++) {
            communityCards.addACard(deck.dealACard(Deck.deckOfCards));
        }
    }

    public void dealsRiver(Community communityCards) {
        communityCards.addACard(deck.dealACard(Deck.deckOfCards));
    }

    public void dealsTurn(Community communityCards) {
        communityCards.addACard(deck.dealACard(Deck.deckOfCards));
    }
}
