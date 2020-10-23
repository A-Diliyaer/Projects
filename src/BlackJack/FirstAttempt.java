package BlackJack;

import java.util.Random;
import java.util.Scanner;

public class FirstAttempt {

    public static final Scanner scan = new Scanner(System.in);
    public static final Random myRandom = new Random();
    public static final String[] suits={"CLUBS", "DIAMONDS", "SPADES", "HEARTS"};
    public static final String[] cards = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static final String[] deck = new String[52];
    public static String playerAddHand = "", dealerAddHand = "", answerHit;
    public static int cardDraw, playerFinal, dealerFinal;
    public static int[] sumsPlayer = new int[2], sumsDealer = new int[2];


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String game = "Blackjack";
        String answer;
        String[] playerHands, dealerHands;
        int dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4;
        int playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4;

        System.out.println("Welcome to BlackJack table #3!");
        do {
            sumsPlayer[1] = 0;
            sumsDealer[1] = 0;
            cardDraw = 0;
            System.out.println("Would you like to play " + game + "?");
            answer = scan.next();
            if (answer.equalsIgnoreCase("yes")) {
                createDeckOfCards();

                shuffleDeckOfCards();

                playerHands = dealsFirstTwoCards();

                System.out.println("Your cards are: " + playerHands[0] + ", " + playerHands[1]);


                dealerHands = dealsFirstTwoCards();

                System.out.println("Dealer's card are: " + dealerHands[0]);
                System.out.println();

                cardValues(playerHands[0]);
                playerCardValue1 = cardValues(playerHands[0])[0];
                playerCardValue3 = cardValues(playerHands[0])[1];
                cardValues(playerHands[1]);
                playerCardValue2 = cardValues(playerHands[1])[0];
                playerCardValue4 = cardValues(playerHands[1])[1];
                sumsPlayer = sumOfCards(playerHands[0], playerHands[1], playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4);

                cardValues(dealerHands[0]);
                dealerCardValue1 = cardValues(dealerHands[0])[0];
                dealerCardValue3 = cardValues(dealerHands[0])[1];
                cardValues(dealerHands[1]);
                dealerCardValue2 = cardValues(dealerHands[1])[0];
                dealerCardValue4 = cardValues(dealerHands[1])[1];
                sumsDealer = sumOfCards(dealerHands[0], dealerHands[1], dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4);

                if (sumsPlayer[0] != 21 && sumsPlayer[1] != 21 && sumsDealer[0] != 21 && sumsDealer[1] != 21) {
                    displaySumOfCards(playerHands[0], playerHands[1], dealerHands[0], dealerCardValue1, dealerCardValue3);
                }


                playerFinal = Math.max(sumsPlayer[0], sumsPlayer[1]);
                dealerFinal = Math.max(sumsDealer[0], sumsDealer[1]);

                if (sumsPlayer[0] < 21 && sumsPlayer[1] < 21 && sumsDealer[0] < 21 && sumsDealer[1] < 21) {
                    wouldYouLikeAHit(playerHands[0], playerHands[1], dealerHands[0], dealerHands[1], playerCardValue1, dealerCardValue1);
                } else if (playerFinal == 21 || dealerFinal == 21){  //this is when the player get 21 right on the first hand of cards

                    displayResults(dealerHands[0], dealerHands[1]);
                }
            }
            game = "another game";
        } while (answer.equalsIgnoreCase("yes"));
        System.out.println("Next time then! Enjoy your stay at Casino hotel! ");
    }
    public static void createDeckOfCards() {
        int i = 0;
        for (String suit : suits) {
            for (String card : cards) {
                deck[i] = card + " " + suit;
                ++i;
            }
        }
    }
    public static void shuffleDeckOfCards() {
        for (int j = 0; j < deck.length; j++) {
            int randomIndexToSwap = myRandom.nextInt(deck.length);
            String temp = deck[randomIndexToSwap];
            deck[randomIndexToSwap] = deck[j];
            deck[j] = temp;
        }
    }
    private static String[] dealsFirstTwoCards() {
        String hand1 = deck[cardDraw];
        ++cardDraw;
        String hand2 = deck[cardDraw];
        ++cardDraw;
        return new String[] {hand1, hand2};
    }
    public static int[] cardValues(String hand1) {
        int cardValue1 = 0, cardValue3 = 0;
        if (hand1.charAt(0) > 47 && hand1.charAt(0) < 58 && hand1.charAt(1) == ' ') {
        cardValue1 = Integer.parseInt(hand1.substring(0,1));
    } else if (hand1.startsWith("10") || hand1.startsWith("J") ||
            hand1.startsWith("Q") || hand1.startsWith("K")) {
        cardValue1 = 10;
    } else if (hand1.startsWith("A")) {
        cardValue1 = 1;
        cardValue3 = 11;
    }
    return new int[] {cardValue1, cardValue3};
}
    public static int[] sumOfCards(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4) {
        int sum1;
        int sum2;
        if (hand1.startsWith("A") && !hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue3 + cardValue2;
        } else {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue1 + cardValue4;
        }
        return new int[] {sum1, sum2};
    }
    public static void displaySumOfCards(String hand1, String hand2, String hand3, int cardValue1, int cardValue3) {
        if (hand1.startsWith("A") || hand2.startsWith("A")) {
            System.out.println("The sum of your cards is " + sumsPlayer[0] + "/" + sumsPlayer[1]);
        } else {
            System.out.println("The sum of your cards is " + sumsPlayer[0]);
        }
        if (hand3.startsWith("A")) {
            System.out.println("The sum of dealer's card is " + cardValue1 + "/" + cardValue3 + "\n");
        } else {
            System.out.println("The sum of dealer's cards is " + cardValue1 + "\n");
        }

    }
    public static void wouldYouLikeAHit(String playerHand1, String playerHand2, String dealerHand1, String dealerHand2, int playerCardValue1, int dealerCardValue1) {
        System.out.println("Would you like a hit?");  // Ask if the player wants a hit
        answerHit = scan.next();
        if (answerHit.equalsIgnoreCase("yes")) { // if player what a hit
            playerHits(playerHand1, playerHand2, playerCardValue1);
        }
        if (answerHit.equalsIgnoreCase("no")){ // if player does not want a hit
            dealerHits(dealerHand1, dealerHand2, dealerCardValue1);
            displayResults(dealerHand1, dealerHand2);
        }
    }
    public static void playerHits(String playerHand1, String playerHand2, int playerCardValue1) {
        playerAddHand = "";
        do {
            System.out.println("\n" + "The player gets one more card" + "\n");
            playerAddHand += ", " + deck[cardDraw];
            if (deck[cardDraw].charAt(0) > 47 && deck[cardDraw].charAt(0) < 58 && deck[cardDraw].charAt(1) == ' ') {
                playerCardValue1 = Integer.parseInt(deck[cardDraw].substring(0,1));
            } else if (deck[cardDraw].startsWith("10") || deck[cardDraw].startsWith("J") ||
                    deck[cardDraw].startsWith("Q") || deck[cardDraw].startsWith("K")) {
                playerCardValue1 = 10;
            } else if (deck[cardDraw].startsWith("A")) {
                if (playerFinal > 10) {
                    playerCardValue1 = 1;
                } else {
                    playerCardValue1 = 11;
                }
            }
            playerFinal += playerCardValue1;
            if ((playerHand1.startsWith("A") || playerHand2.startsWith("A")) && playerFinal > 21 && playerAddHand.length() < 13) {
                playerFinal = sumsPlayer[0] + playerCardValue1;
            }
            System.out.println(playerHand1 + ", " + playerHand2 + playerAddHand);
            System.out.println("Your new sum is " + playerFinal + "\n");
            ++cardDraw;
            if (playerFinal < 21) {
                System.out.println("Would you like another hit?");
                answerHit = scan.next();
            } else {
                answerHit = "no";
            }

        } while (answerHit.equalsIgnoreCase("yes"));
    }
    public static void dealerHits(String dealerHand1, String dealerHand2, int dealerCardValue1) {
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + "\n");
        dealerAddHand = "";
        while (dealerFinal < 17 && playerFinal < 22) {
            System.out.println("The dealer draws one more card");
            dealerAddHand += ", " + deck[cardDraw];
            if (deck[cardDraw].charAt(0) > 47 && deck[cardDraw].charAt(0) < 58 && deck[cardDraw].charAt(1) == ' ') {
                dealerCardValue1 = Integer.parseInt(deck[cardDraw].substring(0,1));
            } else if (deck[cardDraw].startsWith("10") || deck[cardDraw].startsWith("J") ||
                    deck[cardDraw].startsWith("Q") || deck[cardDraw].startsWith("K")) {
                dealerCardValue1 = 10;
            } else if (deck[cardDraw].startsWith("A")) {
                if (dealerFinal > 10) {
                    dealerCardValue1 = 1;
                } else {
                    dealerCardValue1 = 11;
                }
            }
            dealerFinal += dealerCardValue1;
            if ((dealerHand1.startsWith("A") || dealerHand2.startsWith("A")) && dealerFinal > 21&& dealerAddHand.length() < 13) {
                dealerFinal -= 10;
            }
            System.out.println(dealerHand1 + ", " + dealerHand2 + dealerAddHand);
            System.out.println("Dealer's new sum is " + dealerFinal + "\n");
            ++cardDraw;

        }
    }
    public static void displayResults(String dealerHand1, String dealerHand2) {
        if (playerAddHand.length() == 0 && dealerAddHand.length() == 0) {
            System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand + "\n");
        }
        if ((playerFinal > dealerFinal || dealerFinal > 21) && playerFinal < 22) {
            displayYouWin();
        } else if (playerFinal == dealerFinal) {
            displayItIsADraw();
        } else {
            displayYouLose();
        }
    }
    public static void displayYouWin() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        if (sumsPlayer[0] == 21 || sumsPlayer[1] == 21) {
            System.out.println("BlackJack! You Win!");
        } else {
            System.out.println("Winner! Winner! Chicken Dinner!");
        }

    }
    public static void displayItIsADraw() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("It's a draw!");
    }
    public static void displayYouLose() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        if (sumsDealer[0] == 21 || sumsDealer[1] == 21) {
            System.out.println("Dealer got BlackJack! You lose!");
        } else {
            System.out.println("You lose!");
        }

    }
}
