package simobjects.algorithms.bruteforce;

import java.util.Vector;
import simobjects.TestRun;


/**
 * The class holds a vector of BfMatrizes to calculate, which is filled up by
 * BruteForceGeneratorFillerThread
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class BruteForceGenerator {


	private double combinations; // 		all combinations possible := (numServer + 1)^numJobs
	private double itemsPerCombination; //	the number of combinatons in a BfMatrix
	private double packagesToDeliver;//		the number of BfMatrizes left to deliver := (combinations / itemsPerCombination) - deliveredPackages
	private Vector<BfMatrix> bfMatrixToSimulate; // holds [0;10000] BfMatrix's ready to deliver
	private BruteForceGeneratorFillerThread fillerThread; //fills up the Vector<BfMatrix> bfMatrixToSimulate when invoked





	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @param tr		the testRun to calculate
	 * @param barrier	the number of matrizes in a BfMatrix is <= barrier
	 */
	public BruteForceGenerator(TestRun tr, double barrier) {
		super();
		this.combinations = Math.pow((tr.getLayout().getNumServer() + 1), tr.getLayout().getNumJobs());
		this.bfMatrixToSimulate = new Vector<BfMatrix>();
		this.fillerThread = new BruteForceGeneratorFillerThread(this, tr, barrier);
		this.fillerThread.start();

		//give the filler thread time to fill up
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @return BfMatrix to calculate, null when all packages are delivered
	 */
	public BfMatrix getbfMatrix(){
		//invokes the FillerThread each the method is invoked.

		if (packagesToDeliver > 0){
			if(bfMatrixToSimulate.size() > 0 ){
				fillerThread.workOn();
				packagesToDeliver--;
				return bfMatrixToSimulate.remove(0);
			}
			else{
				fillerThread.workOn();
				packagesToDeliver--;
				while(bfMatrixToSimulate.size() == 0 ){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return bfMatrixToSimulate.remove(0);
			}
		}
		else{
			return null;
		}
	}



	/**
	 * @return all combinations possible := (numServer + 1)^numJobs
	 */
	public double getCombinations() {
		return combinations;
	}



	/**
	 * @return the number of BfMatrizes left to deliver := (combinations / itemsPerCombination) - deliveredPackages
	 */
	public double getPackagesToDeliver() {
		return packagesToDeliver;
	}


	/**
	 * @return the number of BfMatrizes in the Vecor
	 */
	public int getNumPackagesInVector(){
		return bfMatrixToSimulate.size();
	}


	/**
	 * @return the number of combinatons in a BfMatrix
	 */
	public double getItemsPerCombination() {
		return itemsPerCombination;
	}

	/**
	 * Adds a BruteForceMatrix to the Vector
	 *
	 * @param bfm
	 */
	public synchronized void addBfMatrixToVector(BfMatrix bfm){
		bfMatrixToSimulate.add(bfm);
	}


	/**
	 * @param itemsPerCombination
	 */
	public void setItemsPerCombination(double itemsPerCombination) {
		this.itemsPerCombination = itemsPerCombination;
		this.packagesToDeliver = (combinations / itemsPerCombination);
	}

	/**
	 * @return the sum of BfMatrix packages which have to be delivered in the whole testrun
	 */
	public double getNumPackages(){
		return combinations / itemsPerCombination;
	}

	/**
	 * @return the actual progress of the simulation as an int.
	 * for example: 1234 means 12.34%
	 */
	public int getProgress(){
		double progress = ((combinations - (packagesToDeliver*itemsPerCombination)) * 100 * 100) / combinations;

		return (int) progress;
	}


}//end of class
