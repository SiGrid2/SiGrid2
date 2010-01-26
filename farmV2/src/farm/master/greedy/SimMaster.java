package farm.master.greedy;

import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;


/**
 * Greedy - SimMaster
 * ----------------------
 * starts the MasterCore, which calcualtes the best Greedy-Result
 * 
 * 
 * FarmMaster[1] <---------> FarmWorker[1..n]
 * 		|							
 * 		|[1:1]						
 * 		|							
 * SimMaster[1]  
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SimMaster implements ISender, IReceiver {

	private ISender myFarmMaster;
	private MasterCore mc;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimMaster(ISender farmMaster) {
		this.myFarmMaster = farmMaster;
	}






	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult) {
		myFarmMaster.addSimResult(receiverID, trID, simResult);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		myFarmMaster.finishedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		myFarmMaster.loadedSim(receiverID, trID); // LOADEDSIM to farmMaster
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
		// TODO Auto-generated method stub
		return 0;
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
	public void setSimState(int receiverID, int trID, SimState simState) {
		// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		myFarmMaster.startedSim(receiverID, trID);
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		// TODO Auto-generated method stub
	}


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
		mc = new MasterCore(this, tr);
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
		System.out.println("SM SS");
		this.startedSim(receiverID, trID);
		mc.start();
	}


}//end of class
