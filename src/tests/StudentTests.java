package tests;

import java.util.Random;

import blackjack.BlackjackModel;
import deckOfCards.Deck;

public class StudentTests {
	public static void main(String args[]) {
		Deck deck = new Deck();
		//System.out.print(deck);
		Random random = new Random(3723);
		BlackjackModel game = new BlackjackModel();
		game.createAndShuffleDeck(random);
		//System.out.print(game.initialPlayerCards());
		//game.initialDealerCards();
		//System.out.print(deck.dealOneCard());
	}
}
