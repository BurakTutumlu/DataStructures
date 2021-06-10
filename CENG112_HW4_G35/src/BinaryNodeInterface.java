//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public interface BinaryNodeInterface<T> {


    public boolean hasLeftChild();


	public boolean hasRightChild();


	public boolean isLeaf();

	
	public int getNumberOfNodes();

	
	public int getHeight();

	
	public BinaryNode<T> copy();
}
