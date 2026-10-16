package cardgame;

@SuppressWarnings("InnerClassMayBeStatic")

public class Card { //cardgame.Card class, stores denomination.
    private final int Denomination; // number on card
    public Card(int value){ // card constructor
        if (value >= 1){
        this.Denomination = value;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public int getValue(){ //returns card value, no publication risk as card number cannot change
        return this.Denomination;
    }

    @Override
    public String toString() { //toString method, states the denomination and explains no publication risk
        return "cardgame.Card cannot become out of date, card denomination is final. \ncardgame.Card has value: " + this.Denomination;
    }
}

