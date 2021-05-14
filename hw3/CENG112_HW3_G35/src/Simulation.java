// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public class Simulation{
	
	IList<Process> processList;		// process list for Simulation
	PriorityQueueInterface<Computation> priorityQueue; // priority queue for Simulation
	
	public Simulation() {	// constructor with empty parameters
		this.processList = new List<Process>();	// initialize process list
		this.priorityQueue = new PriorityQueue<Computation>();	// initialize priority queue
	}

	public Simulation(List<Process> processList, PriorityQueueInterface<Computation> priorityQueue) { // constructor of simulation
		this.processList = processList;// initialize process list
		this.priorityQueue = priorityQueue;	// initialize priority queue
	}

	public IList<Process> getProcessList() { // getter for process list
		return processList;
	}

	public void setProcessList(IList<Process> processList) { // setter for process list
		this.processList = processList;
	}

	public PriorityQueueInterface<Computation> getPriorityQueue() { // getter for priorty queue
		return priorityQueue;
	}

	public void setPriorityQueue(PriorityQueueInterface<Computation> priorityQueue) { // setter for priority queue
		this.priorityQueue = priorityQueue;
	}

	
	
}
