/**
 * 
 */
package simobjects.algorithms.gsp.helper;

import java.util.Vector;

/**
 * @author chris
 *
 */
public class Request {

	private int serverId;
	private Vector <Integer> jobIds;
	
	/**
	 * 
	 */
	public Request(int serverId) {
		this.serverId	= serverId;	
		this.jobIds		= new Vector <Integer> ();
	}


	public Vector<Integer> getJobIds() {
		return jobIds;
	}

	public void setJobIds(Vector<Integer> jobIds) {
		this.jobIds = jobIds;
	}

	public int getServerId() {
		return serverId;
	}
	
	public void addJobId (int jobId){
		jobIds.add(jobId);
	}

	
	
}
