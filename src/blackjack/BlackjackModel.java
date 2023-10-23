package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {
	private ArrayList<Card> dealerCards = new ArrayList<Card>();;
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private Deck deck;

	public ArrayList<Card> getDealerCards() {
		ArrayList<Card> newCards = new ArrayList<Card>(dealerCards);
		return newCards;

	}

	public ArrayList<Card> getPlayerCards() {
		ArrayList<Card> newCards = new ArrayList<Card>(playerCards);
		return newCards;
	}

	public void setDealerCards(ArrayList<Card> cards) {
		this.dealerCards = new ArrayList<Card>(cards);
	}

	public void setPlayerCards(ArrayList<Card> cards) {
		this.playerCards = new ArrayList<Card>(cards);
	}

	public void createAndShuffleDeck(Random random) {
		this.deck = new Deck();
		this.deck.shuffle(random);
	}

	public void initialDealerCards() {
		dealerCards = new ArrayList<Card>();
		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());
	}

	public void initialPlayerCards() {
		playerCards = new ArrayList<Card>();
		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());
	}

	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());
	}

	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());
	}

	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		int total = 0;
		int total2 = 0;
		for (Card values : hand) {
			if (values.getRank().getValue() == 1) {
				total += 1;
				total2 += 11;
			} else {
				total += values.getRank().getValue();
				total2 += values.getRank().getValue();
			}

		}

		if (total > 21 && total2 > 21) {
			if (total < total2) {
				returnArr.add(total);
			} else {
				returnArr.add(total2);
			}
		}
		if (total <= 21) {
			returnArr.add(total);
		}
		if (total2 <= 21 && total != total2) {
			returnArr.add(total2);
		}
		return returnArr;
	}

	public static HandAssessment assessHand(ArrayList<Card> hand) {
		int total = 0;
		int total2 = 0;
		if (possibleHandValues(hand).size() == 2) {
			total = possibleHandValues(hand).get(0);
			total2 = possibleHandValues(hand).get(1);

		} else {
			total = possibleHandValues(hand).get(0);
		}
		if (hand.size() < 2 || hand == null) {
			return HandAssessment.INSUFFICIENT_CARDS;
		}

		if (total == 21 && (hand.size() == 2) || total2 == 21 && hand.size() == 2) {
			return HandAssessment.NATURAL_BLACKJACK;
		}

		if (total > 21 && total2 == 0 || total2 > 21 && total == 0 || total > 21 && total2 > 21) {
			return HandAssessment.BUST;
		} else {
			return HandAssessment.NORMAL;
		}
	}

	public GameResult gameAssessment() {
		if (assessHand(playerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_LOST;
		}
		if (assessHand(dealerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_WON;
		}
		if (assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK
				&& assessHand(dealerCards) != HandAssessment.NATURAL_BLACKJACK) {
			return GameResult.NATURAL_BLACKJACK;
		}
		if (assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK
				&& assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK) {
			return GameResult.PUSH;
		}
		int total = 0;
		int total2 = 0;
		int playerValue = 0;
		if (possibleHandValues(playerCards).size() == 2) {
			total = possibleHandValues(playerCards).get(0);
			total2 = possibleHandValues(playerCards).get(1);

		} else {
			total = possibleHandValues(playerCards).get(0);
		}
		if (total > total2 && total <= 21) {
			playerValue = total;
		} else if (total2 != 0) {
			playerValue = total2;
		} else {
			playerValue = total;
		}
		int totalDealer = 0;
		int totalDealer2 = 0;
		int dealerValue = 0;
		if (possibleHandValues(dealerCards).size() == 2) {
			totalDealer = possibleHandValues(dealerCards).get(0);
			totalDealer2 = possibleHandValues(dealerCards).get(1);

		} else {
			totalDealer = possibleHandValues(dealerCards).get(0);
		}
		if (totalDealer > totalDealer2 && totalDealer <= 21) {
			dealerValue = totalDealer;
		} else if (totalDealer2 != 0) {
			dealerValue = totalDealer2;
		} else {
			dealerValue = totalDealer;
		}

		if (assessHand(playerCards) == HandAssessment.NORMAL && playerValue > dealerValue) {
			return GameResult.PLAYER_WON;
		}
		if (assessHand(dealerCards) == HandAssessment.NORMAL && playerValue < dealerValue) {
			return GameResult.PLAYER_LOST;

		}
		if (assessHand(dealerCards) == HandAssessment.NORMAL && playerValue == dealerValue) {
			return GameResult.PUSH;

		}
		if (assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK && playerValue == 21) {
			return GameResult.PUSH;
		} else {
			return GameResult.PUSH;
		}
	}

	public boolean dealerShouldTakeCard() {
		int total = 0;
		int total2 = 0;
		if (possibleHandValues(dealerCards).size() == 2) {
			total = possibleHandValues(dealerCards).get(0);
			total2 = possibleHandValues(dealerCards).get(1);

		} else {
			total = possibleHandValues(dealerCards).get(0);
		}
		if (total >= 18 || total2 >= 18) {
			return false;
		}
		if (total <= 16 && total != 0 || total2 <= 16 && total2 != 0) {
			return true;
		}
		if (total == 17 && total2 == 7 || total2 == 17 && total == 7) {
			return true;
		} else {
			return false;
		}
	}
}
