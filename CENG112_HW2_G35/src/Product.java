//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class Product implements IProduct {

    private String name;	//field for name
    private boolean isManufactured;	//field for the item is manufactured or not
    private boolean isStored;	//field for the item is stored or not	
    private boolean isSold;		//field for the item is sold or not
    
    public Product(){ //constructor with no parameter
        this.name = "";	//constructor for product, its name will be initialized to empty in here
    }
    
    public Product(String name){	//constructor with name parameter
        this.name = name;	//constructor for product, its name will be initialized to given name in here
    }
	@Override
	public boolean isManufactured() {	//method for is manufactured 
		return isManufactured;
	}

	@Override
	public boolean isStored() {	//method for is stored 
		return isStored;
	}

	@Override
	public boolean isSold() {	//method for is sold
		return isSold;
	}

	public String getName() {	//getter method for getting name of product
		return name;
	}

	public void setName(String name) {	//setter for name 
		this.name = name;
	}

	public void setManufactured(boolean isManufactured) {	//setter for manufactured 
		this.isManufactured = isManufactured;
	}

	public void setStored(boolean isStored) {	//setter for stored
		this.isStored = isStored;
	}

	public void setSold(boolean isSold) {	//setter for sold
		this.isSold = isSold;
	}
	
	

}
