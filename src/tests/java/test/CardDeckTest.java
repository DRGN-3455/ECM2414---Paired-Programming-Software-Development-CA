package test;

import cardgame.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        catch (ArrayIndexOutOfBoundsException e) {
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
        boolean test3Flag; // attempt to draw from an empty deck
        
    }

    @org.junit.jupiter.api.Test
    void testGetCardIds() {
    }

    @org.junit.jupiter.api.Test
    void testGetSize() {
    }

    @org.junit.jupiter.api.Test
    void testGetDeckId() {
    }

    @org.junit.jupiter.api.Test
    void testResetDeck() {
    }

    @org.junit.jupiter.api.Test
    void testWriteOutput() {
    }
}