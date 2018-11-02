package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.EmptyListError;


public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 2;
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}

	@Override
	/**Removes the first thing in the list
	 * Big O Notation: O(n)
	 */
	public T removeFront() {
		return removeIndex(0);	
		}

	@Override
	/** Removes the last thing
	 * Big O Notation: O(1) - we start from the back in GrowabaleList, so last is first
	 */
	public T removeBack() {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		
		T value = this.getIndex(fill-1);
		this.array[fill-1] = null;
		System.out.print(fill);
		fill--;
		
		return value;	
		}

	@Override
	/** Removes the thing at a specified index 
	 * Big 0 Notation: O(n)
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
	/** Adds something to the front of list, makes it bigger if too small
	 * Big O Notation: O(n)
	 */
	public void addFront(T item) {
		if(fill >= this.array.length) {
			makeArrayBigger();
		}
		
		addIndex(item, 0);
	}

	/**
	 * 	Doubles the size of the list
	 */
	private void makeArrayBigger() {
		int newArraySize = fill * 2;
		Object [] newArray = new Object[newArraySize];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		this.array = newArray;
	}
	@Override
	/**Adds something at the back
	 * Big O Notation: O(1)
	 */
	public void addBack(T item) {
		addIndex(item, fill);
	}

	@Override
	/** Adds something at the given index
	 * Big O Notation: O(n)
	 */
	public void addIndex(T item, int index) {
		if (fill >= array.length) {
			makeArrayBigger();
		}
		// loop backwards, shifting items to the right.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}
	
	@Override
	/** Gets the thing at first index
	 * Big O Notation: O(n)
	 */
	public T getFront() {
		return this.getIndex(0);
	}

	@Override
	/** Getst the thing at the last index
	 * Big O Notation: O(1)
	 */
	public T getBack() {
		return this.getIndex(this.fill-1);
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	/** Gets the thing at a specified index
	 * Big O Notation: O(1)
	 */
	public T getIndex(int index) {
		return (T) this.array[index];
	}

	@Override
	/** Returns size of the list
	 * Big O Notation: O(1)
	 */
	public int size() {
		return fill;
	}

	@Override
	/**
	 * Returns whether or not the list in empty
	 * Big O Notation: O(1)
	 */
	public boolean isEmpty() {
		return fill == 0;
	}


}
