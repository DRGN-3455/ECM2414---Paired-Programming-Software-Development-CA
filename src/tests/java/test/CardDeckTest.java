package test;

import cardgame.*;
import org.junit.jupiter.api.Assertions;
import java.io.*;
import java.util.Scanner;

class CardDeckTest {

    @org.junit.jupiter.api.Test
    void testAddCard() {
        boolean test1Flag; //create 4 normal cards
        boolean test2Flag; //assign the cards to the testDeck object.
        boolean test3Flag; //attempt to add 5 cards, should error to pass.
        boolean test4Flag; //adds 1 card and checks that it is placed at the top, as per FIFO
        boolean test5Flag; //adds 2 cards, checks order
        boolean test6Flag; //adds 4 cards, checks order

        try { //test1 create 4 normal cards
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        try { //test2 assign the cards to the testDeck object.
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            test2Flag = true;
        }
        catch (Exception e) {
            test2Flag = false;
        }

        try { //test3 attempt to add 5 cards, should error to pass.
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            Card card5 = new Card(5);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            testDeck.addCard(card5);
            test3Flag = false;
        }
        catch (Exception e) {
            test3Flag = true;
        }

        try { //test4 adds 1 card and checks that it is placed at the top, as per FIFO
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            testDeck.addCard(card1);
            Card[] testArray = new Card[4];
            testArray[0] = card1;
            Assertions.assertArrayEquals(testArray,testDeck.getDeckFIFO());
            test4Flag = true;
        }
        catch (Exception e) {
            test4Flag = false;
        }

        try { //test5 adds 2 cards, checks order
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            testDeck.addCard(card1);
            testDeck.addCard(card2);

            Card[] testArray = new Card[4];
            testArray[0] = card1;
            testArray[1] = card2;

            Assertions.assertArrayEquals(testArray,testDeck.getDeckFIFO());
            test5Flag = true;
        }
        catch (Exception e) {
            test5Flag = false;
        }

        try { //test6 adds 4 cards, checks order
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);

            Card[] testArray = new Card[4];
            testArray[0] = card1;
            testArray[1] = card2;
            testArray[2] = card3;
            testArray[3] = card4;

            Assertions.assertArrayEquals(testArray,testDeck.getDeckFIFO());
            test6Flag = true;
        }
        catch (Exception e) {
            test6Flag = false;
        }



        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
        Assertions.assertTrue(test3Flag);
        Assertions.assertTrue(test4Flag);
        Assertions.assertTrue(test5Flag);
        Assertions.assertTrue(test6Flag);
    }

    @org.junit.jupiter.api.Test
    void testDrawCard() {
        boolean test1Flag; //draw from a single item deck, with correct card returned
        boolean test2Flag; //draw from a full deck, with correct card returned
        boolean test3Flag; // attempt to draw from an empty deck

        try { //test1 draw from a single item deck, with correct card returned
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            testDeck.addCard(card1);
            Card expectedCard = new Card(1);
            Assertions.assertEquals(expectedCard.getValue(),testDeck.drawCard().getValue());
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        try { //test2 draw from a full deck, with correct card returned
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            Card expectedCard = new Card(1);
            Assertions.assertEquals(expectedCard.getValue(),testDeck.drawCard().getValue());
            test2Flag = true;
        }
        catch (Exception e) {
            test2Flag = false;
        }
        try { // attempt to draw from an empty deck
            CardDeck testDeck = new CardDeck();
            testDeck.drawCard();
            test3Flag = false;

        }
        catch (IllegalStateException e) {
            test3Flag = true;
        }

        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
        Assertions.assertTrue(test3Flag);
    }

    @org.junit.jupiter.api.Test
    void testGetDeckFIFO() {
        boolean test1Flag; //get the empty deck state
        boolean test2Flag; //add 4 cards and check it matches the expectation

        try { //test1 get the empty deck state
            CardDeck testDeck = new CardDeck();
            CardDeck[] expectedDeck = new CardDeck[4];
            Assertions.assertArrayEquals(expectedDeck,testDeck.getDeckFIFO());
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        try { //test2 add 4 cards and check it matches the expectation
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            Card[] expectedDeck = {card1,card2,card3,card4};
            Assertions.assertArrayEquals(expectedDeck,testDeck.getDeckFIFO());
            test2Flag = true;
        }
        catch (Exception e) {
            test2Flag = false;
        }

        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
    }

    @org.junit.jupiter.api.Test
    void testGetCardValues() {
        boolean test1Flag; //get card values when there are no cards
        boolean test2Flag; //add 4 cards and check it matches the expectation

        try  {//test1 get card values when there are no cards
            CardDeck testDeck = new CardDeck();
            int[] expectedCards = new int[4];
            Assertions.assertArrayEquals(expectedCards,testDeck.getCardValues());
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        try {//test2 add 4 cards and check it matches the expectation
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            int[] expectedCards = {card1.getValue(),card2.getValue(),card3.getValue(),card4.getValue()};
            Assertions.assertArrayEquals(expectedCards,testDeck.getCardValues());
            test2Flag = true;
        }
        catch (Exception e) {
            test2Flag = false;
        }
        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
    }

    @org.junit.jupiter.api.Test
    void testGetSize() {
        boolean test1Flag; //use getSize on an empty deck
        boolean test2Flag; //use getSize on a non-empty deck

        try {//test1 use getSize on an empty deck
            CardDeck testDeck = new CardDeck();
            Assertions.assertEquals(0,testDeck.getSize());
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        try {//test2 use getSize on a non-empty deck
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            Assertions.assertEquals(4,testDeck.getSize());
            test2Flag = true;
        }

        catch (IllegalStateException e) {
            test2Flag = false;
        }
        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
    }

    @org.junit.jupiter.api.Test
    void testGetDeckId() {
        boolean test1Flag; //gets id of a deck
        boolean test2Flag; // creates 2 decks and checks both id's

        try {//test1 gets id of a deck
            CardDeck testDeck = new CardDeck();
            int id = testDeck.getDeckId();

            if (id >= 1) {
                test1Flag = true;
            }
            else {
                test1Flag = false;
            }
        } catch (Exception e) {
            test1Flag = false;
        }

        try {//test2 creates 2 decks and checks both id's
            CardDeck testDeck = new CardDeck();
            CardDeck testDeck2 = new CardDeck();
            int id1 = testDeck.getDeckId();
            int id2 = testDeck2.getDeckId();

            if (id1 + 1 == id2 && id1 >= 1 && id2 >= 1) {
                test2Flag = true;
            } else {
                test2Flag = false;
            }
        }
        catch (Exception e) {
            test2Flag = false;
        }
        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);

    }

    @org.junit.jupiter.api.Test
    void testResetDeck() {
        boolean test1Flag; // tests that when reset deck occurs the id count also resets

        try  { //test1 tests that when reset deck occurs the id count also resets
            CardDeck testDeck = new CardDeck();
            int deckId = testDeck.getDeckId();
            CardDeck testDeck2 = new CardDeck();
            int deckId2 = testDeck2.getDeckId();
            CardDeck.resetDeck();
            CardDeck testDeck3 = new CardDeck();
            int deckId3 = testDeck3.getDeckId();

            int test1Expected = deckId + 1;

            Assertions.assertEquals(test1Expected,deckId2);
            Assertions.assertEquals(1,deckId3);
            test1Flag = true;
        }
        catch (Exception e) {
            test1Flag = false;
        }

        Assertions.assertTrue(test1Flag);
    }

    @org.junit.jupiter.api.Test
    void testWriteOutput() {
        boolean test1Flag; //test on empty deck
        boolean test2Flag; //test on full deck
        String message = "";
        String expectedMessage;
        try {//test1 test on empty deck
            CardDeck testDeck = new CardDeck();
            int[] expectedCards = new int[4];
            String expectedCardValues = expectedCards[0] + " " + expectedCards[1] + " " + expectedCards[2] + " " + expectedCards[3];
            expectedMessage = "deck" + testDeck.getDeckId() + " contents: " + expectedCardValues;

            testDeck.writeOutput();

            String fileName = "deck" + testDeck.getDeckId() + "_output.txt";
            File testOutput = new File(fileName);

            try (Scanner reader = new Scanner(testOutput)) {
                while (reader.hasNextLine()) {
                    message = reader.nextLine();
                }
            }
            catch (FileNotFoundException x) {
                throw new IllegalStateException("file could not be found or created");
            }

            test1Flag = expectedMessage.equals(message);

        }
        catch (Exception e) {
            test1Flag = false;
        }

        try {
            CardDeck testDeck = new CardDeck();
            Card card1 = new Card(1);
            Card card2 = new Card(2);
            Card card3 = new Card(3);
            Card card4 = new Card(4);
            testDeck.addCard(card1);
            testDeck.addCard(card2);
            testDeck.addCard(card3);
            testDeck.addCard(card4);
            int[] expectedCards = {card1.getValue(),card2.getValue(),card3.getValue(),card4.getValue()};
            String expectedCardValues =  expectedCards[0] + " " + expectedCards[1] + " " + expectedCards[2] + " " + expectedCards[3];
            expectedMessage = "deck" + testDeck.getDeckId() + " contents: " + expectedCardValues;

            testDeck.writeOutput();

            String fileName = "deck" + testDeck.getDeckId() + "_output.txt";
            File testOutput = new File(fileName);

            try (Scanner reader = new Scanner(testOutput)) {
                while (reader.hasNextLine()) {
                    message = reader.nextLine();
                }
            }
            catch (FileNotFoundException x) {
                throw new IllegalStateException("file could not be found or created");
            }
            test2Flag = expectedMessage.equals(message);
        }
        catch (Exception e) {
            test2Flag = false;
        }
        Assertions.assertTrue(test1Flag);
        Assertions.assertTrue(test2Flag);
    }
}
