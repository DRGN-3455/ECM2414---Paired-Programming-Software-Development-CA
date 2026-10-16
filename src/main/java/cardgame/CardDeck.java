package cardgame;
import java.io.*;

public class CardDeck {
    private final Card[] deckFIFO = new Card[4];
    private final int deckId;
    private static int  numberOfDecks = 0;
    public CardDeck() {
        this.deckId = numberOfDecks++;
    }

    public synchronized void addCard(Card addedCard){ //Thread-safe
        for (int x=0; x <= 4 ; x++){ //goes through each slot in a deck
            if (deckFIFO[x] == null){ //if it is the topmost empty slot it is inserted
                this.deckFIFO[x] = addedCard; // card added to bottom of deck
                break; //break as no more iterations are necessary
                }
        }
    }

    public synchronized Card drawCard(){ //Thread-safe
        if (deckFIFO[0] == null){
            throw new IllegalStateException("No cards in this deck");
        }

        Card drawnCard = this.deckFIFO[0]; //Pick up card

        this.deckFIFO[0] = this.deckFIFO[1]; //Shift cards to top
        this.deckFIFO[1] = this.deckFIFO[2];
        this.deckFIFO[2] = this.deckFIFO[3];

        return drawnCard; // returns the drawn card
    }

    public synchronized Card[]  getDeckFIFO() { //data publication warning
        return this.deckFIFO;
    }

    public synchronized int[] getCardIds() {

        int[] values = new int[4];
        for (int x = 0; x <= 4; x++) {
            values[x] = deckFIFO[x].getValue();
        }
        return values;
    }


    public synchronized int getSize(){ //data publication warning

        int cardCount = 0; //number of cards present within deck

        for (int x=0; x <= 4 ; x++){ //counting each card
            if (deckFIFO[x] != null){
                cardCount++; //card found, count incremented
            }
        }

        return cardCount; //returns number of cards currently placed in the deck
    }

//    public synchronized int getSize(){ // data publication warning
//        return this.deckFIFO.length; // returns size of array of deck, always 4.
//    }

    public synchronized int getDeckId(){
        return this.deckId;
    }

    public synchronized void resetDeck() { //resets deck count
        numberOfDecks = 0;
    }

    public synchronized void writeOutput(){
        int deckId = this.getDeckId();
        int[] cardIds = this.getCardIds();
        String message;

        message = cardIds[0] + " " + cardIds[1] + " " + cardIds[2] + " " + cardIds[3];
        message = "deck" + deckId + " contents: " + message;

        String fileName = "deck" + deckId + "_output.txt";

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(message);
            out.newLine();
            out.close();
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
