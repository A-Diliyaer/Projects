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


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String game = "Blackjack";
        String playerHand1="", playerHand2="", playerAddHand, dealerHand1="", dealerHand2="", dealerAddHand, answer,answerHit = "";
        int sumPlayer1=0, sumPlayer2, playerFinal, dealerFinal, sumDealer1=0, sumDealer2, cardDraw;
        int dealerCardValue1 = 0, dealerCardValue2 = 0, dealerCardValue3 = 0, dealerCardValue4 = 0;
        int playerCardValue1 = 0, playerCardValue2 = 0, playerCardValue3 = 0, playerCardValue4 = 0;
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

                //playerFirstTwoCards();
                dealsFirstTwoCards(playerHand1, playerHand2, cardDraw);

                //dealerFirstTwoCards();
                dealsFirstTwoCards(dealerHand1, dealerHand2, cardDraw);

                //playerCardsValue();
                cardValues(playerHand1, playerHand2, playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4);

                displayPlayerSumOfCard(playerHand1, playerHand2, playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4, sumPlayer1, sumPlayer2);

                //dealerCardsValue();
                cardValues(dealerHand1, dealerHand2, dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4);

                displayDealerSumOfCards(dealerHand1, dealerHand2, dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4, sumDealer1, sumDealer2);

                playerFinal = Math.max(sumPlayer1, sumPlayer2);
                dealerFinal = Math.max(sumDealer1, sumDealer2);

                if (sumPlayer1 < 21 && sumPlayer2 < 21) {
                    wouldYouLikeAHit(answerHit);
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

    public static void dealsFirstTwoCards(String hand1, String hand2, int draw) {
        hand1 = deck[draw];
        ++draw;
        hand2 = deck[draw];
        ++draw;
        System.out.println("Your cards are: " + hand1 + ", " + hand2);
    }
    public static void cardValues(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4) {
    if (hand1.charAt(0) > 47 && hand1.charAt(0) < 58 && hand1.charAt(1) == ' ') {
        cardValue1 = Integer.parseInt(hand1.substring(0,1));
    } else if (hand1.startsWith("10") || hand1.startsWith("J") ||
            hand1.startsWith("Q") || hand1.startsWith("K")) {
        cardValue1 = 10;
    } else if (hand1.startsWith("A")) {
        cardValue1 = 1;
        cardValue3 = 11;
    }
    if (hand2.charAt(0) > 47 && hand2.charAt(0) < 58 && hand2.charAt(1) == ' ') {
        cardValue2 = Integer.parseInt(hand2.substring(0,1));
    } else if (hand2.startsWith("10") || hand2.startsWith("J") ||
            hand2.startsWith("Q") || hand2.startsWith("K")) {
        cardValue2 = 10;
    } else if (hand2.startsWith("A")) {
        cardValue2 = 1;
        cardValue4 = 11;
    }
}
    public static void displayPlayerSumOfCard(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4, int sum1, int sum2) {
        if (hand1.startsWith("A") && !hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue3 + cardValue2;
            System.out.println("The sum of your cards is " + sum1 + "/" + sum2);
        } else if (!hand1.startsWith("A") && hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue1 + cardValue4;
            System.out.println("The sum of your cards is " + sum1 + "/" + sum2);
        } else if (hand1.startsWith("A") && hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue1 + cardValue4;
            System.out.println("The sum of your cards is " + sum1 + "/" + sum2);
        } else {
            sum1 = cardValue1 + cardValue2;
            System.out.println("The sum of your cards is " + sum1);
        }
    }
    public static void displayDealerSumOfCards(String hand1, String hand2, int cardValue1, int cardValue2, int cardValue3, int cardValue4, int sum1, int sum2) {
        if (hand1.startsWith("A") && !hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue3 + cardValue2;
            System.out.println("The sum of dealer's card is " + cardValue1 + "/" + cardValue3);
        } else if (!hand1.startsWith("A") && hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue1 + cardValue4;
            System.out.println("The sum of dealer's cards is " + cardValue1);
        } else if (hand1.startsWith("A") && hand2.startsWith("A")) {
            sum1 = cardValue1 + cardValue2;
            sum2 = cardValue1 + cardValue4;
            System.out.println("The sum of dealer's cards is " + cardValue1 + "/" + cardValue3);
        } else {
            sum1 = cardValue1 + cardValue2;
            System.out.println("The sum of dealer's card is " + cardValue1);
        }
    }
    public static void wouldYouLikeAHit(String answerHit) {
        System.out.println("Would you like a hit?");  // Ask if the player wants a hit
        answerHit = scan.next();
        if (answerHit.equalsIgnoreCase("yes")) { // if player what a hit
            playerHits(playerAddHand, playerHand1,  playerHand2, answerHit,playerFinal,playerCardValue1,  cardDraw, sumPlayer1);
        }
        if (answerHit.equalsIgnoreCase("no")){ // if player does not want a hit

            dealerHits();
            displayResults();
        }
    }
    public static void playerHits(String playerAddHand, String playerHand1, String playerHand2, String answerHit, int playerFinal, int playerCardValue1, int cardDraw, int sumPlayer1) {
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
