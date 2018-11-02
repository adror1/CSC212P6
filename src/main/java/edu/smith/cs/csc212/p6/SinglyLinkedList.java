package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

public class SinglyLinkedList<T> implements P6List<T>, Iterable<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	/**Removes first node
	 * Big O Notation: O(1)
	 */
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.next;
		return before;
	}

	@Override
	/**Removes last node
	 * Big O Notation: O(n)
	 */
	public T removeBack() {

		checkNotEmpty();
		
		
		if(start.next == null) {
			T before = start.value;
			start = start.next;

			return before;
		}
		
		Node<T> previous = null;
		Node<T> previousPrevious = null;
		for(Node<T> current = this.start; current != null; current = current.next) {
			previousPrevious = previous;
			previous = current;			

		}
		
		
		previousPrevious.next = null;
		return previous.value;

		
	}

	@Override
	/**Removes node @ specified index
	 * Big O Notation: O(n) -- might have to go all the way through the list to get the right index
	 */
	public T removeIndex(int index) {
		checkNotEmpty();
		
		T before = getIndex(index);
		start = start.next;
		return before;
		
	}

	@Override
	/**Adds node to front of list
	 * Big O Notation: O(1)
	 */
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	/**Adds node to back of list
	 * Big O Notation: O(n)
	 */
	public void addBack(T item) {
		Node<T> previous = null;
		for (Node<T> current = start; current != null; current = current.next) {
			previous = current;
		}
		if (previous != null) {
			previous.next = new Node<T>(item, previous.next);
		}

	}

	@Override
	/** Supposed to add something at a specified index
	 * Big O Notation: O(n) -- might have to go through the entire list
	 */
	public void addIndex(T item, int index) {
		throw new P6NotImplemented();
	}

	@Override
	/** Gets first thing in node
	 * Big O Notation O(1) 
	 */
	public T getFront() {
		return start.value;
	}

	@Override
	/**
	 * Gets thing in last node
	 */
	public T getBack() {
		Node<T> previous = null;
		for(Node<T> current = this.start; current != null; current = current.next) {
			previous = current;
			
		}
		return previous.value;
	} 

	@Override
	/**Gets the thing at a specified index
	 * Big O Notation O(n) --might have to go to the end of the list
	 */
	public T getIndex(int index) {
		int at = 0;
		for (Node<T> current = this.start; current != null; current = current.next) {
			if (at == index) {
				return current.value;
			}
			at++;
		}
		// We couldn't find it, throw an exception!
		throw new IndexOutOfBoundsException();	}

	@Override
	/** Returns size of array
	 * Big O Notation: O(n)-you need to iterate through the list
	 */
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	/** Looks to see if first node is null...if it is, it's empty
	 * Big O Notation: O(1)
	 */
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * Helper method to throw the right error for an empty state.
	 */
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	public String join() {
		// A stringbuilder is an efficient way to create a new string in Java, since += makes copies.
		StringBuilder sb = new StringBuilder();
		for (Node<T> current = start; current != null; current = current.next) {
			if (current != start) {
				sb.append(' ');
			}
			sb.append(current.value);
		}
		return sb.toString();
	}
	

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * I'm providing this class so that SinglyLinkedList can be used in a for loop
	 * for {@linkplain ChunkyLinkedList}. This Iterator type is what java uses for
	 * {@code for (T x : list) { }} lops.
	 * 
	 * @author jfoley
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(SinglyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.next;
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
}
