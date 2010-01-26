package farm.worker.pareto;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import communication.algorithms.pareto.IReceiverPareto;
import simobjects.Job;
import simobjects.Layout;
import simobjects.Server;
import simobjects.TestRun;
import simobjects.algorithms.greedy.comparator.EarningDasdDurationComparator;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;
import simobjects.algorithms.pareto.comparator.EarningComparator;
import simobjects.algorithms.pareto.comparator.ParetoComparator;


/**
 * Pareto WorkerCore. As long as the SimMaster provides Pareto-Data to calculate, 
 * they are requested and calculated. When a null-objext from SimMaster is returned,
 * the simulation is finished.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class WorkerCore extends Thread implements IReceiverPareto{

	private Vector<ParetoPoint> l = new Vector<ParetoPoint>();
	private Vector<ParetoPoint> lStar = new Vector<ParetoPoint>();
	private Vector<ParetoPoint> tempVec = new Vector<ParetoPoint>();
	private ParetoComparator paretoComparator = new ParetoComparator();
	private Layout layout;
	private TestRun tr;
	private SimWorker simWorker;
	private int receiverId;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public WorkerCore(TestRun tr, SimWorker simWorker, int receiverId) {
		super();
		this.tr = tr;	
		this.layout = tr.getLayout();
		this.simWorker = simWorker;
		this.receiverId = receiverId;

		System.out.println("Pareto-WorkerCore started");
	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		ParetoTransportData ptd;
		while (( ptd= simWorker.getParetoDataToCalculate()) != null) {
			System.out.println("got a ptd with jobs #" + ptd.getJobs().size());
			Collections.sort(ptd.getJobs(), new EarningDasdDurationComparator() );
			calcBestParetoPoint(ptd.getParetoPoints(), ptd.getJobs(), ptd.getServer());
			System.out.println("calc ready");
		}		
		simWorker.finishedSim(receiverId, tr.getId());
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * calculates the paretopoint with the highest earning
	 * for a given vector of formerly calculated paretopoints, 
	 * a vector of jobs and
	 * a storage network
	 * 
	 * @param ppVec vector of formerly calculated paretopoints
	 * @param jobVec vector of jobs
	 * @param server storage network
	 */
	private void calcBestParetoPoint(Vector<ParetoPoint>ppVec, Vector<Job> jobVec, Server server ){

		l = ppVec;
		lStar.clear();
		tempVec.clear();

		//makes L(0)...L(1)...L(...)...L(n)
		for (int i = 0; i < jobVec.size(); i++ ) {
			Job job = jobVec.get(i);
			createLStar(job);
			mergeLandLStar();
			System.out.println(i + " - pp " + l.size() );
//			System.out.println("---------------------------");
//			for(ParetoPoint pp:l) {
//			System.out.println("pp: " + pp.getJobNeededDasd()  + " - " + pp.getEarning() + " - " + pp.getEndTime());
//			pp.printJobs();
//			}
//			System.out.println();
		}	

		Collections.sort(l, new EarningComparator());
		ParetoPoint bestPoint = l.lastElement();
		deleteNotUsedParetoPoints();
		Collections.sort(l, paretoComparator);	

		//System.out.println("setcalcu");
		simWorker.setCalculatedPareto(l, bestPoint, server);
		//System.out.println("setcalcu ready");
	}


	/**
	 * deletes all paretopoints in vector L , which own a sla not used in the best solution paretopoint.
	 * implemented because otherwise theres a high conflict potential for collisions with other storage networks.
	 * for example in storage network 1 is a paretopint with a sla X , not part of the best solution. if the paretopoint is
	 * not deleted an this sla X is scheduled on another storage network AND there comes a new sla to storage network 1 
	 * that leads to a new best solution with sla X as a part of the solution, there's a conflict -> sla X is parallel 
	 * scheduled on two storage networks...
	 */
	private void deleteNotUsedParetoPoints() {
		Vector<Job> jobsOfBestSolution = l.lastElement().getJobs();
		Vector<ParetoPoint> ppToDelete = new Vector<ParetoPoint>();
		for (ParetoPoint pp: l) {
			if (!jobsOfBestSolution.containsAll(pp.getJobs())){
				ppToDelete.add(pp);
			}
		}
		l.removeAll(ppToDelete);
	}


	/**
	 * Called with the Job, which has to be added to all ParetoPoints in L.
	 * Fills up Vector L* with the new ParetoPoint when they're valid
	 * 
	 * @param job
	 */
	private void createLStar(Job job){
		lStar.clear();
		for (int i = 0; i < l.size(); i++) {
			if (job.getDasd() > 0) {
				ParetoPoint pp2 = new ParetoPoint(l.get(i),job);
				if (pp2.isValid(layout)){
					lStar.add(pp2);
				}
			}
		}
		Collections.sort(lStar, paretoComparator);
	}


	/**
	 * merges L(x) and L*(x) to L(x+1)
	 */
	private void mergeLandLStar() {

		//System.out.println("L size: "+ l.size());

		tempVec.clear();
		checkLAgainstLStar();
		checkLStarAgainstL();		
		l.clear();
		l.addAll(tempVec);
		Collections.sort(l, paretoComparator);	
	}


	/**
	 * all paretopoints in L* which are NOT dominated by paretopoints in L are put into tempVec
	 */
	private void checkLStarAgainstL(){

		for (int i = 0; i < lStar.size(); i++){			
			ParetoPoint lStarPoint = lStar.get(i);			
			int pos = binSearchLEQEndtime(lStarPoint.getEndTime(), l);

			if (pos > -1){
				ParetoPoint lPoint = l.elementAt(pos);

				if(lStarPoint.getEndTime() > lPoint.getEndTime()){ 					
					tempVec.add(lStarPoint);					
				}
				else{
					int pos2 = binSearchLEQEndtime(lPoint.getEndTime()-1, l);
					int pos3 = -1;
					if (pos2 > -1){
						pos3 = binSearchLEQNeededDasd(lStarPoint.getJobNeededDasd(), l.subList(pos2+1, pos+1));
					}
					else{
						pos3 = binSearchLEQNeededDasd(lStarPoint.getJobNeededDasd(), l.subList(0, pos+1));
					}
					if (pos3 > -1){
						if (pos2> -1) pos3 += pos2 + 1;
						lPoint = l.elementAt(pos3);

						if(lStarPoint.getEarning() >= lPoint.getEarning() ){  //Lstarpoint gut
							tempVec.add(lStarPoint);							
						}
					}
					else{
						tempVec.add(lStarPoint);						
					}
				}
			}
		}
	}


	/**
	 * all paretopoints in L which are NOT dominated by paretopoints in L* are put into tempVec
	 */
	private void checkLAgainstLStar(){

		for (int i = 0; i < l.size(); i++){
			ParetoPoint lPoint = l.elementAt(i);

			int pos = binSearchLEQEndtime(lPoint.getEndTime(), lStar); //posistion of pp in l* where l*.endtime<=l.endtime
			if (pos > -1){ //such a point exists in l*
				ParetoPoint lStarPoint = lStar.elementAt(pos);
				if (lPoint.getEndTime() > lStarPoint.getEndTime()){ //l.endtime > l*.endtime
					//if(lPoint.getEarning() >= lStarPoint.getEarning()){  //l has greater earning than l*? Lpoint gut
					tempVec.add(lPoint);
					//} //04.10: if ausgeklammert wegen nichtvergleichbarkeit verschiedener zeitzustände
				}
				else{ //l.endtime == l*.endtime
					int pos2 = binSearchLEQEndtime(lStarPoint.getEndTime()-1, lStar); //look for < than l*.endtime in l*
					int pos3 = -1;
					if (pos2 > -1){ // < endtime exists
						pos3 = binSearchLEQNeededDasd(lPoint.getJobNeededDasd(), lStar.subList(pos2+1, pos+1));
						// look from (< point) to (l*point) if there is a pp with <= cumulated dasd
					}
					else{
						pos3 = binSearchLEQNeededDasd(lPoint.getJobNeededDasd(), lStar.subList(0, pos+1));
						// look from (start) to (l*point) if there is a pp with <= cumulated dasd
					}
					if (pos3 > -1){ // a l* point with <= dasd exists 
						if (pos2> -1) pos3+=pos2+1;
						lStarPoint = lStar.elementAt(pos3);

						if(lPoint.getEarning() >= lStarPoint.getEarning() ){  //l has greater earning than l*? Lpoint gut
							tempVec.add(lPoint); //add
						}
					}
					else{ // a l* point with <= dasd doesn't exist 
						tempVec.add(lPoint); //add						
					}
				}
			}
			else{ // a point with <= endtime doesn't exists
				tempVec.add(lPoint); //add
			}
		}
	}


	/**
	 * binary search, looking for the paretopoint in the 
	 * given vector which endtime is <= the given one
	 * 
	 * @param endTime
	 * @param vec
	 * @return position of the searched paretopoint
	 */
	private int binSearchLEQEndtime(int endTime, List<ParetoPoint> vec) {
		int erstes = 0;
		int letztes = vec.size() - 1;
		int mitte = erstes + ((letztes - erstes) / 2);

		while (erstes <= letztes ) {
			if (vec.get(mitte).getEndTime() <= endTime) {
				erstes = mitte + 1;  // rechts weitersuchen
			}
			else{
				letztes = mitte - 1;  // links weitersuchen
			}
			mitte = erstes + ((letztes - erstes) / 2);
		}
		return mitte-1;
	}


	/**
	 * binary search, looking for the paretopint in the given vector 
	 * which neededDasd is <= the given one
	 * 
	 * @param avgFill
	 * @param vec
	 * @return position of the searched paretopoint
	 */
	private int binSearchLEQNeededDasd(float dasd, List<ParetoPoint> vec) {

		int erstes = 0;
		int letztes = vec.size() - 1;
		int mitte = erstes + ((letztes - erstes) / 2);

		while (erstes <= letztes ) {
			if (vec.get(mitte).getJobNeededDasd() <= dasd) {
				erstes = mitte + 1;  // rechts weitersuchen
			}
			else {
				letztes = mitte - 1;  // links weitersuchen
			}
			mitte = erstes + ((letztes - erstes) / 2);
		}
		return mitte-1;
	}


}//end of class
