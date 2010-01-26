package simobjects.algorithms.pareto;

import java.io.Serializable;
import java.util.Vector;
import simobjects.Job;
import simobjects.Layout;
import simobjects.Server;


/**
 * A ParetoPoint holds a list of SLAs, their cumulated dasd, the maximal endtime of all SLAs
 * and the earning of all SLAs on the one given Storage Network.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ParetoPoint implements Serializable{

	private static final long serialVersionUID = -4176716054271632328L;
	private long  earning = 0;
	private int   endTime = 0;	
	private int   jobNeededDasd = 0;
	private Server server = null;
	private Vector<Job> jobs = new Vector<Job>();	


	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @param server the Storage Network the ParetoPoint belongs to
	 */
	public ParetoPoint(Server server) {
		super();
		this.server = server;
	}
	
	
	
	/**
	 * called when a new SLA shall be scheduled to an existing ParetoPoint
	 * 
	 * @param pp ParetoPint to extend
	 * @param job the SLA to schedule
	 */
	public ParetoPoint (ParetoPoint pp, Job job){
		this.server = pp.server;
		this.jobs = new Vector<Job>(pp.jobs);
		this.endTime = pp.endTime;
		this.earning = pp.earning;

		
		this.jobNeededDasd = pp.jobNeededDasd;
		this.addJob(job);
	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * helper method: prints infos to console
	 */
	public void printToConsole(){
		System.out.println("server id: " + server.getId());
		System.out.println("endtime: " + endTime);
		System.out.println("earning: " + earning);		
		System.out.println("jobNeededDasd: " + jobNeededDasd);
		System.out.println("scheduled jobs: " + jobs.size());		
	}
	
	
	/**
	 * helper method: prints infos to console
	 */
	public void printJobs() {
		for (Job job: jobs) {
			System.out.print("Jobs: " + job.getId() + " - ");			
		}
		System.out.println();
	}

	

	/**
	 * checks if the paretopoint isValid, means if the scheduled jobs are valid or if
	 * the break a constraint
	 * 
	 * @param l
	 * @return boolean
	 */
	public boolean isValid(Layout l){
		return l.isJobVectorValid(jobs, server.getId());
	}
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter & setter - Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * @return number of scheduled SLAs
	 */
	public int getNumJobs(){
		return jobs.size();
	}


	/**
	 * @return max. endtime of all scheduled SLAs
	 */
	public int getEndTime() {
		return endTime;
	}
	

	/**
	 * @return cumulated needed dasd of all scheduled SLAs
	 */
	public int getJobNeededDasd() {
		return jobNeededDasd;
	}
	
	
	/**
	 * @return earnig of this paretopoint
	 */
	public long getEarning() {
		return earning;
	}


	/**
	 * @return vector with all scheduled SLAs
	 */
	public Vector<Job> getJobs() {
		return jobs;
	}


	/**
	 * @return the storage network the paretopoint belongs to
	 */
	public Server getServer() {
		return server;
	}
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * adds a new SLA to the paretopoint
	 * 
	 * @param job the SLA to add
	 */
	private void addJob(Job job){
		jobs.add(job);
		earning = earning + job.getDasd() * job.getDuration()* (job.getEarning() - server.getCost());
		if (job.getRelCat() < server.getRelCat() || job.getSpeedCat() < server.getSpeedCat()) {
			earning = earning - job.getPenalty();
		}
		
		
		if (job.getStartTime() + job.getDuration() > endTime){
			endTime = job.getStartTime() + job.getDuration();
		}
		jobNeededDasd += job.getDasd() * job.getDuration();
	}	
		
	
}//end of class
