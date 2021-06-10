//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class BinaryNode<T> {
	
	private T data; // generic t attribute
	private BinaryNode<T> leftChild; // reference of left child
	private BinaryNode<T> rightChild; // reference of right child
	
	public BinaryNode() // contructor with default values
	{
		this(null, null, null);
	}
	
	public BinaryNode(T data) // contructor with value
	{
		this(data, null, null);
	}

	public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild) // contructor with all values 
	{
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	/**
	 * @return the data
	 */
	public T getData() { // getter for T data
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {// setter for T data
		this.data = data;
	}

	/**
	 * @return the leftChild
	 */
	public BinaryNode<T> getLeftChild() { // getter for leftchild
		return leftChild;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(BinaryNode<T> leftChild) {// setter for leftchild
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BinaryNode<T> getRightChild() { // getter for rightchild
		return rightChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BinaryNode<T> rightChild) {// setter for rightchild
		this.rightChild = rightChild;
	}
	
	public boolean hasLeftChild() // checks if the leftchild exists
	{
		return leftChild != null;
	}

	public boolean hasRightChild() // checks if the rightchild exists
	{
		return rightChild != null;
	}

	public boolean isLeaf() // checks if the node is a leaf
	{
		return (leftChild == null) && (rightChild == null);
	}
	
	public int getNumberOfNodes() // returns number of nodes after this node
	{
		int leftNumber = 0;
		int rightNumber = 0;
		if(leftChild != null) {
			leftNumber = leftChild.getNumberOfNodes();
		}
		if(rightChild != null) {
			rightNumber = rightChild.getNumberOfNodes();
		}
		
		return 1 + leftNumber + rightNumber;
	}
	
	public int getHeight() // returns height of this node
	{
		return getHeight(this);
	}
	
	private int getHeight(BinaryNode<T> node) // returns height of a node
	{
		int height = 0;
		
		if(node != null) {
			height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
		}
			
		return height;
	}
	
	public BinaryNode<T> copy() // copies this node to a new node
	{
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		if(leftChild != null) {
			newRoot.setLeftChild(leftChild.copy());
		}
		if(rightChild != null) {
			newRoot.setRightChild(rightChild.copy());
		}
		
		return newRoot;
	}

}