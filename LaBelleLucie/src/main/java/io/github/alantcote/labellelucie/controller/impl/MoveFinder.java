package io.github.alantcote.labellelucie.controller.impl;

import java.util.LinkedList;
import java.util.List;

import io.github.alantcote.labellelucie.controller.facade.Move;
import io.github.alantcote.labellelucie.controller.facade.MoveType;
import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.labellelucie.model.facade.GameState;
import io.github.alantcote.playingcards.Card;
import io.github.alantcote.playingcards.Rank;
import io.github.alantcote.playingcards.Suit;

/**
 * A move generator.
 */
public class MoveFinder {
	protected GameState model;

	/**
	 * Construct a new object.
	 * 
	 * @param model the game model.
	 */
	public MoveFinder(GameState model) {
		super();

		this.model = model;
	}

	/**
	 * Determine whether the rules permit a prospective new top card to be played
	 * atop an existing top card, in a foundation fan.
	 * 
	 * @param newTopCard the prospective new top card.
	 * @param oldTopCard the existing top card.
	 * @return the truth-value of the assertion, "<code>newTopCard</code> may be
	 *         played atop <code>oldTopCard</code> in a foundation fan".
	 */
	public boolean canPlayOnFoundation(Card newTopCard, Card oldTopCard) {
		boolean result = false;
		Rank newRank = newTopCard.getRank();
		Suit newSuit = newTopCard.getSuit();

		if (oldTopCard == null) {
			result = (newRank == Rank.ACE);
		} else {
			Rank oldRank = oldTopCard.getRank();
			Suit oldSuit = oldTopCard.getSuit();

			if (newSuit == oldSuit) {
				result = (oldRank.ordinal() + 1 == newRank.ordinal());
			}
		}

		return result;
	}

	/**
	 * Determine whether the rules permit a prospective new top card to be played
	 * atop an existing top card, in a tableau fan.
	 * 
	 * @param newTopCard the prospective new top card.
	 * @param oldTopCard the existing top card.
	 * @return the truth-value of the assertion, "<code>newTopCard</code> may be
	 *         played atop <code>oldTopCard</code> in a tableau fan".
	 */
	public boolean canPlayOnTableau(Card newTopCard, Card oldTopCard) {
		boolean result = false;
		Rank newRank = newTopCard.getRank();
		Suit newSuit = newTopCard.getSuit();

		if (oldTopCard != null) {
			Rank oldRank = oldTopCard.getRank();
			Suit oldSuit = oldTopCard.getSuit();

			if (newSuit == oldSuit) {
				result = (oldRank.ordinal() == newRank.ordinal() + 1);
			}
		}

		return result;
	}

	/**
	 * Get a list of moves that are legal, in the circumstances that exist. The
	 * moves are listed in preference order:
	 * <ol>
	 * <li>Moves from tableau to foundation.</li>
	 * <li>Moves from tableau to tableau.</li>
	 * <li>Reshuffle.</li>
	 * <li>Draw.</li>
	 * </ol>
	 * 
	 * @return a list of moves (empty, if there are no legal ones).
	 */
	public List<Move> findMoves() {
		List<Move> moves = newListOfMove();

		moves.addAll(findSimpleMoves());

		if (model.getRedealsRemaining().get() > 0) {
			Move move = newMove(0, 0, MoveType.RESHUFFLE);

			moves.add(move);
		}

		if (model.getDrawsRemaining().get() > 0) {
			Move move = newMove(0, 0, MoveType.DRAW);

			moves.add(move);
		}

		return moves;
	}

	/**
	 * Get a list of legal moves from tableau to foundation.
	 * 
	 * @return a list of moves (empty, if there are no legal ones).
	 */
	public List<Move> findMovesToFoundation() {
		List<Move> moves = newListOfMove();
		Fan[] foundation = model.getFoundation();

		for (int destIndex = 0; destIndex < foundation.length; ++destIndex) {
			List<Move> newMoves = findMovesToFoundation(destIndex);

			if (newMoves != null) {
				moves.addAll(newMoves);
			}
		}

		return moves;
	}

	/**
	 * Find the legal moves from tableau to a given foundation fan.
	 * 
	 * @param destIndex the index of the foundation fan.
	 * @return a move (<code>null</code> if no such exists).
	 */
	public List<Move> findMovesToFoundation(int destIndex) {
		List<Move> moves = newListOfMove();
		Card destCard = model.getFoundation()[destIndex].getTopCard();
		Fan[] tableau = model.getTableau();

		for (int srcIndex = 0; srcIndex < tableau.length; ++srcIndex) {
			Card srcCard = tableau[srcIndex].getTopCard();

			if (srcCard != null) {
				if (canPlayOnFoundation(srcCard, destCard)) {
					Move move = newMove(destIndex, srcIndex, MoveType.TABLEAU_TO_FOUNDATION);

					moves.add(move);
				}
			}
		}

		return moves;
	}

	/**
	 * Get a list of legal moves from tableau to tableau.
	 * 
	 * @return a list of moves (empty, if there are no legal ones).
	 */
	public List<Move> findMovesToTableau() {
		List<Move> moves = newListOfMove();
		Fan[] tableau = model.getTableau();

		for (int destIndex = 0; destIndex < tableau.length; ++destIndex) {
			Move move = findMoveToTableau(destIndex);

			if (move != null) {
				moves.add(move);
			}
		}

		return moves;
	}

	/**
	 * Find a legal move from tableau to a given tableau fan.
	 * 
	 * @param destIndex the index of the destination tableau fan.
	 * @return a move (<code>null</code> if no such exists).
	 */
	public Move findMoveToTableau(int destIndex) {
		Move move = null;
		Fan[] tableau = model.getTableau();
		Card destCard = tableau[destIndex].getTopCard();

		if (destCard != null) {
			for (int srcIndex = 0; srcIndex < tableau.length; ++srcIndex) {
				Card srcCard = tableau[srcIndex].getTopCard();

				if (srcCard != null) {
					if (canPlayOnTableau(srcCard, destCard)) {
						move = newMove(destIndex, srcIndex, MoveType.TABLEAU_TO_TABLEAU);

						break; // there is at most 1 candidate
					}
				}
			}
		}

		return move;
	}

	/**
	 * Get a list of legal simple moves, in current circumstances. Simple moves are
	 * grouped in the following order:
	 * <ol>
	 * <li>Moves from tableau to foundation.</li>
	 * <li>Moves from tableau to tableau.</li>
	 * </ol>
	 * 
	 * @return a list of moves (empty, if there are no legal ones).
	 */
	public List<Move> findSimpleMoves() {
		List<Move> moves = newListOfMove();

		moves.addAll(findMovesToFoundation());
		moves.addAll(findMovesToTableau());

		return moves;
	}

	/**
	 * Create a new empty list of moves.
	 * 
	 * @return the list of moves.
	 */
	protected List<Move> newListOfMove() {
		return new LinkedList<Move>();
	}

	/**
	 * Create a new move.
	 * 
	 * @param destFanIndex the index of the destination fan.
	 * @param srcFanIndex  the index of the source fan.
	 * @param type         the kind of move.
	 * @return the move.
	 */
	protected Move newMove(int destFanIndex, int srcFanIndex, MoveType type) {
		return new Move(destFanIndex, srcFanIndex, type);
	}
}
