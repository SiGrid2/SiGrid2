package simobjects.algorithms.pareto.comparator;

import java.util.Comparator;

import simobjects.algorithms.pareto.ParetoPoint;


/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class EarningComparator implements Comparator<ParetoPoint> {

	public int compare(ParetoPoint pp1, ParetoPoint pp2) {
		if (pp1.getEarning() > pp2.getEarning()){
			return +1;
		}
		else if (pp1.getEarning() < pp2.getEarning()){
			return -1;
		}
		else{
			if (pp1.getEndTime() < pp2.getEndTime()){
				return +1;
			}
			else if (pp1.getEndTime() > pp2.getEndTime()){
				return -1;
			}
			else{
				if (pp1.getJobNeededDasd() < pp2.getJobNeededDasd()){
					return +1;
				}
				else if (pp1.getJobNeededDasd() > pp2.getJobNeededDasd()){
					return -1;
				}
				else{
					return 0;
				}
			}
		}
	}


}//end of class
