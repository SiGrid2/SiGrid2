package simobjects.algorithms.pareto.comparator;

import java.util.Comparator;

import simobjects.algorithms.pareto.ParetoPoint;

/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ParetoComparator implements Comparator<ParetoPoint>{

	public int compare(ParetoPoint p1, ParetoPoint p2) {
		if (p1.getEndTime() < p2.getEndTime()){
			return -1;
		}
		else if (p1.getEndTime() > p2.getEndTime()){
			return +1;
		}
		else{
			if(p1.getJobNeededDasd() < p2.getJobNeededDasd()){
				return -1;
			}
			else if(p1.getJobNeededDasd() > p2.getJobNeededDasd()){
				return +1;
			}
			else{
				if(p1.getEarning() < p2.getEarning()){
					return -1;
				}
				else if(p1.getEarning() > p2.getEarning()){
					return +1;
				}
				else{
					return 0;
				}
			}
		}
	}

}//end of class


