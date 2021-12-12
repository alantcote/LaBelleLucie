/* $Id: BidiMultiMap.java,v 1.2 2007/10/08 14:55:08 acote Exp $
 *
 * $Log: BidiMultiMap.java,v $
 * Revision 1.2  2007/10/08 14:55:08  acote
 * Updates to enhance (hopefully) the quality of the API documentation produced for this project.
 *
 * Revision 1.1  2007/10/06 19:09:27  acote
 * Added support for bidirectional multi-maps.
 *
 */

package net.sf.cotelab.util.collections;

import java.util.Collection;

/**
 * An extension of {@link MultiMap} that is bidirectional.
 * <p>
 * The behavior of a {@link BidiMultiMap} is undefined if its keys or values are
 * mutable.
 * <p>
 * All general-purpose {@link BidiMultiMap} implementation classes should
 * provide four "standard" constructors:
 * <dl>
 *   <dt>A void (no arguments) constructor</dt>
 *     <dd>Create an empty {@link BidiMultiMap}</dd>
 *   <dt>A constructor with a single argument of type {@link java.util.Map}</dt>
 *     <dd>Create a new {@link BidiMultiMap} with the same key-value mappings
 *         as its argument</dd>
 *   <dt>A constructor with a single argument of type {@link MultiMap}</dt>
 *     <dd>Create a new {@link BidiMultiMap} with the same key-value mappings
 *         as its argument</dd>
 *   <dt>A constructor with a single argument of type {@link BidiMultiMap}</dt>
 *     <dd>Create a new {@link BidiMultiMap} with the same key-value mappings
 *         as its argument</dd>
 * </dl>
 * <p>
 * As with the standard Java collections classes, instances of {@link
 * BidiMultiMap} are unsynchronized.
 * @author alancote@rochester.rr.com
 */
public interface BidiMultiMap<K, V> extends MultiMap<K, V> {
	/**
	 * Returns the collection of keys to which this object maps a given value,
	 * or <code>null</code> if this object contains no mapping for the value.
	 * @param value the given value.
	 * @return the collection of keys to which this object maps <code>value</code>,
	 *         or <code>null</code> if this object contains no mapping for
	 *         <code>value</code>.
	 */
    public Collection<K> getKeys(V value);

    /**
     * Get an inverse {@link BidiMultiMap}.
     * An inverse {@link BidiMultiMap} is a {@link BidiMultiMap} with keys and
     * values reversed from this {@link BidiMultiMap}.
     * @return an inverse {@link BidiMultiMap}.
     */
    public BidiMultiMap<V, K> inverseBidiMultiMap();

	/**
	 * Copy all of the mappings from the specified {@link BidiMultiMap} to this
	 * object.
	 * @param bidimultimap the specified {@link BidiMultiMap}.
	 */
    public void putAll(BidiMultiMap<? extends K, ? extends V> bidimultimap);

	/**
	 * Remove all key/value mappings for a given value.
	 * @param value the given value.
	 * @return The set of keys which were associated with <code>value</code>.
	 */
    public Collection<K> removeValue(V value);
}
