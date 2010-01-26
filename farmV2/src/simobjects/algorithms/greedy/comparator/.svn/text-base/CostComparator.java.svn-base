package simobjects.algorithms.greedy.comparator;

import java.util.Comparator;


import simobjects.Server;

/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class CostComparator implements Comparator<Server> {

	public int compare(Server o1, Server o2) {
		if (o1.getCost() < o2.getCost()){
			return -1;
		}
		else if (o1.getCost() > o2.getCost()){
			return +1;
		}

		else {
			if (o1.getServerClass() > o2.getServerClass()){
				return -1;
			}
			else if (o1.getServerClass() < o2.getServerClass()){
				return +1;
			}
		}

		return 0;
	}
	

}//end of class
