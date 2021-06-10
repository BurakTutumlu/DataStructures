// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public final class LinkedList<T> implements ILinkedList<T>{
	
	private Node firstNode;		// field for first node
	private int numberOfEntries;	// field for number of elements in linkedlist
	
	
	public LinkedList() {	// constructor with empty parameter
		this.firstNode = null;	// initialize first-node to null
		this.numberOfEntries = 0;	// initialize number of elements to 0
	}
	
	private Node getNodeAt(int givenPosition) { // method for finding node with given index
		assert (firstNode != null) &&
				(1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;	// take current node as first node
		
		for(int counter = 0;counter < givenPosition; counter++) {
			currentNode = currentNode.getNextNode(); // iterate over nodes
			assert currentNode != null;
		}
		
		
		return currentNode;
	}
	
	public T remove(int givenPosition) {	// removing object from given index
		
		T result = null;
		
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {	//
			assert !isEmpty();
			if (givenPosition == 1) {	// if the index is 1, 
				result = firstNode.getData();	// we need to take first element's data
				firstNode = firstNode.getNextNode();	// after that, we need to make firstnode to nextnode
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition -1); // initializing previous node	
				Node nodeToRemove = nodeBefore.getNextNode();	// initializing removed node
				result = nodeToRemove.getData();		
				Node nodeAfter = nodeToRemove.getNextNode(); // initializing next node that comes after from removed node
				nodeBefore.setNextNode(nodeAfter); // our previous node's next node will be nodeafter.
			}
			numberOfEntries--;	// decrease number of elements
			return result;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
		}
	}
	
	public T replace(int givenPosition, T newEntry) {	// replace new entry with given index
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {	// check the index
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);	// find index with that index
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);	// change the data with given entry's data
			return originalEntry;
		}else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
		}
	}
	
	public void add(T newEntry) {	// adding the new entry
		Node newNode = new Node(newEntry);
		if(isEmpty()) {
			firstNode = newNode;	// if linkedlist is empty, new entry will be our first element
		}else {
			Node lastNode = getNodeAt(numberOfEntries - 1); // getting last node and our newEntry will be next to the last node
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;	// increase number of elements in linked list
		
	}
	
	public void add(int givenPosition, T newEntry) {	// adding new entry to given index
		if(givenPosition >= 1 && givenPosition <= numberOfEntries + 1) {	
			Node newNode = new Node(newEntry);
			if(givenPosition == 1) {	// if the given index is the first index of the linked list
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition - 1);	// initializing before node for new node
				Node nodeAfter = nodeBefore.getNextNode();	// initializing next node for next node
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
		}

	}
	
	public boolean isEmpty() {	// if the linked list is empty that means, if there is o element in linked list
		boolean result;
		if(numberOfEntries == 0) {	// if this condition is true, result will be true
			assert firstNode == null;	
			result = true;
		}else {	// otherwise, result will be false
			assert firstNode != null;
			result = false;
		}
		return result;
	}
	
	public T[] toArray() {	// converting this list to an array
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];	// creating a T array
		int index = 0;	// index of T array
		Node currenNode = firstNode; // getting first to start assigning
		while(index < numberOfEntries && currenNode != null) {	// assign till the list is empty
			result[index] = currenNode.getData(); // assing current node to array
			currenNode = currenNode.getNextNode(); // get next node
			index++; // increment array index
		}
		return result;
	}
	
	public T getEntry(int givenPosition) { // getting item from given position
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) { // checking the given position is suitable or not
			assert !isEmpty(); 
			return getNodeAt(givenPosition).getData(); // return node from given index
		}
		else { // if given position is not suitable then throw an error
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
		}
	}
	
	public boolean contains(T anEntry) { // Checking whether this item is in the linkedlist
		boolean found = false; 			// initializing found as false
		
		Node currentNode = firstNode;	// getting first node to start searching
		
		while(!found && currentNode != null) {	// search till next node is empty, means to end of the list
			if(anEntry.equals(currentNode.getData())) {	// controlling that item is equal to node
				found = true;	// if item found than found is true
			}
			else { // if item is not equal to node than get next node
				currentNode = currentNode.getNextNode();
			}
		}
		
		return found;	// return found
	}

	private class Node{	//	node of linkedlist
		private T data; // 	Generic value of node data
		private Node next;	//	pointer of another node, for linking together
		

		private Node(T data) {	//	constructor of Node class
			this(data,null);	//	assign data to node data 
		}
		
		
		private Node(T data, Node nextNode) {	// creating node with data and link with next node 
			this.data = data;					// assign data to node data
			this.next = nextNode;				// assign next node to its pointer field
		}


		private T getData() {	// getter for data
			return data;
		}


		private void setData(T data) {	// setter for data
			this.data = data;
		}


		private Node getNextNode() {	// getter for next node
			return next;
		}


		private void setNextNode(Node next) {	// setter for next node
			this.next = next;
		}
	}
}

