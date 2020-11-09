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
        System.out.println("player1Cards = " + player1CardRank + " " + find5Cards(player1Cards,player1CardRank));
        System.out.println("player2Cards = " + player2CardRank + " " + find5Cards(player2Cards,player2CardRank));
        System.out.println("player3Cards = " + player3CardRank + " " + find5Cards(player3Cards,player3CardRank));
        System.out.println("player4Cards = " + player4CardRank + " " + find5Cards(player4Cards,player4CardRank));
        System.out.println("player5Cards = " + player5CardRank + " " + find5Cards(player5Cards,player5CardRank));
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
        System.out.println("player1Cards = " + cardRankCheck(player1Cards) + " " + find5Cards(player1Cards,player1CardRank));
        System.out.println("player2Cards = " + cardRankCheck(player2Cards) + " " + find5Cards(player2Cards,player2CardRank));
        System.out.println("player3Cards = " + cardRankCheck(player3Cards) + " " + find5Cards(player3Cards,player3CardRank));
        System.out.println("player4Cards = " + cardRankCheck(player4Cards) + " " + find5Cards(player4Cards,player4CardRank));
        System.out.println("player5Cards = " + cardRankCheck(player5Cards) + " " + find5Cards(player5Cards,player5CardRank));
    }

    public static void turn() throws InterruptedException {
        river();
    }

    public static List<Integer> cardValue(List<String> playerCards) {
        allCards.addAll(playerCards);
        allCards.addAll(communityCards);
        List<Integer> values = new ArrayList<>();
        List<Integer> sortedValues = new ArrayList<>(values);
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
        Collections.sort(sortedValues);
        if (sortedValues.containsAll(Arrays.asList(2, 3, 4, 5)) && sortedValues.contains(14)) {
            values.set(values.indexOf(14), 1);
        }
        return values;
    }

    public static String cardRankCheck(List<String> playerCards) {
        allCards.clear();
        List<Integer> values = new ArrayList<>(cardValue(playerCards));
        List<Integer> frequencyOfCards = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        int straightCount = 0, suitCount = 0, doubleCount = 0;
        String cardRank = "";
        Collections.sort(values);
        for (int i = 0; i < allCards.size() - 1; i++) {
            if (values.get(i) + 1 == values.get(i + 1)) {
                straightCount++;
            } else if (straightCount != 5 && values.get(i) + 1 != values.get(i + 1)){
                straightCount--;
            }
        }
        for (String allCard : allCards) {
            suits.add(allCard.substring(allCard.length()-1));
        }
        for (int i = 0; i < suits.size(); i++) {
            String suit = suits.get(i);
            if (Collections.frequency(suits, suit) == 5) {
                suitCount = 5;
                break;
            }
        }
        for (Integer value : values) {
            frequencyOfCards.add(Collections.frequency(values, value));
            if (Collections.frequency(values, value) == 2) {
                doubleCount++;
            }
        }
        if (values.containsAll(Arrays.asList(10,11,12,13,14)) && suitCount == 5) {
            cardRank = "Royal Flush";
        } else if (straightCount == 5 && suitCount == 5) {
            cardRank = "Straight Flush";
        } else if (frequencyOfCards.contains(4)) {
            cardRank = "Four Of A Kind";
        } else if (frequencyOfCards.contains(3) && frequencyOfCards.contains(2)) {
            cardRank = "Full House";
        } else if (suitCount == 5) {
            cardRank = "Flush";
        } else if (straightCount == 5) {
            cardRank = "Straight";
        } else if (frequencyOfCards.contains(3)) {
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
        List<String> highRankCards = new ArrayList<>();
        Collections.sort(copyValues);
        if (playerCardRank.equals("Royal Flush")) {
            for (int i = 0; i < 5; i++) {
            }
        } else if (playerCardRank.equals("Straight Flush")) {

        } else if (playerCardRank.equals("Four Of A Kind")) {
            if (communityCards.size() == 5) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 4) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else if (communityCards.size() == 4) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 4) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else  {
                highRankCards.addAll(sortCards(allCards, values));
            }
        } else if (playerCardRank.equals("Full House")) {
            if (communityCards.size() == 5) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 3) {
                        newValues.add(value);
                        copyValues.remove(value);
                    } else if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else if (communityCards.size() == 4) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 3) {
                        newValues.add(value);
                        copyValues.remove(value);
                    } else if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else  {
                highRankCards.addAll(sortCards(allCards, values));
            }
        } else if (playerCardRank.equals("Flush")) {

        } else if (playerCardRank.equals("Straight")) {

        } else if (playerCardRank.equals("Three Of A Kind")) {
            if (communityCards.size() == 5) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 3) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-2));
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else if (communityCards.size() == 4) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 3) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-2));
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else  {
                highRankCards.addAll(sortCards(allCards, values));
            }
        } else if (playerCardRank.equals("Two Pair")) {
            if (communityCards.size() == 5) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else if (communityCards.size() == 4) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else  {
                highRankCards.addAll(sortCards(allCards, values));
            }
        } else if (playerCardRank.equals("One Pair")) {
            if (communityCards.size() == 5) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-3));
                newValues.add(copyValues.get(copyValues.size()-2));
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else if (communityCards.size() == 4) {
                for (Integer value : values) {
                    if (Collections.frequency(values,value) == 2) {
                        newValues.add(value);
                        copyValues.remove(value);
                    }
                }
                newValues.add(copyValues.get(copyValues.size()-3));
                newValues.add(copyValues.get(copyValues.size()-2));
                newValues.add(copyValues.get(copyValues.size()-1));
                Collections.sort(newValues);
                highRankCards.addAll(sortCards(removeExtraCards(allCards, values, newValues), newValues));
            } else  {
                highRankCards.addAll(sortCards(allCards, values));
            }

        } else if (playerCardRank.equals("High Card")) {
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
        }
        return highRankCards;
    }

    public static List<String> removeExtraCards(List<String> cards, List<Integer> originalValues, List<Integer> shortValues) {
        List<Integer> extraValuesToRemove = new ArrayList<>();
        for (Integer value : originalValues) {
            if (!shortValues.contains(value)) {
                extraValuesToRemove.add(value);
            }
        }
        for (int i = 0; i < extraValuesToRemove.size(); i++) {
            cards.remove(originalValues.indexOf(extraValuesToRemove.get(i))-i);
        }
        return cards;
    }

    public static List<String> sortCards(List<String> cards, List<Integer> values) {
        Collections.sort(values);
        for (String card : cards) {
            switch (card.charAt(0)) {
                case '2' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(2));
                case '3' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(3));
                case '4' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(4));
                case '5' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(5));
                case '6' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(6));
                case '7' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(7));
                case '8' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(8));
                case '9' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(9));
                case '1' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(10));
                case 'J' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(11));
                case 'Q' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(12));
                case 'K' -> Collections.swap(cards,cards.indexOf(card), values.indexOf(13));
                case 'A' -> {
                    if (values.contains(14)) {
                        Collections.swap(cards,cards.indexOf(card), values.indexOf(14));
                    } else if (values.contains(1)) {
                        Collections.swap(cards,cards.indexOf(card), values.indexOf(1));
                    }
                }
            }
        }
        return cards;
    }

    public static void winner() {
        List<String> cardRanks = Arrays.asList("Royal Flush","Straight Flush","Four Of A Kind","Full House","Flush","Straight","Three Of A Kind","Two Pair","One Pair","High Card");
        List<Integer> rankIndex = new ArrayList<>();

    }

    public static String dealACard() {
        String card1 = deck.get(0);
        deck.remove(0);
        return card1;
    }


}
