//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046

import java.util.Random;

public class MainOperations {
	// trigger marketing analyst for create product from manufacturer
    public static void triggerMarketingAnalyst(int num, FurnitureManufacturer<Product> furnitureManufacturer) {
        Random rand = new Random();
		// creating random product
		int randomProduct = rand.nextInt(6);
		Product product;
		if(randomProduct == 0){ // if the  number is equal to 0 then create Sofa product
			product = new Product("sofa");
		}
        
		else if(randomProduct == 1){ // if the  number is equal to 1 then create bed product
			product = new Product("bed");
		}
	
		else if(randomProduct == 2) { // if the  number is equal to 2 then create chair product
			product = new Product("chair");
		}
		
		else if(randomProduct == 3) {// if the  number is equal to 3 then create dresser product
			product = new Product("dresser");
		}
		
		else if(randomProduct == 4) {// if the  number is equal to 4 then create table product
			product = new Product("table");
		}
		
		else if(randomProduct == 5) {// if the  number is equal to 5 then create bookCase product
			product = new Product("bookCase");
		}
		else{// if the  number is equal to 0 then create Sofa
			product = new Product();
		}
		
		furnitureManufacturer.enqueue(product); // adding product to the manufacturer
		
		String done = product.getName();
		String failOrSucces;
		if(done.equals("")){ // if the product creation did not completed then print Faýl
			failOrSucces = "FAIL";
		}else{// if the product creation did not completed then print Success
			failOrSucces = "SUCCESS";
		}
		// Reporting user that which item is created
		System.out.println(num + ".		Marketing Analyst requesting " + product.getName() + ","+ failOrSucces + ","+ 
							product.getName() +" manufactured");
	}
	// trigger the storageChief to store item from manufacturer
	public static void triggerStorageChief(int num, 
											StorageChief storageChief,
											FurnitureManufacturer<Product> furnitureManufacturer,
											WareHouse<Product> sofaStack, 
											WareHouse<Product> bedStack, 
											WareHouse<Product> chairStack, 
											WareHouse<Product> dresserStack, 
											WareHouse<Product> tableStack, 
											WareHouse<Product> bookCaseStack) {
		
		String failOrSucces;
		String product_name;
		if(!furnitureManufacturer.isEmpty()){ // if the manufacurer is not empty then
			failOrSucces = "SUCCESS";
			product_name = furnitureManufacturer.getFront().getName(); // getting prodcut name of the first index of the manufacturer
			// transferring item to the its own warehouse
			storageChief.TransferToWareHouse(furnitureManufacturer, sofaStack, bedStack, chairStack, dresserStack, tableStack, bookCaseStack);
			// reporting user that product is stored
			System.out.println(num + ".		Storage Chief storing " + product_name + ","+ 
								failOrSucces+ "," + product_name + " stored in " + product_name + " warehouse");
		}else{// if the manufacurer is empty then print fail
			failOrSucces = "FAIL";
			System.out.println(num + ".		Storage Chief " + failOrSucces);
		}

	}
	public static void triggerCustomerForProduct(int num, Customer customer, WareHouse<Product> sofaStack, 
												WareHouse<Product> bedStack, WareHouse<Product> chairStack, 
												WareHouse<Product> dresserStack, WareHouse<Product> tableStack, 
												WareHouse<Product> bookCaseStack) {	//takes stacks, customer, and num parameters
		Random rand = new Random();
		int randomProduct = rand.nextInt(6);	//random number between 0 and 5
		String product_name = "";	//empty string name
		boolean empty = false;	//checks empty or not
		String failOrSucces = "FAIL";	//if every conditions are not okay, this will be FAIL
		if(randomProduct == 0){	//if random number is equal to 0
			if(sofaStack.isEmpty()){	//empty stack control
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying Sofa, " + failOrSucces + ", Sofa warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = sofaStack.peek().getName();	//takes name of top product
				customer.request_buy(sofaStack);	//customer takes element from that specific stack
			}			
		}
		
		else if(randomProduct == 1){ 	//if random number is equal to 1
			if(bedStack.isEmpty()){
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying Bed, " + failOrSucces + ", Bed warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = bedStack.peek().getName(); //takes name of top product
				customer.request_buy(bedStack);		//customer takes element from that specific stack
				}
		}
			
		else if(randomProduct == 2) { 	//if random number is equal to 2
			if(chairStack.isEmpty()){
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying Chair, " + failOrSucces + ", Chair warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = chairStack.peek().getName(); //takes name of top product
				customer.request_buy(chairStack);	//customer takes element from that specific stack
			}
		}
		
		else if(randomProduct == 3) { 	//if random number is equal to 3
			if(dresserStack.isEmpty()){
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying Dresser, " + failOrSucces + ", Dresser warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = dresserStack.peek().getName(); //takes name of top product
				customer.request_buy(dresserStack);	//customer takes element from that specific stack
			}
		}
		
		else if(randomProduct == 4) { 	//if random number is equal to 4
			if(tableStack.isEmpty()){
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying Table, " + failOrSucces + ", Table warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = tableStack.peek().getName(); //takes name of top product
				customer.request_buy(tableStack);	//customer takes element from that specific stack
			}	
		}
		
		else if(randomProduct == 5) { 	//if random number is equal to 5
			if(bookCaseStack.isEmpty()){
				empty = true;
				failOrSucces = "FAIL";
				System.out.println(num + ".		Customer buying BookCase, " + failOrSucces + ", BookCase warehouse empty");
			}else{
				failOrSucces = "SUCCESS";
				product_name = bookCaseStack.peek().getName(); //takes name of top product
				customer.request_buy(bookCaseStack);	//customer takes element from that specific stack
			}
		}
		if(!empty){
			System.out.println(num + ".		Customer buying " + product_name + "," + 
							failOrSucces+ ", Customer bought " + product_name);
		}
	
	}
	
	public static void printReport(FurnitureManufacturer<Product> furnitureManufacturer,
									WareHouse<Product> sofaStack, WareHouse<Product> bedStack,
									WareHouse<Product> chairStack, WareHouse<Product> dresserStack, 
									WareHouse<Product> tableStack, WareHouse<Product> bookCaseStack,
									Customer customer) {	//takes stacks, queue and customer as parameters
		
		int sofa_amount_factory = 0;	//counts the numbers of sofa which remains in factory 
		int bed_amount_factory = 0;		//counts the numbers of bed which remains in factory 
		int chair_amount_factory = 0;	//counts the numbers of chair which remains in factory 
		int dresser_amount_factory = 0;	//counts the numbers of dresser which remains in factory 
		int table_amount_factory = 0;	//counts the numbers of table which remains in factory 
		int bookCase_amount_factory = 0;	//counts the numbers of bookcase which remains in factory 
		
		int sofa_amount_warehouse = sofaStack.getTopIndex();	//takes top index of sofa warehouse
		int bed_amount_warehouse = bedStack.getTopIndex();	//takes top index of bed warehouse
		int chair_amount_warehouse = chairStack.getTopIndex();	//takes top index of chair warehouse
		int dresser_amount_warehouse = dresserStack.getTopIndex();	//takes top index of dresser warehouse
		int table_amount_warehouse = tableStack.getTopIndex();	//takes top index of table warehouse
		int bookCase_amount_warehouse = bookCaseStack.getTopIndex();	//takes top index of bookcase warehouse

		int sofa_amount_customer = 0;	//number of sofa which is taken by customer
		int bed_amount_customer = 0;	//number of sofa which is taken by customer
		int chair_amount_customer = 0;	//number of sofa which is taken by customer
		int dresser_amount_customer = 0;	//number of sofa which is taken by customer
		int table_amount_customer = 0;	//number of sofa which is taken by customer
		int bookCase_amount_customer = 0;	//number of sofa which is taken by customer
		
		Product dequeued_item;
		while(!furnitureManufacturer.isEmpty()){ // till the manufacurer is empty
			dequeued_item = furnitureManufacturer.dequeue(); // removing item from manufacturer
			if(dequeued_item.getName().equals("sofa")){ // checking if the item in the manufacurer is sofa
				sofa_amount_factory++;// increase the number of sofa that created
			}else if(dequeued_item.getName().equals("bed")){// checking if the item in the manufacurer is bed
				bed_amount_factory++;// increase the number of bed that created
			}else if(dequeued_item.getName().equals("chair")){// checking if the item in the manufacurer is chair
				chair_amount_factory++;// increase the number of chair that created
			}else if(dequeued_item.getName().equals("dresser")){// checking if the item in the manufacurer is dresser
				dresser_amount_factory++;// increase the number of dresser that created
			}else if(dequeued_item.getName().equals("table")){// checking if the item in the manufacurer is table
				table_amount_factory++;// increase the number of table that created
			}else if(dequeued_item.getName().equals("bookCase")){// checking if the item in the manufacurer is bookCase
				bookCase_amount_factory++;// increase the number of bookCase that created
			}
		}
		Product products[] = customer.getBasket(); // getting customers basket
		int index_customer = 0; // index for while loop
		while(index_customer<customer.getNumberOfEntries()){ // till index is less than number of entries of the basket
			if(products[index_customer].getName().equals("sofa")){ // checking if the item in the basket is sofa 
				sofa_amount_customer++; // increase the number of sofa that bought
			}else if(products[index_customer].getName().equals("bed")) {  // checking if the item in the basket is bed 
				bed_amount_customer++;// increase the number of bed that bought
			}else if(products[index_customer].getName().equals("chair")){ // checking if the item in the basket is chair 
				chair_amount_customer++;// increase the number of chair that bought
			}else if(products[index_customer].getName().equals("dresser")){ // checking if the item in the basket is dresser 
				dresser_amount_customer++;// increase the number of dresser that bought
			}else if(products[index_customer].getName().equals("table")){ // checking if the item in the basket is table 
				table_amount_customer++;// increase the number of table that bought
			}else if(products[index_customer].getName().equals("bookCase")){ // checking if the item in the basket is bookCase 
				bookCase_amount_customer++;// increase the number of bookCase that bought
			}
			
			
			index_customer++;
		}
			
		// Printing Report of the program
		System.out.println("REPORT:\n");
		System.out.println("Amount of Bed in Factory Line: " + bed_amount_factory);
		System.out.println("Amount of Sofa in Factory Line: " + sofa_amount_factory);
		System.out.println("Amount of Dresser in Factory Line: " + dresser_amount_factory);
		System.out.println("Amount of Table in Factory Line: " + table_amount_factory);
		System.out.println("Amount of Chair in Factory Line: " + chair_amount_factory);
		System.out.println("Amount of Bookcase in Factory Line: " + bookCase_amount_factory);
		
		System.out.println("\n");
		
		System.out.println("Amount of Bed in Bed Warehouse: " + bed_amount_warehouse);
		System.out.println("Amount of Sofa in Sofa Warehouse: " + sofa_amount_warehouse);
		System.out.println("Amount of Dresser in Dresser Warehouse: " + dresser_amount_warehouse);
		System.out.println("Amount of Table in Table Warehouse: " + table_amount_warehouse);
		System.out.println("Amount of Chair in Chair Warehouse: " + chair_amount_warehouse);
		System.out.println("Amount of Bookcase in BookCase Warehouse: " + bookCase_amount_warehouse);
		
		System.out.println("\n");
		
		System.out.println("Amount of Bed Sold: " + bed_amount_customer);
		System.out.println("Amount of Sofa Sold: " + sofa_amount_customer);
		System.out.println("Amount of Dresser Sold: " + dresser_amount_customer);
		System.out.println("Amount of Table Sold: " + table_amount_customer);
		System.out.println("Amount of Chair Sold: " + chair_amount_customer);
		System.out.println("Amount of Bookcase Sold: " + bookCase_amount_customer);
		
	}
}
