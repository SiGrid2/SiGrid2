package farm.worker;

import communication.general.IReceiver;
import communication.general.ISender;
import communication.general.rmi.ReceiverRMI;
import farm.objects.ReceiverState;
import farm.worker.bruteforce.SimWorker;
import java.rmi.RemoteException;
import java.util.Random;

import org.apache.log4j.Logger;

import simobjects.Algorithm;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;



/**
 * @author Christoph Beck
 * 
 * Manages conection to FarmMaster and controlls the adequate SimWorker 
 * 
 */
public class FarmWorker implements ISender, IReceiver{

	private ReceiverRMI myFarmReceiver;
	private int myReceiverID = -1;
	private String ip;
	private IReceiver simWorker;


	private static Logger logger = Logger.getLogger( FarmWorker.class );


	/**
	 * @param ip
	 */
	public FarmWorker(String ip) {
		super();
		this.ip = ip;

		System.out.println("I am trying to connect..."+ip+"..");
		try {
			myFarmReceiver = new ReceiverRMI(this, ip);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//from core:

	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		myFarmReceiver.abortedSim(myReceiverID, trID);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		myFarmReceiver.addSimLogMessage(myReceiverID, trID, message);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simReult) {
		myFarmReceiver.addSimResult(myReceiverID, trID, simReult);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		logger.info("core finished sim. farmworker #" + myReceiverID);
		myFarmReceiver.finishedSim(myReceiverID, trID);
		simWorker = null; //more RMI trennen???
		System.err.println("SimWorker killed");
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID,  int trID) {
		logger.info("loadedSim()");
		myFarmReceiver.loadedSim(myReceiverID, trID);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
		myReceiverID = myFarmReceiver.register(0);
		logger.info("receiver has registered. id #"+myReceiverID);
		return myReceiverID;
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		myFarmReceiver.unregister(myReceiverID, initID);
		logger.info("receiver has unregistered. id #"+myReceiverID);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID,  SimStatistics stats) {

		myFarmReceiver.addSimStatistics(receiverID, trID,  stats);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState farmState) {
		myFarmReceiver.setReceiverState(myReceiverID, farmState);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		myFarmReceiver.setSimState(myReceiverID, trID, simState);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		logger.info("startedSim()");
		myFarmReceiver.startedSim(myReceiverID, trID);
	}



	//********************************************

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		logger.info("receiver #"+receiverID + " abortSim()");

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		
		if(tr.getAlgorithm().getId() == Algorithm.BRUTEFORCE) { //bruteforce
			logger.info("receiver #"+receiverID + " loadSim()");
			simWorker = new SimWorker(this, ip);
		}
		else if(tr.getAlgorithm().getId() == Algorithm.PARETO) { //pareto
			simWorker = new farm.worker.pareto.SimWorker(this, ip);
		}
		simWorker.loadSim(receiverID, tr);
	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		logger.info("receiver #"+receiverID + " shutDown()");

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID,int trID) {
		logger.info("receiver #"+receiverID + " startSim()");
		simWorker.startSim(receiverID, trID);
		
	}


	/**
	 * @return the ID of this receiver
	 */
	public int getMyReceiverID() {
		return myReceiverID;
	}


}
