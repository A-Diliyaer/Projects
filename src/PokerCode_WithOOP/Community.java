package PokerCode_WithOOP;

import java.util.ArrayList;

public class Community {

    public ArrayList<Card> communityCards = new ArrayList<>();

    public Community() {

    }

    public void addACard(Card card) {
        communityCards.add(card);
    }

    public String toString() {
        return "" + communityCards;
    }

}
