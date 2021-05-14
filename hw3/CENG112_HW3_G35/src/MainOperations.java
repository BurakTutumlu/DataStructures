// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

import java.util.Random;

public class MainOperations {
	private static int computation_id = 0;	
	private static int simulation_id = 1;
	
    public static Simulation createSimulation(int processNumber){// creating simulation object
    	
		IList<Process> processList; // creating a processList from IList
		PriorityQueueInterface<Computation> computationQueue;	// creating a computation queue from PriorityQueueInterface
		Simulation simulation = new Simulation(); // creating simulation object
		int processCount; // number of process in the simulation
		
		if(processNumber==0 ){ // if process number equal  to 0 then create 3 process
			processCount = 3;
		}else if(processNumber==1){// if process number equal  to 1 then create 5 process
			processCount = 5;
		}else{// if process number equal  to 2 then create 10 process
			processCount = 10;
		}
		processList = sortedProcessList(loopProcess(processCount)); //a sorted processList with processCount

		computationQueue = createComputation(processCount,processList); // create computation queue from processLsit

		simulation.setPriorityQueue(computationQueue);// set computation queue of simulation
		simulation.setProcessList(processList); // set process list of simulation
		printResult(computationQueue); // print result table
		simulation_id++; // increment number of simulation
		return simulation; // return simulation
 
	}

	private static IList<Process> sortedProcessList(IList<Process> processList) { // sort processList with their priorities
		IList<Process> new_processList = new List<Process>(); // create new processList
		int priority = 1; // sorting with 1 priority (highest), then increment it after finishing with this
		int numberOfProcess = processList.getLength(); // get number of process in processList
		while(priority <=3){ // sort till the lowest priortiy
			for(int i = 1;i < numberOfProcess + 1 ; i++){
				if(processList.getEntry(i).getPriority()==priority){ // check if the priority equals to current priority
					new_processList.add(processList.getEntry(i));// add current highes priority to sorted processList
				}
			} 
			priority++; // increment current priority (to lower)
		}
		return new_processList; //return sorted processList
	}

	public static PriorityQueueInterface<Computation> createComputation(int n, IList<Process> processList){ // create computation queue from processList
		PriorityQueueInterface<Computation> computationQueue = new PriorityQueue<Computation>(); // create new computation queueu from priority queue interface
		Random rand = new Random(); // for occupation time
		for(int i = 1; i < n+1 ; i++){
				Process process = processList.getEntry(i); // getting process from process List
				Computation computation = new Computation(computation_id,process,rand.nextInt(10) + 1); // create a computation with the process and random occupation time
				computationQueue.add(computation);// add computation to computation queue
				computation_id++;//increment computation id

		}
		return computationQueue;//return computation queueu
	}

	public static Process createProcess(int priority){ // create process from given priortiy
		Process process;
		if(priority == 1) { // if given priority equal to 1 then create process with high priority
			process = new Process(1, "High");
		}else if(priority == 2){// if given priority equal to 2 then create process with normal priority
			process = new Process(2, "Normal");
		}else{// if given priority equal to 3 then create process with low priority
			process = new Process(3, "Low");
		}

		return process; // return process
	}

	public static IList<Process> loopProcess(int loopCount) { // create processList
		IList<Process> processList = new List<Process>(); // create a processList from IList
		Random rand = new Random(); // for random priority
		for(int i = 0; i < loopCount; i++){
			int priority = rand.nextInt(3) + 1;	// takes the random priority
			processList.add(createProcess(priority));	// add created process to the process list
		}
		return processList; // return processList
	}
	
	public static void printResult(PriorityQueueInterface<Computation> computationQueue){

			IList<Computation> high = new List<Computation>(); // list for high computations
			IList<Computation> normal = new List<Computation>();	// list for normal computations
			IList<Computation> low = new List<Computation>();	// list for low computations
			IList<Computation> allComputation = new List<Computation>();	// all computations in a list		
			
			int computationCount = computationQueue.getSize();	// takes the size of the computation queue
			for(int i=0; i < computationCount;i++){
				Computation computation = computationQueue.remove();	// removes computations from the queue
				//System.out.println(computation);
				if(computation.getProcess().getPriority() == 1){	// if priority is 1, that means high
					high.add(computation);	// add to the high list
				}else if(computation.getProcess().getPriority() == 2){	// if priority is 2, that means normal
					normal.add(computation);	// add to the normal list
				}else if(computation.getProcess().getPriority() == 3){	// if priority is 3, that means low
					low.add(computation);	// ad to the low list
				}
			}
			int highCount = high.getLength();	// takes the length of the high list
			int normalCount = normal.getLength();	// takes the length of the normal list
			int lowCount = low.getLength();	// takes the length of the low list

			System.out.println("Simulation Number: " + simulation_id); 
			System.out.print("Computation Queue: ");
			int waiting_time_total = 0; // total waiting time
			int waiting_time_high = 0;	// waiting time for high
			int waiting_time_normal = 0;	// waiting time for normal
			int waiting_time_low = 0;	// waiting time for low

			for(int i=0; i<high.getLength();i++){	// go over in high list
				Computation comp = high.getEntry(i+1);	// takes the elements in high list
				System.out.print("P"+comp.getId()+ "," + 
						comp.getProcess().getType()+ ","  + comp.getOccupation()+ "ns <- " );
				allComputation.add(comp);	// adds to the all computations list
			}
			for(int i=0; i<normal.getLength();i++){	// go over in normal list
				Computation comp = normal.getEntry(i+1);	// takes the elements in normal list
				System.out.print("P"+comp.getId()+ "," + 
						comp.getProcess().getType()+ ","  + comp.getOccupation()+ "ns <- " );
				allComputation.add(comp);	// adds to the all computations list

			}
			for(int i=0; i<low.getLength();i++){	// go over in low list
				Computation comp = low.getEntry(i+1);	// takes the elements in low list
				System.out.print("P"+comp.getId()+ "," + 
						comp.getProcess().getType()+ ","  + comp.getOccupation()+ "ns <- " );
				allComputation.add(comp);	// adds to the all computations list
			}
			System.out.println();
			System.out.println("Total number of computation: " + computationCount); // print process 


			for(int i = 1; i < allComputation.getLength(); i++){	// go over in all computations
				waiting_time_total+=allComputation.getEntry(i).getOccupation();	// increase total waiting time
			}
			if (highCount >= 2) {
				for(int i = 1; i < highCount ; i++){
					waiting_time_high+=high.getEntry(i).getOccupation();	// if high count >= 2
				}														// increase waiting time for high
			}
			
			if (normalCount >= 2) {
				for(int i = 1; i < normalCount ; i++){
					waiting_time_normal+=normal.getEntry(i).getOccupation();	// if normal count >= 2
				}														// increase waiting time for normal
			}
			waiting_time_normal += waiting_time_high; // increase waiting time normal with high waiting time
			if (highCount > 0)
				waiting_time_normal += high.getEntry(highCount).getOccupation();	//increase normal waiting time
			
			if (normalCount == 0) 
				waiting_time_normal = 0;
			
			if (lowCount >= 2) {
				for(int i = 1; i < lowCount ; i++){
					waiting_time_low += low.getEntry(i).getOccupation();	// if low count >= 2
				}														// increase waiting time for low
			}
			waiting_time_low += waiting_time_normal;	// increase low waiting time
			
			if (normalCount > 0)
				waiting_time_low += normal.getEntry(normalCount).getOccupation();	// increase low waiting time
			
			if (lowCount == 0) 
				waiting_time_low = 0;
			
			System.out.println("\nTotal waiting time: " + waiting_time_total);
			System.out.println("Average waiting time: " + waiting_time_total / computationCount);
			
			System.out.println("Total number of computations for high: " + highCount);
			System.out.println("Total number of computations for normal: " + normalCount);
			System.out.println("Total number of computations for low: " + lowCount);
			
			System.out.println("\nTotal waiting time for High: " + waiting_time_high);
			System.out.println("Average waiting time for High: " + waiting_time_high/(highCount > 0 ? highCount: 1));
			
			System.out.println("\nTotal waiting time for Normal: " + waiting_time_normal);
			System.out.println("Average waiting time for Normal: "+ waiting_time_normal/ (normalCount > 0 ? normalCount: 1));
			
			System.out.println("\nTotal waiting time for Low: " + waiting_time_low);
			System.out.println("Average waiting time for Low: " + waiting_time_low/(lowCount > 0 ? lowCount : 1));
			System.out.println("-------------------------------------------------------"
					+ "-----------------------------------------------------");
	}
}
