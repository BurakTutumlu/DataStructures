//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class Restaurant implements Comparable<Restaurant>{

	private String name;	// field for name of restaurant
    private double rating;	// field for rating of restaurant
    private String cuisine;	// field for cuisine of restaurant
    private int deliveryTime; // field for delivery time of restaurant
    
    public Restaurant() {	// constructor with no parameters
		this.name = "";
		this.rating = -1;
		this.cuisine = "";
		this.deliveryTime = -1;
	}
    // this constructor takes parameters with name, rating, cuisine, deliveryTime fields
	public Restaurant(String name, double rating, String cuisine, int deliveryTime) {
		this.name = name;
		this.rating = rating;
		this.cuisine = cuisine;
		this.deliveryTime = deliveryTime;
	}
    // getter method for name
    public String getName() {
		return name;
	}
    // setter method for name
	public void setName(String name) {
		this.name = name;
	}
	// getter method for rating
	public double getRating() {
		return rating;
	}
	// setter method for rating
	public void setRating(double rating) {
		this.rating = rating;
	}
	// getter method for cuisine
	public String getCuisine() {
		return cuisine;
	}
	// setter method for cuisine
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	// getter method for delivery time
	public int getDeliveryTime() {
		return deliveryTime;
	}
	// setter method for delivery time
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	// updates cuisine
    public void updateCuisine(String category){
        this.cuisine = category;
    }
    // updates rating
    public void updateRating(double score){
        this.rating = score;
    }
    // updates delivery time
    public void updateDeliveryTime(int delivery) {
    	this.deliveryTime = delivery;
    }
    // toString method for fields
	@Override
	public String toString() {
		return "Restaurant's name: " + name + ", rating: " + rating + ", cuisine: " + cuisine + ", deliveryTime="
				+ deliveryTime;
	}
	
	// compareTo methods compares this restaurant with another restaurant according to choice
	@Override
	public int compareTo(Restaurant o, int choice) {
		if(choice == 0){
			int result;
			if(this.rating > o.getRating()){	// if this restaurant's rating bigger than other restaurant's, return 1
				result = 1;
			}else if(this.rating < o.getRating()){	// if this restaurant's rating smaller than other restaurant's, return -1
				result = -1;
			}else{	// if their ratings are equal, reutnr 0
				result = 0;
			}
			return result;
		}else if(choice == 2){
			int result;
			if(this.deliveryTime > o.getDeliveryTime()){ // if this restaurant's delivery time bigger than other restaurant's, return 1
				result = 1;
			}else if(this.deliveryTime < o.getDeliveryTime()){ // if this restaurant's delivery time smaller than other restaurant's, return 1
				result = -1;
			}else{
				result = 0; // if their delivery times are equal, reutnr 0
			}
			return result;
		}
		return 0;
	}
	
	
	// this method checks if this object and other object is same or not.
	@Override
	public boolean equals(Object obj) {	
		if (this == obj)
        	return true;
		// null check
		if (obj == null)
			return false;
		// type check and cast
		if (getClass() != obj.getClass())
			return false;

		Restaurant new_rest = (Restaurant) obj;
		
		
		return (this.name.equals(new_rest.getName()) == true) && (this.cuisine.equals(new_rest.getCuisine()) == true) && (this.deliveryTime == new_rest.getDeliveryTime()) && (this.rating == new_rest.getRating());
	}
	
	
    
}
