package controler.communication.farm.axis;

import simobjects.transport.farm.ReceiverStateAxis;
import simobjects.transport.farm.SimResultAxis;
import simobjects.transport.farm.SimStateAxis;
import simobjects.transport.farm.SimStatisticsAxis;
import simobjects.transport.farm.TestrunAxis;

/**
 * Interface for communication between Farm and Controller
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public interface IFarmWS {

	/**
	 * with this method a farm asks the controller intermittent for new orders
	 * 
	 * @param receiverID ID of Farm
	 * @return integer-coded code of instruction
	 */
	public int getActionFromControler(int receiverID);


	/**
	 * if there is a new testrun for a farm on the controller, the testrun
	 * can be load with this method
	 * 
	 * @param receiverID ID of Farm
	 * @return the Testrun to simulate
	 */
	public TestrunAxis getTestRun(int receiverID);


	/**
	 * the farm signalizes: simulation is loaded
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void loadedSim(int receiverID, int trID);


	/**
	 * the farm signalizes: simulation is started
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void startedSim(int receiverID, int trID);


	/**
	 * the farm signalizes: simulation is aborted
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void abortedSim(int receiverID, int trID);


	/**
	 * the farm signalizes: simulation is finished
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void finishedSim(int receiverID, int trID);


	/**
	 * the farm delivers the calculated results of a simulation
	 * to the controller
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param simResult the calculated SimResult
	 */
	public void addSimResult(int receiverID, int trID, SimResultAxis simResult);


	/**
	 * 
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param message 
	 */
	public void addSimLogMessage(int receiverID, int trID, String message);


	/**
	 * the farm delivers the cumulated SimStatistic of a simulation
	 * to the controller (only if algorithm = bruteforce)
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param stats the calculated SimStatistic
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatisticsAxis stats);


	/**
	 * the farm sets its actual SimState
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param simState the actual SimState
	 */
	public void setSimState(int receiverID, int trID, SimStateAxis simState);


	/**
	 * the farm sets its actual ReceiverState
	 * 
	 * @param receiverID ID of Farm
	 * @param receiverState the actual ReceiverState
	 */
	public void setReceiverState(int receiverID, ReceiverStateAxis receiverState);


	/**
	 * to be available in the system, a farm has to register
	 * 
	 * @param initID
	 * @param name name of Farm
	 * @return ID of Farm
	 */
	public int register(long initID, String name);


	/**
	 * to be available in the system, a farm has to unregister
	 * 
	 * @param receiverID ID of Farm
	 * @param initID
	 */
	public void unregister(int receiverID, long initID);


}//end of interface