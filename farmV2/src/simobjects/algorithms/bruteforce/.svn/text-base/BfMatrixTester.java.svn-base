package simobjects.algorithms.bruteforce;

import simobjects.Job;
import simobjects.Layout;
import simobjects.Matrix;
import simobjects.Server;


/**
 * testing-class for BfMatrix
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class BfMatrixTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startMSek 	= System.currentTimeMillis();

		int numJobs 	= 2;
		int numServer 	= 2;
		int numJobsOnServerPossible = numJobs;

		Layout layout 	= createLayout(numJobs, numServer, numJobsOnServerPossible );

		long numComb = (long) Math.pow(numServer+1, numJobs);

		BfMatrix matrix = new BfMatrix(new Matrix(numJobs,numServer),numComb);

		int i = 0;
		Matrix aktMatrix;

		while (matrix.getNumMatrix()>0) {
			System.out.print("case #"+i++);
			aktMatrix=matrix.getMatrixToCalculate();


			if (layout.isMatrixValid(aktMatrix)) {
				System.out.println("... is valid: Earning="+layout.getEarning(aktMatrix));

			}
			else {
				System.out.println("... is NOT valid!");
			}
			aktMatrix.printMatrixToConsoleInt();
			System.out.println("---------------");
		}

		long durationMSek	= 	System.currentTimeMillis() - startMSek;
		long durationSek 	= 	durationMSek / 1000;
		durationMSek 		= 	durationMSek - (durationSek * 1000);


		System.out.println("This took "+durationSek+" sek and "+durationMSek+" millis");

	}

	private static Layout createLayout (int numJobs, int numServer, int numJobsPossible) {
		Layout layout = new Layout ();


		int jobDasd		= 10;
		int serverDasd  = jobDasd * numJobsPossible ;

		int jobIncome	= 10;
		int serverCost	= 1;
		int jobEarning	= jobIncome + serverCost;


		for (int i = 0; i < numJobs; i++) {
			layout.addJob(new Job(i,jobEarning,jobDasd,0,1,0,0,0));

		}
		for (int i = 0; i < numServer; i++) {
			layout.addServer(new Server(i,serverCost,serverDasd,0,0));
		}

		return layout;
	}

	
}//end of class
