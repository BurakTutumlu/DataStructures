//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public interface QueueInterface<T> {
	
	public void enqueue(T newEntry);	//adds element into queue and returns
	
    public T dequeue();	//removes element from queue and returns
    
    public T getFront();	//takes first element of queue
    
    public boolean isEmpty();	//check the queue if it is empty or not
    
    public void clear();  //removing all items from the queue
	
}	