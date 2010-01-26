package communication.algorithms.bruteforce.rmi;

import communication.algorithms.bruteforce.ISenderBruteForce;
import farm.master.bruteforce.SimMaster;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import simobjects.SimResult;
import simobjects.SimStatistics;
import simobjects.algorithms.bruteforce.BfMatrix;


/**
 * Class implementing the RMI-interface for communication from SimMaster to SimWorker and the 
 * delegate methods from SimWorker to SimMaster. Also RMI init stuff. 
 * 
 * @author  Dirk Holzapfel
 * @version 1.0
 */
public class SenderBruteForceRMI implements ISenderBruteForceRMI {

	private ISenderBruteForce mySimMaster;
	private Registry registry;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SenderBruteForceRMI(SimMaster master) {
		mySimMaster = master;
		init();
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	//*****************************
	// ReceiverBruteForceRMI -> ISenderBruteForceRMI -> SimMaster
	//*****************************

	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#getNextPackage()
	 */
	public BfMatrix getNextPackage() throws RemoteException {
		return mySimMaster.getNextPackage();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) throws RemoteException {
		mySimMaster.addSimStatistics(receiverID, trID, stats);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) throws RemoteException {
		mySimMaster.startedSim(receiverID, trID);

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) throws RemoteException {
		mySimMaster.finishedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#register(long)
	 */
	public int register(long initID) throws RemoteException {
		// Until now, we do not need to do something RMI - specific here
		return mySimMaster.register(initID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) throws RemoteException {
		mySimMaster.unregister(receiverID, initID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) throws RemoteException {
		mySimMaster.addSimLogMessage(receiverID, trID, message);

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.rmi.ISenderBruteForceRMI#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simReult) throws RemoteException {
		mySimMaster.addSimResult(receiverID, trID, simReult);

	}


	/**
	 * unbinds the Pareto-Service and stops the RMI-Registry
	 */
	public void stopServer () {
		unbindMeFrom(registry);
		stopRegistry(registry);
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * starts a RMI-Registry and binds the Pareto-Service
	 */
	private void init() {
		registry = startRegistry();
		bindMeTo(registry);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * binds the pareto-Service to the RMI-Registry
	 * 
	 * @param registry
	 */
	private void bindMeTo (Registry registry) {
		try{
			ISenderBruteForceRMI stub = (ISenderBruteForceRMI) UnicastRemoteObject.exportObject( this, 0);
			try{
				/*unbind if the object had been bound before*/

				registry.unbind(serviceName);
				System.out.println("Deleted old entry for "+ serviceName);
			}
			catch (Exception e) {/*if unbind is not possible, do nothing*/}

			/*bind the Object to the registry*/
			registry.bind(serviceName, stub);
			System.err.println("Bound "+ serviceName +" to registry");
		}
		catch (Throwable e)
		{
			System.err.println("bindMeTo did not work:" + e.toString());
			e.printStackTrace();
		}
	}


	/**
	 * starts a new Registry, if there is still a running one tries to get that
	 * 
	 * @return the RMI-Registry
	 */
	private Registry startRegistry () {
		Registry registry = null;

		// check if there is registry running...
		try {
			registry = LocateRegistry.getRegistry();
			try {
				registry.list();
				return registry;
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (RemoteException e1) {
			System.out.println("Unable to get running registry (LocateRegistry.getRegistry() did not work)...");
			e1.printStackTrace();
		}

		// still here?
		// ok, this means, we are alone here. Try to start a new registry
		try
		{
			System.out.println("Trying to create a Registry...");
			LocateRegistry.createRegistry( Registry.REGISTRY_PORT);
			registry = LocateRegistry.getRegistry();
		}
		catch ( RemoteException e )
		{
			System.out.println("... ouups. That did not work.");
			e.printStackTrace();
			System.out.println("Ok. I'm out.");
			System.exit(0);
		}

		System.out.println("... that worked. Fine.");
		return registry;
	}


	/**
	 * stops the registry
	 * 
	 * @param registry
	 */
	private void stopRegistry (Registry registry) {
		registry = null;
	}


	/**
	 * unbinds the pareto-Service from the registry
	 * 
	 * @param registry
	 */
	private void unbindMeFrom (Registry registry) {
		try {
			System.out.println("UNBIND");
			registry.unbind(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}//end of class
