package simobjects.algorithms.pareto.comparator;

import java.util.Comparator;

import simobjects.Server;


/**
 * Comparator
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ServerClassComparator implements Comparator<Server> {

	public int compare(Server s1, Server s2) {
		if (s1.getServerClass() < s2.getServerClass()){
			return +1;
		}
		else if(s1.getServerClass() > s2.getServerClass()){
			return -1;
		}
		else throw new RuntimeException("2 Server found with same class!");
		//TODO: implement and catch own exception
	}


}//end of class
