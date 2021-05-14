// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		ILinkedList<Simulation> simulations = new LinkedList<Simulation>(); // simulations list 
		int[] processNumber = {0,1,2}; // array values stands for number of process for a list
										// 0 = 3; 1 = 5; 2 = 10 
		processNumber = RandomizeArray(processNumber); // shuffle the array
		for (int i = 0; i < 3; i++) { // create simulations
			simulations.add(MainOperations.createSimulation(processNumber[i]));
		}
	}

	public static int[] RandomizeArray(int[] array){ // shuffle an array
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) { 
		    int randomPosition = rgen.nextInt(array.length); // get an random position
			
			//	swap items
		    int temp = array[i]; 
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array; // return shuffled array
	}
	

}