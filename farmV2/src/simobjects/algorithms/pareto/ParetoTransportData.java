package simobjects.algorithms.pareto;

import java.io.Serializable;
import java.util.Vector;

import simobjects.Job;
import simobjects.Server;

/**
 * Transport-Object of Pareto-Data to calculate. 
 * Created by MasterCore, needed from WorkerCore.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ParetoTransportData implements Serializable {	

	private static final long serialVersionUID = -4142072247463849888L;
	private Vector<Job> jobs;
	private Vector<ParetoPoint> paretoPoints;
	private Server server;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public ParetoTransportData(Vector<Job> jobs, Vector<ParetoPoint> paretoPoints, Server server) {
		super();
		this.jobs = jobs;
		this.paretoPoints = paretoPoints;
		this.server = server;
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter & setter - Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @return the vector of SLAs to optimize
	 */
	public Vector<Job> getJobs() {
		return jobs;
	}


	/**
	 * @return vector of formerly calculated paretopoints
	 */
	public Vector<ParetoPoint> getParetoPoints() {
		return paretoPoints;
	}


	/**
	 * @return the storage network the optimisation shall be done on
	 */
	public Server getServer() {
		return server;
	}	


}//end of class
