package farm.worker.gsp;

import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;

import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;
import farm.worker.FarmWorker;


/**
 * @author Christoph Beck
 * @version 1.0
 */

public class SimWorker implements ISender, IReceiver{
	private FarmWorker myFarmWorker;
	private WorkerCore workerCore;	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimWorker(FarmWorker myFarmWorker, String ip, String gna) {
		super();
		this.myFarmWorker = myFarmWorker;
	}

	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		myFarmWorker.abortedSim(receiverID, trID)	;
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		workerCore = new WorkerCore (this);
		workerCore.loadSim(receiverID, tr);
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		workerCore.shutDown(receiverID);
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		workerCore.startSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		myFarmWorker.addSimLogMessage(receiverID, trID, message);
	}


	
	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		myFarmWorker.abortedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult) {
		myFarmWorker.addSimResult(receiverID, trID, simResult);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		//		 not applicable		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		myFarmWorker.finishedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		myFarmWorker.loadedSim(receiverID, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
//		 not applicable	
		return 0;
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState) {
		//		 not applicable	
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		//		 not applicable	
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		myFarmWorker.startedSim(receiverID, trID);		
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		// not applicable		
	}

}//end of class
