package BlackJack;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FirstAttempt {

    private static final Scanner scan = new Scanner(System.in);
    private static final Random myRandom = new Random();
    private static final String[] suits={"CLUBS", "DIAMONDS", "SPADES", "HEARTS"};
    private static final String[] cards = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private static final String[] deck = new String[52];
    private static String playerHand1, playerHand2, playerAddHand, dealerHand1, dealerHand2, dealerAddHand, answer,answerHit;
    private static int sumPlayer1, sumPlayer2, playerFinal, dealerFinal, sumDealer1, sumDealer2, cardDraw;
    private static int playerCardValue1 = 0, playerCardValue2 = 0, playerCardValue3 = 0, playerCardValue4 = 0;
    private static int dealerCardValue1 = 0, dealerCardValue2 = 0, dealerCardValue3 = 0, dealerCardValue4 = 0;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String game = "Blackjack";
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

                playerFirstTwoCards();

                dealerFirstTwoCards();

                playerCardsValue();

                displayPlayerSumOfCard();

                dealerCardsValue();

                displayDealerSumOfCards();

                playerFinal = Math.max(sumPlayer1, sumPlayer2);
                dealerFinal = Math.max(sumDealer1, sumDealer2);

                if (sumPlayer1 < 21 && sumPlayer2 < 21) {
                    wouldYouLikeAHit();
                } else if (playerFinal == 21){  //this is when the player get 21 right on the first hand of cards
                    dealerHits();
                    displayResults21();
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
    public static void playerFirstTwoCards() {
        playerHand1 = deck[cardDraw];
        ++cardDraw;
        playerHand2 = deck[cardDraw];
        ++cardDraw;
        System.out.println("Your cards are: " + playerHand1 + ", " + playerHand2);
    }
    public static void dealerFirstTwoCards() {
        dealerHand1 = deck[cardDraw];
        ++cardDraw;
        dealerHand2 = deck[cardDraw];
        ++cardDraw;
        System.out.println("Dealer's first card is: " + dealerHand1);
    }
    public static void playerCardsValue() {
        if (playerHand1.charAt(0) > 47 && playerHand1.charAt(0) < 58 && playerHand1.charAt(1) == ' ') {
            playerCardValue1 = Integer.parseInt(playerHand1.substring(0,1));
        } else if (playerHand1.startsWith("10") || playerHand1.startsWith("J") ||
                playerHand1.startsWith("Q") || playerHand1.startsWith("K")) {
            playerCardValue1 = 10;
        } else if (playerHand1.startsWith("A")) {
            playerCardValue1 = 1;
            playerCardValue3 = 11;
        }
        if (playerHand2.charAt(0) > 47 && playerHand2.charAt(0) < 58 && playerHand2.charAt(1) == ' ') {
            playerCardValue2 = Integer.parseInt(playerHand2.substring(0,1));
        } else if (playerHand2.startsWith("10") || playerHand2.startsWith("J") ||
                playerHand2.startsWith("Q") || playerHand2.startsWith("K")) {
            playerCardValue2 = 10;
        } else if (playerHand2.startsWith("A")) {
            playerCardValue2 = 1;
            playerCardValue4 = 11;
        }
    }

    public static void displayPlayerSumOfCard() {
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
    }
    public static void dealerCardsValue() {
        if (dealerHand1.charAt(0) > 47 && dealerHand1.charAt(0) < 58 && dealerHand1.charAt(1) == ' ') {
            dealerCardValue1 = Integer.parseInt(dealerHand1.substring(0,1));
        } else if (dealerHand1.startsWith("10") || dealerHand1.startsWith("J") ||
                dealerHand1.startsWith("Q") || dealerHand1.startsWith("K")) {
            dealerCardValue1 = 10;
        } else if (dealerHand1.startsWith("A")) {
            dealerCardValue1 = 1;
            dealerCardValue3 = 11;
        }
        if (dealerHand2.charAt(0) > 47 && dealerHand2.charAt(0) < 58 && dealerHand2.charAt(1) == ' ') {
            dealerCardValue2 = Integer.parseInt(dealerHand2.substring(0,1));
        } else if (dealerHand2.startsWith("10") || dealerHand2.startsWith("J") ||
                dealerHand2.startsWith("Q") || dealerHand2.startsWith("K")) {
            dealerCardValue2 = 10;
        } else if (dealerHand2.startsWith("A")) {
            dealerCardValue2 = 1;
            dealerCardValue4 = 11;
        }
    }
    public static void displayDealerSumOfCards() {
        if (dealerHand1.startsWith("A") && !dealerHand2.startsWith("A")) {
            sumDealer1 = dealerCardValue1 + dealerCardValue2;
            sumDealer2 = dealerCardValue3 + dealerCardValue2;
            System.out.println("The sum of dealer's card is " + dealerCardValue1 + "/" + dealerCardValue3);
        } else if (!dealerHand1.startsWith("A") && dealerHand2.startsWith("A")) {
            sumDealer1 = dealerCardValue1 + dealerCardValue2;
            sumDealer2 = dealerCardValue1 + dealerCardValue4;
            System.out.println("The sum of dealer's cards is " + dealerCardValue1);
        } else if (dealerHand1.startsWith("A") && dealerHand2.startsWith("A")) {
            sumDealer1 = dealerCardValue1 + dealerCardValue2;
            sumDealer2 = dealerCardValue1 + dealerCardValue4;
            System.out.println("The sum of dealer's cards is " + dealerCardValue1 + "/" + dealerCardValue3);
        } else {
            sumDealer1 = dealerCardValue1 + dealerCardValue2;
            System.out.println("The sum of dealer's card is " + dealerCardValue1);
        }
    }
    public static void wouldYouLikeAHit() {
        System.out.println("Would you like a hit?");  // Ask if the player wants a hit
        answerHit = scan.next();
        if (answerHit.equalsIgnoreCase("yes")) { // if player what a hit
            playerHits();
        }
        if (answerHit.equalsIgnoreCase("no")){ // if player does not want a hit

            dealerHits();
            displayResults();
        }
    }
    public static void playerHits() {
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
    public static void dealerHits() {
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2);
        dealerAddHand = "";
        while (dealerFinal < 17) {
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
    public static void displayResults() {
        if (playerFinal > dealerFinal || dealerFinal > 21) {
            displayYouWin();
        } else if (playerFinal == dealerFinal) {
            displayItIsADraw();
        } else {
            displayYouLose();
        }
    }
    public static void displayResults21() {
        if (playerFinal == dealerFinal) {
            displayItIsADraw();
        } else {
            displayYouWin();
        }
    }
    public static void displayYouWin() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("Winner! Winner! Chicken Dinner!");
    }
    public static void displayItIsADraw() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("It's a draw!");
    }
    public static void displayYouLose() {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("You lose!");
    }
}
