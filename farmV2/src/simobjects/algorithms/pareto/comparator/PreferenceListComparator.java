package simobjects.algorithms.pareto.comparator;

import java.util.Comparator;

import simobjects.algorithms.pareto.PreferencePoint;



/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class PreferenceListComparator implements Comparator<PreferencePoint> {

	public int compare(PreferencePoint arg0, PreferencePoint arg1) {

		if (arg0.getEarning() > arg1.getEarning()) {
			return -1;
		}
		else if (arg0.getEarning() < arg1.getEarning()) {
			return +1;
		}
		return 0;
	}


}//end of class
