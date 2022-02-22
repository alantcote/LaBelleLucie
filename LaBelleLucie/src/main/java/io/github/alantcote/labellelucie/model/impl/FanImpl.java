package io.github.alantcote.labellelucie.model.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.github.alantcote.labellelucie.model.facade.Fan;
import io.github.alantcote.playingcards.Card;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * The implementation of a fan model.
 */
public class FanImpl implements Fan {
	/**
	 * The cards in the fan.
	 */
	protected ObservableList<Card> cards = newObservableList_Card();

	/**
	 * Add a card to the fan.
	 * @param e the card.
	 * @return <code>true</code> if the card was added successfully.
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Card e) {
		return cards.add(e);
	}

	/**
	 * Add a card to the fan at a specified location.
	 * @param index the location.
	 * @param element the card.
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, Card element) {
		cards.add(index, element);
	}

	/**
	 * Add multiple cards to the fan.
	 * @param arg0 the cards to be added.
	 * @return <code>true</code> iff the cards were all added.
	 * @see javafx.collections.ObservableList#addAll(java.lang.Object[])
	 */
	public boolean addAll(Card... arg0) {
		return cards.addAll(arg0);
	}

	/**
	 * Add multiple cards to the fan.
	 * @param c the cards to be added.
	 * @return <code>true</code> iff the cards were all added.
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Card> c) {
		return cards.addAll(c);
	}

	/**
	 * Add multiple cards to the fan at a specific location.
	 * @param index the location.
	 * @param c the cards.
	 * @return <code>true</code> iff the cards were all added.
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends Card> c) {
		return cards.addAll(index, c);
	}

	/**
	 * Add an invalidation listener to the fan.
	 * @param arg0 the listener.
	 * @see javafx.beans.Observable#addListener(javafx.beans.InvalidationListener)
	 */
	public void addListener(InvalidationListener arg0) {
		cards.addListener(arg0);
	}

	/**
	 * Add a listener to changes to the fan.
	 * @param arg0 the listener.
	 * @see javafx.collections.ObservableList#addListener(javafx.collections.ListChangeListener)
	 */
	public void addListener(ListChangeListener<? super Card> arg0) {
		cards.addListener(arg0);
	}

	/**
	 * Remove all cards from the fan.
	 * @see java.util.List#clear()
	 */
	public void clear() {
		cards.clear();
	}

	/**
	 * Ascertain whether the fan contains a given card.
	 * @param o the card.
	 * @return <code>true</code> iff the fan contains the card.
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return cards.contains(o);
	}

	/**
	 * Ascertain whether the fan contains a given collection of cards.
	 * @param c the cards.
	 * @return <code>true</code> iff the fan contains the cards.
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return cards.containsAll(c);
	}

	/**
	 * Determine whether this object equals another object.
	 * @param o the other object.
	 * @return <code>true</code> iff the objects are equal.
	 * @see java.util.List#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof FanImpl) {
			return cards.equals(((FanImpl) o).cards);
		}
		
		return false;
	}

	/**
	 * Get the card from a specific location in the fan.
	 * @param index the location.
	 * @return the card.
	 * @see java.util.List#get(int)
	 */
	public Card get(int index) {
		return cards.get(index);
	}

	/**
	 * Get the top card in the fan.
	 * @return the top card (<code>null</code> if the fan is empty).
	 */
	public Card getTopCard() {
		Card result = null;
		int fanSize = size();

		if (fanSize > 0) {
			result = get(fanSize - 1);
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return cards.hashCode();
	}

	/**
	 * Get the location of a given card in the fan.
	 * @param o the card.
	 * @return the location.
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return cards.indexOf(o);
	}

	/**
	 * Determine whether the fan is empty.
	 * @return <code>true</code> iff the fan contains no cards.
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * Get an iterator over the fan.
	 * @return the iterator.
	 * @see java.util.List#iterator()
	 */
	public Iterator<Card> iterator() {
		return cards.iterator();
	}

	/**
	 * Get the last location of a given card in the fan.
	 * @param o the card.
	 * @return the location.
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		return cards.lastIndexOf(o);
	}

	/**
	 * Get an iterator over the fan.
	 * @return the iterator.
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<Card> listIterator() {
		return cards.listIterator();
	}

	/**
	 * Get an iterator over the fan, beginning with the card at a given location.
	 * @param index the location.
	 * @return the iterator.
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<Card> listIterator(int index) {
		return cards.listIterator(index);
	}

	/**
	 * Remove the card at a given location from the fan.
	 * @param index the location.
	 * @return the card.
	 * @see java.util.List#remove(int)
	 */
	public Card remove(int index) {
		return cards.remove(index);
	}

	/**
	 * Remove a sequence of cards from the fan.
	 * @param arg0 the location of the first card in the fan.
	 * @param arg1 the limit.
	 * @see javafx.collections.ObservableList#remove(int, int)
	 */
	public void remove(int arg0, int arg1) {
		cards.remove(arg0, arg1);
	}

	/**
	 * Remove a card from the fan.
	 * @param o the card.
	 * @return <code>true</code> iff the card was removed.
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return cards.remove(o);
	}

	/**
	 * Remove multiple cards from the fan.
	 * @param arg0 the cards.
	 * @return <code>true</code> iff all of the cards were removed.
	 * @see javafx.collections.ObservableList#removeAll(java.lang.Object[])
	 */
	public boolean removeAll(Card... arg0) {
		return cards.removeAll(arg0);
	}

	/**
	 * Remove multiple cards from the fan.
	 * @param c the cards.
	 * @return <code>true</code> iff all of the cards were removed.
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return cards.removeAll(c);
	}

	/**
	 * Remove an invalidation listener from the fan.
	 * @param arg0 the listener.
	 * @see javafx.beans.Observable#removeListener(javafx.beans.InvalidationListener)
	 */
	public void removeListener(InvalidationListener arg0) {
		cards.removeListener(arg0);
	}

	/**
	 * Remove a list change listener from the fan.
	 * @param arg0 the listener.
	 * @see javafx.collections.ObservableList#removeListener(javafx.collections.ListChangeListener)
	 */
	public void removeListener(ListChangeListener<? super Card> arg0) {
		cards.removeListener(arg0);
	}

	/**
	 * Remove all the cards from the fan that are not in a given group.
	 * @param arg0 the group of cards to retain.
	 * @return <code>true</code> iff the operation succeeded.
	 * @see javafx.collections.ObservableList#retainAll(java.lang.Object[])
	 */
	public boolean retainAll(Card... arg0) {
		return cards.retainAll(arg0);
	}

	/**
	 * Remove all the cards from the fan that are not in a given group.
	 * @param c the group of cards to retain.
	 * @return <code>true</code> iff the operation succeeded.
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return cards.retainAll(c);
	}

	/**
	 * Set the card at a given location in the fan.
	 * @param index the location.
	 * @param element the new card.
	 * @return the card that was previously at the location.
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public Card set(int index, Card element) {
		return cards.set(index, element);
	}

	/**
	 * Replace the cards in the fan with a given group.
	 * @param arg0 the new cards to be in the fan.
	 * @return <code>true</code> iff the operation succeeded.
	 * @see javafx.collections.ObservableList#setAll(java.lang.Object[])
	 */
	public boolean setAll(Card... arg0) {
		return cards.setAll(arg0);
	}

	/**
	 * Replace the cards in the fan with a given group.
	 * @param arg0 the new cards to be in the fan.
	 * @return <code>true</code> iff the operation succeeded.
	 * @see javafx.collections.ObservableList#setAll(java.util.Collection)
	 */
	public boolean setAll(Collection<? extends Card> arg0) {
		return cards.setAll(arg0);
	}

	/**
	 * Find out how many cards are in the fan.
	 * @return the count.
	 * @see java.util.List#size()
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Returns a view of the portion of this list between the specified
	 * fromIndex, inclusive, and toIndex, exclusive.
	 * @param fromIndex the start of the sublist.
	 * @param toIndex one past the end of the sublist.
	 * @return the sublist.
	 * @see java.util.List#subList(int, int)
	 */
	public List<Card> subList(int fromIndex, int toIndex) {
		return cards.subList(fromIndex, toIndex);
	}

	/**
	 * Create an array of the cards in the fan.
	 * @return the array.
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {
		return cards.toArray();
	}

	/**
	 * Populate a given array with the cards from the fan.
	 * @param a the array.
	 * @return the array.
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public <T> T[] toArray(T[] a) {
		return cards.toArray(a);
	}

	/**
	 * Create a list to contain the fan's cards.
	 * @return the list.
	 */
	protected ObservableList<Card> newObservableList_Card() {
		return FXCollections.observableArrayList();
	}
}
