package simobjects;

import java.util.Comparator;

/**
 * Comparator
 */
public class IntComparator implements Comparator<Integer> {

	public int compare(Integer arg0, Integer arg1) {
		if (arg0 < arg1) {
			return +1;
		}
		else if(arg0 > arg1) {
			return -1;
		}
		return 0;
	}
}
