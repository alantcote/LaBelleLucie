package net.cote.projecteuler;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A permutation generator.
 * <p>
 * This permutation generator is modeled after <tt>java.util.Iterator</tt>,
 * even though the underlying sequence of permutations is not really a
 * <tt>java.util.Collection</tt>, but rather is calculated on-the-fly.
 * <p>
 * The <tt>order</tt> of a permutation iterator is the number of elements
 * included in each of the iterator's generated permutations. A single generated
 * permutation is an array of integers on the range <tt>0..(order-1)</tt>, in
 * an order determined by the iterator's algorithm. Once created, a permutation
 * generator returns a different permutation from each call to its
 * <tt>next()</tt> method, without repetition, until all possible such
 * permutations have been produced, when <tt>hasNext()</tt> returns
 * <tt>false</tt>.
 * <p>
 * This implementation is an adaptation of a method described in section 1.2.5
 * of Knuth's Fundamental Algorithms, Volume 1, second edition.
 * 
 * @author acote
 */
public class RawPermutationIterator implements Iterator<int[]> {
	/**
	 * A position in permutationOfOrderMinusOne at which to insert
	 * orderMinusOne.
	 */
	private int insertionPoint = 0;

	/**
	 * The number of elements in each generated permutation vector.
	 */
	private int order;

	/**
	 * The highest-valued element in each generated permutation vector.
	 */
	private int orderMinusOne;

	/**
	 * A permutation obtained from rawPermutationIteratorOfOrderMinusOne.
	 */
	private int permutationOfOrderMinusOne[];

	/**
	 * A RawPermutationGenerator of order (order-1).
	 */
	private RawPermutationIterator rawPermutationIteratorOfOrderMinusOne;
	
	/**
	 * The number of permutations that remain to be returned by <tt>next()</tt>.
	 */
	private int remainingCount;

	/**
	 * Construct a new instance.
	 * @param order the order of the permutation (<tt>order > 0</tt>).
	 * @throws IllegalArgumentException
	 *             if <tt>order <= 0 || order >
	 *             Factorial.MAX_INT_FACTORIAL_BASE</tt>.
	 */
	public RawPermutationIterator(int order) throws IllegalArgumentException {
		super();
		
		if ((order < 1) || (order > Factorial.MAX_INT_FACTORIAL_BASE)) {
			throw new IllegalArgumentException();
		}
		
		this.order = order;
		orderMinusOne = order - 1;
		
		if (order == 1) {
			rawPermutationIteratorOfOrderMinusOne = null;
			permutationOfOrderMinusOne = new int[0];
		} else {
			rawPermutationIteratorOfOrderMinusOne =
				new RawPermutationIterator(orderMinusOne);
			permutationOfOrderMinusOne =
				rawPermutationIteratorOfOrderMinusOne.next();
		}
		
		remainingCount = Factorial.intFactorial(this.order);
	}

	public boolean hasNext() {
		return (remainingCount > 0) && (permutationOfOrderMinusOne != null);
	}

	public int[] next() throws NoSuchElementException {
		int result[] = new int[order];
		
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		for (int i = 0; i < insertionPoint; ++i) {
			result[i] = permutationOfOrderMinusOne[i];
		}
		
		result[insertionPoint] = orderMinusOne;
		
		for (int i = insertionPoint + 1; i < order; ++i) {
			result[i] = permutationOfOrderMinusOne[i - 1];
		}
		
		if (++insertionPoint > orderMinusOne) {
			insertionPoint = 0;
			permutationOfOrderMinusOne = null;
			if (rawPermutationIteratorOfOrderMinusOne != null) {
				if (rawPermutationIteratorOfOrderMinusOne.hasNext()) {
					permutationOfOrderMinusOne =
						rawPermutationIteratorOfOrderMinusOne.next();
				}
			}
		}
		
		--remainingCount;
		
		return result;
	}

	public void remove()
			throws IllegalStateException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
