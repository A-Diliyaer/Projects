package BlackJack;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FirstAttempt {

    public static final Scanner scan = new Scanner(System.in);
    public static final Random myRandom = new Random();
    public static final String[] suits={"CLUBS", "DIAMONDS", "SPADES", "HEARTS"};
    public static final String[] cards = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static final String[] deck = new String[52];
    public static String playerAddHand, dealerAddHand, answerHit;
    public static int cardDraw, sumPlayer1, sumPlayer2, sumDealer1, sumDealer2, playerFinal, dealerFinal;


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String game = "Blackjack";
        String answer;
        String[] playerHands, dealerHands;
        int dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4;
        int playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4;

        System.out.println("Welcome to BlackJack table #3!");
        do {
            sumPlayer2 = 0;
            sumDealer2 = 0;
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

                displayPlayerSumOfCards(playerHands[0], playerHands[1], playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4);

                cardValues(dealerHands[0]);
                dealerCardValue1 = cardValues(dealerHands[0])[0];
                dealerCardValue3 = cardValues(dealerHands[0])[1];
                cardValues(dealerHands[1]);
                dealerCardValue2 = cardValues(dealerHands[1])[0];
                dealerCardValue4 = cardValues(dealerHands[1])[1];

                displayDealerSumOfCards(dealerHands[0], dealerHands[1], dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4);

                playerFinal = Math.max(sumPlayer1, sumPlayer2);
                dealerFinal = Math.max(sumDealer1, sumDealer2);

                if (sumPlayer1 < 21 && sumPlayer2 < 21 && sumDealer1 < 21 && sumDealer2 < 21) {
                    wouldYouLikeAHit(playerHands[0], playerHands[1], dealerHands[0], dealerHands[1], playerCardValue1, dealerCardValue1);
                } else if (playerFinal == 21 || dealerFinal == 21){  //this is when the player get 21 right on the first hand of cards
                    //dealerHits(dealerHands[0], dealerHands[1], dealerCardValue1);
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
    public static void displayPlayerSumOfCards(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4) {
        if (hand1.startsWith("A") && !hand2.startsWith("A")) {
            sumPlayer1 = cardValue1 + cardValue2;
            sumPlayer2 = cardValue3 + cardValue2;
            System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
        } else if (!hand1.startsWith("A") && hand2.startsWith("A")) {
            sumPlayer1 = cardValue1 + cardValue2;
            sumPlayer2 = cardValue1 + cardValue4;
            System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
        } else if (hand1.startsWith("A") && hand2.startsWith("A")) {
            sumPlayer1 = cardValue1 + cardValue2;
            sumPlayer2 = cardValue1 + cardValue4;
            System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
        } else {
            sumPlayer1 = cardValue1 + cardValue2;
            System.out.println("The sum of your cards is " + sumPlayer1);
        }

    }
    public static void displayDealerSumOfCards(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4) {
        if (hand1.startsWith("A") && !hand2.startsWith("A")) {
            sumDealer1 = cardValue1 + cardValue2;
            sumDealer2 = cardValue3 + cardValue2;
            System.out.println("The sum of dealer's card is " + cardValue1 + "/" + cardValue3);
        } else if (!hand1.startsWith("A") && hand2.startsWith("A")) {
            sumDealer1 = cardValue1 + cardValue2;
            sumDealer2 = cardValue1 + cardValue4;
            System.out.println("The sum of dealer's cards is " + cardValue1);
        } else if (hand1.startsWith("A") && hand2.startsWith("A")) {
            sumDealer1 = cardValue1 + cardValue2;
            sumDealer2 = cardValue1 + cardValue4;
            System.out.println("The sum of dealer's cards is " + cardValue1 + "/" + cardValue3);
        } else {
            sumDealer1 = cardValue1 + cardValue2;
            System.out.println("The sum of dealer's card is " + cardValue1);
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
            System.out.println("The player gets one more card");
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
            if ((playerHand1.startsWith("A") || playerHand2.startsWith("A")) && playerFinal > 21) {
                playerFinal = sumPlayer1 + playerCardValue1;
            }
            System.out.println(playerHand1 + ", " + playerHand2 + playerAddHand);
            System.out.println(playerFinal);
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
            if ((dealerHand1.startsWith("A") || dealerHand2.startsWith("A")) && dealerFinal > 21) {
                dealerFinal -= 10;
            }
            System.out.println(dealerHand1 + ", " + dealerHand2 + dealerAddHand);
            System.out.println(dealerFinal);
            ++cardDraw;

        }
    }
    public static void displayResults(String dealerHand1, String dealerHand2) {
        if ((playerFinal > dealerFinal || dealerFinal > 21) && playerFinal < 22) {
            displayYouWin(dealerHand1, dealerHand2);
        } else if (playerFinal == dealerFinal) {
            displayItIsADraw(dealerHand1, dealerHand2);
        } else {
            displayYouLose(dealerHand1, dealerHand2);
        }
    }/*
    public static void displayResults21(String dealerHand1, String dealerHand2) {
        if (playerFinal == dealerFinal) {
            displayItIsADraw(dealerHand1, dealerHand2);
        } else {
            displayYouWin(dealerHand1, dealerHand2);
        }
    }
    */
    public static void displayYouWin(String dealerHand1, String dealerHand2) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("Winner! Winner! Chicken Dinner!");
    }
    public static void displayItIsADraw(String dealerHand1, String dealerHand2) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("It's a draw!");
    }
    public static void displayYouLose(String dealerHand1, String dealerHand2) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("You lose!");
    }
}
