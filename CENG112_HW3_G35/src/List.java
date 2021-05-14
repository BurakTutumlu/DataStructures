// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

import java.util.Arrays;

public class List<T> implements IList<T>{
	
	private T[] list;	// list with T[] field
	private int numberOfEntries;	// number of elements field for list
	private boolean initialized = false;	// check initializing control field
	private static final int DEFAULT_CAPACITY = 25;	// default capacity for list  
	private static final int MAX_CAPACITY = 10000;	// max capacity for list
	private int initialCapacity;	// initial capacity
		
	public List() {	// constructor for list
		this(DEFAULT_CAPACITY);
	}
	
	public List(int capacity) { // capacity parameter with list
		if(initialCapacity < DEFAULT_CAPACITY)	
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempList = (T[])new Object[initialCapacity + 1]; 	
		list = tempList;
		numberOfEntries = 0;	// initializing to 0
		initialCapacity = capacity;
		initialized = true;	// initialized
	}
	
	@Override
	public void add(T newEntry) {
		checkInitialization();	// control for list if it initialized or not	
		list[numberOfEntries + 1] = newEntry; // adding entry to the next index
		numberOfEntries++;	// increase number of elements
		ensureCapacity();	
	}

	@Override
	public void add(int newPosition, T newEntry) {	// adding new entry to the given index
		checkInitialization();
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			if (newPosition <= numberOfEntries)
				makeRoom(newPosition);
			list[newPosition] = newEntry; // this line adds entry to new index
			numberOfEntries++;	// increase number of elements
			ensureCapacity();
		}
		else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	}

	@Override
	public T remove(int givenPosition) {	// remove element from given index1
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {	// 
			assert !isEmpty();
			T result = list[givenPosition];	// result is the element of the list
			
			if (givenPosition < numberOfEntries)	
				removeGap(givenPosition);
			
			numberOfEntries--;	// decrease number of elements
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	}
	
	@Override
	public void clear() {	// removes all elements
		while(isEmpty()){
			remove(numberOfEntries);
		}		
	}

	@Override
	public T replace(int givenPosition, T newEntry) {	// replace element with given index with new entry
		checkInitialization();
		
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;	// new entry will be in that index
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}

	@Override
	public T getEntry(int givenPosition) {	// getting the element on the index
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			return list[givenPosition];
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}

	@Override
	public T[] toArray() {
		checkInitialization();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index + 1];	// convert list to array,
		}										// it's index sequence will be changed
		return result;
	}

	@Override
	public boolean contains(T anEntry) {	// checks if the searched element is in list or not
		checkInitialization();
		boolean found = false;	// check statement	 false
		int index = 1; 
		while(!found && (index <= numberOfEntries))
		{
			if (anEntry.equals(list[index])) 	// if equal to that index element
				found = true;	// check statement is true
			index++;
		}
		return found;
	}

	@Override
	public int getLength() { // return number of items
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() { // checks if the list empty
		return numberOfEntries == 0;
	}
	
	private void ensureCapacity() {	//	doubling the capacity of list
		int capacity = list.length - 1; // getting size of the list
		if (numberOfEntries >= capacity) { // checking if the items exceed the capacity then double the capacity
			int newCapacity = 2 * capacity; // double the capacity
			checkCapacity(newCapacity); // checking the new capacity
			list = Arrays.copyOf(list, newCapacity + 1); // assign all item to new list
		}
	}
	
	private void makeRoom(int newPosition) {//	when inserting an item from list, there should be a gap between items
											// to insert item
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		
		int newIndex = newPosition;// the index of gap 
		int lastIndex = numberOfEntries;// last index of list
		
		for (int index = lastIndex; index >= newIndex; index--) // pushing items to the enf of list
			list[index + 1] = list[index];
	}
	
	private void removeGap(int givenPosition) {	//	when deleting an item from list, there is a gap between items
												// 	this method deleting the gap
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
		
		int removedIndex = givenPosition;	// the index of gap 
		int lastIndex = numberOfEntries; 	// last index of list
		
		for (int index = removedIndex; index < lastIndex; index++)	//	removing the gap
			list[index] = list[index + 1];	//	Pulling items back into gap
	}
	
	public boolean checkInitialization(){	// checking if the initialization is complete
		if (!initialized){ // if initialization is not done than throw error
			throw new SecurityException("The class is not created properly.");
		}
		return true;
	}
	
	private void checkCapacity(int initialCapacity) { // checking the capacity given from user
        if(initialCapacity > MAX_CAPACITY){	// if the capacity is bigger than Max capacity throw error
            throw new SecurityException("Your queue is higher than max capacity (10000)");
        }
    }
}
