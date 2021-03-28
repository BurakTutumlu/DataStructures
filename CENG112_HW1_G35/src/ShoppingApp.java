import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;  

/////////////We checked lots of conditions and bugs, we took great care
/////////////
public class ShoppingApp {

	private static IBag<Item> shoppingBasket = new Bag<Item>(2000);		//basket is created with size 2000
	private static IBag<Item> bevaragesCompartment = new Bag<Item>(4000);	//beverages compartment created with capacity 4000
	private static IBag<Item> meatsComportment = new Bag<Item>(5000);	//meats compartment created with capacity 5000
	private static IBag<Item> vegetablesFruitsCompartment = new Bag<Item>(3000);	//vegetables-fruits compartment created with capacity 3000
	private static IBag<Item> snacksCompartment = new Bag<Item>(2000);	//snacks compartment created with capacity 2000
	private static IBag<Item> inventory = readFromFile();	//creates inventory after reading from the file

	public static void main(String[] args) throws IOException {
		// starting Application
		App();

	}
	
	// Creating inventory from file.
	static IBag<Item> readFromFile(){
		FileIO read_file = new FileIO();	//First creating FileIO object
		IBag<Item> inventory = read_file.readItemCSV(); // reading from file 
		return inventory; // returning inventory
	}
	
	// showing the baskets inside in terms of the user input
	static void showBasketsInside(){
		System.out.println("* Do you want to see your basket? 'yes' or 'no' ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		while(!done){ // till user typing 'yes' or 'no'
			String check_basket_input = sc.nextLine(); // getting user inputs

			if (check_basket_input.equals("yes")) { // if user types yes than show baskets inside
				System.out.println(" Items on basket: ");
				shoppingBasket.displayItems();
				done = true;
			}else if (check_basket_input.equals("no")) { // if user types no than do nothing and finish while loop
				done = true;
			}else{ // if user types different than 'yes' or 'no' than get another input
				System.out.println(" Please enter a valid option. ");
			}
		}
	}
	

	static String checkContinue(){ // checks that user wants to continue
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("* Do you want to continue your buying operations:"+ "  'yes' or 'no' (YOU ARE IN THE MARKET) ");
		String check_continue = sc.nextLine();
		return check_continue;
	}

	// shopping from market
	static void shopping(){ 
		// displaying user menu
		MenuOperations.displayMenu();

		boolean done = false; 
		
		// shopping continues till user types no, means finish shopping, or basket has no more capacity
		while(!done &&  shoppingBasket.getFridge_capacity()>0) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// Showing basket capacity to user.
			System.out.println(" Remaining space in the basket: " + shoppingBasket.getFridge_capacity());
			
			// Checks user wants to continue or not
			String check_continue = checkContinue();
			if (check_continue.equals("no")) { // if user wants to exit than exits from shopping
				done=true;
			}
			else if(check_continue.equals("yes")){ // if user wants to continue to shopping
				inventory.displayItems(); // displaying inventory items to user
				String item_name = "";
				boolean has_item = false;
				while(!has_item){ // this while runs till user types an input that same with the inventory items name
					System.out.println("* What do you want to buy? ");
					item_name = sc.nextLine(); // asking user to types an items name
					for(int i=0; i < inventory.getItemCount();i++){
						if(inventory.getItem(i).getItemName().equals(item_name) ){ // checking item that is it in the inventory or not
							has_item = true;
							break;
						}
					}
					if(!has_item){ // if item is not in the inventory than
						System.out.println(" Please,enter an inventory value ");
					}
				}
				
				for(int i=0; i < inventory.getItemCount();i++){
					//checking if there is an item or not with searching name
					//no item with that name
					if(inventory.getItem(i).getItemName().equals(item_name) && inventory.getItem(i).getWeight()==0){
						System.out.println(" We do not have that product you are looking for ");
					}
					//there is item with that name
					
					else if(inventory.getItem(i).getItemName().equals(item_name)) {
						System.out.println("* How much do you want to buy? ");
						
						boolean right_amount = true;
						int amount = 0;
						while(right_amount){
							while(true) {
								@SuppressWarnings("resource")
								Scanner scc = new Scanner(System.in);
								try{
									amount = scc.nextInt();	//getting amount from the user
									break;
								}catch (InputMismatchException e) {//catching exception with wrong input
									System.out.println(" Please, enter a valid option. ");
									
								}
							}
							if(amount>shoppingBasket.getFridge_capacity()){	//amount and basket control
								System.out.println(" You have less capacity for that value, so you can not add "+
													"that value. Please enter an amount lesser than " 
													+ shoppingBasket.getFridge_capacity());
								
							}
							else{
								if (amount>inventory.getItem(i).getWeight()) {	//amount and inventory capacity control
									System.out.println(" amount can not be bigger than item weight ");
									System.out.println(" try again ");
									if (inventory.getItem(i).getWeight() == 0) {	//if item with that weight is finished
										break;
									}
								}
								else {
									//getting item which going to be sent
									Item to_send = new Item(inventory.getItem(i).getItemName(),inventory.getItem(i).getCompartment(), 
											amount);

									try{
										inventory.getItem(i).decreaseWeight(amount); //decrease inventory item when it bought
										boolean found;
										if(shoppingBasket.getItemCount()>=1){	//when there is more than 1 item in basket
											found = false;
											for(int k=0; k<shoppingBasket.getItemCount(); k++){
												if(shoppingBasket.getItem(k).getItemName().equals(to_send.getItemName())){
													found = true;
													shoppingBasket.getItem(k).increaseWeight(amount);	//increase weight of that item
													shoppingBasket.decreaseFridge_capacity(amount);		//decrease of the basket capacity
													
												}
											}
											if(!found){
												shoppingBasket.add(to_send);	//adding basket that item which is going to sent
												shoppingBasket.decreaseFridge_capacity(amount);	//decrease basket capacity
											}
										}
										else{
											shoppingBasket.add(to_send);	//adding basket that item which is going to sent
											shoppingBasket.decreaseFridge_capacity(amount);	//decrease basket capacity
										}									
										break;
									}catch(IOException e){//catching all IO exceptions
										
									}
									break;
								}
							}							
						}
					}
				}
				showBasketsInside();	//shows the items on the basket
				checkCompartmentStatus();	//checking capacity of compartments
			}
			else{
				System.out.println(" Please enter a valid option. ");
				continue;
			}
			//showBasketsInside();	//shows the items on the basket
			//checkCompartmentStatus();	//checking capacity of compartments
		}
		System.out.println(" Your shopping is finished. ");
	}
	

	static void addingToFridge() throws IOException{

		int length = shoppingBasket.getItemCount();	//getting item count of basket
		for(int i=length - 1; i>=0; i--){	//we are searching from last to first
			int amount = shoppingBasket.getItem(i).getWeight();	//amount going  to be founded item's weight

			
			if(shoppingBasket.getItem(i).getCompartment().equals("vegetables and fruits") ){//checking vegetable fruit compartment

				if(vegetablesFruitsCompartment.getFridge_capacity() >= amount ){	
					shoppingBasket.transferTo(vegetablesFruitsCompartment,shoppingBasket.getItem(i) );	 //transferring	
					vegetablesFruitsCompartment.decreaseFridge_capacity(amount);	//decrease that compartment capacity
					shoppingBasket.increaseFridge_capacity(amount);		//increase basket capacity
				}
				else if(vegetablesFruitsCompartment.getFridge_capacity() >= 0 && 
				vegetablesFruitsCompartment.getFridge_capacity() < amount){
					
					//if the amount is bigger than compartment and compartment's capacity is not 0
					//getting that item when the conditions are properly ok.
					Item new_item = new Item(shoppingBasket.getItem(i).getItemName(),shoppingBasket.getItem(i).getCompartment(),vegetablesFruitsCompartment.getFridge_capacity());
					vegetablesFruitsCompartment.add(new_item);
					
					vegetablesFruitsCompartment.decreaseFridge_capacity(vegetablesFruitsCompartment.getFridge_capacity());
					shoppingBasket.increaseFridge_capacity(vegetablesFruitsCompartment.getFridge_capacity());
				}else{
					System.out.println(" There is no place in the fridge for that amount. ");
				}
				
			}else if(shoppingBasket.getItem(i).getCompartment().equals("meats")){ //checking meat compartment

				if(meatsComportment.getFridge_capacity() >= amount){
				
					shoppingBasket.transferTo(meatsComportment,shoppingBasket.getItem(i) ); //transferring
					meatsComportment.decreaseFridge_capacity(amount);	//decrease that compartment capacity
					shoppingBasket.increaseFridge_capacity(amount);		//increase basket capacity
				
				}
				else if(meatsComportment.getFridge_capacity() >= 0 && 
						meatsComportment.getFridge_capacity() < amount){
					
					//if the amount is bigger than compartment and compartment's capacity is not 0
					//getting that item when the conditions are properly OK.
					Item new_item = new Item(shoppingBasket.getItem(i).getItemName(),shoppingBasket.getItem(i).getCompartment(),meatsComportment.getFridge_capacity());
					meatsComportment.add(new_item);
					
					meatsComportment.decreaseFridge_capacity(meatsComportment.getFridge_capacity());
					shoppingBasket.increaseFridge_capacity(meatsComportment.getFridge_capacity());
				}
				else{
					System.out.println(" There is no place in the fridge for that amount. ");
				}
				
			}else if(shoppingBasket.getItem(i).getCompartment().equals("beverages")){ //checking beverages compartment

				if(bevaragesCompartment.getFridge_capacity() >= amount){
					shoppingBasket.transferTo(bevaragesCompartment,shoppingBasket.getItem(i) );	//transferring
					bevaragesCompartment.decreaseFridge_capacity(amount);	//decrease that compartment capacity
					shoppingBasket.increaseFridge_capacity(amount);		//increase basket capacity
					
				}
				else if(bevaragesCompartment.getFridge_capacity() >= 0 && 
				bevaragesCompartment.getFridge_capacity() < amount){
					
					//if the amount is bigger than compartment and compartment's capacity is not 0
					//getting that item when the conditions are properly ok.
					Item new_item = new Item(shoppingBasket.getItem(i).getItemName(),shoppingBasket.getItem(i).getCompartment(),bevaragesCompartment.getFridge_capacity());
					bevaragesCompartment.add(new_item);
					
					bevaragesCompartment.decreaseFridge_capacity(bevaragesCompartment.getFridge_capacity());
					shoppingBasket.increaseFridge_capacity(bevaragesCompartment.getFridge_capacity());
				}
				else{
					System.out.println(" There is no place in the fridge for that amount. ");
				}
				
			}else if(shoppingBasket.getItem(i).getCompartment().equals("snacks")){	//checking snacks compartment

				if(snacksCompartment.getFridge_capacity() >= amount){
					shoppingBasket.transferTo(snacksCompartment,shoppingBasket.getItem(i) );	//transferring
					snacksCompartment.decreaseFridge_capacity(amount);	//decrease that compartment capacity
					shoppingBasket.increaseFridge_capacity(amount);		//increase basket capacity
				
				}
				else if(snacksCompartment.getFridge_capacity() >= 0 && 
				snacksCompartment.getFridge_capacity() < amount){
					
					//if the amount is bigger than compartment and compartment's capacity is not 0
					//getting that item when the conditions are properly ok.
					Item new_item = new Item(shoppingBasket.getItem(i).getItemName(),shoppingBasket.getItem(i).getCompartment(),snacksCompartment.getFridge_capacity());
					snacksCompartment.add(new_item);
					
					snacksCompartment.decreaseFridge_capacity(snacksCompartment.getFridge_capacity());
					shoppingBasket.increaseFridge_capacity(snacksCompartment.getFridge_capacity());
				}
				else{
					System.out.println(" There is no place in the fridge for that amount. ");
				}
			}
		}
							
						
	}	
	
	// deleting all items from basket
	static void discardBasket() {
		for(int i = shoppingBasket.getItemCount() - 1; i >= 0; i--) {
			Item for_remove = shoppingBasket.remove(); // removing last item
			shoppingBasket.increaseFridge_capacity(for_remove.getWeight()); // increasing basket capacity
			
		}
	}

	// Asking user that he or she wants to see the compartments status
	static void checkCompartmentStatus() {
		System.out.println("* Would you like to see your fridge capacity? 'yes' or 'no' ");
		
		while(true){
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String check_fridge_input = sc.nextLine();
			if (check_fridge_input.equals("yes")) { // if user types yes than showing compartment status
				System.out.println("[0]  Vegetables and Fruits		" + 
						vegetablesFruitsCompartment.getFridge_capacity());
			
				System.out.println("[1]  Meats				" + 
						meatsComportment.getFridge_capacity());
				
				System.out.println("[2]  Beverages          		" + 
						bevaragesCompartment.getFridge_capacity());
				
				System.out.println("[3]  Snacks          			" + 
						snacksCompartment.getFridge_capacity());

				break;
			}else if (check_fridge_input.equals("no")){ // if user does not want to see than do nothing
				break;
			}else{ // if user did not type 'yes' or 'no' than program asks him or her to type again.
				System.out.println(" Please enter a valid option. ");
			}
		}
	}
	
	// for checking all compartments that have some space.
	static boolean compartmentIsFull() { // checking all compartment capacities
		if(vegetablesFruitsCompartment.getFridge_capacity() == 0 && meatsComportment.getFridge_capacity() == 0 
				&& bevaragesCompartment.getFridge_capacity() == 0 && snacksCompartment.getFridge_capacity() == 0) {
			return true;
		}
		return false;
	}

	
	static void App() throws IOException{
		boolean done = false;
		boolean is_compartment_full = false;
		// this is kind of a main function, so programs runs till user finish the shopping
		while(!done){ 
			shopping(); // calling shopping function to shop
			System.out.println("\n Now, items from basket are transferring to fridge. (YOU ARE AT HOME)");
			// transferring basket items to user fridge.
			addingToFridge();
			// Deleting all items from basket.
			discardBasket();
			// checking compartments status
			checkCompartmentStatus();
			is_compartment_full = compartmentIsFull();
			if(is_compartment_full == true){
				System.out.println("All compartments are full!");
				System.out.println("See you later :)");
				break;
			}
			System.out.println("* Do you want to continue with the purchase? 'yes' or 'no'");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			// checking that user wants to continue shopping or not
			while(true){
				String check_continue = sc.nextLine(); // getting input
				if (check_continue.equals("no")) { // user does not want to continue shopping
					System.out.println(" See you later :) ");
					done=true;
					break;
				}else if(check_continue.equals("yes")){ // user wants to continue shopping
					break;
				}else{ // if input is not 'yes' or 'no' than we ask him type again.
					System.out.println(" Please enter a valid option. ");
				}
			}
		}
	}
}
