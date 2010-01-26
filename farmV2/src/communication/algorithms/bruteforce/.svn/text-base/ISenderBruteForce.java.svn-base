package communication.algorithms.bruteforce;

import simobjects.SimResult;
import simobjects.SimStatistics;
import simobjects.algorithms.bruteforce.BfMatrix;

/**
 * Interface for methods called from SimWorker on SimMaster 
 * 
 * @author  Dirk Holzapfel
 * @version 1.0
 */
public interface ISenderBruteForce {


	/**
	 * returns the next BfMatrix to calculate
	 * 
	 * @return the BfMatrix to calculate
	 */
	public BfMatrix getNextPackage();	


	/**
	 * SimWorker signalizes: finished Sim!
	 * 
	 * @param receiverID
	 * @param trID
	 */
	public void finishedSim (int receiverID, int trID);


	/**
	 * adds the SimStatistic from the SimWorker to the composited one at SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param stats
	 */
	public void addSimStatistics (int receiverID, int trID, SimStatistics stats);


	/**
	 * SimWorker registers at SimMaster
	 * 
	 * @param initID
	 * @return ID of SimWorker
	 */
	public int register	(long initID);


	/**
	 * SimWorker unregisters at SimMaster
	 * 
	 * @param receiverID
	 * @param initID
	 */
	public void	unregister (int receiverID, long initID);


	/**
	 * a SimLogMessage is sent to SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param message
	 */
	public void	addSimLogMessage (int receiverID, int trID, String message);


	/**
	 * the best found SimResult is given to SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param simReult
	 */
	public void	addSimResult (int receiverID, int trID, SimResult simReult);


	/**
	 * SimWorker signalizes: started Sim!
	 * 
	 * @param receiverID
	 * @param trID
	 */
	public void startedSim (int receiverID, int trID);


}//end of interface
