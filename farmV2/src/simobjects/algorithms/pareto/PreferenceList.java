package simobjects.algorithms.pareto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import simobjects.Job;
import simobjects.Server;

/**
 * This class administrates two Maps of PreferenceLists. A PreferenceList is a List where is saved in which order a sla prefers
 * serveral storage networks. 
 * 
 * @author Dirk Holzapfel
 * @version 1.0 
 */
public class PreferenceList {
	
	private Map<Job, Vector<PreferencePoint>> preferenceList = new HashMap<Job, Vector<PreferencePoint>>();
	private Map<Job, Vector<PreferencePoint>> preferenceListDump = new HashMap<Job, Vector<PreferencePoint>>();
	private Map<Server, Vector<Job>> lastOutgoingPackage = new HashMap<Server, Vector<Job>>();

	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	public PreferenceList() {
		super();		
	}
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * Returns a Collection of all sla's which prefer actually Server server (position 1 in the vector in the 
	 * Map) and this PreferencePoint is available (available==true).
	 * 
	 * @param server the storage network to check for sla's prefering it.
	 * @return a Collection of sla's prefering the storage network.
	 * 
	  
	 */
	public Vector<Job> getAllJobsPreferingServer(Server server){
		Collection<Vector<PreferencePoint>> pp = preferenceList.values();
		Vector<Job> jobVec = new Vector<Job>();
		for (Vector<PreferencePoint> pp_vec: pp) {
			if (pp_vec.size() > 0) {
				if (pp_vec.firstElement().getServer().equals(server) && pp_vec.firstElement().isAvailable()) {
					jobVec.add(pp_vec.firstElement().getJob());
				}
			}			
		}
		lastOutgoingPackage.put(server, jobVec);
		return jobVec;
	}

	

	/**
	 * According to the results of the Pareto-Algorithm this method administrates the two internal Maps of PreferenceLists.
	 * The focus is on the difference between the two ParetoPoints:
	 * <p>
	 * Case 1:	sla's which where formerly scheduled but are no more 	 -> their preferenceList is completely restored
	 * 																		via PreferenceListDump.
	 * </p>
	 * <p>
	 * Case 2: 	sla's which are new scheduled 							 -> Their actStorageNetwork is removed from their
	 * 																		Vector<PreferencePoint>, their next 
	 * 																		PreferencePoint is set to available==false.
	 * </p>
	 * <p>
	 * Case 3: 	sla's which could be newly scheduled but are not		 -> Their actStorageNetwork is removed from their
	 * 																		Vector<PreferencePoint>, their next 
	 * 																	   PreferencePoint is set to available==true.
	 * </p>	
	 * 
	 * @param pp the best ParetoPoint after run X		
	 * @param ppOld the best ParetoPoint before run X
	 * 	 	 
	 */
	@SuppressWarnings("unchecked")
	public void paretoDiff(ParetoPoint pp, ParetoPoint ppOld) {
		Server server = pp.getServer();
		Vector<Job> formerlyScheduledJobs = (Vector<Job>)ppOld.getJobs().clone();
		
		//case 1
		formerlyScheduledJobs.removeAll(pp.getJobs());
		//System.out.println("PreferenceList case 1: " + formerlyScheduledJobs.size() + " sla's.");
		
		for (Job job: formerlyScheduledJobs) {
			preferenceList.get(job).clear();
			preferenceList.get(job).addAll((Vector<PreferencePoint>) preferenceListDump.get(job).clone());			
		}
		
		//case 2
		Vector<Job> ppJobs = (Vector<Job>)pp.getJobs().clone();
		ppJobs.removeAll(ppOld.getJobs());
		//System.out.println("PreferenceList case 2: " + ppJobs.size() + " sla's.");
		
		for (Job job: ppJobs) {		
			if (preferenceList.get(job).size() > 0) {
				preferenceList.get(job).remove(0);
			}
			if (preferenceList.get(job).size() > 0) {
				preferenceList.get(job).firstElement().setAvailable(false);
			}			
		}
		
		//case 3
		Vector<Job> outgoneJobs2 = (Vector<Job>)lastOutgoingPackage.get(server).clone();
		outgoneJobs2.removeAll(pp.getJobs());
		//System.out.println("PreferenceList case 3: " + outgoneJobs2.size() + " sla's.");
		for (Job job: outgoneJobs2) {
			if (preferenceList.get(job).size() > 0) {
				preferenceList.get(job).remove(0);
			}
			if (preferenceList.get(job).size() > 0) {
				preferenceList.get(job).firstElement().setAvailable(true);
			}			
		}
	}
	
	/**
	 * (finished==true) if all jobs have an empty preferenceList or all first PreferencePoints are have (available==false)
	 * 
	 * @return a boolean if the PreferenceList is in a finished - state. 
	 * 
	 */
	public boolean isEverythingFinished() {
		Collection<Vector<PreferencePoint>> pp = preferenceList.values();		
		for (Vector<PreferencePoint> pp_vec: pp) {
			if (pp_vec.size() > 0) {
				if (pp_vec.firstElement().isAvailable())return false;
			}
		}
		return true;
	}
	
	
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter & setter - Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * @return the actual preferenceList
	 */
	public Map<Job, Vector<PreferencePoint>> getPreferenceList() {
		return preferenceList;
	}
	

	/**
	 * @return the actual preferenceListDump
	 */
	public Map<Job, Vector<PreferencePoint>> getPreferenceListDump() {
		return preferenceListDump;
	}	

	
} //end of class
