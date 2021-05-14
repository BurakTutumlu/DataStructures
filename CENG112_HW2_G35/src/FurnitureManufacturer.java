//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
import java.util.NoSuchElementException;


public class FurnitureManufacturer<T> implements IQueue<T> {
	
	
	private T[] queue;	//declares queue for manufacturer
	private int frontIndex;	//front index of queue
	private int backIndex;	//back index of queue
	private boolean initialized = false;	//after manufacturer queue created with constructors, 
											//this statement will be true
	private static final int DEFAULT_CAPACITY = 50; //default capacity of queue
	private static final int MAX_CAPACITY = 10000;	//max capacity of queue
	
	public FurnitureManufacturer()	//constructor with no parameter
	{
		this(DEFAULT_CAPACITY);	//created with default capacity
		initialized = true;		//after created queue, initialized will be true
		this.frontIndex = 0;	//front index starts with 0
		this.backIndex = DEFAULT_CAPACITY;	//back index will be the capacity of queue
		ensureCapacity();	//capacity will be increased
	}
	
	public FurnitureManufacturer(int initialCapacity)	//constructor with initial capacity parameter
	{
		checkCapacity(initialCapacity);	//firstly, we need to check capacity
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1]; //creates queue for temporarily operations
		queue = tempQueue;	//holds temporarily queue
		initialized = true; //after created queue, initialized will be true
		this.frontIndex = 0;	//front index of queue
		this.backIndex = initialCapacity;	//back index will be the capacity of queue
		ensureCapacity();	//capacity will be increased
	}
	
	@Override
	public void enqueue(T newEntry) {	//adds element into queue
		checkInitialization();	//check queue is created or not
		ensureCapacity();	//increase capacity

		backIndex = (backIndex + 1) % queue.length;	//back index will be increased by one
		queue[backIndex] = newEntry;	//adds element into queue's back index
	}

	@Override
	public T dequeue() {	//remove element from queue

		checkInitialization();	//check the queue is created or not
		T front = null;	//initialized fron field into null
		if(isEmpty())	// empty queue control
			throw new SecurityException();	//if it is empty, security exception will be thrown
		else
		{
			front = queue[frontIndex];	//front will be the element in the front index of qeueue
			queue[frontIndex] = null;	//after that, this index will be null, because we are removing the element
			frontIndex = (frontIndex + 1) % queue.length;	//front index will be increased by one
			return front;	//return removed element
		}
	}
	// getting the first item of the queue
	@Override
	public T getFront() {
		T front =  null;		// creating an null item

		checkInitialization();		// controlling if the queue is initialized

		if(isEmpty())		// checking if the queue is empty

			throw new NoSuchElementException(); //EmptyQueueException
		else{
			front = queue[frontIndex];			// getting the first item of the queue and return it.

			return front;}
		
	}
	// checking the queue is empty with its frond and back indexes
	// if it is empty then return true
	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}
	// Deleting all items on the queue
	@Override
	public void clear() {

		while(!isEmpty()){		// till the queue is empty

            dequeue();		// remove item from the queue

        }
	} 
	// Double the capacity
	private void ensureCapacity()
	{
		if (frontIndex == ((backIndex + 2) % queue.length)){
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;			// getting old queue length


			int newSize = 2 * oldSize;
			checkCapacity(newSize);
			
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];			// creating an array with 2 times its old lenght  

			queue = tempQueue;
			// adding items to the new queue
			for(int index = 0; index < oldSize - 1; index++ )
			{
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			// assign the index variables
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}
	// Checking initialization of the object
	// if it is not initialized then it throws an error
	private void checkInitialization(){
        if(!this.initialized){
            throw new SecurityException("Your class is not initialized");
        }
    }
	// Checking the initial capacity of the manufacturer
	// settin the maximum limit of the capacity with 10000
	private void checkCapacity(int initialCapacity) {
        if(initialCapacity > MAX_CAPACITY){
            throw new SecurityException("Your queue is higher than max capacity (10000)");
        }
    }

}
