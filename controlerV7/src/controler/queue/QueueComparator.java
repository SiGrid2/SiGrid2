package controler.queue;

import java.util.Comparator;

import simobjects.SimJob;

/**
 * Comparator
 * 
 * @author Dirk Holzapfel 
 * @version 1.0
 */
public class QueueComparator implements Comparator<SimJob>{

	public int compare(SimJob o1, SimJob o2) {
		if (o1.getQueuePosition() < o2.getQueuePosition()){
			return -1;
		}
		else if(o1.getQueuePosition() > o2.getQueuePosition()){
			return +1;
		}
		return 0;
		
	}

}//end of class
