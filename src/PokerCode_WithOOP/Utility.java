package PokerCode_WithOOP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {

    public Player player = new Player();

    public ArrayList<Integer> cardValue(ArrayList<Card> hand) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> sortedValues = new ArrayList<>(values);
        if (hand.size() > 2) {
            for (Card card : hand) {
                switch (card.cardValue.charAt(0)) {
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

    public String findRank(Player player) {
        ArrayList<Integer> values = new ArrayList<>(cardValue(player.hand));
        ArrayList<Integer> straight = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();
            int straightCount = 0, suitCount = 0, doubleCount = 0, tripleCount = 0, quadCount = 0, royalFlush = 0;
            int straightFlush = 0;
            String cardRank = "", suitType = "";
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
            for (Card allCard : player.hand) {
                suits.add(allCard.suitType);
            }
            for (int i = 0; i < suits.size(); i++) {
                String suit = suits.get(i);
                if (Collections.frequency(suits, suit) == 5) {
                    suitCount = 5;
                    suitType = suit;
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
            if (values.containsAll(Arrays.asList(10, 11, 12, 13, 14))) {
                for (Card card : player.hand) {
                    if (card.cardValue.equals("10") || card.cardValue.equals("J") || card.cardValue.equals("Q") || card.cardValue.equals("K") || card.cardValue.equals("A")) {
                        if (card.suitType.equals(suitType) && !suitType.isEmpty()) {
                            royalFlush++;
                        }
                    }
                }
                if (royalFlush == 5) {
                    cardRank = "Royal Flush";
                } else {
                    cardRank = "Straight";
                }
            } else if (straightCount == 4) {
                List<Card> cardsTemp = new ArrayList<>(removeExtraCards(player, values, straight).hand);
                for (Card card : cardsTemp) {
                    if (card.suitType.equals(suitType)) {
                        straightFlush++;
                    }
                }
                if (straightFlush == 5) {
                    cardRank = "Straight Flush";
                } else {
                    cardRank = "Straight";
                }
            } else if (quadCount == 4) {
                cardRank = "Four Of A Kind";
            } else if ((tripleCount == 3 && doubleCount == 2) || tripleCount > 3) {
                cardRank = "Full House";
            } else if (suitCount >= 5) {
                cardRank = "Flush";
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

    public ArrayList<Card> find5Cards(Player player, Community community ,String playerCardRank) {
        ArrayList<Integer> values = new ArrayList<>(cardValue(player.hand));
        ArrayList<Integer> copyValues = new ArrayList<>(values);
        ArrayList<Integer> newValues = new ArrayList<>();
        ArrayList<Integer> straight = new ArrayList<>();
        ArrayList<Card> highRankCards = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();
        ArrayList<Card> copyAllCards = new ArrayList<>();
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
                for (Integer copyValue : copyValues) { // 2 8 4 10 6 4 11
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
                if (community.communityCards.size() > 3) {
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;

            case "Four Of A Kind":
                if (community.communityCards.size() > 3) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 4) {
                            newValues.add(value);
                            copyValues.remove(value);
                        }
                    }
                    newValues.add(copyValues.get(copyValues.size() - 1));
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;
            case "Full House":
                if (community.communityCards.size() > 3) {
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
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;
            case "Flush":
                for (Card card : player.hand) {
                    suits.add(card.suitType);
                }
                for (String suit : suits) {
                    if (Collections.frequency(suits, suit) == 5) {
                        for (Card card : player.hand) {
                            if (card.suitType.equals(suit)) {
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
                if (community.communityCards.size() > 3) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 3) {
                            newValues.add(value);
                            copyValues.remove(value);
                        }
                    }
                    newValues.add(copyValues.get(copyValues.size() - 2));
                    newValues.add(copyValues.get(copyValues.size() - 1));
                    Collections.sort(newValues);
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;
            case "Two Pair":
                if (community.communityCards.size() > 3) {
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
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;
            case "One Pair":
                if (community.communityCards.size() > 3) {
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
                    highRankCards.addAll(sortCards(removeExtraCards(player, values, newValues).hand, newValues));
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }

                break;
            case "High Card":
                if (community.communityCards.size() == 5) {
                    highRankCards.addAll(sortCards(player.hand, values));
                    highRankCards.remove(0);
                    highRankCards.remove(1);
                } else if (community.communityCards.size() == 4) {
                    highRankCards.addAll(sortCards(player.hand, values));
                    highRankCards.remove(0);
                } else {
                    highRankCards.addAll(sortCards(player.hand, values));
                }
                break;
        }
        return highRankCards;
    }

    public static Player removeExtraCards(Player player, ArrayList<Integer> originalValues, ArrayList<Integer> shortValues) {
        ArrayList<Integer> copyValue = new ArrayList<>(originalValues);
        for (Integer shortValue : shortValues) {
            copyValue.remove(shortValue);
        }
        List<Integer> toBeRemoved = new ArrayList<>(copyValue);
        for (Integer value : toBeRemoved) {
            player.hand.remove(originalValues.indexOf(value));
            originalValues.remove(value);
        }
        return player;
    }

    public static ArrayList<Card> sortCards(ArrayList<Card> hand, List<Integer> values) {
        ArrayList<Card> copyCards = new ArrayList<>(hand);
        Collections.sort(values);
        for (Card card : hand) {
            switch (card.cardValue.charAt(0)) {
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

}
