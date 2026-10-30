package cardgame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Runnable {
  // starts at -1 for no winner, otherwise the id of the winning player
  private static final AtomicInteger winnerId = new AtomicInteger(-1);

  private final int id;
  // Hand is 4 cards
  private final List<Card> hand = new ArrayList<>();
  // Players decks
  private final CardDeck leftDeck;
  private final CardDeck rightDeck;
  private PrintWriter out;

  public Player(int id, CardDeck leftDeck, CardDeck rightDeck) {
    this.id = id;
    this.leftDeck = leftDeck;
    this.rightDeck = rightDeck;
  }

  public int getId() {
    return id;
  }

  public static void resetWinnerId() {
    winnerId.set(-1);
  }

  public static int getWinnerId() {
    return winnerId.get();
  }

  public synchronized void addToHand(Card card) {
    if (hand.size() < 4) {
      hand.add(card);
    } else {
      throw new IllegalStateException("Hand is full");
    }
  }

  public synchronized List<Card> getHand() {
    return new ArrayList<>(hand);
  }

  public synchronized boolean hasWinningHand() {
    if (hand.size() < 4) {
      return false;
    }
    int value = hand.get(0).getValue();
    for (Card card : hand) {
      if (card.getValue() != value) {
        return false;
      }
    }
    return true;
  }

  // run method Checks if there is a winner immediately after dealing and runs the
  // player’s strategy until they have won.
  @Override
  public void run() {
    try (PrintWriter writer = new PrintWriter((new FileWriter("player" + id + "_output.txt")))) {
      out = writer;
      out.println("Player " + id + " initial hand: " + handString());

      // Check for immediate win
      boolean hasWon = hasWinningHand() && winnerId.compareAndSet(-1, id);
      while (!hasWon && winnerId.get() == -1) {
        takeTurn();
        if (hasWinningHand() && winnerId.compareAndSet(-1, id)) {
          hasWon = true;
        }
      }

      if (hasWon) {
        out.println("Player " + id + " has won the game!");
        out.println("Player " + id + " exiting the game.");
        out.println("Player " + id + " final hand: " + handString());
      } else {
        int winningPlayerId = winnerId.get();
        out.println("Player " + winningPlayerId + " has informed Player " + id + " that" + winningPlayerId
            + " has won the game.");
        out.println("Player " + id + " exiting the game.");
        out.println("Player " + id + " hand: " + handString());
      }
    } catch (IOException e) {
      System.err.println("Error writing to output file for Player " + id + ": " + e.getMessage());
    }
  }

  // Returns true if all 4 cards have the save value

  public void takeTurn() {
    CardDeck firstDeck = leftDeck.getDeckId() <= rightDeck.getDeckId() ? leftDeck : rightDeck;
    CardDeck secondDeck = leftDeck.getDeckId() > rightDeck.getDeckId() ? leftDeck : rightDeck;

    synchronized (firstDeck) {
      synchronized (secondDeck) {
        // Draw a card from the left deck
        Card drawnCard = leftDeck.drawCard();
        if (drawnCard == null) {
          return;
        }
        addToHand(drawnCard);
        out.println("Player " + id + " draws a " + drawnCard.getValue() + " from Deck " + leftDeck.getDeckId());

        Card discardedCard = chooseCardToDiscard();
        removeFromHand(discardedCard);
        rightDeck.addCard(discardedCard);
        out.println("Player " + id + " discards a " + discardedCard.getValue() + " to Deck " + rightDeck.getDeckId());
        out.println("Player " + id + " hand: " + handString());
      }
    }
  }

  private synchronized Card chooseCardToDiscard() {
    for (Card card : hand) {
      if (card.getValue() != id) {
        return card;
      }
    }
    return hand.get(0); // If all cards are the same, discard the first one
  }

  private synchronized void removeFromHand(Card card) {
    hand.remove(card);
  }

  private synchronized String handString() {
    StringBuilder sb = new StringBuilder();
    for (Card card : hand) {
      sb.append(card.getValue()).append(" ");
    }
    return sb.toString().trim();
  }
}
