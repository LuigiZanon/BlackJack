import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();

        this.cards = fullDeck();

        shuffle();
    }

    private ArrayList<Card> fullDeck(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Suit cardSuit : Suit.values()) {
            for (Rank cardValue : Rank.values()) {
                cards.add(new Card(cardSuit, cardValue));
            }
        }
        return cards;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card dealCard(){
        return cards.remove(0);
    }

    public String toString(){
        String cardListString = "";
        int i = 1;
        for (Card card : cards) {
            cardListString += "[" + i + "] " + card.toString() + "\n";
            i++;
        }
        return cardListString;
    }

    public void rearrangeDeck(){
        this.cards = fullDeck();
        shuffle();
    }
}
