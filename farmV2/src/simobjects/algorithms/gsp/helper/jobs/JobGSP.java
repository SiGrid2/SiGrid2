package simobjects.algorithms.gsp.helper.jobs;

import java.util.Collections;
import java.util.Vector;

import simobjects.Job;
import simobjects.Server;
import simobjects.algorithms.gsp.helper.server.ServerPref;

/**
 * @author Christoph Beck
 *
 */
public class JobGSP {
	private Vector<ServerPref> serverPref;
	private int assignedTo;
	private Job job;

	/**
	 * constructor for class JobGSP
	 * @param job
	 */
	public JobGSP (Job job) {
		this.serverPref = new Vector<ServerPref>();
		this.job = job;
		assignedTo = -1;
	}

	/**
	 * @return
	 */
	public int size () {
		return serverPref.size();
	}

	/**
	 * @param version
	 * @return
	 */
	public synchronized Server getNextToAsk (int version) {
		if(assignedTo>= 0) {
			return null;
		}
		if(serverPref==null){
			return null;
		}
		if(serverPref.size()==0){
			return null;
		}
		if(serverPref.firstElement().getVersion()< version) {
			return serverPref.firstElement().getServer();
		}
		return null;
	}

	/**
	 * @return
	 */
	public synchronized int getVersion () {
		return  serverPref.firstElement().getVersion();
	}
	
	/**
	 * @param version
	 * @return
	 */
	public synchronized boolean active (int version) {
		if(getNextToAsk(version)==null) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param version
	 */
	public synchronized void instantReject (int version) {
		this.assignedTo=-1;
		serverPref.firstElement().setVersion(version);
		resort();
	}
	
	/**
	 * @param idServer
	 * @param version
	 */
	public synchronized void lateReject (int idServer, int version) {
		this.assignedTo=-1;
		serverPref.firstElement().setVersion(version);
		resort();
	}
	
	/**
	 * 
	 */
	public synchronized void accepted () {
		this.assignedTo=serverPref.firstElement().getServer().getId();
		System.out.println("+-+>assignedTo="+serverPref.firstElement().getServer().getId());
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void resort() {
		Collections.sort (serverPref);	
	}
	
	/**
	 * @param newSP
	 */
	public void add (ServerPref newSP) {
		serverPref.add(newSP);
		resort();
	}

	/**
	 * make them order proof
	 */
	public void finalizePref() {
		resort();
		int rank = 0;
		for (ServerPref sp : serverPref) {
			sp.setRank(rank++);
		}
	}
	/**
	 * @return job assoziated 
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job well, ... guess!!
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	
	/**
	 * @return the ids on  the list
	 */
	public int [] getPreflistIDs () {
		int ids [] = new int[serverPref.size()];
		for (int i = 0; i < ids.length; i++) {
			ids [i] = serverPref.get(i).getServer().getId();
		}
		return ids;
	}

	/**
	 * @return true if assigned 
	 */
	public int getAssignedTo() {
		return assignedTo;
	}
	
	/**
	 * dumps the Preflist
	 */
	public void dumpPreflist (){
		System.out.print("Job #"+job.getId()+"\t");
		for (ServerPref pref : serverPref) {
			System.out.print(""+pref.getServer().getId()+"\t");
		}
		System.out.println();
	}
}
