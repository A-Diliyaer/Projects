package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {

    public ArrayList<String> cardValues = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
    public ArrayList<String> suitTypes = new ArrayList<>(Arrays.asList("♠", "♣", "♥", "♦"));
    public static ArrayList<Card> deckOfCards = new ArrayList<>();


    public ArrayList<Card> createDeckOfCard() {
        ArrayList<Card> deckOfCards = new ArrayList<>();
        for (String eachSuit : suitTypes) {
            for (String eachValue : cardValues) {
                deckOfCards.add(new Card(eachValue,eachSuit));
            }
        }
        return deckOfCards;
    }

    public void shuffle(ArrayList<Card> deckOfCards) {
        Collections.shuffle(deckOfCards);
    }

    public Card dealACard(ArrayList<Card> deckOfCards) {
        Card card = deckOfCards.get(0);
        deckOfCards.remove(0);
        return card;
    }




}
