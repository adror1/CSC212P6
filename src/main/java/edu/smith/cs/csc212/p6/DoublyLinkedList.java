package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

/**
 * 
 * @author arielledror
 *
 * @param <T>
 * 
 * Some of this was taken from this site: https://en.wikipedia.org/wiki/Doubly_linked_list
 */

public class DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	/**
	 * Removes from the front of the list
	 */
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.after;
		return before;	}

	@Override
	/**
	 * Removes from the back of the list 
	 */
	public T removeBack() {
		checkNotEmpty();

		if(start.after == null) {
			T before = start.value;
			start = start.after;

			return before;
		}
	
		Node<T> previous = null;
		Node<T> previousPrevious = null;
		for(Node<T> current = this.start; current != this.end; current = current.after) {
			previousPrevious = previous;
			previous = current;			

		}
		
		
		previousPrevious.after = null;
		return previous.value;

			}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public void addFront(T item) {
			checkNotEmpty();
			}

	@Override
	public void addBack(T item) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public void addIndex(T item, int index) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	public T getFront() {
		return start.value;
	}

	@Override
	public T getBack() {
		throw new P6NotImplemented();
		}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		throw new P6NotImplemented();
	}

	@Override
	/**
	 * Returns size
	 */
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = this.end) {
			count++;
		}
		
		System.out.println(count);
		return count;	}

	@Override
	/**
	 * Returns whether or not the list is empty
	 */
	public boolean isEmpty() {
		return this.start == null;
	}
	
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
