package simobjects.algorithms.greedy.comparator;

import java.util.Comparator;
import simobjects.SimResult;


/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SimresultComparator implements Comparator<SimResult> {

	public int compare(SimResult o1, SimResult o2) {
		if (o1.getEarning()  > o2.getEarning() ){
			return -1;
		}
		else if (o1.getEarning()  < o2.getEarning() ){
			return +1;
		}
		
		return 0;
	}

	
}//end of class