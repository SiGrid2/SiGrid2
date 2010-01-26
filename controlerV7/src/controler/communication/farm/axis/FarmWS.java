/**
 * FarmWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package controler.communication.farm.axis;

/**
 * Interface for communication between Farm and Controller
 * This file was auto-generated from WSDL * 
 */
public interface FarmWS extends java.rmi.Remote {


	/**
	 * to be available in the system, a farm has to register
	 * 
	 * @param initID
	 * @param name name of Farm
	 * @return ID of Farm
	 */
	public int register(long initID, java.lang.String name) throws java.rmi.RemoteException;


	/**
	 * to be available in the system, a farm has to unregister
	 * 
	 * @param receiverID ID of Farm
	 * @param initID
	 */
	public void unregister(int receiverID, long initID) throws java.rmi.RemoteException;


	/**
	 * with this method a farm asks the controller intermittent for new orders
	 * 
	 * @param receiverID ID of Farm
	 * @return integer-coded code of instruction
	 */
	public int getActionFromControler(int receiverID) throws java.rmi.RemoteException;


	/**
	 * if there is a new testrun for a farm on the controller, the testrun
	 * can be load with this method
	 * 
	 * @param receiverID ID of Farm
	 * @return the Testrun to simulate
	 */
	public simobjects.transport.farm.TestrunAxis getTestRun(int receiverID) throws java.rmi.RemoteException;


	/**
	 * the farm signalizes: simulation is loaded
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void loadedSim(int receiverID, int trID) throws java.rmi.RemoteException;


	/**
	 * the farm signalizes: simulation is started
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void startedSim(int receiverID, int trID) throws java.rmi.RemoteException;


	/**
	 * the farm signalizes: simulation is aborted
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void abortedSim(int receiverID, int trID) throws java.rmi.RemoteException;


	/**
	 * the farm signalizes: simulation is finished
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 */
	public void finishedSim(int receiverID, int trID) throws java.rmi.RemoteException;


	/**
	 * the farm delivers the calculated results of a simulation
	 * to the controller
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param simResult the calculated SimResult
	 */
	public void addSimResult(int receiverID, int trID, simobjects.transport.farm.SimResultAxis simResult) throws java.rmi.RemoteException;


	/**
	 * 
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param message 
	 */
	public void addSimLogMessage(int receiverID, int trID, java.lang.String message) throws java.rmi.RemoteException;


	/**
	 * the farm delivers the cumulated SimStatistic of a simulation
	 * to the controller (only if algorithm = bruteforce)
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param stats the calculated SimStatistic
	 */
	public void addSimStatistics(int receiverID, int trID, simobjects.transport.farm.SimStatisticsAxis stats) throws java.rmi.RemoteException;


	/**
	 * the farm sets its actual SimState
	 * 
	 * @param receiverID ID of Farm
	 * @param trID ID of actual Testrun
	 * @param simState the actual SimState
	 */
	public void setSimState(int receiverID, int trID, simobjects.transport.farm.SimStateAxis simState) throws java.rmi.RemoteException;


	/**
	 * the farm sets its actual ReceiverState
	 * 
	 * @param receiverID ID of Farm
	 * @param receiverState the actual ReceiverState
	 */
	public void setReceiverState(int receiverID, simobjects.transport.farm.ReceiverStateAxis receiverState) throws java.rmi.RemoteException;


}//end of interface
