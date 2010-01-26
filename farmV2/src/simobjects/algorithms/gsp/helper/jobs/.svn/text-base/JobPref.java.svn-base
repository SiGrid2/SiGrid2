package simobjects.algorithms.gsp.helper.jobs;

import simobjects.Job;

/**
 * @author Christoph Beck
 *
 */
public class JobPref implements Comparable{
	private Job job;
	private int rank;
	private double serverValues;
	private double relativeValues;
	private double totalValues;
	
	private boolean isRunning = false;
	
	/**
	 * constructor for class JobPref
	 * @param job
	 * @param serverValues
	 * @param relativeValues
	 * @param totalValues
	 */
	public JobPref(Job job, double serverValues, double relativeValues, double totalValues) {
		super();
		this.job = job;
		this.serverValues = cut(serverValues);
		this.relativeValues = cut(relativeValues);
		this.totalValues = cut(totalValues);
		
	}
	
	/**
	 * constructor for class JobPref
	 * @param job
	 * @param serverValues
	 */
	public JobPref(Job job, double serverValues) {
		super();
		this.job = job;
		this.serverValues = cut(serverValues);
		this.relativeValues = 0;
		this.totalValues = 0;
	}
	
	/**
	 * @param d
	 * @return
	 */
	private double cut (double d){
		double cut = 1;
		double tmp = Math.round(d*cut);
		return tmp/cut;
	}
	/**
	 * @return
	 */
	public Job getJob() {
		return job;
	}
	
	/**
	 * @param job
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	
	/**
	 * @return
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object theOther) {
		if (theOther instanceof JobPref) {
			JobPref theOtherSP = (JobPref)theOther;
			// compare: 
			// 1. The initial serverValues 	
			// 2. The TotalValues 	
			// 3. The RelativeValues 	
			
			
			int thisID=job.getId(); int theOtherId=theOtherSP.getJob().getId();
		
					if(serverValues==theOtherSP.getServerValues()) {
						if(relativeValues==theOtherSP.getRelativeValues()) {

							return -1 * (int)  Math.round(totalValues - theOtherSP.getTotalValues());
						}
						else{

							return -1 * (int) Math.round(relativeValues - theOtherSP.getRelativeValues());
						}
							
					}
					else {
			
						return (int) Math.round(serverValues - theOtherSP.getServerValues());
					}

			
		}
		else {
			return -1;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object theOther) {
		if (theOther instanceof JobPref) {
			if (compareTo(theOther)==0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * @return relativeValues
	 */
	public double getRelativeValues() {
		return relativeValues;
	}

	/**
	 * @return serverValues
	 */
	public double getServerValues() {
		return serverValues;
	}

	/**
	 * @return totalValues
	 */
	public double getTotalValues() {
		return totalValues;
	}
	
	
}
