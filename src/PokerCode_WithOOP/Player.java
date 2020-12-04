package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {

    public ArrayList<Card> hand = new ArrayList<>();

    public Player() {
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }





    @Override
    public String toString() {
        return "" + hand;
    }
}
