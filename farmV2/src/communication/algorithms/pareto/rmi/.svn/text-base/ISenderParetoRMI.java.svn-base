package communication.algorithms.pareto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


import simobjects.Server;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;


/**
 * Interface for RMI-Methods called from SimWorker on SimMaster 
 * 
 * @author Dirk Holzapfel
 * @version 1.0  
 */
public interface ISenderParetoRMI extends Remote{

	final static String serviceName = "Pareto-Sender";


	/**
	 * the SimWorker registers at the SimMaster
	 * 
	 * @param iReceiverParetoRMI
	 * @return ID of SimWorker
	 * @throws RemoteException
	 */
	public int 		register			(IReceiverParetoRMI iReceiverParetoRMI) throws RemoteException;


	/**
	 * the SimWorker unregisters at the SimMaster
	 * 
	 * @param receiverID
	 * @throws RemoteException
	 */
	public void 	unregister			(int receiverID) throws RemoteException;


	/**
	 * the SimWorker sends a SimLogMessage to the SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param message
	 * @throws RemoteException
	 */
	public void 	addSimLogMessage	(int receiverID, int trID, String message) throws RemoteException;



	/**
	 * the SimWorker gets new pareto-data to calculate from the SimMaster
	 * 
	 * @return a ParetoTransportData-Object to calculate
	 * @throws RemoteException
	 */
	public ParetoTransportData getParetoDataToCalculate()throws RemoteException;	


	/**
	 * the SimWorker delivers the results of an optimisation to the SimMaster
	 * 
	 * @param ppVec vector of pareto-points
	 * @param bestParetoPoint the pareto-point with highest earning in ppVec
	 * @param server the storage network the optimization was made for
	 * @throws RemoteException
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server)throws RemoteException;

}//end of interface
