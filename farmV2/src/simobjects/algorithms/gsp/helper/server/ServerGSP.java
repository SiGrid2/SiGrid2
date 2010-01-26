package simobjects.algorithms.gsp.helper.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import simobjects.Job;
import simobjects.Server;
import simobjects.algorithms.gsp.helper.jobs.JobPref;

public class ServerGSP {
	private Server 			server;
	private Vector<JobPref> jobs;
	private int 			jobDasds[];
	private boolean 		jobStatus[];
	private int 			jobStartTimes[];
	private int 			jobIDs[];

	/**
	 * constructor for class ServerGSP
	 * @param server
	 */
	public ServerGSP(Server server) {
		this.server = server;
		jobs		= new Vector<JobPref> ();
		
		jobDasds	= null;
		jobStatus	= null;
		jobIDs		= null;
	}

	/**
	 * @param jobPref
	 */
	@SuppressWarnings("unchecked")
	public void add (JobPref jobPref) {
		jobs.add(jobPref);
		Collections.sort(jobs);
		//dumpPreflist();
	}

	/**
	 * @return jobDasds
	 */
	public int []  getJobDasds  () {
		if (jobDasds==null){
			jobDasds= new int[jobs.size()];
			for (int i = 0; i < jobDasds.length; i++) {
				jobDasds[i]=jobs.get(i).getJob().getDasd();
			}
		}
		return jobDasds;
	}

	/**
	 * @return jobStatus
	 */
	public boolean []  getJobStatus  () {
		if (jobStatus==null){
			jobStatus = new boolean[jobs.size()];
		}
		for (int i = 0; i < jobStatus.length; i++) {
			jobStatus[i]=jobs.get(i).isRunning();
		}
		return jobStatus;
	}

	/**
	 * @return jobStartTimes
	 */
	public int[] getJobStartTimes() {
		if(jobStartTimes==null) {
			TreeSet <Integer> startTimesV = new TreeSet<Integer>();

			JobPref aJob;

			for (Iterator iter = jobs.iterator(); iter.hasNext();) {
				aJob = (JobPref) iter.next();
				startTimesV.add(aJob.getJob().getStartTime());
			}

			Integer startTimesI[] 	= startTimesV.toArray(new Integer[0]);
			jobStartTimes = new int[startTimesI.length];

			for (int i = 0; i < jobStartTimes.length; i++) {
				jobStartTimes[i] = startTimesI[i].intValue();
			}
		}
		return jobStartTimes;
	}

	/**
	 * @return jobIDs
	 */
	public int[] getJobIds() {
		if(jobIDs==null) {

			jobIDs= new int[jobs.size()];
			for (int i = 0; i < jobIDs.length; i++) {
				jobIDs[i]=jobs.get(i).getJob().getId();
			}

		}
		return jobIDs;
	}


	/**
	 * @param res
	 */
	public void updateStatus (boolean res[]) {
		for (int i = 0; i < res.length; i++) {
			jobs.get(i).setRunning(res[i]);
		}
	}

	/**
	 * @param jobId
	 */
	public void assign (int jobId) {
		for (JobPref aJob : jobs) {
			if(aJob.getJob().getId()==jobId) {
				aJob.setRunning(true);
			}
		}
	}
	
	/**
	 * @param jobId
	 */
	public void unassign (int jobId) {
		for (JobPref aJob : jobs) {
			if(aJob.getJob().getId()==jobId) {
				aJob.setRunning(false);
			}
		}
	}
	
	/**
	 * @return server
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * @return jobs
	 */
	public Vector<JobPref> getJobs() {
		return jobs;
	}
	
	public void dumpPreflist (){
		NumberFormat formater = new DecimalFormat ("#,###0.0000");
		System.out.print("Server #"+server.getId()+"\t");
		for (JobPref pref : jobs) {
			System.out.print(""+pref.getJob().getId()+"\t");
		}
		System.out.println();
		/*System.out.print("Server #"+server.getId()+"\t");
		for (JobPref pref : jobs) {
			System.out.print(""+formater.format(pref.getServerValues())+"\t");
		}
		System.out.println();
		System.out.print("Server #"+server.getId()+"\t");
		for (JobPref pref : jobs) {
			System.out.print(""+formater.format(pref.getRelativeValues())+"\t");
		}
		System.out.println();
		System.out.print("Server #"+server.getId()+"\t");
		for (JobPref pref : jobs) {
			System.out.print(""+formater.format(pref.getTotalValues())+"\t");
		}
		System.out.println();*/
	}
	
}
