package communication.algorithms.pareto.rmi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


import simobjects.Server;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;

import communication.algorithms.pareto.IReceiverPareto;
import communication.algorithms.pareto.ISenderPareto;


/**
 * Class implementing the RMI-interface for communication from SimWorker to SimMaster. 
 * Also RMI init stuff.  
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ReceiverParetoRMI extends UnicastRemoteObject implements IReceiverParetoRMI, ISenderPareto{

	private static final long serialVersionUID = -858263915919885182L;

	private Registry registry;

	private IReceiverPareto receiverPareto;
	private ISenderParetoRMI senderParetoRMI;




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public ReceiverParetoRMI(IReceiverPareto receiverPareto, String ip) throws RemoteException {
		super();
		this.receiverPareto = receiverPareto;

		init(ip);
	}	




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* ***********************************
	 * delegate-methods to SimMaster
	 ************************************/	

	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		// not needed

	}



	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#register()
	 */
	public int register() {
		try {
			return senderParetoRMI.register(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}



	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#unregister(int)
	 */
	public void unregister(int receiverID) {
		// not implemented yet

	}



	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#getParetoDataToCalculate()
	 */
	public ParetoTransportData getParetoDataToCalculate() {
		try {
			return senderParetoRMI.getParetoDataToCalculate();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}




	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#setCalculatedPareto(java.util.Vector, simobjects.algorithms.pareto.ParetoPoint, simobjects.Server)
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server) {
		try {
			senderParetoRMI.setCalculatedPareto(ppVec, bestParetoPoint, server);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * establishes a connection to the RMI-registry
	 * 
	 * @param ip the IP adress of the RMI-registry 
	 */
	private void init(String ip) {
		getRegistry(ip);

		try {
			senderParetoRMI = (ISenderParetoRMI) registry.lookup(ISenderParetoRMI.serviceName);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * gets the RMI-registry under a certain IP
	 * 
	 * @param masterIP the IP adress of the RMI-registry 
	 */
	private void getRegistry (String masterIP) {
		try {
			registry = LocateRegistry.getRegistry(masterIP);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}//end of class
