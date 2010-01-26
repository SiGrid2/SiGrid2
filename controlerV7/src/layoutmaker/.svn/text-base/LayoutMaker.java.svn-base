package layoutmaker;

import controler.communication.gui.noAxis.AsciiReader;
import simobjects.Layout;
import simobjects.LayoutEvaluation;

/**
 * this class was used for creating layouts according to the four layout-testcases
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class LayoutMaker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		LayoutMaker.getXXX(800, 25);
	}

	private static void getXXX(int jobs, int server) {
//		for (int i =0; i < 3; i++) {
//		Layout l = LayoutEvaluation.getUncorrelatedInstances(10, 100, jobs, 1, 6, 1, 6, 1, 5, 1, 5, 10, 10000, server);
//		freer(l);			
//		}


//		for (int i =0; i < 3; i++) {
//		Layout l = LayoutEvaluation.getWeaklyCorrelatedInstances(10, 1000, jobs, 150, 1, 12, 1, 12, 1, 5, 1, 5, 30000, 3000000, server);
//		freer(l);		
//		}
//		LayoutEvaluation.getUncorrelatedInstances(10, 1000, 400, 1, 12, 1, 12, 1, 5, 1, 5, 30000, 160000, 25); 

		for (int i =0; i < 3; i++) {
			Layout l = LayoutEvaluation.getStronglyCorrelatedInstances(10, 1000, jobs, 150, 1, 12, 1, 12, 1, 5, 1, 5, 6, server);
			freer(l);
		}


//		for (int i =0; i < 3; i++) {
//		Layout l = LayoutEvaluation.getSlaWithStaticEarningInstances(10, 100, jobs, 100, 1, 6, 1, 6, 1, 5, 1, 5, 10000, server);
//		freer(l);
//		}



	}

	private static void freer(Layout l) {
		AsciiReader r= new AsciiReader(l);
		System.out.println("## LayoutEvaluation.getWeaklyCorrelatedInstances(10, 100, "+l.getNumJobs()+", 15, 1, 6, 1, 6, 1, 5, 1, 5, 10, 10000, "+l.getNumServer()+"); ");
		System.out.println("SimJob:		farmid=1");
		System.out.println("TestRun: 	algorithmid=1	");
		System.out.println();
		r.dump(System.out);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("## LayoutEvaluation.getWeaklyCorrelatedInstances(10, 100, "+l.getNumJobs()+", 15, 1, 6, 1, 6, 1, 5, 1, 5, 10, 10000, "+l.getNumServer()+"); ");
		System.out.println("SimJob:		farmid=1");
		System.out.println("TestRun: 	algorithmid=2	");
		System.out.println();
		r.dump(System.out);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("## LayoutEvaluation.getStronglyCorrelatedInstances(10, 1000, " +l.getNumJobs()+", 150, 1, 12, 1, 12, 1, 5, 1, 5, 6, "+l.getNumServer()+"); ");
		System.out.println("SimJob:		farmid=1");
		System.out.println("TestRun: 	algorithmid=3	");
		System.out.println();
		r.dump(System.out);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}

}
