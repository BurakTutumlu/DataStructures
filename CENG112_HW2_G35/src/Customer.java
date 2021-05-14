//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class Customer {

    private Product basket[]; // creating customer basket
    private int numberOfEntries; // number of products in the basket

    public Customer(){ // constructor of the customer class with default capacity 100
       
        // initializing the attributes.
        basket = new Product[100];
        numberOfEntries = 0;  
    }
    public Customer(int capacity){ // constuctor of the customer class with capacity
        // initializing the attributes.
        basket = new Product[capacity];
        numberOfEntries = 0;
    }
	
    public void request_buy(WareHouse<Product> productStack){	
    	Product product = productStack.pop();	//when request method called from customer, item taken from stack
    											//then taken item from stack defines the product
        basket[numberOfEntries] = product;		//after taken product numberOfEntries indcrease and 
        										//product initializes that index
        numberOfEntries++;
    	
    }
	public int getNumberOfEntries() {	//getter for number of items on the basket
		return numberOfEntries;
	}
	
	public Product[] getBasket() {	//getter for basket 
		return basket;
	}

}
