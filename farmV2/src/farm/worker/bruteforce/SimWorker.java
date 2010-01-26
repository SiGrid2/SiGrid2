package farm.worker.bruteforce;

import java.util.Random;
import communication.algorithms.bruteforce.ISenderBruteForce;
import communication.algorithms.bruteforce.rmi.ReceiverBruteForceRMI;
import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;
import farm.worker.FarmWorker;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.algorithms.bruteforce.BfMatrix;

/**
 * Bruteforce - SimWorker
 * ----------------------
 * connects via RMI to the SimMaster.
 * starts the WorkerCore, who calculates BFMatrix's
 * 
 * 
 * FarmMaster[1] <---------> FarmWorker[1..n]
 * 		|							|
 * 		|[1:1]						|[1:1]
 * 		|							|
 * SimMaster[1]  <---------> SimWorker[1..n]
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SimWorker implements ISender, IReceiver, ISenderBruteForce {

	private ReceiverBruteForceRMI mySimReceiver;
	private FarmWorker myFarmWorker;
	private WorkerCore myWorkerCore;
	private int receiverID;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimWorker(FarmWorker myFarmWorker, String ip) {
		super();
		this.myFarmWorker = myFarmWorker;
		mySimReceiver = new ReceiverBruteForceRMI(ip);
		register(new Random().nextLong()); //this is prototypic!!
	}





	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	//*****************************
	// WorkerCore -> ISenderBruteForce - > ReceiverBruteForceRMI
	//*****************************

	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#getNextPackage()
	 */
	public BfMatrix getNextPackage() {
		return mySimReceiver.getNextPackage();
	}


	//*****************************
	// WorkerCore -> ISender -> to FarmWorker
	//*****************************

	public void abortedSim(int receiverID, int trID) {
		myFarmWorker.abortedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		//mySimReceiver.finishedSim(receiverID,  trID);
		//myFarmWorker.finishedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		myFarmWorker.loadedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState farmState) {
		myFarmWorker.setReceiverState(receiverID, farmState);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		myFarmWorker.startedSim(receiverID, trID);
		//mySimReceiver.startedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
		receiverID = mySimReceiver.register(initID);
		return receiverID;
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		mySimReceiver.unregister(receiverID, initID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		myFarmWorker.setSimState(receiverID, trID, simState);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID,  SimResult simResult) {
		mySimReceiver.addSimResult(receiverID, trID,  simResult);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats)  {
		mySimReceiver.addSimStatistics(receiverID, trID, stats) ;
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		mySimReceiver.addSimLogMessage(receiverID,trID,  message);
	}



	//*****************************
	// FarmWorker -> IReceiver -> WorkerCore
	//*****************************

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		myWorkerCore = new WorkerCore(this, tr, receiverID);
		this.loadedSim(receiverID, tr.getId());
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		myWorkerCore.start();
		System.out.println("Worker start #"+myFarmWorker.getMyReceiverID());
	}


}//end of class
