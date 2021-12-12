/* $Id: MapEntry.java,v 1.6 2007/10/08 15:28:19 acote Exp $
 *
 * $Log: MapEntry.java,v $
 * Revision 1.6  2007/10/08 15:28:19  acote
 * Cleaning up javadoc comments.
 *
 * Revision 1.5  2007/10/08 15:11:01  acote
 * Cleaning up javadoc comments.
 *
 * Revision 1.4  2007/10/08 14:55:08  acote
 * Updates to enhance (hopefully) the quality of the API documentation produced for this project.
 *
 * Revision 1.3  2007/10/08 11:39:54  acote
 * Cleaning up javadoc comments.
 *
 * Revision 1.2  2007/10/06 16:58:12  acote
 * Nothing evolves quite like software.
 *
 * Revision 1.1  2007/10/06 14:59:27  acote
 * Migrated out of MultiMap.java and HashMultiMap.java.
 *
 */
package net.sf.cotelab.util.collections;

import java.io.Serializable;

/**
 * A map entry (key-value pair) as used in {@link net.sf.cotelab.util.collections}.
 * @param <K> the type of key to be supported.
 * @param <V> the type of value to be supported.
 * @author alancote@rochester.rr.com
 */
public class MapEntry<K, V> implements Serializable {
	/**
	 * Serialization support.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The key.
	 */
	protected K key;

	/**
	 * The value.
	 */
	protected V value;

	/**
	 * Construct a new object.
	 * @param key the key to be encapsulated.
	 * @param value the value to be encapsulated.
	 */
	public MapEntry(K key, V value) {
		super();
		
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Compare the specified object with this object for equality.
	 * Returns <code>true</code> if the given object is also a {@link MapEntry}
	 * and the two entries represent the same mapping.  More formally, two
	 * entries <code>e1</code> and <code>e2</code> represent the same mapping if
	 * <pre><code>
	 *     (e1.getKey()==null ?
	 *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
	 *     (e1.getValue()==null ?
	 *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
	 * </code></pre>
	 * @param obj the object to be compared for equality with this object.
	 * @return <code>true</code> if the specified object is equal to this
	 *         object.
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof MapEntry<?, ?>)) {
			MapEntry<K, V> other = (MapEntry<K, V>) obj;
			
			return (((other.getKey() == null) ?
					 (other.getKey() == null) :
				     other.getKey().equals(key)) &&
					((other.getValue() == null) ?
					 (other.getValue() == null) :
					 other.getValue().equals(value)));
		}
		
		return false;
	}
	
	/**
	 * Get the key corresponding to this object.
	 * @return the key corresponding to this object.
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Get the value corresponding to this object.
	 * @return the value corresponding to this object.
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * Get the hash code value for this object.
	 * The hash code for a {@link MapEntry}} <code>e</code> is defined to be
	 * <pre><code>
	 *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
	 *     (e.getValue()==null ? 0 : e.getValue().hashCode())
	 * </code></pre>
	 * @return the hash code for this object.
	 */
	public int hashCode() {
		return (key == null ? 0 : key.hashCode()) ^
	       (value == null ? 0 : value.hashCode());
	}
}
