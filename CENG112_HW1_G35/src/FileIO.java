import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {
	
	public FileIO() {}
	
	public IBag<Item> readItemCSV() {
		try {
			Bag<Item> inventory = new Bag<Item>();	//Create inventory with Items to represent shopping mall
			File csv = new File("inventory.txt");			//reading txt file
			BufferedReader bf = new BufferedReader(new FileReader(csv));
			String line = bf.readLine(); // reading next line from file.
			while(line!=null) {
				String[] items = line.split(","); 	// splitting file line into parts, 
				Item item = new Item(items[0],items[1],Integer.valueOf(items[2])); // Now we can create Item objects.
				inventory.add(item);	//adding items to inventory
				line=bf.readLine();		//getting next line as string
			}
			bf.close(); // we turn it off after all the executions in the file.
			return inventory; // returning Item array object
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

