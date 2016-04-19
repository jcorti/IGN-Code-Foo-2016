package com.ign.corti;

/**
 * this is a class of one card 
 * @author corti
 *
 */
public class Card {
	//instance variables 
	private int cardNumber;
	private int rank;
	private String front;
	/**
	 * This is the constructor 
	 * @param cardNumber the amount of cards used 
	 * @param rank card value
	 * @param front card location
	 */
	public Card(int cardNumber, int rank, String front) {
		this.cardNumber = cardNumber;
		this.rank = rank;
		this.front = front;
	}
	//checks if its an ace
	public boolean isAce() {
		return rank == 0;
	}
/**
 * gets the value of card
 * @return value
 */
	public int rank() {
		if (rank == 0) {
			return 1;
		}
		if (rank >= 9) {
			return 10;
		}
			return rank + 1;
	}
 /**
  * toString method overrides the built in toString
  */
	public String toString() {
		return this.front;
	}
}

