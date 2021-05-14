//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class StorageChief {

    public StorageChief(){	//constructor with no parameter and no field
    	
    }
    // Transfering item with its own warehouse
    public void TransferToWareHouse(FurnitureManufacturer<Product> manufacturer, WareHouse<Product> sofaStack,
                                    WareHouse<Product> bedStack,WareHouse<Product> chairStack,
                                    WareHouse<Product> dresserStack, WareHouse<Product> tableStack,
                                    WareHouse<Product> bookCaseStack){
    	
        if(!manufacturer.isEmpty()){	//checks the manufacturer is empty or not
            Product product =  manufacturer.dequeue();  // getting the first product of the manufacturer
            if(product.getName().equals("sofa")){ //checks if the product name is equal to sofa
                 sofaStack.push(product);	//adds product to stack
            }else if(product.getName().equals("bed")){	//checks if the product name is equal to bed
                bedStack.push(product);	//adds product to stack
            }else if(product.getName().equals("chair")){ //checks if the product name is equal to chair
                chairStack.push(product); //adds product to stack
            }else if(product.getName().equals("dresser")){ //checks if the product name is equal to dresser
                dresserStack.push(product); //adds product to stack
            }else if(product.getName().equals("table")){ //checks if the product name is equal to table
                tableStack.push(product); //adds product to stack
            }else if(product.getName().equals("bookCase")){ // checking if the product name is equal to bookCase
                bookCaseStack.push(product);
            }
        }
        
    }

}
