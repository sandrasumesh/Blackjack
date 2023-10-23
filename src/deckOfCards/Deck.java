package deckOfCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				Card newCard = new Card(r, s);
				cards.add(newCard);
			}

		}

	}


//shuffle method
	public void shuffle(Random randomNumbergenerator) {
		Collections.shuffle(cards, randomNumbergenerator);

	}

//deal method
	public Card dealOneCard() {
		/*
		 * if (cards.size() == 0) { cards = new ArrayList<Card>(); for (Suit s :
		 * Suit.values()) { for (Rank r : Rank.values()) { Card newCard = new Card(r,
		 * s); cards.add(newCard); }
		 */

		// }

		Card dealtCard = cards.get(0);
		cards.remove(0);
		return dealtCard;

	}

}

