import java.util.Arrays;

public class Bag<T> implements IBag<T> {
	
	private T[] bag;			//bag declared
	private int numberOfEntries;	//item count 
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	private boolean initialized = false;		//checking initialization control
	private int fridge_capacity ;	//capacity of fridge
	
	
	
	public Bag() {
		this(DEFAULT_CAPACITY);
		initialized = true;
	} 
	
	
	public Bag(int fridge_capacity) {

        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[DEFAULT_CAPACITY]; // creating T array from default capacity
        bag = tempBag; 									// assigning bag with our T array
        numberOfEntries = 0; 							// initializing numberOfEntries with 0
        initialized = true; 							// initialized = true
        this.fridge_capacity = fridge_capacity;			// initializing fridge capacity with parameter.
	} 
	
	public Bag(int capacity, int fridge_capacity) {
		if(capacity <= MAX_CAPACITY) {  				//checking capacity with limited capacity
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[capacity]; // creating T array from parameter.
			bag = tempBag; 								// assigning bag with our T array.
			numberOfEntries = 0; 						// initializing numberOfEntries with 0
			initialized = true;							// initialized = true
            this.fridge_capacity = fridge_capacity; 	// initializing fridge capacity with parameter.
		}else { // if parameter capacity greater than MAX_CAPACITY then throw error for this.
			throw new IllegalStateException("Attempt to create a bag "+capacity+
											" whose capacity exceeds "+
											MAX_CAPACITY+" allowed maximum.");
		}
	}

	@Override
	public boolean add(T newEntry) {
		checkInitialization();	//making initialization 
		if(isFull()) {
			doubleCapacity();	//if is full, you can make capacity double of it, 
								//but we will not going to use in this shopping app
		}
		bag[numberOfEntries] = (T) newEntry; //adding new entry to index + 1 which is numberOfEntries
		
		numberOfEntries++;		//after adding, item count must be increased
		return true;
	}
	
	private void doubleCapacity() {		//This method from IBag implementation, we did not use this method in this app
		int newLenght = 2 * bag.length;
		bag = Arrays.copyOf(bag, newLenght);
	}
	

	@Override
	public boolean isEmpty() {
		if(numberOfEntries==0) {	//if item count is 0, it means bag is empty
			return true;
			}
		return false;		//bag is not empty
	}	

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return numberOfEntries >= bag.length;	//if item count == bag length, it means bag is full
	}

	@Override
	public T removeByIndex(int index) {
		// TODO Auto-generated method stub
		checkInitialization(); // first checking initializaling, if did not initialized return false
		
		T result = null; // in case of any situation declaring result, removed one, as null.
		if(!isEmpty() && (index >= 0)) { // if bag is empty than do nothing and return false.
			result = bag[index]; // getting object that will be deleted.
			bag[index] = bag[numberOfEntries - 1]; // transferring last object to given index
			bag[numberOfEntries - 1] = null; // last object is deleted
			numberOfEntries--; // decrease number of entries.
		}
		return result;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		checkInitialization();		//initialization control
		
		T result = null;		//first initialized return element to null
		if(!isEmpty() && numberOfEntries > 0) { //if bag is not empty and item count bigger that 0 
			result = bag[numberOfEntries - 1];	//in remove method, we need to get last element of bag first
			bag[numberOfEntries - 1] = null;	//making last element of bag null
			numberOfEntries--;	//decrease number of items in bag
			
		}
		return result;
	}

	@Override
	public T remove(T item) {							
		// TODO Auto-generated method stub
		checkInitialization();	//initialization control
		
		T result = null;	//first initialized return element to null
		if (!isEmpty() && numberOfEntries > 0) {	//if bag is not empty and item count bigger that 0
			for (int i = 0; i < numberOfEntries; i++) {
				if (bag[i].equals(item)) {	//if we find specific item on bag
					result = bag[i];		//take to that item
					bag[i] = bag[numberOfEntries - 1];	//change to item place at the end of the bag
					bag[numberOfEntries - 1] = null;	//delete last element of bag
					numberOfEntries--;	//decrease number of item counts
					return result;	
					}
			}
		}
		return null;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return numberOfEntries; // returning number of entries
	}

	@Override
	public int getIndexOf(T item) {
		// TODO Auto-generated method stub
		checkInitialization(); // controlling initialization
		
		if (!isEmpty()) { // if basg is not empty than we can search the object
			for(int i = 0; i < numberOfEntries; i++) // for all object we start searching 
				if(bag[i].equals(item)) // if the item is equal to an index of bag then return the index
					return i;
		}
		
		return -1; //if object did not found then return -1
	}

	@Override
	public boolean contains(T item) {
		// TODO Auto-generated method stub
		checkInitialization(); // controlling initialization
		return getIndexOf(item) > -1; 	// finding index of object it is in it returns greater than -1,
										// so we can compare it like this.
	}

	@Override
	public void displayItems() {							
		// TODO Auto-generated method stub
		for (int i = 0; i < numberOfEntries; i++) {	//we will print items until reaching to item counts
			System.out.println( bag[i]);
		}
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub
		while(!isEmpty())  	// till bag is empty 
			remove(); 		// remove last index of bag
	}

	@Override
	public void transferTo(IBag<T> targetBag, T item) { // transferring this bag to another bag
		// TODO Auto-generated method stub
		int i = getIndexOf(item); // getting index of this item from this bag
		T sender = bag[i]; // recreating this item
		targetBag.add(sender); // adding item to target bag
		
		removeByIndex(i); // removing item that has been sent from this bag.
	}

	private void checkInitialization() {
		if(!initialized)
			throw new SecurityException("Array bag object is not initialized properly.");	//if we could not create
																			//bag properly, it will give an Exception
	}
	

	public T getItem(int index){	//we need to get specific item with index
		
		if(index < numberOfEntries){	//if given index smaller that item count, we can return to that index element
			return bag[index];
		}else{
			return null;
		}
		
	}

	public int getFridge_capacity() {
		return fridge_capacity; // return the fridge capacity
	}

	
	public void decreaseFridge_capacity(int amount){
		this.fridge_capacity -= amount; // decreasing fridge capacity with parameter amount
	}


	@Override
	public void increaseFridge_capacity(int amount) {
		// TODO Auto-generated method stub
		this.fridge_capacity += amount; // increasing fridge capacity with parameter amount
	}
}
