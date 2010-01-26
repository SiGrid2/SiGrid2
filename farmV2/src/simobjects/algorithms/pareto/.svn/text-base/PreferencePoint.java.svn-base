package simobjects.algorithms.pareto;

import simobjects.Job;
import simobjects.Server;

/**
 * This class shows the relation between a sla and an storage network. The earning for that combination is stored.
 * A PreferencePoint can be available or not. Class is used by class PreferenceList in the context of Pareto-Optimization.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class PreferencePoint {
	
	private Job job;
	private Server server;
	private long earning;
	private boolean available = true;
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public PreferencePoint(Server server, Job job, long earning) {
		super();
		this.server = server;
		this.job = job;
		this.earning = earning;
	}
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter & setter - Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**	  
	 * @return the storage network of this PreferencePoint
	 */
	public Server getServer() {
		return server;
	}	

	
	/**
	 * @return the earning of the given Sla-StorageNetwork mapping. 
	 */
	public long getEarning() {
		return earning;
	}
	

	/**
	 * @return the sla
	 */
	public Job getJob() {
		return job;
	}
	

	/**
	 * @return a boolean if this PreferencePoint is available. Parameter controlled by {@link PreferenceList}.
	 */
	public boolean isAvailable() {
		return available;
	}
	

	/**
	 * @param available Parameter controlled by {@link PreferenceList}.
	 */
	protected void setAvailable(boolean available) {
		this.available = available;
	}	

}//end of class
