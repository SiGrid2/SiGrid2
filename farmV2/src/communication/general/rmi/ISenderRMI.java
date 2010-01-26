package communication.general.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import farm.objects.ReceiverState;

import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
/**
 * @author Christoph Beck
 * Interface that is used by the simMessageBus to tunnel the standard ISender Interface through RMI
 */
public interface ISenderRMI extends Remote{

	final static String serviceName = "RMI-Farm-Sender";

	/**
	  * @param receiverID id of the farm that has finished loading a simulation
	 * @param trID id of the simulation loaded
	 * @throws RemoteException
	 */
	public void loadedSim   (int receiverID, int trID) throws RemoteException;
	
	
	/**
	 * @param receiverID id of the farm that has finished starting a simulation
	 * @param trID id of the simulation started
	 * @throws RemoteException
	 */
	public void startedSim  (int receiverID, int trID) throws RemoteException;
	
	
	/**
* @param receiverIDid of the farm that has finished laborting a simulation
	 * @param trID id of the simulation aborted
	 * @throws RemoteException
	 */
	public void abortedSim  (int receiverID, int trID) throws RemoteException;
	
	
	/**
		* @param receiverID id of the farm that has finished simulation
	 * @param trID id of the simulation finshed
	 * @throws RemoteException
	 */
	public void finishedSim (int receiverID, int trID) throws RemoteException;

	/**
		 * @param receiverID id of the farm that is adding a simresult 
	 * @param trID id of the simulation running
	 * @param simResult the simResult
	 * @throws RemoteException
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult) throws RemoteException;
	
	
	/**
		* @param receiverID the farm that is adding a SimLogMessage 
	 * @param trID id of the simulation loaded
	 * @param message the SimLogMessage 
	 * @throws RemoteException
	 */
	public void addSimLogMessage (int receiverID, int trID, String message) throws RemoteException;

	/**
* @param receiverID the farm that is updating the simState 
	 * @param trID id of the simulation loaded
	 * @param simState the new state
	 * @throws RemoteException
	 */
	public void setSimState(int receiverID, int trID,  SimState simState) throws RemoteException;
	
	
	/**
 * @param receiverID the farm that is updating the ReceiverState
	 * @param receiverState the new receiver state
	 * @throws RemoteException
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState) throws RemoteException;

	/**
	 * @param initID a challenge id from the farm that wants to register
	 * @return a farm id
	 * @throws RemoteException
	 */
	public int 	register(IReceiverRMI iReceiver) throws RemoteException;
	
	
	/**
	 * @param receiverID the farm that unregistering
	  * @param initID the id which was the initial challege id when first registering
	 * @throws RemoteException
	 */
	public void unregister(int receiverID, long initID) throws RemoteException;

	/**
	 * @param receiverID the farm that is adding addSimStatistics
	 * @param trID id of the simulation loaded
	 * @param stats the simStatistics
	 * @throws RemoteException
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) throws RemoteException;

}
