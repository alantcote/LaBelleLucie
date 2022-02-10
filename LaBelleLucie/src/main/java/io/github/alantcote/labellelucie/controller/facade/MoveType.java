package io.github.alantcote.labellelucie.controller.facade;

/**
 * The "kind" of a move.
 */
public enum MoveType {
	/**
	 * Move a non-top tableau card to the top of its fan.
	 */
	DRAW,

	/**
	 * Reshuffle the tableau cards and deal the result to the tableau.
	 */
	RESHUFFLE,

	/**
	 * Move a tableau card from the top of its fan to the top of a foundation fan.
	 */
	TABLEAU_TO_FOUNDATION,

	/**
	 * Move a tableau card from the top of its fan to the top of a different tableau
	 * fan.
	 */
	TABLEAU_TO_TABLEAU
}