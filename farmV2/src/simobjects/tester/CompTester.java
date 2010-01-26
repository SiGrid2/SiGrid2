package simobjects.tester;

import java.util.Collections;
import java.util.Vector;

import simobjects.Job;
import simobjects.algorithms.greedy.comparator.EarningComparator;
import simobjects.algorithms.greedy.comparator.EarningDasdComparator;
import simobjects.algorithms.greedy.comparator.EarningDasdDurationComparator;
import simobjects.algorithms.greedy.comparator.EarningDurationComparator;
import simobjects.algorithms.greedy.comparator.PenaltyComparator;

/**
 * class for testing greedy-comparators
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class CompTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompTester tester = new CompTester();
		tester.work();
	}

	private void work() {
		Vector<Job> jobvec = new Vector<Job>();

		Job job1 = new Job();
		job1.setDasd(1);
		job1.setDuration(1);
		job1.setEarning(2);
		job1.setPenalty(1);
		job1.setRelCat(1);
		job1.setSpeedCat(1);
		job1.setStartTime(1);

		Job job2 = new Job();
		job2.setDasd(1);
		job2.setDuration(1);
		job2.setEarning(10);
		job2.setPenalty(10);
		job2.setRelCat(1);
		job2.setSpeedCat(1);
		job2.setStartTime(1);

		jobvec.add(job1);
		jobvec.add(job2);

		Collections.sort(jobvec, new EarningComparator());
		System.out.println(jobvec.firstElement().getEarning());

		Collections.sort(jobvec, new EarningDasdComparator());
		System.out.println(jobvec.firstElement().getEarning());

		Collections.sort(jobvec, new EarningDasdDurationComparator());
		System.out.println(jobvec.firstElement().getEarning());

		Collections.sort(jobvec, new EarningDurationComparator());
		System.out.println(jobvec.firstElement().getEarning());

		Collections.sort(jobvec, new PenaltyComparator());
		System.out.println(jobvec.firstElement().getEarning());
	}


}//end of class
