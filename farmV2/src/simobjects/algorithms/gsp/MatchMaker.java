package simobjects.algorithms.gsp;

import java.util.Vector;

import simobjects.Server;
import simobjects.algorithms.gsp.helper.LayoutGSP;
import simobjects.algorithms.gsp.helper.jobs.JobGSP;
import simobjects.algorithms.gsp.helper.server.ServerGSP;

public class MatchMaker {
	private LayoutGSP 	layout;
	private JobGSP []	jobs;
	private ServerGSP aktServer;

	private ServerGSP[]	server;
	private int version;
	private boolean DEBUG=true;
	private Vector <Integer> requestJobs;
	private boolean speedUp;

	/**
	 * The Request scheduler
	 * @param layout 
	 */
	public MatchMaker(LayoutGSP layout) {
		this.layout = layout;

		jobs 	= new JobGSP [layout.getGspJobs().size()];
		for (int i 	= 0; i < jobs.length; i++) {
			jobs[i] = layout.getGspJobs().get(i);
		}

		server 	= new ServerGSP [layout.getGspServers().size()];
		for (int i = 0; i < server.length; i++) {
			server[i] = layout.getGspServers().get(i);
		}

		version = 0;
		requestJobs = new Vector<Integer>();
		speedUp = layout.isSpeedup();
	}

	/**
	 *  the matching Process
	 */
	public void match() {
/*
 * this is not very effective: it would be better to build a Matrix of all 
 * upcoming requests before actUally issuing them. To do this, all unassigned 
 * Jobs are asked if there are servers not yet asked. 
 * Then the requests could be issued server by server, not job by Job,
 * resulting in far less requests.
 * 
 */

		Server nextSrv;
		// set version counters
		version = 0;
		int oldversion = -2;

		// as long as the mapping (and thus, the version) is changed
		while(oldversion != version) {
			oldversion = version;

			// go through all jobs
			for (JobGSP actJob : jobs) {
				if(actJob.getNextToAsk(oldversion)!=null) {
					System.out.println("-->actJob="+actJob.getJob().getId()+" assignedTo="+actJob.getAssignedTo()+" NextToAsk="+actJob.getNextToAsk(oldversion));
					addFirstJobToRequest(actJob);
					// repeat as long, as the server has not asked every server on his list
					while((nextSrv=actJob.getNextToAsk(version))!=null){
						System.out.println("while((nextSrv=actJob.getNextToAsk(version))!=null)");
						aktServer = layout.getServerGSPByID(nextSrv.getId());
						// for speedup: see if other Jobs would ask the same server
						if(speedUp) {
							for (JobGSP theOtherJob: jobs) {
								if (sameServerToAsk(actJob, theOtherJob)) {
									addToRequest (theOtherJob);
									System.out.print("theOtherJob"+theOtherJob.getJob().getId());
								}
							}
						}


						if(handleRequest( new Request (getRequest(), aktServer))){
							version ++;
						}

					}
				}
				
			System.out.println("+++>Version="+version);	
				// either Job has asked every server on his list, or has been accepted
			}
			// match() has now gone through all jobs - if no change has happened, finish.
		}

	}

	/**
	 *  the matching Process
	 */
	public void speedmatch() {
/*
 * this is not very effective: it would be better to build a Matrix of all 
 * upcoming requests before actUally issuing them. To do this, all unassigned 
 * Jobs are asked if there are servers not yet asked. 
 * Then the requests could be issued server by server, not job by Job,
 * resulting in far less requests.
 * 
 */

		Server nextSrv;
		// set version counters
		version = 0;
		int oldversion = -2;

		
		// as long as the mapping (and thus, the version) is changed
		while(oldversion != version) {
			oldversion = version;

			// go through all jobs
			for (JobGSP actJob : jobs) {
				if(actJob.getNextToAsk(oldversion)!=null) {
					System.out.println("-->actJob="+actJob.getJob().getId()+" assignedTo="+actJob.getAssignedTo()+" NextToAsk="+actJob.getNextToAsk(oldversion));
				}
				else {
					System.out.println("==>actJob="+actJob.getJob().getId()+" assignedTo="+actJob.getAssignedTo()+" NextToAsk=NULL");
				}
				
			
				// either Job has asked every server on his list, or has been accepted
			}
			// match() has now gone through all jobs - if no change has happened, finish.
			System.out.println("+++>Version="+version+" Oldv+ersion="+oldversion);	
		}

	}

	/**
	 * @param actJob
	 * @param theOtherJob
	 */
	private boolean sameServerToAsk(JobGSP aktJob, JobGSP theOtherJob) {

		if(theOtherJob.getNextToAsk(version)==null) return false;
		if(aktJob==theOtherJob) return false;

		if(aktJob.getNextToAsk(version)!=theOtherJob.getNextToAsk(version)) {
			return false;
		}

		return true;
	}

	private boolean handleRequest(Request request) {
		System.out.println("##########################################################");
		request.dumpRequest("Mapping Request");
		if(request.isSuccess()){
			if(DEBUG ){
				System.out.println("Success!!!");
			}

			rejectJobs(request.getRejectedJobIds());
			assignJobs(request.getAcceptedJobIds());
			unassignJobs(request.getUnassignedJobIds());
			return true;

		}
		else {
			if(DEBUG ){
				System.out.println("failure!!!");
			}

			rejectJobs(request.getRejectedJobIds());
			return false;
		}
	}

	private void assignJobs(int[] assignedJobIds) {
		for (int i = 0; i < assignedJobIds.length; i++) {
			assign(assignedJobIds[i], aktServer.getServer().getId());
		}

	}

	private void unassignJobs(int[] unassignedJobIds) {
		for (int i = 0; i < unassignedJobIds.length; i++) {
			unassign(unassignedJobIds[i], aktServer.getServer().getId());
		}
	}

	private void rejectJobs(int[] rejectedJobIds) {
		for (int i = 0; i < rejectedJobIds.length; i++) {
			reject(rejectedJobIds[i], aktServer.getServer().getId());
		}
	}



	public void assign (int jobID, int serverID) {
		if(DEBUG ){
			System.out.println("Job #"+jobID+" is now assigned to server "+serverID);
		}

		layout.getServerGSPByID(serverID).assign(jobID);
		layout.getJobGSPByID(jobID).accepted();
	}

	public void reject (int jobID, int serverID) {
		layout.getJobGSPByID(jobID).instantReject(version);
	}

	public void unassign (int jobID, int serverID) {
		if(DEBUG ){
			System.out.println("Server id="+serverID+ " unassigns job ID="+jobID);
		}

		layout.getServerGSPByID(serverID).unassign(jobID);

		layout.getJobGSPByID(jobID).lateReject(serverID, version);
	}

	public void addFirstJobToRequest (JobGSP requestJob) {
		requestJobs = new Vector<Integer>();
		requestJobs.add(requestJob.getJob().getId());
	}

	public void addToRequest (JobGSP requestJob) {
		requestJobs.add(requestJob.getJob().getId());
	}

	public int[] getRequest () {
		int [] request = new int [requestJobs.size()];

		for (int i = 0; i < request.length; i++) {
			request[i] = requestJobs.elementAt(i).intValue();
		}

		return request;
	}

	public boolean isSpeedUp() {
		return speedUp;
	}

	public void setSpeedUp(boolean speedUp) {
		this.speedUp = speedUp;
	}
}
