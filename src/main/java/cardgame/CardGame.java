package cardgame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
  private final Scanner scanner = new Scanner(System.in);
  private final List<Player> players = new ArrayList<>();
  private final List<CardDeck> decks = new ArrayList<>();
  private final List<Thread> threads = new ArrayList<>();

  public static void main(String[] args) {
    CardGame game = new CardGame();
    game.play();
  }

  public void play() {
    int n = askNumberOfPlayers();

    List<Card> pack;
    while (true) {
      String packFile = askForPackFile();

      File file = new File(packFile);
      if (validatePackFile(file, n)) {
        pack = readPackFile(file);
        break;
      } else {
        System.out.println("Invalid pack file. Please try again.");
      }
    }

    createDecksAndPlayers(n);
    dealHands(pack, players);
    fillDecks(decks, pack);

    Player winner = checkForWinner(players);
    startThreads(players, winner);
    endGame();
  }

  public int askNumberOfPlayers() {
    while (true) {
      System.out.println("Enter number of players: ");
      String line = scanner.nextLine().trim();
      try {
        int n = Integer.parseInt(line);
        if (n > 0) {
          return n;
        } else {
          System.out.println("Number of players must be greater than 0.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
  }

  public String askForPackFile() {
    System.out.println("Enter the pack file name: ");
    return scanner.nextLine().trim();
  }

  public boolean validatePackFile(File packFile, int numberOfPlayers) {
    List<String> lines;
    try {
      lines = Files.readAllLines(packFile.toPath());
    } catch (IOException e) {
      System.err.println("Error reading pack file: " + e.getMessage());
      return false;
    }

    if (lines.size() != numberOfPlayers * 8) {
      System.out.println("Pack file must contain exactly " + (numberOfPlayers * 8) + " lines.");
      return false;
    }

    for (int i = 0; i < lines.size(); i++) {
      try {
        if (Integer.parseInt(lines.get(i).trim()) < 0) {
          System.out.println("Pack file contains negative values at line " + (i + 1));
          return false;
        }
      } catch (NumberFormatException e) {
        System.out.println("Pack file contains invalid data at line " + (i + 1));
        return false;
      }
    }
    return true;
  }

  public List<Card> readPackFile(File packFile) {
    List<Card> cards = new ArrayList<>();
    try {
      List<String> lines = Files.readAllLines(packFile.toPath());
      for (String line : lines) {
        int value = Integer.parseInt(line.trim());
        cards.add(new Card(value));
      }
    } catch (IOException e) {
      System.err.println("Error reading pack file: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.err.println("Pack file contains invalid data: " + e.getMessage());
    }
    return cards;
  }

  public void createDecksAndPlayers(int numberOfPlayers) {
    for (int i = 1; i <= numberOfPlayers; i++) {
      decks.add(new CardDeck());
    }
    for (int i = 1; i <= numberOfPlayers; i++) {
      CardDeck leftDeck = decks.get(i - 1);
      CardDeck rightDeck = decks.get(i % numberOfPlayers);
      players.add(new Player(i, leftDeck, rightDeck));
    }
  }

  public void dealHands(List<Card> pack, List<Player> players) {
    int n = players.size();
    for (int round = 0; round < 4; round++) {
      for (int p = 0; p < n; p++) {
        players.get(p).addToHand(pack.get(round * n + p));
      }
    }
  }

  public void fillDecks(List<CardDeck> decks, List<Card> pack) {
    int n = decks.size();
    for (int i = 4 * n; i < pack.size(); i++) {
      decks.get((i - 4 * n) % n).addCard(pack.get(i));
    }
  }

  public Player checkForWinner(List<Player> players) {
    // Runs hasWinningHand() for each player and prints the winner if there is one
    for (Player player : players) {
      if (player.hasWinningHand()) {
        return player;
      }
    }
    return null;
  }

  public void startThreads(List<Player> players, Player winner) {
    if (winner != null) {
      Thread winnerThread = new Thread(winner);
      threads.add(winnerThread);
      winnerThread.start();
      try {
        winnerThread.join();
        System.out.println("Player " + winner.getId() + " won!");
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    for (Player player : players) {
      if (player != winner) {
        Thread playerThread = new Thread(player);
        threads.add(playerThread);
        playerThread.start();
      }
    }
  }

  public void endGame() {
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    writeDeckOutputs(decks);
  }

  public void writeDeckOutputs(List<CardDeck> decks) {
    for (CardDeck deck : decks) {
      deck.writeOutput();
    }
  }
}
