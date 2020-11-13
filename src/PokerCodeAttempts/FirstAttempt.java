package PokerCodeAttempts;

import java.util.*;

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
    static String player1CardRank = "";
    static String player2CardRank = "";
    static String player3CardRank = "";
    static String player4CardRank = "";
    static String player5CardRank = "";
    static List<String> player1HighestCards = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        String enter;
        do {  //   LOOP ONLY FOR TESTING
            player1Cards.clear();
            player2Cards.clear();
            player3Cards.clear();
            player4Cards.clear();
            player5Cards.clear();
            communityCards.clear();
            deck.clear();
            startGame();
            holeCards();
            flop();
            river();
            turn();
            enter = scan.nextLine();
        } while (enter.equals(""));
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

    public static String dealACard() {
        String card1 = deck.get(0);
        deck.remove(0);
        return card1;
    }

    public static void holeCards() throws InterruptedException {
        player1Cards.add(dealACard());
        player2Cards.add(dealACard());
        player3Cards.add(dealACard());
        player4Cards.add(dealACard());
        player5Cards.add(dealACard());
        player1Cards.add(dealACard());
        player2Cards.add(dealACard());
        player3Cards.add(dealACard());
        player4Cards.add(dealACard());
        player5Cards.add(dealACard());

        Thread.sleep(0);
        System.out.println("player1Cards = " + player1Cards);
        Thread.sleep(0);
        System.out.println("player2Cards = " + player2Cards);
        Thread.sleep(0);
        System.out.println("player3Cards = " + player3Cards);
        Thread.sleep(0);
        System.out.println("player4Cards = " + player4Cards);
        Thread.sleep(0);
        System.out.println("player5Cards = " + player5Cards);
    }

    public static void flop() throws InterruptedException {
        communityCards.add(dealACard());
        communityCards.add(dealACard());
        communityCards.add(dealACard());
        Thread.sleep(0);
        System.out.println("communityCards = " + communityCards);
        player1CardRank = cardRankCheck(player1Cards);
        player2CardRank = cardRankCheck(player2Cards);
        player3CardRank = cardRankCheck(player3Cards);
        player4CardRank = cardRankCheck(player4Cards);
        player5CardRank = cardRankCheck(player5Cards);
        System.out.println("player1Cards = " + player1CardRank + " " + find5Cards(player1Cards, player1CardRank));
        System.out.println("player2Cards = " + player2CardRank + " " + find5Cards(player2Cards, player2CardRank));
        System.out.println("player3Cards = " + player3CardRank + " " + find5Cards(player3Cards, player3CardRank));
        System.out.println("player4Cards = " + player4CardRank + " " + find5Cards(player4Cards, player4CardRank));
        System.out.println("player5Cards = " + player5CardRank + " " + find5Cards(player5Cards, player5CardRank));
    }

    public static void river() throws InterruptedException {
        communityCards.add(dealACard());
        Thread.sleep(0);
        System.out.println("communityCards = " + communityCards);
        player1CardRank = cardRankCheck(player1Cards);
        player2CardRank = cardRankCheck(player2Cards);
        player3CardRank = cardRankCheck(player3Cards);
        player4CardRank = cardRankCheck(player4Cards);
        player5CardRank = cardRankCheck(player5Cards);
        System.out.println("player1Cards = " + cardRankCheck(player1Cards) + " " + find5Cards(player1Cards, player1CardRank));
        System.out.println("player2Cards = " + cardRankCheck(player2Cards) + " " + find5Cards(player2Cards, player2CardRank));
        System.out.println("player3Cards = " + cardRankCheck(player3Cards) + " " + find5Cards(player3Cards, player3CardRank));
        System.out.println("player4Cards = " + cardRankCheck(player4Cards) + " " + find5Cards(player4Cards, player4CardRank));
        System.out.println("player5Cards = " + cardRankCheck(player5Cards) + " " + find5Cards(player5Cards, player5CardRank));
    }

    public static void turn() throws InterruptedException {
        river();
    }

    public static List<Integer> cardValue(List<String> playerCards) {
        List<Integer> values = new ArrayList<>();
        List<Integer> sortedValues = new ArrayList<>(values);
        if (playerCards.size() == 2) {
            allCards.addAll(playerCards);
            allCards.addAll(communityCards);
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
        } else if (playerCards.size() > 2) {
            for (String card : playerCards) {
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
        }

        Collections.sort(sortedValues);
        if (sortedValues.containsAll(Arrays.asList(2, 3, 4, 5)) && sortedValues.contains(14)) {
            values.set(values.indexOf(14), 1);
        }
        return values;
    }

    public static String cardRankCheck(List<String> playerCards) {
        allCards.clear();
        List<Integer> values = new ArrayList<>(cardValue(playerCards));
        List<Integer> straight = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        int straightCount = 0, suitCount = 0, doubleCount = 0, tripleCount = 0, quadCount = 0;
        String cardRank = "";
        Collections.sort(values);
        for (int i = 0; i < values.size(); i++) {
            straight.clear();
            straight.add(values.get(i));
            straight.add(values.get(i) + 1);
            straight.add(values.get(i) + 2);
            straight.add(values.get(i) + 3);
            straight.add(values.get(i) + 4);
            if (values.containsAll(straight)) {
                straightCount = 4;
            }
        }
        for (String allCard : allCards) {
            suits.add(allCard.substring(allCard.length() - 1));
        }
        for (int i = 0; i < suits.size(); i++) {
            String suit = suits.get(i);
            if (Collections.frequency(suits, suit) == 5) {
                suitCount = 5;
                break;
            }
        }
        for (Integer value : values) {
            if (Collections.frequency(values, value) == 2) {
                doubleCount++;
            } else if (Collections.frequency(values, value) == 3) {
                tripleCount++;
            } else if (Collections.frequency(values, value) == 4) {
                quadCount++;
            }
        }
        if (values.containsAll(Arrays.asList(10, 11, 12, 13, 14)) && suitCount == 5) {
            cardRank = "Royal Flush";
        } else if (straightCount == 4 && suitCount == 5) {
            cardRank = "Straight Flush";
        } else if (quadCount == 4) {
            cardRank = "Four Of A Kind";
        } else if ((tripleCount == 3 && doubleCount == 2) || tripleCount > 3) {
            cardRank = "Full House";
        } else if (suitCount >= 5) {
            cardRank = "Flush";
        } else if (straightCount == 4) {
            cardRank = "Straight";
        } else if (tripleCount == 3) {
            cardRank = "Three Of A Kind";
        } else if (doubleCount >= 4) {
            cardRank = "Two Pair";
        } else if (doubleCount == 2) {
            cardRank = "One Pair";
        } else if (doubleCount == 0) {
            cardRank = "High Card";
        }

        return cardRank;
    }

    public static List<String> find5Cards(List<String> playerCards, String playerCardRank) {
        allCards.clear();
        List<Integer> values = new ArrayList<>(cardValue(playerCards));
        List<Integer> copyValues = new ArrayList<>(values);
        List<Integer> newValues = new ArrayList<>();
        List<Integer> straight = new ArrayList<>();
        List<String> highRankCards = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        List<String> copyAllCards = new ArrayList<>();
        Collections.sort(copyValues);
        int doubleCount = 0, tripleCount = 0;
        for (Integer value : values) {
            if (Collections.frequency(values, value) == 2) {
                doubleCount++;
            } else if (Collections.frequency(values, value) == 3) {
                tripleCount++;
            }
        }
        switch (playerCardRank) {
            case "Royal Flush":
            case "Straight Flush":
            case "Straight":
                for (Integer copyValue : copyValues) {
                    straight.clear();
                    straight.add(copyValue);
                    straight.add(copyValue + 1);
                    straight.add(copyValue + 2);
                    straight.add(copyValue + 3);
                    straight.add(copyValue + 4);
                    if (values.containsAll(straight)) {
                        newValues.clear();
                        newValues.addAll(straight);
                    }
                }
                Collections.sort(newValues);
                if (communityCards.size() > 3) {
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;

            case "Four Of A Kind":
                if (communityCards.size() > 3) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 4) {
                            newValues.add(value);
                            copyValues.remove(value);
                        }
                    }
                    newValues.add(copyValues.get(copyValues.size() - 1));
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;
            case "Full House":
                if (communityCards.size() > 3) {
                    if (tripleCount == 3 && doubleCount == 2) {
                        for (Integer value : values) {
                            if (Collections.frequency(values, value) == 3) {
                                newValues.add(value);
                                copyValues.remove(value);
                            } else if (Collections.frequency(values, value) == 2) {
                                newValues.add(value);
                                copyValues.remove(value);
                            }
                        }
                    } else if (tripleCount == 6) {
                        Collections.reverse(copyValues);
                        for (int i = 0; i < 5; i++) {
                            newValues.add(copyValues.get(i));
                        }
                    }
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;
            case "Flush":
                for (String card : allCards) {
                    suits.add(card.substring(card.length() - 1));
                }
                for (String suit : suits) {
                    if (Collections.frequency(suits, suit) == 5) {
                        for (String card : allCards) {
                            if (card.charAt(card.length() - 1) == suit.charAt(0)) {
                                copyAllCards.add(card);
                            }
                        }
                        break;
                    }
                }
                newValues.addAll(cardValue(copyAllCards));
                highRankCards.addAll(sortCards(copyAllCards, newValues));
                break;
            case "Three Of A Kind":
                if (communityCards.size() > 3) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 3) {
                            newValues.add(value);
                            copyValues.remove(value);
                        }
                    }
                    newValues.add(copyValues.get(copyValues.size() - 2));
                    newValues.add(copyValues.get(copyValues.size() - 1));
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;
            case "Two Pair":
                if (communityCards.size() > 3) {
                    if (doubleCount == 4) {
                        for (Integer value : values) {
                            if (Collections.frequency(values, value) == 2) {
                                newValues.add(value);
                                copyValues.remove(value);
                            }
                        }
                        newValues.add(copyValues.get(copyValues.size() - 1));
                    } else if (doubleCount == 6) {
                        Collections.reverse(copyValues);
                        for (int i = 0; i < 5; i++) {
                            newValues.add(copyValues.get(i));
                        }
                    }
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;
            case "One Pair":
                if (communityCards.size() > 3) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 2) {
                            newValues.add(value);
                            copyValues.remove(value);
                        }
                    }
                    newValues.add(copyValues.get(copyValues.size() - 3));
                    newValues.add(copyValues.get(copyValues.size() - 2));
                    newValues.add(copyValues.get(copyValues.size() - 1));
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }

                break;
            case "High Card":
                if (communityCards.size() == 5) {
                    highRankCards.addAll(sortCards(allCards, values));
                    highRankCards.remove(0);
                    highRankCards.remove(1);
                } else if (communityCards.size() == 4) {
                    highRankCards.addAll(sortCards(allCards, values));
                    highRankCards.remove(0);
                } else {
                    highRankCards.addAll(sortCards(allCards, values));
                }
                break;
        }
        return highRankCards;
    }

    public static List<String> removeExtraCards(List<String> cards, List<Integer> originalValues, List<Integer> shortValues) {
        List<Integer> copyValue = new ArrayList<>(originalValues);
        for (Integer shortValue : shortValues) {
            copyValue.remove(shortValue);
        }
        List<Integer> toBeRemoved = new ArrayList<>(copyValue);
        for (Integer value : toBeRemoved) {
            cards.remove(originalValues.indexOf(value));
            originalValues.remove(value);
        }
        return cards;
    }

    public static List<String> sortCards(List<String> cards, List<Integer> values) {
        List<String> copyCards = new ArrayList<>(cards);
        Collections.sort(values);
        for (String card : cards) {
            switch (card.charAt(0)) {
                case '2' -> {
                    copyCards.set(values.indexOf(2), card);
                    values.set(values.indexOf(2), 0);
                }
                case '3' -> {
                    copyCards.set(values.indexOf(3), card);
                    values.set(values.indexOf(3), 0);
                }
                case '4' -> {
                    copyCards.set(values.indexOf(4), card);
                    values.set(values.indexOf(4), 0);
                }
                case '5' -> {
                    copyCards.set(values.indexOf(5), card);
                    values.set(values.indexOf(5), 0);
                }
                case '6' -> {
                    copyCards.set(values.indexOf(6), card);
                    values.set(values.indexOf(6), 0);
                }
                case '7' -> {
                    copyCards.set(values.indexOf(7), card);
                    values.set(values.indexOf(7), 0);
                }
                case '8' -> {
                    copyCards.set(values.indexOf(8), card);
                    values.set(values.indexOf(8), 0);
                }
                case '9' -> {
                    copyCards.set(values.indexOf(9), card);
                    values.set(values.indexOf(9), 0);
                }
                case '1' -> {
                    copyCards.set(values.indexOf(10), card);
                    values.set(values.indexOf(10), 0);
                }
                case 'J' -> {
                    copyCards.set(values.indexOf(11), card);
                    values.set(values.indexOf(11), 0);
                }
                case 'Q' -> {
                    copyCards.set(values.indexOf(12), card);
                    values.set(values.indexOf(12), 0);
                }
                case 'K' -> {
                    copyCards.set(values.indexOf(13), card);
                    values.set(values.indexOf(13), 0);
                }
                case 'A' -> {
                    if (values.contains(14)) {
                        copyCards.set(values.indexOf(14), card);
                        values.set(values.indexOf(14), 0);
                    } else if (values.contains(1)) {
                        copyCards.set(values.indexOf(1), card);
                        values.set(values.indexOf(1), 0);
                    }
                }
            }
        }
        return copyCards;
    }

    public static void winner() {
        List<String> cardRanks = Arrays.asList("Royal Flush", "Straight Flush", "Four Of A Kind", "Full House", "Flush", "Straight", "Three Of A Kind", "Two Pair", "One Pair", "High Card");
        List<Integer> rankIndex = new ArrayList<>();

    }




}
