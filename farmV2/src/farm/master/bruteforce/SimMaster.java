package farm.master.bruteforce;

import communication.algorithms.bruteforce.ISenderBruteForce;
import communication.algorithms.bruteforce.rmi.SenderBruteForceRMI;
import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverList;
import farm.objects.ReceiverState;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.algorithms.bruteforce.BfMatrix;


/**
 * Bruteforce - SimMaster
 * ----------------------
 * opens a RMI-channel to the SimWorker and is the master for them.
 * starts the MasterCore, which provides the matrizes to calculate for the workers
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
public class SimMaster implements ISenderBruteForce, ISender, IReceiver {

	private MasterCore myMasterCore;
	private ISender myFarmMaster;
	private SenderBruteForceRMI mySimSender;
	private ReceiverList myWorker;
	private SimStatistics stats;	
	private long bestEarning = Long.MIN_VALUE;	
	private ProgressThread progressThread; 
	private SimResult bestSimResult;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * 	 * 
	 * @param farmMaster - the class that started this one, farmMaster has connection to the controler
	 */
	public SimMaster(ISender farmMaster) {

		this.myFarmMaster=farmMaster;
		mySimSender = new SenderBruteForceRMI(this);
		myWorker=new ReceiverList();
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	//*****************************
	// SenderBruteForceRMI -> ISenderBruteForce - > MasterCore
	//*****************************

	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#getNextPackage()
	 */
	public BfMatrix getNextPackage() {
		return myMasterCore.getNextPackage();
	}


	//******************************
	// Invoked from SenderBruteForceRMI mySimSender to accumulate
	// the sim statistics...
	//******************************

	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public synchronized void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		System.out.println("getting stats from " +receiverID);
		if (myWorker.addSimStatistics(receiverID)) {
			//System.out.println("adding...");
			this.stats.add(stats);
		}
		System.out.println("Statscomplete: " + myWorker.isStatsComplete() );
		if (myWorker.isStatsComplete()) {
			System.out.println("all worker statscomplete");
			myFarmMaster.addSimStatistics(0, trID, stats);
			finishedSim(0, trID);
		}
		System.out.println();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#finishedSim(int, int)
	 */
	@SuppressWarnings("deprecation")
	public synchronized void finishedSim(int receiverID, int trID) {

		myWorker.finishedSim(receiverID);

		System.out.println("in finished sim ****");
		mySimSender.stopServer();
		myFarmMaster.addSimResult(0, trID, bestSimResult);
		progressThread.stop(); //no more progress infos!
		myFarmMaster.finishedSim(0, trID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#register(long)
	 */
	public synchronized int register(long initID) {
		System.out.println("registered");
		return myWorker.register(initID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		myWorker.unregister(receiverID, initID);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		// there might be some filtering here later on...
		myFarmMaster.addSimLogMessage(receiverID, trID, message);
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID,int trID , SimResult simResult) {

		if (simResult.getEarning() > bestEarning){
			bestEarning = simResult.getEarning();
			bestSimResult = simResult;			
		}		
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.bruteforce.ISenderBruteForce#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		myFarmMaster.startedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID,int trID ) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID,int trID ) {
		myFarmMaster.loadedSim(receiverID, trID); // LOADEDSIM to farmMaster
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID , SimState simState) {
		myFarmMaster.setSimState(receiverID, trID, simState);
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID,int trID ) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		// starts the MasterCore, inits the SimStatistics
		myMasterCore = new MasterCore(this, tr);
		this.stats=new SimStatistics(tr.getStatsStart(),tr.getStatsEnd(), tr.getStatsNumFields());
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
		//	the ProgressThread is started and lets the MasterCore set the Simstate all xxx[ms]
		progressThread = new ProgressThread(myMasterCore, 10000); 
		progressThread.start();
		this.startedSim(0, trID);
	}


}//end of class
