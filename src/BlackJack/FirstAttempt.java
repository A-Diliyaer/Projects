package BlackJack;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FirstAttempt {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random myRandom = new Random();
        String[] cards = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] suits = {"CLUBS", "DIAMONDS", "SPADES", "HEARTS"};
        String[] deck = new String[52];
        String answer, playerHand1, playerHand2, dealerHand1, dealerHand2;
        int sumPlayer1 = 0, sumPlayer2 = 0;
        int playerCardValue1 = 0, playerCardValue2 = 0, playerCardValue3 = 0, playerCardValue4 = 0;
        int sumDealer1 = 0, sumDealer2;
        int dealerCardValue1 = 0, dealerCardValue2 = 0, dealerCardValue3 = 0, dealerCardValue4 = 0;
        int cardDraw, count1, count2;
        System.out.println("Welcome to BlackJack table #3!");
        do {
            cardDraw = 0;
            count1 = 0;
            count2 =0;
            System.out.println("Would you like to play BlackJack?");
            answer = scan.next();
            if (answer.equalsIgnoreCase("yes")) {
                /** Creates a deck of 52 cards
                 *
                 */
                for (int i = 0; i < deck.length; i++) {
                    deck[i] = cards[count2] + " " + suits[count1];
                    ++count2;
                    if (i == 12 || i == 25 || i == 38) {
                        ++count1;
                        count2 = 0;
                    }
                }
                System.out.println(Arrays.toString(deck)); // Remove when complete
                /** Shuffles the deck
                 *
                 */
                for (int j = 0; j < deck.length; j++) {
                    int randomIndexToSwap = myRandom.nextInt(deck.length);
                    String temp = deck[randomIndexToSwap];
                    deck[randomIndexToSwap] = deck[j];
                    deck[j] = temp;
                }
                System.out.println(Arrays.toString(deck)); // Remove when complete
                /** Deals two cards to the player
                 *
                 */
                playerHand1 = deck[cardDraw];
                ++cardDraw;
                playerHand2 = deck[cardDraw];
                ++cardDraw;
                System.out.println("Your cards are: " + playerHand1 + ", " + playerHand2);
                /** Deals two cards to the dealer, but only show the first card
                 *
                 */
                dealerHand1 = deck[cardDraw];
                ++cardDraw;
                dealerHand2 = deck[cardDraw];
                ++cardDraw;
                System.out.println("Dealer's first card is: " + dealerHand1);
                /** Determines the sum of cards for the player
                 *
                 */
                if (playerHand1.charAt(0) > 47 && playerHand1.charAt(0) < 58 && playerHand1.charAt(1) == ' ') {
                    playerCardValue1 = Integer.parseInt(playerHand1.substring(0,1));
                } else if (playerHand1.startsWith("10") ||
                        playerHand1.substring(0,1).startsWith("J") ||
                        playerHand1.substring(0,1).startsWith("Q") ||
                        playerHand1.substring(0,1).startsWith("K")) {
                    playerCardValue1 = 10;
                } else if (playerHand1.startsWith("A")) {
                    playerCardValue1 = 1;
                    playerCardValue3 = 11;
                }
                if (playerHand2.charAt(0) > 47 && playerHand2.charAt(0) < 58 && playerHand2.charAt(1) == ' ') {
                    playerCardValue2 = Integer.parseInt(playerHand2.substring(0,1));
                } else if (playerHand2.startsWith("10") ||
                        playerHand2.substring(0,1).startsWith("J") ||
                        playerHand2.substring(0,1).startsWith("Q") ||
                        playerHand2.substring(0,1).startsWith("K")) {
                    playerCardValue2 = 10;
                } else if (playerHand2.startsWith("A")) {
                    playerCardValue2 = 1;
                    playerCardValue4 = 11;
                }
                /** Displays the sum of cards for the player
                 *
                 */
                if (playerHand1.startsWith("A") && !playerHand2.startsWith("A")) {
                    sumPlayer1 = playerCardValue1 + playerCardValue2;
                    sumPlayer2 = playerCardValue3 + playerCardValue2;
                    System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
                } else if (!playerHand1.startsWith("A") && playerHand2.startsWith("A")) {
                    sumPlayer1 = playerCardValue1 + playerCardValue2;
                    sumPlayer2 = playerCardValue1 + playerCardValue4;
                    System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
                } else if (playerHand1.startsWith("A") && playerHand2.startsWith("A")) {
                    sumPlayer1 = playerCardValue1 + playerCardValue2;
                    sumPlayer2 = playerCardValue1 + playerCardValue4;
                    System.out.println("The sum of your cards is " + sumPlayer1 + "/" + sumPlayer2);
                } else {
                    sumPlayer1 = playerCardValue1 + playerCardValue2;
                    System.out.println("The sum of your cards is " + sumPlayer1);
                }
                /** Determines the sum of cards for the dealer
                 *
                 */
                if (dealerHand1.charAt(0) > 47 && dealerHand1.charAt(0) < 58 && dealerHand1.charAt(1) == ' ') {
                    dealerCardValue1 = Integer.parseInt(dealerHand1.substring(0,1));
                } else if (dealerHand1.startsWith("10") ||
                        dealerHand1.substring(0,1).startsWith("J") ||
                        dealerHand1.substring(0,1).startsWith("Q") ||
                        dealerHand1.substring(0,1).startsWith("K")) {
                    dealerCardValue1 = 10;
                } else if (dealerHand1.startsWith("A")) {
                    dealerCardValue1 = 1;
                    dealerCardValue3 = 11;
                }
                if (dealerHand2.charAt(0) > 47 && dealerHand2.charAt(0) < 58 && dealerHand2.charAt(1) == ' ') {
                    dealerCardValue2 = Integer.parseInt(dealerHand2.substring(0,1));
                } else if (dealerHand2.startsWith("10") ||
                        dealerHand2.substring(0,1).startsWith("J") ||
                        dealerHand2.substring(0,1).startsWith("Q") ||
                        dealerHand2.substring(0,1).startsWith("K")) {
                    dealerCardValue2 = 10;
                } else if (dealerHand2.startsWith("A")) {
                    dealerCardValue2 = 1;
                    dealerCardValue4 = 11;
                }
                /** Displays the sum of cards for the dealer
                 *
                 */
                if (dealerHand1.startsWith("A")) {
                    sumDealer1 = dealerCardValue1;
                    sumDealer2 = dealerCardValue3;
                    System.out.println("The sum of dealer's card is " + sumDealer1 + "/" + sumDealer2);
                } else {
                    sumDealer1 = dealerCardValue1;
                    System.out.println("The sum of dealer's card is " + sumDealer1);
                }
                /** Ask if the player wants a hit
                 *
                 */
                if (sumPlayer1 < 21 || sumPlayer2 < 21) { // if players first hand of cards if not 21
                    System.out.println("Would you like a hit?");
                    answer = scan.next();
                    if (answer.equalsIgnoreCase("yes")) { // if player what a hit

                    } else { // if player does not want a hit
                        sumDealer2 = dealerCardValue1 + dealerCardValue2; // need to modify
                        if ((dealerCardValue1 == 1 || dealerCardValue2 == 1 || dealerCardValue3 == 11 || dealerCardValue4 == 11) && (sumDealer1 < 21 || sumDealer2 < 21)) {

                        }
                    }
                } else {
                    //this is when the player get 21 right on the first hand of cards
                }

            }

        } while (answer.equalsIgnoreCase("yes"));

    }
}
