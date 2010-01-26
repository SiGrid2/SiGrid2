package communication.general;

import farm.objects.ReceiverState;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;

/**
 * @author Christoph beck
 * specifies the interface used to pass on messages about simulations. 
 * Is used by a class implementing IReciver
 * 
 */
public interface ISender {

	/**
	 * @param receiverID id of the farm that has finished loading a simulation
	 * @param trID id of the simulation loaded
	 */
	public void loadedSim   (int receiverID, int trID);
	
	
	/**
	 * @param receiverID id of the farm that has finished starting a simulation
	 * @param trID id of the simulation started
	 */
	public void startedSim  (int receiverID, int trID);
	
	
	/**
	* @param receiverIDid of the farm that has finished laborting a simulation
	 * @param trID id of the simulation aborted
	 */
	public void abortedSim  (int receiverID, int trID);
	
	
	/**
	* @param receiverID id of the farm that has finished simulation
	 * @param trID id of the simulation finshed
	 */
	public void finishedSim (int receiverID, int trID);
	
	
	/**
	 * @param receiverID id of the farm that is adding a simresult 
	 * @param trID id of the simulation running
	 * @param simResult the simResult
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult);
	
	
	/**
	* @param receiverID the farm that is adding a SimLogMessage 
	 * @param trID id of the simulation loaded
	 * @param message the SimLogMessage 
	 */
	public void addSimLogMessage (int receiverID, int trID,  String message);

	/**
	* @param receiverID the farm that is updating the simState 
	 * @param trID id of the simulation loaded
	 * @param simState the new state
	 */
	public void setSimState(int receiverID, int trID, SimState simState);
	
	
	/**
	 * @param receiverID the farm that is updating the ReceiverState
	 * @param receiverState the new receiver state
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState);

	
	/**
	 * @param initID a challenge id from the farm that wants to register
	 * @return a farm id
	 */
	public int register(long initID);
	
		
	/**
	  * @param receiverID the farm that unregistering
	  * @param initID the id which was the initial challege id when first registering
	 */
	public void unregister(int receiverID, long initID);

	
	/**
	 * @param receiverID the farm that is adding addSimStatistics
	 * @param trID id of the simulation loaded
	 * @param stats the simStatistics
	 */
	public void addSimStatistics(int receiverID, int trID,  SimStatistics stats);	
	

}
