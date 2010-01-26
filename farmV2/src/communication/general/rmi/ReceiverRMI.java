package communication.general.rmi;

import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.apache.log4j.Logger;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;

/**
 * @author Christoph BEck
 * 
 * Connector class tot connect the interfaces IReceiverRMI, ISender to tunnel ISender over RMI
 *
 */
public class ReceiverRMI  extends UnicastRemoteObject implements IReceiverRMI, ISender {

	private static final long serialVersionUID = -6897000242465890129L;
	private IReceiver myReceiver;
	protected ISenderRMI mySenderRMI;
	private Registry registry;
	private int receiverID = -1;

	private static Logger logger = Logger.getLogger( ReceiverRMI.class );



	public ReceiverRMI(IReceiver myReceiver, String ip) throws RemoteException {
		super();		
		this.myReceiver = myReceiver;
		init(ip);

	}

	//************************************
	// IReceiverRMI - methods
	//************************************



	/* (non-Javadoc)
	 * @see communication.general.rmi.IReceiverRMI#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) throws RemoteException {
		myReceiver.abortSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.IReceiverRMI#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) throws RemoteException {
		myReceiver.loadSim(receiverID, tr);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.IReceiverRMI#shutDown(int)
	 */
	public void shutDown(int receiverID) throws RemoteException {
		myReceiver.shutDown(receiverID);

	}

	/* (non-Javadoc)
	 * @see communication.general.rmi.IReceiverRMI#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) throws RemoteException {		
		myReceiver.startSim(receiverID, trID);

	}





	//************************************
	// ISender-methods
	//************************************

	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		try {
			mySenderRMI.abortedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID,  String message) {
		try {
			mySenderRMI.addSimLogMessage(receiverID, trID,  message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simReult) {
		try {
			mySenderRMI.addSimResult(receiverID, trID,  simReult);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		try {
			mySenderRMI.finishedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		try {
			mySenderRMI.loadedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
		try {
			this.receiverID = mySenderRMI.register(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Hey, now I am registred as client #!"+receiverID);

		return this.receiverID;
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		try {
			mySenderRMI.addSimStatistics(receiverID, trID, stats);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState) {
		try {
			mySenderRMI.setReceiverState(receiverID, receiverState);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		try {
			mySenderRMI.setSimState(receiverID,trID, simState);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		try {
			mySenderRMI.startedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		try {
			mySenderRMI.unregister(receiverID, initID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//************************************
	// helper-methods
	//************************************


	/**
	 * @param ip ip to connect to
	 */
	private void init(String ip) {
		getRegistry(ip);

		try {
			mySenderRMI = (ISenderRMI) registry.lookup(ISenderRMI.serviceName);
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
	 * @param masterIP the ip of the rmi registry
	 */
	private void getRegistry (String masterIP) {

		try {
			registry = LocateRegistry.getRegistry(masterIP);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
