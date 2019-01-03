/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Train Hub
// FILE:             LinkedList.java
//
// TEAM:    Individual
// Authors: Nawal Dua
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: none
// 
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Defines the iterator methods for the linked list data type
 *
 * <p>Bugs: 
 * 
 * @author Nawal Dua
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The iterator implementation for LinkedList.  The constructor for this
 * class requires that a reference to a Listnode with the first data
 * item is passed in.
 * 
 * If the Listnode reference used to create the LinkedListIterator is null,
 * that implies there is no data in the LinkedList and this iterator
 * should handle that case correctly.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	private Listnode<T> cur;
	

	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) {
		cur = head.getNext();
	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	@Override
	public T next() {
		if (cur == null){ // if current reference is null
			throw new NoSuchElementException();  // throw exception
		}
		T data = cur.getData();  // get the next data
		cur = cur.getNext();	// get the next element
		return data; 			// return the data
		
	}
	
	/**
	 * Returns true if there are more data items to iterate through 
	 * for this list.
	 * 
	 * @return true if there are any remaining data items to iterator through
	 */
	@Override
	public boolean hasNext() {
		if (cur == null) {  
			return false;
		}
		else return true;
		
	}
       
    /**
     * The remove operation is not supported by this iterator
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
    @Override
	public void remove() {
	  throw new UnsupportedOperationException();
	}

}
