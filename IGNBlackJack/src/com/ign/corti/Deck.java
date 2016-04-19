package com.ign.corti;

public class Deck {
	//instance variables
    final static int DECK_SIZE = 52;
    private Card[] cards;
    private int n = 0;
    /**
     * Constructor 
     * this also calls card and creates 52 cards 
     */
    public Deck() {
        cards = new Card[DECK_SIZE];
        for (int index = 0; index < 4; index++) {
            for (int sIndex = 0; sIndex < 13; sIndex++) {
                cards[n] = new Card(n, sIndex, index + "" + sIndex + ".png");
                n++;
            }
        }
    }
    // subtract from card
    public Card dealFrom() {
        return cards[--n];
    }
    //checks if empty
    public boolean isEmpty() {
        return (n == 0);
    }
    //gets size
    public int size() {
        return n;
    }
    //shuffles cards
    public void shuffle() {
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random() * i);
            Card swap = cards[i];
            cards[i] = cards[r];
            cards[r] = swap;
        }
    }
}
