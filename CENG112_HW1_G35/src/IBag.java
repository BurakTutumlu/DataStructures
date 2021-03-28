public interface IBag<T> {
	public boolean add(T newEntry);
	public boolean isEmpty();
	public boolean isFull();
	public T getItem(int index);
	public T removeByIndex(int index);
	public T remove();
	public T remove(T item);
	public int getItemCount();
	public int getIndexOf(T item);
	public boolean contains(T item);
	public void displayItems();
	public void dump();
	public void transferTo(IBag<T> targetBag, T item);
	public void decreaseFridge_capacity(int amount);
	public void increaseFridge_capacity(int amount);
	public int getFridge_capacity();
	
}
