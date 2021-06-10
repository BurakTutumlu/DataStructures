//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046

import java.util.Iterator;

public interface BinarySearchTreeI<T extends Comparable> {
	
	public boolean contains(T entry);
	
	public T getEntry(T entry);
	
	public void add(T newEntry);
	
	public T remove(T entry);
	
	public Iterator<T> getInorderIterator();
	
	//public void inorderTraverse();
	
}
