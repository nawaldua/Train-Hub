/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Train Hub
// FILE:             LinkedList.java
//
// TEAM:    Individual
// Authors: Nawal Dua
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Patrick Heithoff, Friend, pheithoff@wisc.edu
//			He helped me understand where to get started in this file, and how
// 			this file relates to the other files.
// 
// Online sources: none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * defines the linked list data structure that implements the ListADT interface
 *
 * <p>Bugs:
 *
 * @author Nawal Dua
 */

/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will reference the
 * first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIterator constructed by passing the
 * first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without calling the
 * add and remove methods in this class. So, the size() method must be
 * implemented by counting nodes. This counting must occur each time the size
 * method is called. DO NOT USE a numItems field.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedList<E> implements ListADT<E> {

	private Listnode<E> header;

	public LinkedList() {
		header = new Listnode<E>(null);
	}
	//
	// It must be a SINGLY-LINKED chain of ListNode<E> nodes
	// It must have a "header node" ("dummy node" without data)
	// It must NOT have a tail reference
	// It must NOT keep a number of data items
	// NOTE: in this program, the chains of nodes in this program may be
	// changed outside of the LinkedList class, so the actual data count
	// must be determined each time size is called.
	//
	// It must return a LinkedListIterator<E> as its iterator.
	//
	// Note: The "header node"'s data reference is always null and
	// its next references the node with the first data of the list.
	//
	// Be sure to implement this LinkedList<E> using Listnode
	// you must use LinkedListIterator<E> instead of Iterator<E>
	//

	/**
	 * Returns a reference to the header node for this linked list. The header node
	 * is the first node in the chain and it does not contain a data reference. It
	 * does contain a reference to the first node with data (next node in the
	 * chain). That node will exist and contain a data reference if any data has
	 * been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access to the
	 * headerNode in this way to classes that use the linked list. We are providing
	 * direct access to support testing and to allow multiple nodes to be moved as a
	 * chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
	public Listnode<E> getHeaderNode() {

		return header;
	}

	/**
	 * Must return a reference to a LinkedListIterator for this list.
	 */
	public LinkedListIterator<E> iterator() {
		return new LinkedListIterator(header);

	}

	@Override
	public void add(E item) {

		if (item == null) { // if the item being added is null
			throw new IllegalArgumentException(); // throw the exception
		}

		Listnode<E> node = new Listnode<E>(item); // create a new Listnode object with item
		Listnode<E> cur = header; // set the current reference to the header

		while (cur.getNext() != null) { // traverse the current reference to the last node
			cur = cur.getNext();
		}

		cur.setNext(node); // add the item to the end of the linked list

	}

	@Override
	public void add(int pos, E item) {

		if (item == null) { // if the item is not valid
			throw new IllegalArgumentException(); // throw exception
		}
		if (pos < 0 || pos > size()) { // if bad position
			throw new IllegalArgumentException(); // throw exception
		}

		Listnode<E> node = new Listnode<E>(item); // make a new listnode object
		Listnode<E> cur = header; // set the current reference to the header

		for (int i = 0; i < pos; i++) { // set the current reference to the listnode at pos
			cur = cur.getNext();
		}

		if (cur.getNext() == null) { // if the position is last, just add the new node at the end
			cur.setNext(node);
		} else {
			node.setNext(cur.getNext()); // if the position is not last, add the node into the linked
			cur.setNext(node); // list appropriately
		}

	}

	@Override
	public boolean contains(E item) {
		Listnode<E> cur = header; // set the current reference to the header

		if (item == null) { // if item is not valid, throw exception
			throw new IllegalArgumentException();
		}

		while (cur.getNext() != null && !cur.getData().equals(item)) {  // go throw the linked list to see 
			cur = cur.getNext();										// if any of the data is the same as item
		}
		if (cur.getData().equals(item)) {  // if linked list has the item, retun true
			return true;
		} else {							// else return false
			return false;
		}

	}

	@Override
	public E get(int pos) {

		Listnode<E> cur = header.getNext();  // set current reference to the first node that contains data

		if (pos < 0 || pos >= size()) {  // if bad position, throw an exception
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < pos; i++) { // traverse to the position
			cur = cur.getNext();
		}

		return cur.getData();  // return the data at the position

	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {  // if the size of the linked list is 0
			return true;		// it is empty
		} else
			return false;

	}

	@Override
	public E remove(int pos) {

		Listnode<E> cur = header;  // set the current reference to the header
		Listnode<E> removalNode = cur.getNext();  // set the reference to the node that needs
													// to be removed in front of the current
													// reference

		if (pos < 0 || pos > size()) {  			// if bad position, throw an exception
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < pos; i++) {  			// traverse both the references to the appropriate
			cur = cur.getNext();					// location
			removalNode = removalNode.getNext();
		}

		cur.setNext(cur.getNext().getNext());		// remove the removal node from the linked list
		return removalNode.getData();				// return the node that was removed

	}

	@Override
	public int size() {								

		Listnode<E> cur = header;					// set the current reference to the header
		int size = 0;								// start the size off with 0
		while (cur.getNext() != null) {				// while there are still other nodes in the linked list
			cur = cur.getNext();
			size++;									// increment the size
		}
		return size;								// return the size
	}
}
