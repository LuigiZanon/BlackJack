import java.util.ArrayList;

public class Hand{
    private ArrayList<Card> cards;

    Hand(Card card1, Card card2){
        this.cards = new ArrayList<>();
        this.cards.add(card1);
        this.cards.add(card2);
    }

    Hand(){
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card){
        this.cards.add(card);
        hasAce();
    }

    public void clearCards(){
        this.cards.clear();
    }

    public boolean HasBlackJack(){
        return getHandValue() == 21;
   }

   public boolean above(){
        return getHandValue() > 21;
   }

   private void hasAce(){
        if(above()){
            for (Card card : cards) {
                if ("Ace".equals(card.getRank().cardName)) {
                    card.change_Rank(1);
                }
            }
        }
   }

    public int getHandValue(){
        int handValue = 0;
        for (Card card : cards) {
            handValue += card.getValue();
        }
        return handValue;
    }

    public String toString(){
        String cardListString = "";
        System.out.println("Hand value: " + getHandValue());
        for (Card card : cards) {
            cardListString += card.toString() + "\n";
        }
        return cardListString;
    }
}
