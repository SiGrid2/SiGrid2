package simobjects.algorithms.gsp.helper.server;

import simobjects.Server;

/**
 * @author Christoph Beck
 *
 */
public class ServerPref implements Comparable{
	private Server server;
	private int rank;
	private int version;
	private double jobValues;
	private double relativeValues;
	private double totalValues;
	private boolean simple;
	

	/**
	 * constructor for class ServerPref
	 * @param server
	 * @param version
	 * @param jobValues
	 * @param relativeValues
	 * @param totalValues
	 */
	public ServerPref(Server server, int version, double jobValues, double relativeValues, double totalValues) {
		super();
		this.server = server;
		this.rank = 0;
		this.version = version;
		this.jobValues = cut(jobValues);
		this.relativeValues = cut(relativeValues);
		this.totalValues = cut(totalValues);
		this.simple = false;
	}
	
	/**
	 * constructor for class ServerPref
	 * @param server
	 * @param version
	 * @param rank
	 */
	public ServerPref(Server server, int version, int rank) {
		super();
		this.server = server;
		this.rank = rank;
		this.version = version;
		this.jobValues = 0;
		this.relativeValues = 0;
		this.totalValues = 0;
		this.simple = true;
	}

	/**
	 * constructor for class ServerPref
	 */
	public ServerPref() {
		this(null, 0 , 0,0,0);
	}
	/**
	 * @return rank
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
	/**
	 * @return
	 */
	public Server getServer() {
		return server;
	}
	/**
	 * @param server
	 */
	public void setServer(Server server) {
		this.server = server;
	}
	/**
	 * @return
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object theOther) {
		if (theOther instanceof ServerPref) {
			ServerPref theOtherSP = (ServerPref)theOther;
			// check: 
			// 1. The version 		(last asked first)
			// 1. The initial rank 	(lowest first)
			// 3. The job id 		(this should never happen, as the rank is already uniq)
			if(this.getVersion()==theOtherSP.getVersion()) {
				if(this.rank==theOtherSP.getRank()) {
					if(jobValues==theOtherSP.getJobValues()) {
						if(relativeValues==theOtherSP.getRelativeValues()) {
						
							return -1 * (int)  Math.round(totalValues - theOtherSP.getTotalValues());
						}
						else{
						
							return -1 * (int) Math.round(relativeValues - theOtherSP.getRelativeValues());
						}
							
					}
					else {
						
						return (int) Math.round(jobValues - theOtherSP.getJobValues());
					}
				}
				else {
					return this.rank-theOtherSP.getRank();
				}
			}
			else {
				return this.getVersion() - theOtherSP.getVersion();
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

		if (compareTo(theOther)==0) {
			return true;
		}
		return false;
	}
	private double cut (double d){
		double cut = 1;
		double tmp = Math.round(d*cut);
		return tmp/cut;
	}

	public double getJobValues() {
		return jobValues;
	}

	public double getRelativeValues() {
		return relativeValues;
	}

	public double getTotalValues() {
		return totalValues;
	}

	public boolean isSimple() {
		return simple;
	}
}
