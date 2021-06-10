//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046

import java.util.Iterator; 
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable> implements BinarySearchTreeI<T> {

	private	BinaryNode<T> rootNode;	// field for root node of the tree
	private int choice;	// we hold this for various options
	
	public BinarySearchTree(int choice){	// constructor with choice parameter
		rootNode = null;
		this.choice = choice;
	}
	
	// getter method for root node
	public	 BinaryNode<T> getRootNode(){
		return rootNode;
	}
	// setter method for root node
	private void setRootNode(BinaryNode<T> entry){
		
		this.rootNode = entry;
	}
	// if root node is empty, function will return true, otherwise will return false
	public boolean isEmpty(){

		return rootNode == null;
	}
	// if it includes that entry or not
	@Override
	public boolean contains(T entry) {

		return getEntry(entry) != null;
	}
	// finds node with minimum data
	public BinaryNode<T> getMinimum(){
		BinaryNode<T> current = rootNode;
		
		while(current.hasLeftChild()){
			current = current.getLeftChild();
		}

		return current;
	}

	// find node with maximum data
	public BinaryNode<T> getMaximum(){
		BinaryNode<T> current = rootNode;
		
		while(current.hasRightChild()){
			current = current.getRightChild();
		}

		return current;
	}
	// getter method for entry
	@Override
	public T getEntry(T entry) {

		return findEntry(getRootNode(), entry);
	}
	// finds spesific entry, with given entry
	private T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry)) 
				result = rootEntry;
			else if (entry.compareTo(rootEntry,choice) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else 
				result = findEntry(rootNode.getRightChild(), entry);
		}
		return result;
	}

		// adds the value 
	public void add(T value) {
		if(isEmpty()){
			setRootNode(new BinaryNode<>(value));
		}else{
	    	addRecursive(rootNode, value);
		}
	}
	
	// recursive method for add
	private BinaryNode<T> addRecursive(BinaryNode<T> current, T value) {
	    /*
		if (current == null) {
			current.setData(value);
	        return current;
	    }
		*/
		@SuppressWarnings("unchecked")
		int res = current.getData().compareTo(value, choice);
	    if (res < 0) {
			if(!current.hasLeftChild()){
				current.setLeftChild(new BinaryNode<T>(value));	// set left child
			}
			else {
				addRecursive(current.getLeftChild(), value); // get left child with recursive

			}
	    } else if (res > 0) {
			if(!current.hasRightChild()){
				current.setRightChild(new BinaryNode<T>(value));
			}else{
	       		addRecursive(current.getRightChild(), value);	// get right child with recursive
			}
	    } else {
			// value already exists
			if(!current.getData().equals(value)){
				if(!current.hasRightChild()){
					current.setRightChild(new BinaryNode<T>(value));	// set right child
				}else{
					current = current.getRightChild();
					while(current.hasLeftChild()){
						current = current.getLeftChild();	// get left child
					}
					if (!current.hasLeftChild()){
						current.setLeftChild(new BinaryNode<T>(value));	// set left child
					}
				}
			}
			
	    }

	    return current;
	}	

	private class ReturnObject {
		private T data;
		public ReturnObject() {
			this.data = null;
		}
		public ReturnObject(T data) {
			this.data = data;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}

	@Override
	public T remove(T anEntry){
		ReturnObject oldEntry = new ReturnObject();
		BinaryNode<T> newRoot = removeEntry(getRootNode(), anEntry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.getData();
	} // end remove

	// this method removes given entry and returns removed entry
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry){
		if (rootNode != null) {
			T rootData = rootNode.getData();
			@SuppressWarnings("unchecked")
			int comparison = rootNode.getData().compareTo(entry,choice);	// compares
			
			if (comparison == 0) {
				oldEntry.setData(rootData);										
				rootNode = removeFromRoot(rootNode); // removes from rrot
			}
			
			else if (comparison < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);	// removes from left
				rootNode.setLeftChild(subtreeRoot);
			}
			else {
				BinaryNode<T> rightChild = rootNode.getRightChild();	
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}
		return rootNode;
	}
	
	// removes from the root of the bst
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode){
		
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()){
			BinaryNode<T> leftSubTreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubTreeRoot);
			
			rootNode.setData(largestNode.getData());	// sets root data as largest data
			
			rootNode.setLeftChild(removeLargest(leftSubTreeRoot));	
		}
		
		else if(rootNode.hasRightChild()){
			rootNode = rootNode.getRightChild();	// gets right child
		}
		else{
			rootNode = rootNode.getLeftChild();	// gets left child
		}
		
		return rootNode;
	}

	// finds largest
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode){
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());
		
		return rootNode;
	}
	
	// removes largest
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode){
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		}
		else {
			rootNode = rootNode.getLeftChild();
		}
		
		return rootNode;
	}
	
	
	// inorder method for traversing bst
	public void inorderTraverse(){
		inorderTraverse(rootNode);
	}
	// inorder method for traversing with given node
	private void inorderTraverse(BinaryNode<T> node){

		if(node != null){
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}
	// inorder method for traversing with reverse
	public void inorderTraverseInReverse(){
		inorderTraverseInReverse(rootNode);
	}
	// inorder method traversing with reverse with given node
	private void inorderTraverseInReverse(BinaryNode<T> node){

		if(node != null){
			inorderTraverseInReverse(node.getRightChild());
			System.out.println(node.getData());
			inorderTraverseInReverse(node.getLeftChild());

		}
	}

	@Override
	public Iterator<T> getInorderIterator() {

		return new InorderIterator();
	}	
	
	
	public void iterativeInorderTraverse(){
		StackInterface<BinaryNode<T>> nodeStack = new Stack<>();	// used stack
		BinaryNode<T> currentNode = rootNode;	// now, we are in root node

		while(!nodeStack.isEmpty() || (currentNode != null)){
			while(currentNode != null){
				nodeStack.push(currentNode);	// pushing to stack
				currentNode = currentNode.getLeftChild();	
			}

			if(!nodeStack.isEmpty()){

				BinaryNode<T> nextNode = nodeStack.pop();	// popping from stack
				assert nextNode != null;

				System.out.println(nextNode.getData());
				currentNode = nextNode.getRightChild();
			}
		}
	}
	
	// inorder iteration iterates first root node, after that smaller to bigger
	private class InorderIterator implements Iterator<T> {


		private StackInterface<BinaryNode<T>> nodeStack;	// uses stack
		private BinaryNode<T> currentNode;	// node
		
		// constructor for private class InorderIterator
		public InorderIterator(){
			nodeStack = new Stack<>();
			currentNode = rootNode;
		}


		// if it has next node
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}
		
		// next node
		@Override
		public T next() {
			BinaryNode<T> nextNode = null;

			while(currentNode != null){
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}

			if(!nodeStack.isEmpty()){
				nextNode = nodeStack.pop();
				assert nextNode != null;

				currentNode = nextNode.getRightChild();

			}else{
				throw new NoSuchElementException();
			}
			return nextNode.getData();
		}
		
	}

}
