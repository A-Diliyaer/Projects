package PokerCode_WithOOP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {


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

    public String findRank(Player player, Community community) {
        ArrayList<Card> playerHandCopy = new ArrayList<>(player.hand);
        playerHandCopy.addAll(community.communityCards);
        ArrayList<Integer> values = new ArrayList<>(cardValue(playerHandCopy));
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
                List<Card> cardsTemp = new ArrayList<>(removeExtraCards(playerHandCopy, values, straight));
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
        ArrayList<Card> playerHandCopy = new ArrayList<>(player.hand);
        playerHandCopy.addAll(community.communityCards);
        ArrayList<Integer> values = new ArrayList<>(cardValue(playerHandCopy));
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
                }
                break;
            case "Flush":
                for (Card card : playerHandCopy) {
                    suits.add(card.suitType);
                }
                for (String suit : suits) {
                    if (Collections.frequency(suits, suit) == 5) {
                        for (Card card : playerHandCopy) {
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
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
                    highRankCards.addAll(sortCards(removeExtraCards(playerHandCopy, values, newValues), newValues));
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
                }

                break;
            case "High Card":
                if (community.communityCards.size() == 5) {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
                    highRankCards.remove(0);
                    highRankCards.remove(1);
                } else if (community.communityCards.size() == 4) {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
                    highRankCards.remove(0);
                } else {
                    highRankCards.addAll(sortCards(playerHandCopy, values));
                }
                break;
        }
        return highRankCards;
    }

    public ArrayList<Card> removeExtraCards(ArrayList<Card> playerHand, ArrayList<Integer> originalValues, ArrayList<Integer> shortValues) {
        ArrayList<Integer> copyValue = new ArrayList<>(originalValues);
        for (Integer shortValue : shortValues) {
            copyValue.remove(shortValue);
        }
        List<Integer> toBeRemoved = new ArrayList<>(copyValue);
        for (Integer value : toBeRemoved) {
            playerHand.remove(originalValues.indexOf(value));
            originalValues.remove(value);
        }
        return playerHand;
    }

    public ArrayList<Card> sortCards(ArrayList<Card> hand, List<Integer> values) {
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

    public void displayWinner(ArrayList<Player> players, Community community) throws InterruptedException {
        ArrayList<String> cardRanks = new ArrayList<>(Arrays.asList("Royal Flush", "Straight Flush", "Four Of A Kind", "Full House",
                "Flush", "Straight", "Three Of A Kind", "Two Pair", "One Pair", "High Card"));
        ArrayList<Integer> rankIndex = new ArrayList<>();
        ArrayList<Integer> sameRankIndex = new ArrayList<>();
        ArrayList<Integer> sumOfCards = new ArrayList<>();
        ArrayList<Integer> pairList = new ArrayList<>();
        ArrayList<Integer> sumOfPairs = new ArrayList<>();
        ArrayList<Integer> multiWinner = new ArrayList<>();
        ArrayList<List<Integer>> listOfValues = new ArrayList<>();
        ArrayList<String> allPlayerRanks = new ArrayList<>();
        ArrayList<ArrayList<Card>> allPlayerCardsList = new ArrayList<>();
        ArrayList<ArrayList<Card>> allPlayerHoleCards = new ArrayList<>();

        int highestRankIndex, indexOffset = 0, winnerIndex;
        String highestRank;
        for (Player player : players) {
            allPlayerRanks.add(findRank(player,community));
        }

        for (Player player : players) {
            allPlayerHoleCards.add(player.hand);
            allPlayerCardsList.add(find5Cards(player,community,findRank(player,community)));
        }

        System.out.println();
        for (String playerRank : allPlayerRanks) {
            rankIndex.add(cardRanks.indexOf(playerRank));
        }
        highestRankIndex = Collections.min(rankIndex);
        highestRank = allPlayerRanks.get(rankIndex.indexOf(highestRankIndex));
        if (Collections.frequency(rankIndex, highestRankIndex) == 1) {
            winnerIndex = rankIndex.indexOf(highestRankIndex);
            Thread.sleep(1000);
            System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
        } else {
            for (int i = 0; i < rankIndex.size(); i++) {
                Integer rank = rankIndex.get(i);
                if (rank == highestRankIndex) {
                    sameRankIndex.add(rankIndex.indexOf(rank) + indexOffset);
                    rankIndex.remove(rank);
                    i--;
                    indexOffset++;
                }
            }
            for (Integer rank : sameRankIndex) {
                listOfValues.add(cardValue(allPlayerCardsList.get(rank)));
            }
            for (List<Integer> values : listOfValues) {
                sumOfCards.add(findSum(values));

            }

            if (highestRank.equals("Two Pair")) {
                for (List<Integer> values : listOfValues) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 2) {
                            pairList.add(value);
                        }
                    }
                }
                for (int i = 0; i < pairList.size(); i += 4) {
                    sumOfPairs.add(pairList.get(i) + pairList.get(i + 1) + pairList.get(i + 2) + pairList.get(i + 3));
                }
                if (sumOfPairs.size() == 1) {
                    winnerIndex = sameRankIndex.get(sumOfCards.indexOf(Collections.max(sumOfCards)));
                    Thread.sleep(1000);
                    System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                } else if (sumOfPairs.size() > 1) {
                    if (Collections.frequency(pairList, Collections.max(pairList)) == 2) {
                        winnerIndex = sameRankIndex.get(pairList.indexOf(Collections.max(pairList)) / 4);
                        Thread.sleep(1000);
                        System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                    } else if (Collections.frequency(pairList, Collections.max(pairList)) == 10) {
                        for (int i = 0; i < 10; i++) {
                            pairList.remove(Collections.max(pairList));
                        }
                        if (Collections.frequency(pairList, Collections.max(pairList)) == 2) {
                            winnerIndex = sameRankIndex.get(pairList.indexOf(Collections.max(pairList)) / 2);
                            Thread.sleep(1000);
                            System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                        } else if (Collections.frequency(pairList, Collections.max(pairList)) == 10) {
                            if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 1) {
                                winnerIndex = sameRankIndex.get(sumOfCards.indexOf(Collections.max(sumOfCards)));
                                Thread.sleep(1000);
                                System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                            } else if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 5) {
                                for (int i = 0; i < 5; i++) {
                                    if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(0))) {
                                        multiWinner.add(i);
                                    } else if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(1))) {
                                        multiWinner.add(i);
                                    }
                                }
                                if (multiWinner.size() != 0) {
                                    for (Integer winner : multiWinner) {
                                        Thread.sleep(1000);
                                        System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                                    }
                                } else {
                                    Thread.sleep(1000);
                                    System.out.println("It's a Draw! Everyone Wins!");
                                }
                            } else {
                                for (int i = 0; i < sumOfCards.size(); i++) {
                                    if (sumOfCards.get(i).equals(Collections.max(sumOfCards))) {
                                        multiWinner.add(sameRankIndex.get(i));
                                    }
                                }
                                for (Integer winner : multiWinner) {
                                    Thread.sleep(1000);
                                    System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                                }
                            }
                        }
                    } else {
                        for (int i = 2; i < pairList.size(); i += 4) {
                            if (!pairList.get(i).equals(Collections.max(pairList))) {
                                pairList.set(i, 0);
                                pairList.set(i + 1, 0);
                                pairList.set(i - 1, 0);
                                pairList.set(i - 2, 0);
                            } else if (pairList.get(i).equals(Collections.max(pairList))) {
                                pairList.set(i, 0);
                                pairList.set(i + 1, 0);
                            }
                        }
                        if (Collections.frequency(pairList, Collections.max(pairList)) == 2) {
                            winnerIndex = sameRankIndex.get(pairList.indexOf(Collections.max(pairList)) / 4);
                            Thread.sleep(1000);
                            System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                        } else {
                            for (int i = 0; i < pairList.size(); i++) {
                                if (!pairList.get(i).equals(Collections.max(pairList))) {
                                    sumOfCards.set(i / 4, 0);
                                }
                            }
                            for (int i = 0; i < sumOfCards.size(); i++) {
                                if (sumOfCards.get(i).equals(Collections.max(sumOfCards))) {
                                    multiWinner.add(sameRankIndex.get(i));
                                }
                            }
                            for (Integer winner : multiWinner) {
                                Thread.sleep(1000);
                                System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                            }
                        }
                    }
                }
            } else if (highestRank.equals("One Pair")) {
                for (List<Integer> values : listOfValues) {
                    for (Integer value : values) {
                        if (Collections.frequency(values, value) == 2) {
                            pairList.add(value);
                        }
                    }
                }
                if (Collections.frequency(pairList, Collections.max(pairList)) == 2) {
                    winnerIndex = sameRankIndex.get((pairList.indexOf(Collections.max(pairList)) / 2));
                    Thread.sleep(1000);
                    System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                } else {
                    for (int i = 0; i < listOfValues.size(); i++) {
                        if (!listOfValues.get(i).contains(Collections.max(pairList))) {
                            sumOfCards.set(i, 0);
                        }
                    }
                    if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 1) {
                        winnerIndex = sameRankIndex.get(sumOfCards.indexOf(Collections.max(sumOfCards)));
                        Thread.sleep(1000);
                        System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                    } else if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 5) {
                        for (int i = 0; i < 5; i++) {
                            if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(0))) {
                                multiWinner.add(i);
                            } else if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(1))) {
                                multiWinner.add(i);
                            }
                        }
                        if (multiWinner.size() != 0) {
                            for (Integer winner : multiWinner) {
                                Thread.sleep(1000);
                                System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                            }
                        } else {
                            Thread.sleep(1000);
                            System.out.println("It's a Draw! Everyone Wins!");
                        }
                    } else {
                        for (int i = 0; i < sumOfCards.size(); i++) {
                            if (sumOfCards.get(i).equals(Collections.max(sumOfCards))) {
                                multiWinner.add(sameRankIndex.get(i));
                            }
                        }
                        for (Integer winner : multiWinner) {
                            Thread.sleep(1000);
                            System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                        }
                    }
                }
            } else {
                if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 1) {
                    winnerIndex = sameRankIndex.get(sumOfCards.indexOf(Collections.max(sumOfCards)));
                    Thread.sleep(1000);
                    System.out.println(players.get(winnerIndex).name + " wins with " + highestRank + " " + allPlayerCardsList.get(winnerIndex));
                } else if (Collections.frequency(sumOfCards, Collections.max(sumOfCards)) == 5) {
                    for (int i = 0; i < 5; i++) {
                        if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(0))) {
                            multiWinner.add(i);
                        } else if (allPlayerCardsList.get(i).contains(allPlayerHoleCards.get(i).get(1))) {
                            multiWinner.add(i);
                        }
                    }
                    if (multiWinner.size() != 0) {
                        for (Integer winner : multiWinner) {
                            Thread.sleep(1000);
                            System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                        }
                    } else {
                        Thread.sleep(1000);
                        System.out.println("It's a Draw! Everyone Wins!");
                    }
                } else {
                    for (int i = 0; i < sumOfCards.size(); i++) {
                        if (sumOfCards.get(i).equals(Collections.max(sumOfCards))) {
                            multiWinner.add(sameRankIndex.get(i));
                        }
                    }
                    for (Integer winner : multiWinner) {
                        Thread.sleep(1000);
                        System.out.println(players.get(winner).name + " Wins with " + highestRank + " " + allPlayerCardsList.get(winner));
                    }
                }
            }
        }
    }

    public static int findSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

}
