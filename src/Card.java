public class Card {
    private int point;
    private String suit;
    private String rank;

    // normal constructor
    public Card(int point, String suit, String rank){
        this.point = point;
        this.suit = suit;
        this.rank = rank;
    }

    // constructor to make copies of cards
    public Card(Card card) {
        point = card.getPoint();
        suit = card.getSuit();
        rank = card.getRank();
    }

    // accessor and mutator methods for each card
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // toString method to print cards
    public String toString() {
        return rank + " of " + suit;
    }
}
