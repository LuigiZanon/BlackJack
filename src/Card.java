public class Card {
    private final Suit suit;
    private  Rank rank;

    public Card(Suit suit, Rank Rank) {
        this.suit = suit;
        this.rank = Rank;
    }

    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    public int getValue() {
        return rank.cardRank;
    }

    public void change_Rank(int value){
        this.rank.cardRank = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public String toString() {
        return (rank + " of " + suit + " [" + this.getValue() + "]");
    }
}
