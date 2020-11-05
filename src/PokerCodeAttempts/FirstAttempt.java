package PokerCodeAttempts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstAttempt {

    static List<String> deck = new ArrayList<>();
    static List<String> cards = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    static List<String> suits = Arrays.asList("♠", "♣", "♥", "♦");
    static List<String> players = new ArrayList<>();
    static List<String> player1Cards = new ArrayList<>();
    static List<String> player2Cards = new ArrayList<>();
    static List<String> player3Cards = new ArrayList<>();
    static List<String> player4Cards = new ArrayList<>();
    static List<String> player5Cards = new ArrayList<>();
    static List<String> communityCards = new ArrayList<>();
    static List<String> allCards = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        startGame();
        holeCards();
        flop();
        river();
        turn();
    }

    public static void startGame() {
        createDeck();
        System.out.println(deck);
        shuffle();
        System.out.println(deck);
    }

    public static void createDeck() {
        for (String suit : suits) {
            for (String card : cards) {
                deck.add(card + suit);
            }
        }
    }

    public static void shuffle() {
        Collections.shuffle(deck);
    }

    public static void holeCards() throws InterruptedException {
        player1Cards.add(dealACard());
        player1Cards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("player1Cards = " + player1Cards);
        player2Cards.add(dealACard());
        player2Cards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("player2Cards = " + player2Cards);
        player3Cards.add(dealACard());
        player3Cards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("player3Cards = " + player3Cards);
        player4Cards.add(dealACard());
        player4Cards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("player4Cards = " + player4Cards);
        player5Cards.add(dealACard());
        player5Cards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("player5Cards = " + player5Cards);
    }

    public static void flop() throws InterruptedException {
        communityCards.add(dealACard());
        communityCards.add(dealACard());
        communityCards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("communityCards = " + communityCards);
        System.out.println("player1Cards = " + cardRankCheck(player1Cards) + " " + player1Cards);
        System.out.println("player2Cards = " + cardRankCheck(player2Cards) + " " + player2Cards);
        System.out.println("player3Cards = " + cardRankCheck(player3Cards) + " " + player3Cards);
        System.out.println("player4Cards = " + cardRankCheck(player4Cards) + " " + player4Cards);
        System.out.println("player5Cards = " + cardRankCheck(player5Cards) + " " + player5Cards);
    }

    public static void river() throws InterruptedException {
        communityCards.add(dealACard());
        Thread.sleep(1000);
        System.out.println("communityCards = " + communityCards);
        System.out.println("player1Cards = " + cardRankCheck(player1Cards) + " " + player1Cards);
        System.out.println("player2Cards = " + cardRankCheck(player2Cards) + " " + player2Cards);
        System.out.println("player3Cards = " + cardRankCheck(player3Cards) + " " + player3Cards);
        System.out.println("player4Cards = " + cardRankCheck(player4Cards) + " " + player4Cards);
        System.out.println("player5Cards = " + cardRankCheck(player5Cards) + " " + player5Cards);
    }

    public static void turn() throws InterruptedException {
        river();
    }

    public static List<Integer> cardValue(List<String> playerCards) {
        allCards.addAll(playerCards);
        allCards.addAll(communityCards);
        List<Integer> values = new ArrayList<>();
        for (String card : allCards) {
            switch (card.charAt(0)) {
                case '2' -> values.add(2);
                case '3' -> values.add(3);
                case '4' -> values.add(4);
                case '5' -> values.add(5);
                case '6' -> values.add(6);
                case '7' -> values.add(7);
                case '8' -> values.add(8);
                case '9' -> values.add(9);
                case '1' -> values.add(10);
                case 'J' -> values.add(11);
                case 'Q' -> values.add(12);
                case 'K' -> values.add(13);
                case 'A' -> values.add(14);
            }
        }
        Collections.sort(values);
        if (values.containsAll(Arrays.asList(2, 3, 4, 5)) && values.contains(14)) {
            values.set(values.indexOf(14), 1);
        }
        return values;
    }

    public static String cardRankCheck(List<String> playerCards) {
        allCards.clear();
        List<Integer> values = new ArrayList<>(cardValue(playerCards));
        List<Integer> frequencyOfCards = new ArrayList<>();
        int straightCount = 0, suitCount = 0, doubleCount = 0;
        ;
        String cardRank = "";
        for (int i = 0; i < allCards.size() - 1; i++) {
            if (allCards.get(i).charAt(1) == allCards.get(i + 1).charAt(1)) {
                suitCount++;
            }
            if (values.get(i) + 1 == values.get(i + 1)) {
                straightCount++;
            }
        }
        for (Integer value : values) {
            frequencyOfCards.add(Collections.frequency(values, value));
        }
        for (Integer count : frequencyOfCards) {
            if (count == 2) {
                doubleCount++;
            }
        }
        if (straightCount == 5) {
            cardRank = "Straight";
            if (suitCount == 5) {
                cardRank += " Flash";
            }
        } else if (suitCount == 5) {
            cardRank = "Flash";
        } else if (frequencyOfCards.contains(4)) {
            cardRank = "Four Of A Kind";
        } else if (frequencyOfCards.contains(3)) {
            cardRank = "Three Of A Kind";
            if (frequencyOfCards.contains(2)) {
                cardRank = "Full House";
            }
        } else if (doubleCount == 4) {
            cardRank = "Two Pair";
        } else if (doubleCount == 2) {
            cardRank = "One Pair";
        } else if (doubleCount == 0) {
            cardRank = "High Card";
        }

        return cardRank;
    }

    public static String dealACard() {
        String card1 = deck.get(0);
        deck.remove(0);
        return card1;
    }


}
