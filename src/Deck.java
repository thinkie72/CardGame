import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck() {
        cards = new ArrayList<>();
        // arrays to hold possible card info
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] points = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};

        // fills all cards in deck with the info
        for (String suit : suits) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(points[j], suit, ranks[j]));
            }
        }

        cardsLeft = cards.size();
    }

    // checks if deck is empty
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // gets the number of cards left in the deck
    public int getCardsLeft() {
        return cardsLeft;
    }

    // deals cards to players
    public Card deal() {
        if (isEmpty())
            return null;
        return cards.remove(--cardsLeft);
    }

    // shuffles the deck using algorithm on canvas
    public void shuffle() {
        int random;
        for (int i = cardsLeft - 1; i > 0; i--) {
            random = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, temp);
        }
    }
}
