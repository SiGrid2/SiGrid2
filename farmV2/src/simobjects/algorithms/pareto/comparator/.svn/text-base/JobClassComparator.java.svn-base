package simobjects.algorithms.pareto.comparator;

import java.util.Comparator;

import simobjects.Job;


/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class JobClassComparator implements Comparator<Job> {

	public int compare(Job j1, Job j2) {
		if (j1.getServerClass() < j2.getServerClass()){
			return +1;
		}
		else if (j1.getServerClass() > j2.getServerClass()){
			return -1;
		}
		else{
			if (j1.getEarning() < j2.getEarning()){
				return +1;
			}
			else if(j1.getEarning() > j2.getEarning()){
				return -1;
			}
			else return 0;
		}
	}

	
}//end of class
