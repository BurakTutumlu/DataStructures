//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		

		// Creating all objects that we use in the program
		WareHouse<Product> sofaStack = new WareHouse<Product>(100);	//creates sofa stack with 100 capacity
		WareHouse<Product> bedStack = new WareHouse<Product>(100); //creates bed stack with 100 capacity
		WareHouse<Product> chairStack = new WareHouse<Product>(100); //creates chair stack with 100 capacity
		WareHouse<Product> dresserStack = new WareHouse<Product>(100); //creates dresser stack with 100 capacity
		WareHouse<Product> tableStack = new WareHouse<Product>(100); //creates table stack with 100 capacity
		WareHouse<Product> bookCaseStack = new WareHouse<Product>(100); //creates bookcase stack with 100 capacity
		StorageChief storageChief = new StorageChief();	//creates storage chief
		Customer customer = new Customer();	//creates customer
		FurnitureManufacturer<Product> furnitureManufacturer = new FurnitureManufacturer<Product>(100);	//creates queue with capacity
		Random rand = new Random();	//for taking random integer, this statement is necessary
		
		boolean done = false;	//for user input is in correct format or not
		int num = 0;
		while(!done){
			try {	//for exceptions control
			Scanner scan = new Scanner(System.in);	//takes input from user		
			System.out.println("Enter the number of random request cycles: ");
			num = scan.nextInt();	//this returns string to integer
			done = true;
			scan.close();
			}catch(InputMismatchException e) {	//catching the exception
				System.out.println("Please enter an integer");
				done = false;
			}
		}
	
		int operation_index = 0; // number of operatins on the program
		while(operation_index <= num){ // till the number of operations is less than user input, program runs
			int operation = rand.nextInt(3); // getting operation number
			if(operation==0){ // if the operation number is equal to 0 then
				//trigger Marketing Analyst for creating product
				MainOperations.triggerMarketingAnalyst(operation_index,furnitureManufacturer);
			}else if(operation==1){ // if the operation number is equal to 1 then
				//trigger Storage Chief to store item from manufacturer
				MainOperations.triggerStorageChief(operation_index, storageChief, furnitureManufacturer, sofaStack, bedStack, chairStack, dresserStack, 
										tableStack, bookCaseStack);
			}else{// if the operation number is equal to 2 then
				//trigger Customer for buy product
				MainOperations.triggerCustomerForProduct(operation_index, customer, sofaStack, bedStack, chairStack, 
											dresserStack, tableStack, bookCaseStack);
			}
			operation_index++; // increasing operation number
		}
		// printing the report
		MainOperations.printReport(furnitureManufacturer,sofaStack, bedStack, chairStack, dresserStack, 
										tableStack, bookCaseStack, customer );
						

	}
}