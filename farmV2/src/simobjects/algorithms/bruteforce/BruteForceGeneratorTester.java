package simobjects.algorithms.bruteforce;

import simobjects.Layout;
import simobjects.TestRun;


/**
 * testing-class for BruteForceGenerator
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class BruteForceGeneratorTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Layout layout = Layout.createLayout(2, 2, 1);

		TestRun tr = new TestRun();
		tr.setLayout(layout);

		BruteForceGenerator gen = new BruteForceGenerator(tr,1 );

		System.out.println((long)gen.getCombinations());
		System.out.println(gen.getNumPackagesInVector());
		System.out.println((long)gen.getItemsPerCombination());
		System.out.println((long)gen.getPackagesToDeliver() );

		//long i = 0;
		BfMatrix b;
		while((b = gen.getbfMatrix()) != null){
			//System.out.println("durchlauf :"+ ++i);

			b.getMatrixToCalculate().printMatrixToConsoleInt();
			//System.out.println(gen.getItemsPerCombination());
		}
		System.out.println("****");
		System.out.println(gen.getNumPackages());
	}

}//end of class
