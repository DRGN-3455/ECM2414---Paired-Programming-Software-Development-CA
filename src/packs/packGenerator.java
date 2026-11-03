package cardgame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class packGenerator {
    public static void main() {
        int numOfPlayers = getInput();
        Scanner scanner = new Scanner(System.in);
        if (numOfPlayers < 1) throw new IllegalArgumentException();
        int[] pack = new int[numOfPlayers*8];
        PrintWriter packWriter = null;

        for (int i = 0; i < numOfPlayers*8; i++) {
            pack[i] = (int)(Math.random() * numOfPlayers);
        }

        try (PrintWriter writer = new PrintWriter((new FileWriter("randomPackFor" + numOfPlayers + ".txt")))) {
            packWriter = writer;
            packWriter.print("");
            for (int i = 0; i < numOfPlayers*8; i++) {
                packWriter.println(pack[i]);
            }
            packWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing to randomPackFor" + numOfPlayers + ".txt: " + e.getMessage());
        } finally {
            if (packWriter != null) {
                packWriter.close();
                scanner.close();
            }
        }
    }

    public static int getInput(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter number of players: ");
            String input = scanner.nextLine().trim();
            int numOfPlayers = Integer.parseInt(input);

            try {
                if (numOfPlayers > 0) {
                    return numOfPlayers;
                } else {
                    System.out.println("Number of players must be greater than 0.");
                }
            }

            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            scanner.close();

        }
    }

}
