package cardgame;

public class CardGame {
  public int askNumberOfPlayers() {
    return 2; // Placeholder return value
  }

  public String askForPackFile() {
    return "path/to/packfile"; // Placeholder return value
  }

  public void validatePackFile(String packFile, int numberOfPlayers) {
  }

  public Card[] readPackFile(String packFile) {
    return new Card[0]; // Placeholder return value
  }

  public void createDecksAndPlayers(int numberOfPlayers, Card[] cards) {
  }

  public void dealHands(Player[] players, CardDeck[] decks) {
  }

  public void fillDecks(CardDeck[] decks, Card[] cards) {
  }

  public void checkForWinner(Player[] players) {
    // Runs hasWinningHand() for each player and prints the winner if there is one
    for (Player player : players) {
      if (player.hasWinningHand()) {
        System.out.println("Player " + player + " has won the game!");
        return;
      }
    }
    System.out.println("No player has won the game.");
  }

  public void startThreads(Player[] players) {
  }

  public void main() {

  }
}
