/**
 * 
 */
package simobjects.algorithms.gsp.helper;

import java.util.Vector;

/**
 * @author chris
 *
 */
public class RequestMap {
	private Vector <Request> requests;
	/**
	 * 
	 */
	public RequestMap() {
		requests = new Vector <Request> ();
	}
	
	public void add (int serverId, int jobId) {
		this.getRequestByServerId(serverId).addJobId(jobId);
	}

	public int[] getServerIds () {
		int [] serverIds = new int [requests.size()] ;
		for (int i = 0; i < serverIds.length; i++) {
			serverIds[i] = requests.get(i).getServerId();
		}
		return serverIds;
	}
	
	public int[] getRequestForServerId (int serverId) {
		Vector <Integer> requestJobs = this.getRequestByServerId(serverId).getJobIds();
		int [] request = new int [requestJobs.size()];

		for (int i = 0; i < request.length; i++) {
			request[i] = requestJobs.elementAt(i).intValue();
		}

		return request;
	}
	private Request getRequestByServerId (int serverId) {
		for (Request request : requests) {
			if (request.getServerId() == serverId) {
				return request;
			}
		}
		return new Request(serverId);
	}
	
	
	
}
