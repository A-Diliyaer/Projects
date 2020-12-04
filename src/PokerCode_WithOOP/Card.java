package PokerCode_WithOOP;

public class Card {

    public String cardValue;
    public String suitType;

    public Card(String cardValue, String suitType) {
        this.cardValue = cardValue;
        this.suitType = suitType;
    }

    public String toString() {
        return cardValue+suitType;
    }
}
