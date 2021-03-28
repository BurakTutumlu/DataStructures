import java.io.IOException;

public class Item {
	
	private String itemName ;
	private String compartment;
	private int weight;
	
	public Item() { // Constructor without parameters
		itemName = null;
		compartment = null;
		weight = 0;
	}

	public Item(String itemName, String compartment, int weight) { // Constructor with parameters
		this.itemName = itemName;
		this.compartment = compartment;
		this.weight = weight;
	}

	public String getItemName() { // getter for item name
		return itemName;
	}

	public void setItemName(String itemName) { // setter for item name
		this.itemName = itemName;
	}

	public String getCompartment() { // getter for item compartment
		return compartment;
	}

	public void setCompartment(String compartment) { // setter for item compartment
		this.compartment = compartment;
	}

	public int getWeight() { // getter for item weight
		return weight;
	}

	public void setWeight(int weight) { // setter for item weight
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) { // equals method for comparing two items
		if (this == obj) // if item object equal to parameter object
			return true;
		if (obj == null) // if other object is null then return false.
			return false;
		if (getClass() != obj.getClass()) // if this object class is different than other object class
			return false;				// then return false
		Item other = (Item) obj;  // creating other object for comparing
		if (compartment == null) { // checking compartments of both objects.
			if (other.compartment != null)
				return false;
		} else if (!compartment.equals(other.compartment))
			return false;
		if (itemName == null) { // checking itemNames of both objects
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (weight != other.weight) // checking weights of both objects
			return false;
		return true;
	}

	@Override
	public String toString() { // for printing object with its values
		if(weight==0){ // if weights is 0 than do not show this into inventory
			return "\n";
		}
		else{
			return "itemName: " + itemName + ", compartment: " + compartment + ", weight: " + weight + "";
		}
	}
	
	
	public int decreaseWeight(int decreaseWeight) throws IOException { // decreasing item Weight with given amount
		
		if (this.weight < decreaseWeight) { // if given amount is bigger than objects amount than throw an error
			throw new IOException("azaltýlacak miktar, kendi miktarýndan fazla olamaz.");
		}
		
		this.weight = this.weight-decreaseWeight; // decreasing weight of this object
		return this.weight; // return items weight
	}
	
	
	public void increaseWeight(int increaseWeight) {// increasing weight of item
			
		this.weight = this.weight+increaseWeight; // increasing weight of item
	}
	
}
