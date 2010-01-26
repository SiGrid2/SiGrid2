package communication.general;

import simobjects.TestRun;

/**
 * @author Christoph beck
 * specifies the interface used to start simulations. 
 * Is used by a class implementing ISender
 */
public interface IReceiver {

	/**
	 * @param receiverID the reciever which should load the simulation
	 * @param tr the simulation to be loaded
	 */
	public void loadSim   (int receiverID, TestRun tr);
	
	
	/**
	 * @param receiverID the reciever which should start the simulation
	 * @param trID the simulation to be started
	 */
	public void startSim  (int receiverID, int trID);
	
	
	/**
	 * @param receiverID the reciever which should aborted the simulation
	 * @param trID the simulation to be aborted
	 */
	public void abortSim  (int receiverID, int trID);
	
	
	/**
	 * @param receiverID the reciever which should shut down 
	 */
	public void shutDown  (int receiverID);


}
