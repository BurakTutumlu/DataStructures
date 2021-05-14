//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public interface IStack<T> {
	
    public void push(T newEntry);	//adds element into stack and returns
    
    public T pop();	//removes element from stack and returns
    
    public T peek();	//looks top element of stack 
    
    public boolean isEmpty();   //check the queue if it is empty or not
    
    public void clear();    //removing all items from the queue
    
}
