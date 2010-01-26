package farm.worker.bruteforce;

import simobjects.Layout;
import simobjects.Matrix;
import simobjects.SimResult;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.algorithms.bruteforce.BfMatrix;


/**
 * Bruteforce WorkerCore. As long as the SimMaster provides BfMatrix's to calculate, 
 * they are requested and calculated. When a null-objext from SimMaster is returned,
 * the simulation is finished.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class WorkerCore extends Thread{

	private SimWorker myWorker;
	private Layout layout;
	private long maxEarning = Long.MIN_VALUE;
	private SimStatistics stats;
	private int receiverID;
	private int trID;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public WorkerCore(SimWorker worker, TestRun tr, int simWorkerID) {
		super();
		this.myWorker = worker;
		this.layout = tr.getLayout();
		this.trID = tr.getId();
		this.stats=new SimStatistics(tr.getStatsStart(),tr.getStatsEnd(), tr.getStatsNumFields());
		receiverID = simWorkerID;
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run () {
		myWorker.startedSim(0, trID);
		BfMatrix bfm = null;
		while ((bfm = myWorker.getNextPackage())!= null){
			//System.out.println("got bfm with matrizes to calcualte: " + bfm.getNumMatrix());
			Matrix m;
			long aktEarning = Long.MIN_VALUE;

			while(bfm.getNumMatrix()>0){

				m = bfm.getMatrixToCalculate();

				if(layout.isMatrixValid(m)) {
					aktEarning = layout.getEarning(m);
					stats.addEarning(aktEarning);

					if (aktEarning > maxEarning) {
						maxEarning = aktEarning;
						myWorker.addSimResult(receiverID, trID, new SimResult(m,aktEarning));
					}
				}
			}
		}

		myWorker.addSimStatistics(receiverID, trID, stats);
		myWorker.finishedSim(receiverID, trID);
	}


}//end of class

