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
	public T removeFront() {
		return removeIndex(0);	
		}

	@Override
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
	public void addFront(T item) {
		if(fill >= this.array.length) {
			makeArrayBigger();
		}
		
		addIndex(item, 0);
//		int newArraySize = fill * 2;
//		Object[] newArray = new Object[newArraySize];
//		
//		for(int i = 0; i < array.length; i++) {
//			newArray[i] = array[i+1];
//		}
//		this.array = newArray;
//		this.array[0] = item;
	}

	private void makeArrayBigger() {
		int newArraySize = fill * 2;
		Object [] newArray = new Object[newArraySize];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		this.array = newArray;
	}
	@Override
	public void addBack(T item) {
		addIndex(item, fill);
	}

	@Override
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
	public T getFront() {
		return this.getIndex(0);
	}

	@Override
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
	public T getIndex(int index) {
		return (T) this.array[index];
	}

	@Override
	public int size() {
		return fill;
	}

	@Override
	public boolean isEmpty() {
		return fill == 0;
	}


}
