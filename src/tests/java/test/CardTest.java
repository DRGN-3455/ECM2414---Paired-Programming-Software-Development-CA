package test;
import cardgame.Card;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @org.junit.jupiter.api.Test
    public void testGtetValue() {
        Card getCard = new Card(2);
        assertEquals(2, getCard.getValue());
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        Card getCard = new Card(5);
        assertEquals("cardgame.Card cannot become out of date, card denomination is final. \ncardgame.Card has value: 5", getCard.toString());
    }
}