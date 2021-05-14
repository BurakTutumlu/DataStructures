// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public interface PriorityQueueInterface<T>{
	
	public void add(T newEntry);
	
	public T remove();
	
	public T peek();
	
	public boolean isEmpty();
	
	public int getSize();
	
	public void clear();

}
