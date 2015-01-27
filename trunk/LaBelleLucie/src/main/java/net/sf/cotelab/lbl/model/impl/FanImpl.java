/**
 * 
 */
package net.sf.cotelab.lbl.model.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import net.sf.cotelab.lbl.model.facade.Fan;
import net.sf.cotelab.playingcards.Card;

/**
 * @author US80653H
 */
public class FanImpl implements Fan {
	protected ObservableList<Card> cards = newObservableList_Card();

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Card e) {
		return cards.add(e);
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, Card element) {
		cards.add(index, element);
	}

	/**
	 * @param arg0
	 * @return
	 * @see javafx.collections.ObservableList#addAll(java.lang.Object[])
	 */
	public boolean addAll(Card... arg0) {
		return cards.addAll(arg0);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Card> c) {
		return cards.addAll(c);
	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends Card> c) {
		return cards.addAll(index, c);
	}

	/**
	 * @param arg0
	 * @see javafx.beans.Observable#addListener(javafx.beans.InvalidationListener)
	 */
	public void addListener(InvalidationListener arg0) {
		cards.addListener(arg0);
	}

	/**
	 * @param arg0
	 * @see javafx.collections.ObservableList#addListener(javafx.collections.ListChangeListener)
	 */
	public void addListener(ListChangeListener<? super Card> arg0) {
		cards.addListener(arg0);
	}

	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear() {
		cards.clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return cards.contains(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return cards.containsAll(c);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return cards.equals(o);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#get(int)
	 */
	public Card get(int index) {
		return cards.get(index);
	}
	
	public Card getTopCard() {
		Card result = null;
		int fanSize = size();
		
		if (fanSize > 0) {
			result = get(fanSize - 1);
		}
		
		return result;
	}

	/**
	 * @return
	 * @see java.util.List#hashCode()
	 */
	public int hashCode() {
		return cards.hashCode();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return cards.indexOf(o);
	}

	/**
	 * @return
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.List#iterator()
	 */
	public Iterator<Card> iterator() {
		return cards.iterator();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		return cards.lastIndexOf(o);
	}

	/**
	 * @return
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<Card> listIterator() {
		return cards.listIterator();
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<Card> listIterator(int index) {
		return cards.listIterator(index);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public Card remove(int index) {
		return cards.remove(index);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see javafx.collections.ObservableList#remove(int, int)
	 */
	public void remove(int arg0, int arg1) {
		cards.remove(arg0, arg1);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return cards.remove(o);
	}

	/**
	 * @param arg0
	 * @return
	 * @see javafx.collections.ObservableList#removeAll(java.lang.Object[])
	 */
	public boolean removeAll(Card... arg0) {
		return cards.removeAll(arg0);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return cards.removeAll(c);
	}

	/**
	 * @param arg0
	 * @see javafx.beans.Observable#removeListener(javafx.beans.InvalidationListener)
	 */
	public void removeListener(InvalidationListener arg0) {
		cards.removeListener(arg0);
	}

	/**
	 * @param arg0
	 * @see javafx.collections.ObservableList#removeListener(javafx.collections.ListChangeListener)
	 */
	public void removeListener(ListChangeListener<? super Card> arg0) {
		cards.removeListener(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see javafx.collections.ObservableList#retainAll(java.lang.Object[])
	 */
	public boolean retainAll(Card... arg0) {
		return cards.retainAll(arg0);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return cards.retainAll(c);
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public Card set(int index, Card element) {
		return cards.set(index, element);
	}

	/**
	 * @param arg0
	 * @return
	 * @see javafx.collections.ObservableList#setAll(java.lang.Object[])
	 */
	public boolean setAll(Card... arg0) {
		return cards.setAll(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see javafx.collections.ObservableList#setAll(java.util.Collection)
	 */
	public boolean setAll(Collection<? extends Card> arg0) {
		return cards.setAll(arg0);
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see java.util.List#subList(int, int)
	 */
	public List<Card> subList(int fromIndex, int toIndex) {
		return cards.subList(fromIndex, toIndex);
	}

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {
		return cards.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public <T> T[] toArray(T[] a) {
		return cards.toArray(a);
	}

	protected ObservableList<Card> newObservableList_Card() {
		return FXCollections.observableArrayList();
	}
}
