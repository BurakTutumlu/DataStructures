//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
import java.util.Arrays;
import java.util.EmptyStackException;

public class WareHouse<T> implements IStack<T> {	//implements stack's methods

    private T[] stack;	//declares stack
    private int topIndex;	//declares top index of stack
    private boolean initialized = false;	//checks the stack is initialized or not 
    private static final int DEFAULT_CAPACITY = 50;	//capacity
    private static final int MAX_CAPACITY = 10000;	//capacity

    public WareHouse(){	//constructor with no parameter	
        this(DEFAULT_CAPACITY);	//capacity
        initialized = true;	//checks the stack is initialized or not
        this.topIndex = -1;	//
    }

   public WareHouse(int initialCapacity){	//constructor with capacity parameter
        checkCapacity(initialCapacity);	//checks the capacity
        @SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];	//creates temp stack
        stack = tempStack;	
        topIndex = -1;
        initialized = true;
    }
    
	@Override
	public void push(T newEntry) {	//adds new element into stack
        checkInitialization();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;	//adds the element into stack's top index + 1
        topIndex++;	//increase top index
		
	}

    private void checkInitialization(){
        if(!initialized){
            throw new SecurityException("Your class is not initialized");
        }
    }

    private void ensureCapacity(){	//increase capacity
        if(topIndex == stack.length - 1 ){
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack,newLength);	//new stack with 2 size of old stack
        }
    }

    private void checkCapacity(int initialCapacity) {
        if(initialCapacity > MAX_CAPACITY){
            throw new SecurityException("Your queue is higher than max capacity (10000)");
        }
    }
	@Override
	public T pop() { // deleting last item of the stack
        checkInitialization(); // controlling if the stack is initialized
        if(isEmpty()){// checking if the stack is empty
            throw new EmptyStackException();// then throw an error
        }
        else{
            T top = stack[topIndex]; // getting last item
            stack[topIndex] = null; // deleting last item
            topIndex--; // decreasing top index number
            return top;// return the last item of the stack.
        }
	}

	@Override
	public T peek() { // getting the last item of the stack
        checkInitialization(); // controlling if the stack is initialized
        if(isEmpty()){ // checking if the stack is empty
            throw new EmptyStackException(); // then throw an error
        }
        else{
            return stack[topIndex]; // return the last item of the stack.
        }
	}

	@Override
	public boolean isEmpty() { // checking if the stack is empty.
		return topIndex < 0;
	}
	@Override
    
	public void clear() { // removing all items from stack
		while(!isEmpty()){ // till if the stack is empty
            pop();  // remove item
        }
	}	//stack
    
	public int getTopIndex() {  // getting the number of entries of the stack
		return topIndex + 1;
	}
	
}
