package PokerCodeAttempts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstAttempt {

    static List<String> deck = new ArrayList<>();
    static List<String> cardValue = Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A");
    static List<String> suits = Arrays.asList("♠","♣","♥","♦");
    static List<String> players = new ArrayList<>();

    public static void main(String[] args) {
        startGame();
    }
    public static void startGame() {
        createDeck();
        System.out.println(deck);
        shuffle();
        System.out.println(deck);
        for (int i = 0; i < 10; i++) {
            players.add(dealACard());
        }
        System.out.println(players);
    }
    public static void createDeck() {
        for (String suit : suits) {
            for (String card : cardValue) {
                deck.add(card + suit);
            }
        }
    }
    public static void shuffle() {
        Collections.shuffle(deck);
    }
    public static String dealACard() {
        String card1 = deck.get(0);
        deck.remove(0);
        return card1;
    }

}
