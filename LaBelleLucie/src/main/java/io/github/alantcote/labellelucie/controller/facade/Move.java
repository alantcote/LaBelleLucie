package io.github.alantcote.labellelucie.controller.facade;

/**
 * A move. Objects of this class are immutable.
 */
public class Move {
	/**
	 * The destination fan index (zero if not applicable).
	 */
	protected final int destFanIndex;

	/**
	 * The source fan index (zero if not applicable).
	 */
	protected final int srcFanIndex;

	/**
	 * The kind of move.
	 */
	protected final MoveType type;

	/**
	 * Construct a new object. The index parameters are not applicable to moves of
	 * type <code>DRAW</code> or <code>RESHUFFLE</code> in the current
	 * implementation.
	 * 
	 * @param destFanIndex the destination fan index (zero if not applicable).
	 * @param srcFanIndex  the source fan index (zero if not applicable).
	 * @param type         the kind of move.
	 * @throws IllegalArgumentException if the arguments are invalid.
	 */
	public Move(int destFanIndex, int srcFanIndex, MoveType type) {
		super();

		switch (type) {
		case DRAW:
			if (!((destFanIndex == 0) && (srcFanIndex == 0))) {
				throw new IllegalArgumentException("indexes not applicable");
			}
			break;
		case RESHUFFLE:
			if (!((destFanIndex == 0) && (srcFanIndex == 0))) {
				throw new IllegalArgumentException("indexes not applicable");
			}
			break;
		case TABLEAU_TO_FOUNDATION:
			break;
		case TABLEAU_TO_TABLEAU:
			if (destFanIndex == srcFanIndex) {
				throw new IllegalArgumentException("indexes may not match");
			}
			break;
		default:
			throw new IllegalArgumentException("unknown type");
		}

		this.destFanIndex = destFanIndex;
		this.srcFanIndex = srcFanIndex;
		this.type = type;
	}

	/**
	 * Get the source fan index.
	 * @return the destFanIndex
	 */
	public int getDestFanIndex() {
		return destFanIndex;
	}

	/**
	 * Get the source fan index.
	 * @return the srcFanIndex
	 */
	public int getSrcFanIndex() {
		return srcFanIndex;
	}

	/**
	 * Get the move type.
	 * @return the type
	 */
	public MoveType getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String result = "Move(type=" + type + "; srcFanIndex=" + srcFanIndex + "; destFanIndex=" + destFanIndex + ")";

		return result;
	}
}
