//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046
public class Main {
	static BinarySearchTree<Food> bst_foodsMain = new BinarySearchTree<Food>(1);
	static BinarySearchTree<Restaurant> bst_restaurantMain = new BinarySearchTree<Restaurant>(0);
	public static void main(String[] args) {
		FileIO fileIO = new FileIO();
		LinkedList<Food> foods = fileIO.readItemCSV(); // reading csv file
		BinarySearchTree<Food> bst_foods = new BinarySearchTree<Food>(1);
		BinarySearchTree<Food> bst_foods3 = new BinarySearchTree<Food>(3);
		

		BinarySearchTree<Restaurant> bst_restaurant = new BinarySearchTree<Restaurant>(0);
		BinarySearchTree<Restaurant> bst_restaurant2 = new BinarySearchTree<Restaurant>(2);
		Food food = new Food();
		while(!foods.isEmpty()){
			food = foods.remove(1); // getting foods from file
			bst_foods.add(food); // adding foods to the binary search tree
			bst_restaurant.add(food.getRestaurant()); // adding foods from the food
			if(food.getRestaurant().getCuisine().strip().equals("Pizza")){ // adding pizza restaurants
				bst_restaurant2.add(food.getRestaurant());
			}
			if(food.getRestaurant().getCuisine().strip().equals("Coffee")){ // adding coffe cuisines
				bst_foods3.add(food);
			}
			
			bst_foodsMain.add(food); // adding foods to bst for other operations
			
		}

		// Printing the operations result
		System.out.println("---------------1---------------");
		printRestaurant(bst_restaurant);
		
		System.out.println("---------------2---------------");
		printFoods(bst_foods);
	
		System.out.println("---------------3---------------");
		System.out.println(bst_restaurant2.getMaximum().getData().getName().strip() + " ".repeat(35-(bst_restaurant2.getMaximum().getData().getName().length())) + bst_restaurant2.getMaximum().getData().getDeliveryTime());
		
		System.out.println("---------------4---------------");
		System.out.println(bst_foods3.getMinimum().getData().getName().strip() + " ".repeat(34-(bst_foods3.getMinimum().getData().getName().length())) + bst_foods3.getMinimum().getData().getStock());
		
		System.out.println("---------------5---------------");
		deleteFoods(bst_foodsMain);
		
		System.out.println("---------------6---------------");
		getRestaurants(bst_foodsMain);
		deleteRestaurants(bst_restaurantMain);
		
		System.out.println("---------------7---------------");	
		updatePrice(bst_foodsMain, 1.2);
		System.out.println("Prices in FoodBSTs are updated.");
		
		System.out.println("---------------8---------------");
		updateStock(bst_foodsMain,2);
		System.out.println("Stocks in FoodBSTs are updated.");
		
		System.out.println("---------------9---------------");
		printRestaurant(bst_restaurantMain);
		
		System.out.println("---------------10---------------");
		printFoodsInReverse(bst_foodsMain);
	}

	private static void updatePrice(BinarySearchTree<Food> bst, double price){ // update the price of foods
		BinaryNode<Food> rootNode = bst.getRootNode(); // get root node
		updatePrice(rootNode,price);
	}
	private static void updatePrice(BinaryNode<Food> node, double price){ // traverse tree
	
		if(node != null){
			updatePrice(node.getLeftChild(), price); // get left child

			node.getData().updatePrice(node.getData().getPrice()*price); // update price
			updatePrice(node.getRightChild(), price); // get right child
		}
	}
	
	private static void updateStock(BinarySearchTree<Food> bst, int quantity){ // update the stock of food
		BinaryNode<Food> rootNode = bst.getRootNode(); // get root node
		updateStock(rootNode,quantity);
	}
	private static void updateStock(BinaryNode<Food> node, int quantity){ // traverse tree
	
		if(node != null){
			updateStock(node.getLeftChild(), quantity); // get left child
			node.getData().updateStock((int) node.getData().getStock()/quantity); // update Stock
			updateStock(node.getRightChild(), quantity); // get right child
		}
	}


	private static void getRestaurants(BinarySearchTree<Food> bst){ // get restaurants
		BinaryNode<Food> rootNode = bst.getRootNode(); // get root node
		getRestaurants(rootNode);
	}
	private static void getRestaurants(BinaryNode<Food> node){ // traverse tree
	
		if(node != null){
			getRestaurants(node.getLeftChild());	// gets left
			bst_restaurantMain.add(node.getData().getRestaurant()); // add restaurant to the other bst
			getRestaurants(node.getRightChild());	// gets right
		}
	}

	private static void deleteFoods(BinarySearchTree<Food> bst){ // delete Foods
		BinaryNode<Food> rootNode = bst.getRootNode();	// gets root node
		deleteFoods(rootNode);
	}

	private static void deleteFoods(BinaryNode<Food> node){ // traverse tree
	
		if(node != null){
			deleteFoods(node.getLeftChild());	// gets left
			if(node.getData().getPrice() > 80){ // check if the food is the one that we are looking for
				Food removedNode = bst_foodsMain.remove(node.getData()); // remove food
				System.out.println(removedNode.getName().strip() + " ".repeat(34-(removedNode.getName().length())) + removedNode.getPrice() + " ".repeat(20-(Double.toString(removedNode.getPrice()).length())) + "removed");
			}
			deleteFoods(node.getRightChild());	// gets right
		}
	}


	private static void deleteRestaurants(BinarySearchTree<Restaurant> bst){ // delete Restaurant
		BinaryNode<Restaurant> rootNode = bst.getRootNode();	// gets root node
		deleteRestaurants(rootNode);
	}

	private static void deleteRestaurants(BinaryNode<Restaurant> node){ // traverse tree
	
		if(node != null){
			deleteRestaurants(node.getLeftChild());	// gets left
			if(node.getData().getRating() < 8.0){// check if the restaurant is the one that we are looking for
				Restaurant removedNode = bst_restaurantMain.remove(node.getData()); // remove restaurant
				System.out.println(removedNode.getName().strip() + " ".repeat(35-(removedNode.getName().length())) + removedNode.getRating() + " ".repeat(20-(Double.toString(removedNode.getRating()).length())) + "removed");
			}
			deleteRestaurants(node.getRightChild());	// gets right
		}
	}


	private static void printRestaurant(BinarySearchTree<Restaurant> bst){ // print restaurant bst
		BinaryNode<Restaurant> rootNode = bst.getRootNode();	// gets root node
		printRestaurant(rootNode);
	}

	private static void printRestaurant(BinaryNode<Restaurant> node){ // traverse tree
	
		if(node != null){
			printRestaurant(node.getLeftChild());	// gets left
			System.out.println(node.getData().getName().strip() + " ".repeat(35-(node.getData().getName().length())) + node.getData().getRating());
			printRestaurant(node.getRightChild());	// gets right
		}
	}
	

	private static void printFoods(BinarySearchTree<Food> bst){ // print foods bst
		BinaryNode<Food> rootNode = bst.getRootNode(); // gets root node
		printFoods(rootNode);
	}
	
	private static void printFoods(BinaryNode<Food> node){ // traverse tree
	
		if(node != null){
			printFoods(node.getLeftChild());	// gets left
			System.out.println(node.getData().getName() + " ".repeat(34-(node.getData().getName().length())) + node.getData().getPrice() + " ".repeat(20-Double.toString(node.getData().getPrice()).length()) + node.getData().getStock());
			printFoods(node.getRightChild());	// gets right
		}
	}
	
	// prints reverse order of the foods
	private static void printFoodsInReverse(BinarySearchTree<Food> bst){
		BinaryNode<Food> rootNode = bst.getRootNode();	// gets root node
		printFoodsInReverse(rootNode);
	}
	
	// prints reverse order of the foods
	private static void printFoodsInReverse(BinaryNode<Food> node){
	
		if(node != null){
			printFoodsInReverse(node.getRightChild());	// gets left child
			System.out.println(node.getData().getName() + " ".repeat(34-(node.getData().getName().length())) + node.getData().getPrice() + " ".repeat(20-Double.toString(node.getData().getPrice()).length()) + node.getData().getStock());
			printFoodsInReverse(node.getLeftChild());	// gets right child
		}
	}


}

		