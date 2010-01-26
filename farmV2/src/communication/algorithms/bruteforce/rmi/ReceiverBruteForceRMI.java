package communication.algorithms.bruteforce.rmi;

import communication.algorithms.bruteforce.ISenderBruteForce;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import simobjects.SimResult;
import simobjects.SimStatistics;
import simobjects.algorithms.bruteforce.BfMatrix;


/**
 * Class implementing the RMI-interface for communication from SimWorker to SimMaster. 
 * Also RMI init stuff.  
 * 
 * @author  Dirk Holzapfel
 * @version 1.0
 */
public class ReceiverBruteForceRMI implements IReceiverBruteForceRMI, ISenderBruteForce {

	private ISenderBruteForceRMI mySenderBruteForceRMI;
	private Registry registry;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public ReceiverBruteForceRMI(String ip) {
		init(ip);
	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#getNextPackage()
	 */
	public BfMatrix getNextPackage() {
		try {
			return mySenderBruteForceRMI.getNextPackage();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		try {
			mySenderBruteForceRMI.addSimStatistics(receiverID, trID,  stats);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		try {
			mySenderBruteForceRMI.finishedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#register(long)
	 */
	public int register(long initID) {
		try {
			System.out.println("Receiverbfrmi register");
			return mySenderBruteForceRMI.register(initID);
		} catch (RemoteException e) {
			return -1;
		}
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		try {
			mySenderBruteForceRMI.unregister(receiverID, initID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID,  SimResult simReult) {
		try {
			mySenderBruteForceRMI.addSimResult(receiverID, trID,  simReult);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		try {
			mySenderBruteForceRMI.addSimLogMessage(receiverID, trID,  message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		try {
			mySenderBruteForceRMI.startedSim(receiverID, trID);
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
			mySenderBruteForceRMI = (ISenderBruteForceRMI) registry.lookup(ISenderBruteForceRMI.serviceName);
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
