package communication.algorithms.bruteforce.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import simobjects.SimResult;
import simobjects.SimStatistics;
import simobjects.algorithms.bruteforce.BfMatrix;

/**
 * Interface for RMI-Methods called from SimWorker on SimMaster
 * 
 * @author  Dirk Holzapfel
 * @version 1.0
 */
public interface ISenderBruteForceRMI extends Remote{

	final static String serviceName = "RMI-BruteForce-Sender";


	/**
	 * returns the next BfMatrix to calculate
	 * 
	 * @return the BfMatrix to calculate
	 * @throws RemoteException
	 */
	public BfMatrix	getNextPackage () throws RemoteException;


	/**
	 * adds the SimStatistic from the SimWorker to the composited one at SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param stats
	 * @throws RemoteException
	 */
	public void addSimStatistics (int receiverID, int trID, SimStatistics stats) throws RemoteException;


	/**
	 * SimWorker signalizes: finished Sim!
	 * 
	 * @param receiverID
	 * @param trID
	 * @throws RemoteException
	 */
	public void	finishedSim (int receiverID, int trID) throws RemoteException;


	/**
	 * SimWorker registers at SimMaster
	 * 
	 * @param initID
	 * @return ID of SimWorker
	 * @throws RemoteException
	 */
	public int register (long initID) throws RemoteException;


	/**
	 * SimWorker unregisters at SimMaster
	 * 
	 * @param receiverID
	 * @param initID
	 * @throws RemoteException
	 */
	public void	unregister (int receiverID, long initID) throws RemoteException;


	/**
	 * the best found SimResult is given to SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param simReult
	 * @throws RemoteException
	 */
	public void addSimResult (int receiverID, int trID, SimResult simReult) throws RemoteException;


	/**
	 * a SimLogMessage is sent to SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param message
	 * @throws RemoteException
	 */
	public void	addSimLogMessage (int receiverID, int trID, String message) throws RemoteException;


	/**
	 * SimWorker signalizes: started Sim!
	 * 
	 * @param receiverID
	 * @param trID
	 * @throws RemoteException
	 */
	public void	startedSim (int receiverID, int trID) throws RemoteException;


}//end of interface
