//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046

public class Food implements Orderable, Comparable<Food>{
    private String name; // name attribute
    private double price;// price attribute
    private int stock; // stock attribute
    private Restaurant restaurant; // restaurant attribute
    
    public Food() { // contructor with default values
		this.name = "";
		this.price = -1;
		this.stock = -1;
		this.restaurant = null;
	}

	public Food(String name, double price, int stock, Restaurant restaurant) { // contructor with values
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.restaurant = restaurant;
	}

	public String getName() { // getter for name
		return name;
	}
	public void setName(String name) { // setter for name
		this.name = name;
	}
	public double getPrice() { // getter for price 
		return price;
	}
	public void setPrice(double price) { // setter for price
		this.price = price;
	}
	public int getStock() { // getter for stock
		return stock;
	}
	public void setStock(int stock) { // setter for stock
		this.stock = stock;
	}
	public Restaurant getRestaurant() { // getter for restaurant
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) { // setter for restaurant
		this.restaurant = restaurant;
	}

	@Override
	public void updatePrice(double price) { // update price
        this.price = price;
	}

	@Override
	public void updateStock(int stock) { // update stock
		this.stock = stock;
	}

	@Override
	public String toString() { // toString for printing food object
		return "Food's name: " + name + ", price: " + price + ", stock: " + stock + ", " + restaurant;
	}


	@Override
	public int compareTo(Food o, int choice) { // compareTo method with a choice
		if(choice == 1	){ // if choice is 1, then compare it with its price attribute
			int result;
			if(this.price > o.getPrice()){
				result = 1;
			}else if(this.price < o.getPrice()){
				result = -1;
			}else{
				result = 0;
			}
			return result;
		}else if(choice == 3){ // else if choice is 3, then compare it with its stock attribute
			int result;
			if(this.stock > o.getStock()){
				result = 1;
			}else if(this.stock < o.getStock()){
				result = -1;
			}else{
				result = 0;
			}
			return result;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) { // equals method, compares it with another object
		if (this == obj)
        	return true;
		// null check
		if (obj == null)
			return false;
		// type check and cast
		if (getClass() != obj.getClass())
			return false;
		
		Food new_food = (Food) obj;
		
		return this.name.equals(new_food.getName()) && (this.price == new_food.getPrice()) && this.restaurant.equals(new_food.getRestaurant()) && (this.stock == new_food.getStock());
	}
}