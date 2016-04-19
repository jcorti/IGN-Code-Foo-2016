package com.ign.corti;

public class Player {
	//instance variables 
    final static int MAX_CARDS = 52;
    public Card[] cards = new Card[MAX_CARDS];
    private int n = 0;
    private String name;

    /**
     * Constructor
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }
    /**
     * checks whats in the hand
     * @return
     */
    public int inHand() {
        return n;
    }
    /**
     * this will return a card 
     * @param c card that was delt
     * @return
     */
    public Card dealTo(Card c) {
        cards[n++] = c;
        return c;
    }
    /**
     * this will reset n to 0 which means you have no cards
     */
    public void reset() {
        n = 0;
    }
    /**
     * 	This will give you the points you currently have
     * @return
     */
    public int value() {
        int val = 0;
        boolean hasAce = false;
        for (int i = 0; i < n; i++) {
            val = val + cards[i].rank();
            if (cards[i].isAce()) {
                hasAce = true;
            }
        }
        if (hasAce && (val <= 11)) {
            val = val + 10;
        }
        return val;
    }
}
