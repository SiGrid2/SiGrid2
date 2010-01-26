package simobjects;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;


/**
 * annotated class, representing an farm in sigrid and the DB
 *  
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class SimFarm {


	@Id @GeneratedValue
	@Column(name="simfarm_id")
	private int 		id;
	private long		firstRegAtMs;


	@Transient
	private int			status; // (idle, paused, or running Tr #343)
	private long 		initID;
	private String name;

	@CollectionOfElements
	private List <SimJob> simJob;




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimFarm() {
		simJob = new Vector<SimJob>();		
	}







	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * called if a simjob is queued to a farm, this correlation should be saved in DB 
	 * 
	 * @param simJob
	 */
	public void addSimJob(SimJob simJob){
		this.simJob.add(simJob);
	}

	/**
	 * called if a simjob is removed from queue from a farm, this correlation 
	 * should also be removed from DB 
	 * 
	 * @param simJob
	 */
	public void removeSimJob(SimJob simJob){
		this.simJob.remove(simJob);
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * @return how many milliseconds this simfarm is running
	 */
	public long getUpSinceMS() {
		return System.currentTimeMillis() - firstRegAtMs;
	}





	/**
	 * @return the firstRegAtMs
	 */
	public long getFirstRegAtMs() {
		return firstRegAtMs;
	}



	/**
	 * @param firstRegAtMs the firstRegAtMs to set
	 */
	public void setFirstRegAtMs(long firstRegAtMs) {
		this.firstRegAtMs = firstRegAtMs;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the initID
	 */
	public long getInitID() {
		return initID;
	}



	/**
	 * @param initID the initID to set
	 */
	public void setInitID(long initID) {
		this.initID = initID;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the simJob
	 */
	public List<SimJob> getSimJob() {
		return simJob;
	}



	/**
	 * @param simJob the simJob to set
	 */
	public void setSimJob(List<SimJob> simJob) {
		this.simJob = simJob;
	}



	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}//end of class
