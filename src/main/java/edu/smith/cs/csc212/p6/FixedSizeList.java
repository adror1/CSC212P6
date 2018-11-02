package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

public class FixedSizeList<T> implements P6List<T> {
	private Object[] array;
	private int fill;
	
	public FixedSizeList(int maximumSize) {
		this.array = new Object[maximumSize];
		this.fill = 0;
	}

	@Override
	/** Removes first index
	 * Big O Notation: O(n)
	 */
	public T removeFront() {
		return removeIndex(0);
	}

	@Override
	/**Removes last index. If size is 0, it returns an error
	 * Big O Notation: O(1)
	 */
	public T removeBack() {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		
		System.out.println(fill);
		T value = this.getIndex(fill-1);
		this.array[fill-1] = null;
		System.out.print(fill);
		fill--;
		
		return value;
	}

	@Override
	/**goes through to specified index and removes at that index. moves everything over
	 * Big O Notation: O(n)
	 */
	public T removeIndex(int index) {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}

	@Override
	/**Adds in front of first index
	 * Big O Notation: O(n)
	 */
	public void addFront(T item) {
		addIndex(item, 0);		
	}

	@Override
	/**Adds on back
	 * Big O Notation: O(1)
	 */
	public void addBack(T item) {
		if (fill < array.length) {
			array[fill++] = item;
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	/**Moves through the list and then shifts everything to its new index once one is added
	 * If there isn't space, returns an error
	 * Big O Notation: O(n)
	 */
	public void addIndex(T item, int index) {
		if (fill >= array.length) {
			throw new RanOutOfSpaceError();
		}
		// loop backwards, shifting items to the left.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	/**Returns what is at an index
	 * Big O Notation: O(1)
	 */
	public T getIndex(int index) {
		if (index < 0 || index >= fill) {
			throw new BadIndexError();
		}
		return (T) this.array[index];
	}

	@Override
	/**Size of array
	 * Big O Notation: O(1)
	 */
	public int size() {
		return this.fill;
	}
	/**
	 * Returns if the array is empty
	 * Big O Notation: O(1)
	 */
	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	/**gets what is at the front
	 * Big O Notation: O(1)
	 */
	public T getFront() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(0);
	}

	@Override
	/**gets what is at the back
	 * Big O Notation: O(1)
	 */
	public T getBack() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(this.size()-1);
	}
	/**
	 * This was an attempt at Iterator...it does not work, but I wanted to show you that I tried it
	 * in hopes of partial credit.
	 */
//	private static class Iter<T> implements Iterator<T> {
//		/**
//		 * This is the value that walks through the list.
//		 */
//		int fill = 0;
//
//		/**
//		 * This constructor details where to start, given a list.
//		 * @param list - the FixedSizeList to iterate or loop over.
//		 */
//		public Iter(Object [] list) {
//			this.fill = list[0];
//		}
//
//		@Override
//		public boolean hasNext() {
//			return this.getBack() < this.fill();
//		}
//
//		@Override
//		public T next() {
//			for (int i = 0; i < fill; i++) {
//				this.getIndex(i);
//		}
//	}
//	
//	/**
//	 * 
//	 * @return an object that understands "next()" and "hasNext()".
//	 */
//	public Iterator<T> iterator() {
//		return new Iter<>(this);
//	}
//	
	
}
