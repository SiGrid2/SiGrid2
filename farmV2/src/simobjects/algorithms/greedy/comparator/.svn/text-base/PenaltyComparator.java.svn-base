package simobjects.algorithms.greedy.comparator;

import java.util.Comparator;

import simobjects.Job;


/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class PenaltyComparator implements Comparator<Job> {

	public int compare(Job o1, Job o2) {
		if (o1.getEarning() * o1.getDuration() *o1.getDasd() - o1.getPenalty() > o2.getEarning() * o2.getDuration() *o2.getDasd() - o2.getPenalty()){
			return -1;
		}
		else if (o1.getEarning() * o1.getDuration() *o1.getDasd() - o1.getPenalty() < o2.getEarning() * o2.getDuration() *o2.getDasd() - o2.getPenalty()){
			return +1;
		}
		
		return 0;
	}

	
}//end of class
