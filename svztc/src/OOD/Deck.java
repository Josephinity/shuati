package OOD;

import java.util.*;
/**

 */



enum Suit {
    DIAMOND, CLUB, HEART, SPADE;
}

class Card {

    final int value;
    final Suit suit;

    public Card(int value, Suit c) {
        this.value = value;
        suit = c;
    }
}


public abstract class Deck {

   List<Card> deck;

   public Deck() {
       deck = new ArrayList<>();
   }

    abstract void shuffle();

    //public abstract List<Card> deal();

    public Card draw() {
        try {
            return deck.remove(deck.size() - 1);
        } catch (NullPointerException e) {
            System.out.println("Deck does not exist");
            return null;
        } catch (EmptyStackException e) {
            System.out.println("Deck is out of cards");
            return null;
        }
    }

    boolean isEmpty() {
        return deck.isEmpty();
    }

    int size() {
        return deck.size();
    }
}

class BlackJackDeck extends Deck {

    public final boolean hasJokers = false;

    public BlackJackDeck() {
        super();
        for(Suit s: Suit.values()) {
            for(int i = 1; i <= 13; i++) {
                deck.add(new Card(i, s));
            }
        }
        shuffle();
    }

    void shuffle() {
        Random r = new Random();
        int s = deck.size();
        for(int i = 0; i < s; i++) {
            int j = r.nextInt(s);
            Card c = deck.get(j);
            deck.set(j, deck.get(i));
            deck.set(i, c);
        }
    }
}


class ArenaDeck extends Deck {

    public final boolean hasJokers = false;


    public ArenaDeck() {
        super();
        for(Suit s: Suit.values()) {
            for(int i = 1; i <= 13; i++) {
                deck.add(new Card(i, s));
            }
        }
    }

    public void shuffle() {

    }

    public void reveal() {
        Card card = draw();
        System.out.println(card.suit + " " + card.value);
        randomPutback(card);
    }

    private void randomPutback(Card card) {
        Random rand = new Random();
        int pos = rand.nextInt(size() + 1);
        deck.add(pos, card);    //also can make deck private, and create a public class to add card in Deck.
    }

}


