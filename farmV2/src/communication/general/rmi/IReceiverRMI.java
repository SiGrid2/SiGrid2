package communication.general.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import simobjects.TestRun;

/**
 * @author Christoph Beck
 * Interface that is used by the simMessageBus to tunnel the standard IReceiver Interface through RMI
 */
public interface IReceiverRMI extends Remote{

	/**
	 * @param receiverID the reciever which should load the simulation
	 * @param tr the simulation to be loaded
	 * @throws RemoteException
	 */
	public void loadSim   (int receiverID, TestRun tr) throws RemoteException;
	/**
	 * @param receiverID the reciever which should start the simulation
	 * @param trID the simulation to be started
	 * @throws RemoteException
	 */
	public void startSim  (int receiverID, int trID) throws RemoteException;
	/**
	 * @param receiverID the reciever which should aborted the simulation
	 * @param trID the simulation to be aborted
	 * @throws RemoteException
	 */
	public void abortSim  (int receiverID, int trID) throws RemoteException;
	/**
	 * @param  receiverID the reciever which should shut down 
	 * @throws RemoteException
	 */
	public void shutDown  (int receiverID) throws RemoteException;

}
