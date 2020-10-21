package BlackJack;

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
        String playerHand1="", playerHand2="", playerAddHand="", dealerHand1="", dealerHand2="", dealerAddHand="" , answer,answerHit="";
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
                System.out.println("Your cards are: " + playerHand1 + ", " + playerHand2);

                //dealerFirstTwoCards();
                dealsFirstTwoCards(dealerHand1, dealerHand2, cardDraw);
                System.out.println("Dealer's card are: " + dealerHand1);

                //playerCardsValue();
                cardValues(playerHand1, playerCardValue1, playerCardValue3);
                cardValues(playerHand2, playerCardValue2, playerCardValue4);


                displayPlayerSumOfCard(playerHand1, playerHand2, playerCardValue1, playerCardValue2, playerCardValue3, playerCardValue4, sumPlayer1, sumPlayer2);

                //dealerCardsValue();
                cardValues(dealerHand1, dealerCardValue1, dealerCardValue3);
                cardValues(dealerHand2, dealerCardValue2, dealerCardValue4);

                displayDealerSumOfCards(dealerHand1, dealerHand2, dealerCardValue1, dealerCardValue2, dealerCardValue3, dealerCardValue4, sumDealer1, sumDealer2);

                playerFinal = Math.max(sumPlayer1, sumPlayer2);
                dealerFinal = Math.max(sumDealer1, sumDealer2);

                if (sumPlayer1 < 21 && sumPlayer2 < 21) {
                    wouldYouLikeAHit(playerAddHand, playerHand1, playerHand2, dealerHand1, dealerHand2, dealerAddHand, answerHit, playerFinal, playerCardValue1, cardDraw, sumPlayer1, dealerCardValue1, dealerFinal);
                } else if (playerFinal == 21){  //this is when the player get 21 right on the first hand of cards
                    dealerHits(dealerHand1, dealerHand2, dealerAddHand, dealerCardValue1, dealerFinal, cardDraw);
                    displayResults21(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
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
    private static void dealsFirstTwoCards(String hand1, String hand2, int draw) {
        hand1 = deck[draw];
        ++draw;
        hand2 = deck[draw];
        ++draw;

    }
    public static void cardValues(String hand1, int cardValue1, int cardValue3) {
    if (hand1.charAt(0) > 47 && hand1.charAt(0) < 58 && hand1.charAt(1) == ' ') {
        cardValue1 = Integer.parseInt(hand1.substring(0,1));
    } else if (hand1.startsWith("10") || hand1.startsWith("J") ||
            hand1.startsWith("Q") || hand1.startsWith("K")) {
        cardValue1 = 10;
    } else if (hand1.startsWith("A")) {
        cardValue1 = 1;
        cardValue3 = 11;
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
    public static void wouldYouLikeAHit(String playerAddHand, String playerHand1, String playerHand2,
                                        String dealerHand1, String dealerHand2, String dealerAddHand,
                                        String answerHit, int playerFinal, int playerCardValue1, int cardDraw,
                                        int sumPlayer1, int dealerCardValue1, int dealerFinal) {
        System.out.println("Would you like a hit?");  // Ask if the player wants a hit
        answerHit = scan.next();
        if (answerHit.equalsIgnoreCase("yes")) { // if player what a hit
            playerHits(playerAddHand, playerHand1,  playerHand2, answerHit,playerFinal,playerCardValue1,  cardDraw, sumPlayer1);
        }
        if (answerHit.equalsIgnoreCase("no")){ // if player does not want a hit

            dealerHits(dealerHand1, dealerHand2, dealerAddHand, dealerCardValue1, dealerFinal, cardDraw);
            displayResults(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
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
    public static void dealerHits(String dealerHand1, String dealerHand2, String dealerAddHand, int dealerCardValue1, int dealerFinal, int cardDraw) {
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
    public static void displayResults(String dealerHand1, String dealerHand2, String dealerAddHand, int playerFinal, int dealerFinal) {
        if (playerFinal > dealerFinal || dealerFinal > 21) {
            displayYouWin(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
        } else if (playerFinal == dealerFinal) {
            displayItIsADraw(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
        } else {
            displayYouLose(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
        }
    }
    public static void displayResults21(String dealerHand1, String dealerHand2, String dealerAddHand, int playerFinal, int dealerFinal) {
        if (playerFinal == dealerFinal) {
            displayItIsADraw(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
        } else {
            displayYouWin(dealerHand1, dealerHand2, dealerAddHand, playerFinal, dealerFinal);
        }
    }
    public static void displayYouWin(String dealerHand1, String dealerHand2, String dealerAddHand, int playerFinal, int dealerFinal) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("Winner! Winner! Chicken Dinner!");
    }
    public static void displayItIsADraw(String dealerHand1, String dealerHand2, String dealerAddHand, int playerFinal, int dealerFinal) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("It's a draw!");
    }
    public static void displayYouLose(String dealerHand1, String dealerHand2, String dealerAddHand, int playerFinal, int dealerFinal) {
        System.out.println("The sum of your cards is " + playerFinal);
        System.out.println("Dealer's cards are: " + dealerHand1 + ", " + dealerHand2 + dealerAddHand);
        System.out.println("The sum of dealer's cards is " + dealerFinal);
        System.out.println("You lose!");
    }
}
