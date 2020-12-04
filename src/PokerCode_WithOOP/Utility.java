package PokerCode_WithOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {



    public ArrayList<Integer> cardValue(Player playerHand) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> sortedValues = new ArrayList<>(values);
        if (playerHand.hand.size() > 2) {
            for (Card card : playerHand.hand) {
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

    /*

    public String findRank(Player playerHand) {
//            allCards.clear();
            List<Integer> values = new ArrayList<>(cardValue(playerHand));
            List<Integer> straight = new ArrayList<>();
            List<String> suits = new ArrayList<>();
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
            for (Card allCard : playerHand.hand) {
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
                for (Card card : playerHand.hand) {
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
                List<String> cardsTemp = new ArrayList<>(removeExtraCards(allCards, values, straight));
                for (String card : cardsTemp) {
                    if (card.contains(suitType)) {
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

     */

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

}
