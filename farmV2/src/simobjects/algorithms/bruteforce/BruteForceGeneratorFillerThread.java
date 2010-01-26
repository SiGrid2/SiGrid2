package simobjects.algorithms.bruteforce;

import simobjects.Matrix;
import simobjects.TestRun;


/**
 * This Thread is woken up to fill up BfMatrix-Vector in the Generator when necessary.
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class BruteForceGeneratorFillerThread extends Thread {

	private BruteForceGenerator gen;
	private double barrier; //matrix - package size--> max. matrizes in BfMatrix
	private int jobs;
	private int server;
	private double itemsPerCombination;
	private Matrix matrix;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @param gen 		the BruteforceGeneratorClass, which holds the BfMatrix packages to deliver. Invokes this Thread to fill up
	 * 					its BfMatrix-Vector when necessary
	 * @param tr		testrun to calculate
	 * @param barrier	the number of matrizes in a BfMatrix is <= barrier
	 */
	public BruteForceGeneratorFillerThread(BruteForceGenerator gen, TestRun tr, double barrier) {
		super();
		this.gen = gen;
		this.barrier = barrier;
		this.jobs = tr.getLayout().getNumJobs();
		this.server = tr.getLayout().getNumServer();
		this.matrix =  new Matrix(jobs, server);
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
		divideMatrix(jobs);
	}


	/**
	 * @return itemsPerCombination
	 */
	public double getItemsPerCombination() {
		return itemsPerCombination;
	}


	/**
	 * invoked by BruteForceGenerator when it needs more BfMatrix
	 * packages
	 */
	public synchronized void workOn() {
		this.notify();
	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * recursive method that calculates the ItemsPerCombination in a BfMatrix, so that it is <= barrier
	 * called as long as (server+1)^jobs > barrier.
	 * if so called with (jobs-1)
	 *
	 * @param jobs
	 */
	private void divideMatrix(int jobs){
		if (Math.pow(server+1, jobs) > barrier){
			divideMatrix(jobs-1);
		}
		else{
			//ok, time to write matrixes into vector!
			itemsPerCombination = Math.pow(server + 1, jobs);
			gen.setItemsPerCombination(itemsPerCombination);
			gen.addBfMatrixToVector(new BfMatrix(new Matrix(matrix), itemsPerCombination));//null-matrix

			nextBruteForceMatrix(this.jobs - jobs);
		}
	}


	/**
	 * recursive method that calculates the next following BfMatrix and adds it in BruteForceGenerator.
	 * Example (2x2 matrix, barrier 1):
	 *
	 * matrix 0 0
	 * 		  0 0 (null-Matrix setted in divideMatrix();
	 *
	 *

		- -
		x -

		x -
		x -

		- x
		x -

		- -
		- x

		x -
		- x

		- x
		- x

		x -
		- -

		- x
		- -
	 * @param job	the row/job to start
	 */
	private synchronized void nextBruteForceMatrix(int job){

		matrix = new Matrix(matrix);

		if (job == 0 ){
			//finished!
		}
		else{
			int  server = getLastTrueIndex(job);

			if ( server == 0){
				matrix.getMatrix()[job-1][0] = true;
				gen.addBfMatrixToVector(new BfMatrix(new Matrix(matrix), itemsPerCombination));
				while(gen.getNumPackagesInVector() > 10000 ){
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				nextBruteForceMatrix(job-1);
				nextBruteForceMatrix(job);
			}

			else if ( server < this.server){
				matrix.getMatrix()[job-1][server-1] = false;
				matrix.getMatrix()[job-1][server] = true;
				gen.addBfMatrixToVector(new BfMatrix(new Matrix(matrix), itemsPerCombination));
				while(gen.getNumPackagesInVector() > 10000 ){
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				nextBruteForceMatrix(job-1);
				nextBruteForceMatrix(job);
			}

			else{
				matrix.getMatrix()[job-1][server-1] = false;
				nextBruteForceMatrix(job-1);
			}
		}
	}


	/**
	 * @param row
	 * @return the last "true" index of a certain row in the matrix
	 * example: - x - -    Index = 2
	 * 			1 2 3 4
	 */
	private int getLastTrueIndex(int row){

		for (int column = (matrix.getMatrix()[row-1].length)-1; column >= 0; column--) {
			if (matrix.getMatrix()[row-1][column] == true){
				return column + 1;
			}
		}
		return 0;
	}


}//end of class
