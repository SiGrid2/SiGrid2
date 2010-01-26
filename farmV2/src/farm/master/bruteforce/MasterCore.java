package farm.master.bruteforce;

import simobjects.SimState;
import simobjects.TestRun;
import simobjects.algorithms.bruteforce.BfMatrix;
import simobjects.algorithms.bruteforce.BruteForceGenerator;


/**
 * MasterCore of the Bruteforce-Algorithm. Delivers BFMatrix's and can set the simulation progress
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class MasterCore{

	private BruteForceGenerator generator; 	//class that produces the BruteForce-Matrizes for the workers to simulate
	private SimState simState;
	private SimMaster myMaster;
	private int trID = -1;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * The BruteForce-MasterCore provides the BFMatrix's for the workers
	 * to calculate
	 *
	 * @param myMaster	the SimMaster that has started this class
	 * @param testRun	the TestRun to simulate
	 */
	public MasterCore(SimMaster myMaster, TestRun testRun) {
		super();
		this.myMaster = myMaster;
		this.generator = new BruteForceGenerator(testRun, 2000000);
		this.simState = new SimState();
		this.trID = testRun.getId();
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * Method called from the workers, when they need new work.
	 * 
	 * @return BfMatrix to simulate, null if everything is calculated
	 */
	public BfMatrix getNextPackage() {

		BfMatrix bfm = generator.getbfMatrix();
		return bfm;
	}


	/**
	 * Returns the actual progress of the simulation as an int.
	 * for example: 1234 means 12.34%
	 * 
	 * @return progress of simulation in percent
	 */
	public int getProgress(){

		return generator.getProgress();
	}


	/**
	 * Method may be called by a thread - sets the actual progress of the simulation in the SimMaster
	 */
	public void setProgress() {

		simState.setProgress(generator.getProgress());
		myMaster.setSimState(0, trID, simState);
	}

}//end of class

