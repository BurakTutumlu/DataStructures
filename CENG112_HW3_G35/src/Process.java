// G35 -
// 250201039 - Burak TUTUMLU
// 250201046 - Bekir YÖRÜK

public class Process implements IProcess{
    private int priority;	// field for priority of the process
    private String type;	// field for type of the process
    	
    public Process() {	// constructor with empty parameter
		this.priority = -1;	// int value will be -1
    	this.type = null;	// String object initialized to null
	}

	public Process(int priority, String type) {	// constructor with given parameters
		this.priority = priority;	// initialize priority
    	this.type = type;		// initialize type
    }
    
	@Override
	public String getType() { // getter for type
		return this.type;
	}
	@Override
	public int getPriority() { // getter for priority
		return this.priority;
	}

	@Override
	public String toString() { // printer for process object
		return "Process priority=" + priority + ", type=" + type;
	}

}
