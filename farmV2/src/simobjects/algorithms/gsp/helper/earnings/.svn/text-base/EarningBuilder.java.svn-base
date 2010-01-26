package simobjects.algorithms.gsp.helper.earnings;

import simobjects.Layout;

/**
 * @author Christoph Beck
 *
 * used to calculate the entries of the earning matrix
 */
public class EarningBuilder {
	private Layout layout;
	private double [][] earningMatrix;
	private double [][] relEarningMatrix;

	/**
	 * constructor for class EarningBuilder
	 * @param layout
	 */
	public EarningBuilder(Layout layout) {
		this.layout = layout;
		init();
	}

	/**
	 *  inits the builder
	 */
	private void init() {
		earningMatrix 		= new double [layout.getNumServer()][layout.getNumJobs()];
		relEarningMatrix 	= new double [layout.getNumServer()][layout.getNumJobs()];
		
		
		int size;

		for (int iServer = 0; iServer < earningMatrix.length; iServer++) {
			for (int iJob = 0; iJob < earningMatrix[iServer].length; iJob++) {
				
				size = layout.getJob(iJob).getDasd() * layout.getJob(iJob).getDuration();
				
				earningMatrix[iServer][iJob] 	= getEarning (iServer, iJob);
				relEarningMatrix[iServer][iJob] = earningMatrix[iServer][iJob] / size;
				
			}
		}

	}

	/**
	 * @return
	 */
	public double[][] getRelEarningMatrix() {
		return relEarningMatrix;
	}

	/**
	 * @param serverID
	 * @param jobID
	 * @return
	 */
	public double getEarning (int serverID, int jobID) {
		double dasd 		= layout.getJob(jobID).getDasd();
		double duration		= layout.getJob(jobID).getDuration();
		double size			= dasd * duration;

		double cost			= layout.getServer(serverID).getCost();
		double earning		= layout.getJob(jobID).getEarning();

		double rawEarning = (earning-cost) * size;
		return (rawEarning - getPenalty(serverID, jobID));
	}

	/**
	 * @param serverID
	 * @param jobID
	 * @return
	 */
	public double getPenalty (int serverID, int jobID) {
		if(isBreach(serverID, jobID )) {
			return layout.getJob(jobID).getPenalty();
		}
		else {
			return 0;
		}
	}

	/**
	 * @param ServerID
	 * @param JobID
	 * @return
	 */
	public boolean isBreach (int ServerID, int JobID) {
		int srvSpd = layout.getServer(ServerID).getSpeedCat() ;
		int srvRel = layout.getServer(ServerID).getRelCat();

		int jobSpd = layout.getJob(JobID).getSpeedCat() ;
		int jobRel = layout.getJob(JobID).getRelCat();

		if (srvSpd > jobSpd) {
			return true;
		}

		if (srvRel > jobRel) {
			return true;
		}

		return false;
	}

	/**
	 * @return
	 */
	public double[][] getEarningMatrix() {
		return earningMatrix;
	}
	
	
}
