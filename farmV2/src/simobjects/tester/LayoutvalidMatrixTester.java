package simobjects.tester;

import simobjects.Job;
import simobjects.Layout;
import simobjects.Matrix;
import simobjects.Server;

/**
 * class for testing the method isMatrixValid in class Layout
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class LayoutvalidMatrixTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Layout l = new Layout();
		l.addServer(new Server(0,1,1,1,1));

		Job j1 = new Job(0,0,1,2,1,1,1,1);
		Job j2 = new Job(1,1,1,1,15,1,1,1);

		l.addJob(j1);
		l.addJob(j2);

		Matrix m = new Matrix(2,1);
		m.assignJobToServer(0, 0);
		m.assignJobToServer(1, 0);

		System.out.println(l.isMatrixValid(m));
	}


}//end of class
