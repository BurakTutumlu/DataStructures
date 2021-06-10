// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public interface ILinkedList<T> {
	
	public T remove(int givenPosition);
	
	public T replace(int givenPosition, T newEntry);
	
	public void add(T newEntry);
	
	public void add(int givenPosition, T newEntry);
	
	public boolean isEmpty();
	
	public T[] toArray();
	
	public T getEntry(int givenPosition);
	
	public boolean contains(T anEntry);
	
}
