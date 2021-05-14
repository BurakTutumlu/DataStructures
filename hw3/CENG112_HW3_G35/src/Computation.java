// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public class Computation implements Comparable<Process>{
	
	private int id;					// unique computation id in [1,1000]
	private IProcess process;		// the process that makes computation request
	private int occupation;			// needed time for the computation
	
	
	public Computation() {			// Constructor for computation
		this.id = 0;				// initialize id field
		this.process = null;		// initialize process field
		this.occupation = 0;		// initialize occupation field
	}

	public Computation(int id, IProcess process, int occupation) {	// constructor with given arguments
		this.id = id;	
		this.process = process;
		this.occupation = occupation;
	}

	public int getId() {	// getter method for id field
		return id;
	}

	public void setId(int id) {	// setter method for id field
		this.id = id;
	}

	public IProcess getProcess() {	// getter method for process field
		return process;
	}

	public void setProcess(IProcess process) {	// setter method for process field
		this.process = process;
	}

	public int getOccupation() { // getter for occupation field
		return occupation;
	}

	public void setOccupation(int occupation) { // setter for occupation field
		this.occupation = occupation;
	}

	@Override
	public String toString() { // to print computation object with its attributes
		return "Computation id=" + id + ", process=" + process + ", occupation=" + occupation;
	}

	@Override
	public int compareTo(Process o) {// comparing this computation class with another computation class
		return (this.process.getPriority() >= o.getPriority() ? 1: 0); //  compared with priorities and made assignment
	}
		
}
