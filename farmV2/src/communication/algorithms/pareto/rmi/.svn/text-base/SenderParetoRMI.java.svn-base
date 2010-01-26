package communication.algorithms.pareto.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import simobjects.Server;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;

import communication.algorithms.pareto.IReceiverPareto;
import communication.algorithms.pareto.ISenderPareto;


/**
 * Class implementing the RMI-interface for communication from SimMaster to SimWorker and the 
 * delegate methods from SimWorker to SimMaster. Also RMI init stuff. 
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SenderParetoRMI implements ISenderParetoRMI, IReceiverPareto{

	private ISenderPareto simMaster;
	private Registry registry;
	private Map<Integer, IReceiverParetoRMI> myReceivers;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SenderParetoRMI(ISenderPareto simMaster) {
		super();
		this.simMaster = simMaster;
		this.myReceivers = new HashMap<Integer, IReceiverParetoRMI>();
		init();
		System.out.println("SenderParetoRMI started");
	}

	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* *****************************
	 * delegate methods to SimMaster
	 ******************************/	



	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.rmi.ISenderParetoRMI#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) throws RemoteException {
		// not needed		
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.rmi.ISenderParetoRMI#getParetoDataToCalculate()
	 */
	public ParetoTransportData getParetoDataToCalculate() throws RemoteException {
		return simMaster.getParetoDataToCalculate();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.rmi.ISenderParetoRMI#setCalculatedPareto(java.util.Vector, simobjects.algorithms.pareto.ParetoPoint, simobjects.Server)
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server) throws RemoteException {
		simMaster.setCalculatedPareto(ppVec, bestParetoPoint, server);

	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.rmi.ISenderParetoRMI#register(communication.algorithms.pareto.rmi.IReceiverParetoRMI)
	 */
	public int register(IReceiverParetoRMI iReceiverParetoRMI) throws RemoteException {
		int id = simMaster.register();		
		myReceivers.put(id, iReceiverParetoRMI);		
		System.out.println("a paretoworker registered. got id " + id);

		return id;
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.rmi.ISenderParetoRMI#unregister(int)
	 */
	public void unregister(int receiverID) throws RemoteException {
		simMaster.unregister(receiverID);
		myReceivers.remove(receiverID);		
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
	 * 
	 */
	private void init() {
		registry = startRegistry();
		bindMeTo(registry);

		//time for the Simworkers to register
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
		try
		{
			ISenderParetoRMI stub = (ISenderParetoRMI) UnicastRemoteObject.exportObject( this, 0);

			try
			{
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
			System.out.println("unbind SenderPareto from RMI-Registry");
			registry.unbind(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}//end of class
