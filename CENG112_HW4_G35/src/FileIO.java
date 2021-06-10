//G35
//Burak TUTUMLU - 250201039
//Bekir YÖRÜK - 250201046

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {
	
	public FileIO() {}
	
	public LinkedList<Food> readItemCSV() {
		try {
			LinkedList<Food> inventory = new LinkedList<Food>();	//Create inventory linked list
			File csv = new File("CENG112_HW4.csv");			//reading csv file
			BufferedReader bf = new BufferedReader(new FileReader(csv));
			String line = bf.readLine(); // reading next line from file.
			line=bf.readLine();		//getting next line as string
			while(line!=null) {
				String[] items = line.split(",");	// splitting file line into parts,
				// Now we can create items 
                Restaurant restaurant = new Restaurant(items[3],Double.parseDouble(items[4]),items[5],Integer.valueOf(items[6])); 
				Food item = new Food(items[0],Double.parseDouble(items[1]),Integer.valueOf(items[2]),restaurant); 
				inventory.add(item);	//adding items to inventory
				line=bf.readLine();		//getting next line as string
			}
			bf.close(); // we turn it off after all the executions in the file.
			return inventory; // returning item array
		}catch (FileNotFoundException e){ // If file not found then this catch method catches the error.
			System.out.println("File not found");
			return null;
		}
		catch(IOException e) {	//To hold all File IO exceptions
			System.out.println("There is an error while reading");
			return null;
		}
	}
}

