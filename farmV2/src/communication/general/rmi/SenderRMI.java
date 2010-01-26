package communication.general.rmi;

import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;

public class SenderRMI implements ISenderRMI, IReceiver {

	private ISender mySender;
	private Registry registry;

	private Map<Integer, IReceiverRMI> myReceivers;






	public SenderRMI(ISender mySender) {
		super();
		this.mySender = mySender;
		myReceivers = new HashMap<Integer, IReceiverRMI>();

		init();
	}

	//************************************
	// ISenderRMI-methods
	//************************************

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) throws RemoteException {
		mySender.abortedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message)
			throws RemoteException {
		mySender.addSimLogMessage(receiverID, trID, message);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simReult)
			throws RemoteException {
		mySender.addSimResult(receiverID, trID, simReult);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) throws RemoteException {
		mySender.finishedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) throws RemoteException {
		mySender.loadedSim(receiverID, trID);

	}


	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState farmState)
			throws RemoteException {
		mySender.setReceiverState(receiverID, farmState);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState)
			throws RemoteException {
		mySender.setSimState(receiverID, trID, simState);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) throws RemoteException {
		mySender.startedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#register(communication.general.rmi.IReceiverRMI)
	 */
	public synchronized int register(IReceiverRMI iReceiver) throws RemoteException {
		int id = mySender.register(new Random().nextInt());
		myReceivers.put(id, iReceiver);

		return id;
	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#unregister(int, long)
	 */
	public synchronized void unregister(int receiverID, long initID) throws RemoteException {
		mySender.unregister(receiverID, initID);
		myReceivers.remove(receiverID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.ISenderRMI#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) throws RemoteException {
		mySender.addSimStatistics(receiverID, trID, stats);
	}

    //************************************
	// IReceiver-methods
	//************************************

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		try {
			getReceiverByID(receiverID).abortSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		try {
			getReceiverByID(receiverID).loadSim(receiverID, tr);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		try {
			getReceiverByID(receiverID).shutDown(receiverID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		try {			
			getReceiverByID(receiverID).startSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	//************************************
	// helper-methods
	//************************************

	/**
	 * @param id
	 * @return
	 */
	private IReceiverRMI getReceiverByID(int id){
		return myReceivers.get(id);
	}

	/**
	 *  used to init the Sender RMI
	 */
	private void init() {
		registry = startRegistry();
		startServer();

		//time to register for the workers
		BufferedReader tastatur_input;
		tastatur_input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Press ENTER when all needed workers have registered.");
		String t;
		try {
			t = tastatur_input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void startServer () {
        bindMeTo(registry);
    }

    private void stopServer () {
        unbindMeFrom(registry);
        stopRegistry(registry);

    }


    private void bindMeTo (Registry registry) {
        try
        {

            ISenderRMI stub = (ISenderRMI) UnicastRemoteObject.exportObject( this, 1099);

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

    private void stopRegistry (Registry registry) {
        registry = null;
    }

    private void unbindMeFrom (Registry registry) {
        try {
            System.out.println("UNBIND");
            registry.unbind(serviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
