package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import cardgame.Player;

public class PlayerTest {
  @org.junit.jupiter.api.Test
  public void testPlayerCreation() {
    // Create a player with id 1 and null decks for testing
    Player player = new Player(1, null, null);
    // Check if the player id is set correctly
    assertEquals(1, player.getId());
    player.addToHand(new cardgame.Card(5));
    // Check if the hand size is 1 after adding a card
    assertEquals(1, player.getHand().size());
    // Check if the card in the hand is the one we added
    assertEquals(5, player.getHand().get(0).getValue());
  }

  @org.junit.jupiter.api.Test
  public void testHasWinningHand() {
    Player player = new Player(1, null, null);
    // Add 4 cards of the same value to the player's hand
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(3));
    // Check if the player has a winning hand
    assertEquals(true, player.hasWinningHand());
    // Check if the player's hand is still 4 after checking for a winning hand
    assertEquals(4, player.getHand().size());
  }

  @org.junit.jupiter.api.Test
  public void testHasNonWinningHand() {
    Player player = new Player(1, null, null);
    // Add 4 cards of different values to the player's hand
    player.addToHand(new cardgame.Card(1));
    player.addToHand(new cardgame.Card(2));
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(4));
    // Check if the player does not have a winning hand
    assertEquals(false, player.hasWinningHand());
    // Check if the player's hand size is still 4 after checking for a winning hand
    assertEquals(4, player.getHand().size());
    // Check if the cards in the hand are the ones we added
    assertEquals(1, player.getHand().get(0).getValue());
    assertEquals(2, player.getHand().get(1).getValue());
    assertEquals(3, player.getHand().get(2).getValue());
    assertEquals(4, player.getHand().get(3).getValue());
  }

  @org.junit.jupiter.api.Test
  public void testResetWinnerId() {
    // Reset the winnerId to -1
    Player.resetWinnerId();
    // Check if the winnerId is reset to -1
    assertEquals(-1, Player.getWinnerId());
  }

  // Add to hand when hand is full should throw exception
  @org.junit.jupiter.api.Test
  public void testAddToHandWhenFull() {
    Player player = new Player(1, null, null);
    // Add 4 cards to the player's hand
    player.addToHand(new cardgame.Card(1));
    player.addToHand(new cardgame.Card(2));
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(4));
    // Try to add a 5th card and expect an IllegalStateException
    try {
      player.addToHand(new cardgame.Card(5));
    } catch (IllegalStateException e) {
      // Check if the exception message is correct
      assertEquals("Hand is full", e.getMessage());
    }
  }

  // Hand with fewer than 4 cards should not be a winning hand
  @org.junit.jupiter.api.Test
  public void testHasWinningHandWithFewerCards() {
    Player player = new Player(1, null, null);
    // Add 3 cards of the same value to the player's hand
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(3));
    player.addToHand(new cardgame.Card(3));
    // Check if the player does not have a winning hand since there are only 3 cards
    assertEquals(false, player.hasWinningHand());
  }
}