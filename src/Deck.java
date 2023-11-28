import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(int[] points, String[] suits, String[] ranks) {
        cards = new ArrayList<>();
        Card temp;
        for (int i = 0; i < points.length; i++)
            cards.add(new Card(points[i], suits[i], ranks[i]));
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty())
            return null;
        return cards.get(--cardsLeft);
    }

    public void shuffle() {
        int random;
        for (int i = cardsLeft - 1; i >= 0; i--) {
            random = (int) (Math.random() + i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, temp);
        }
    }
}
