package farm.master.greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import simobjects.Job;
import simobjects.Matrix;
import simobjects.Server;
import simobjects.SimResult;
import simobjects.TestRun;
import simobjects.algorithms.greedy.comparator.CostComparator;
import simobjects.algorithms.greedy.comparator.EarningComparator;
import simobjects.algorithms.greedy.comparator.EarningDasdComparator;
import simobjects.algorithms.greedy.comparator.EarningDasdDurationComparator;
import simobjects.algorithms.greedy.comparator.EarningDurationComparator;
import simobjects.algorithms.greedy.comparator.PenaltyComparator;
import simobjects.algorithms.greedy.comparator.SimresultComparator;


/**
 * MasterCore of Greedy-Algorithm.
 * Resolves a solution of the sla/storage network optimization problem and sends it to the controller.
 * At the moment four comparators are implemented.
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class MasterCore extends Thread{

	private SimMaster simMaster;
	private TestRun tr;
	private Matrix matrix;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public MasterCore(SimMaster simMaster, TestRun tr) {
		this.simMaster = simMaster;
		this.tr = tr;
		matrix = new Matrix(tr.getLayout().getNumJobs(), tr.getLayout().getNumServer());
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

		Vector<SimResult> simResultVec = new Vector<SimResult>();
		System.out.println("Greedy MasterCore started!");

		simResultVec.add(this.greedy(new EarningComparator()));
		System.out.println("earning: " + simResultVec.lastElement().getEarning());		

		simResultVec.add(this.greedy(new EarningDasdComparator()));
		System.out.println("earning dasd: " + simResultVec.lastElement().getEarning());		

		simResultVec.add(this.greedy(new EarningDurationComparator()));
		System.out.println("earning duration : " + simResultVec.lastElement().getEarning());		

		simResultVec.add(this.greedy(new EarningDasdDurationComparator()));
		System.out.println("earning dasd duration: " + simResultVec.lastElement().getEarning());

		Collections.sort(simResultVec, new SimresultComparator());	

		simMaster.addSimResult(0, tr.getId(), simResultVec.firstElement());
		simMaster.finishedSim(0, tr.getId());
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * Calculates the best SimResult according to the given comparator
	 * 
	 * 1. sort storage-networks according to cost
	 * 2. sort sla's according to efficiencyComparator
	 * 3. iterate over storage-networks {iterate over sla's}
	 * 4. schedule sla's when they produce a valid matrix, positive profit and don't produce penalty
	 * 5. delete sla's with 'duration * earning * dasd - penalty <= 0' 
	 * 6. sort left sla's according to penalty
	 * 7. iterate over storage-networks {iterate over sla's}
	 * 8. schedule jobs when they produce a valid matrix and positive profit  
	 * 
	 * @param efficiencyComparator the comparazor to sort the SLAs
	 * @return best SimResult	 
	 */
	@SuppressWarnings("unchecked")
	private SimResult greedy(Comparator<Job> efficiencyComparator) {
		matrix.clear();
		//get all available servers and sort them to COST / CLASS
		Vector<Server> serverVec= (Vector<Server>)tr.getLayout().getServerVector().clone();		
		Collections.sort(serverVec, new CostComparator());
		Vector<Job> jobVec = (Vector<Job>) tr.getLayout().getJobVector().clone();
		Collections.sort(jobVec, efficiencyComparator);
		Vector<Job> scheduledJobs = new Vector<Job>();

		for (Server server: serverVec) {	
			for (Job job: jobVec){
				if (job.getRelCat() >= server.getRelCat() && job.getSpeedCat() >= server.getSpeedCat() && job.getEarning() > server.getCost()) {
					matrix.assignJobToServer(job.getId(), server.getId());
					if (tr.getLayout().isMatrixValid(matrix)){
						scheduledJobs.add(job);
						//System.out.println("scheduled job#"+job.getId());
					}
					else{
						//deschedule job
						matrix.assignJobToServer(job.getId(), -1);	
						//System.out.println("not scheduled job#"+job.getId());
					}
				}
			}
			jobVec.removeAll(scheduledJobs);
			scheduledJobs.clear();
		}

		//remove all jobs with job.getDuration()*job.getEarning()* job.getDasd() - job.getPenalty() <= 0
		Vector<Job> jobTemp = new Vector<Job>();
		for (Job job: jobVec) {
			if(job.getDuration()*job.getEarning()*job.getDasd() - job.getPenalty() <= 0) {
				jobTemp.add(job);
			}
		}
		jobVec.removeAll(jobTemp);

		//matrix.printMatrixToConsoleInt();

		//sort jobVec according to penaltyComparator
		Collections.sort(jobVec, new PenaltyComparator());
		System.out.println("elements in jobvec for enhancement" + jobVec.size());


		//enhancement of greedy:
		//try to schedule jobs, which produce penalty
		long earning;
		for (Server server: serverVec) {			
			for (Job job: jobVec){
				if (job.getEarning() > server.getCost()){
					earning = tr.getLayout().getEarning(matrix);
					matrix.assignJobToServer(job.getId(), server.getId());
					if (tr.getLayout().isMatrixValid(matrix) && tr.getLayout().getEarning(matrix) > earning){
						scheduledJobs.add(job);
					}
					else{
						//deschedule job
						matrix.assignJobToServer(job.getId(), -1);							
					}
				}					
			}
			jobVec.removeAll(scheduledJobs);
			scheduledJobs.clear();
		}

		SimResult simResult = new SimResult();
		simResult.setJobServerMapping(matrix);
		simResult.setEarning(tr.getLayout().getEarning(matrix));

		return simResult;
	}


}//end of class
