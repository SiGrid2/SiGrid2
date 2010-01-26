package farm.worker.pareto;

import java.rmi.RemoteException;
import java.util.Vector;
import simobjects.Server;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;
import communication.algorithms.pareto.IReceiverPareto;
import communication.algorithms.pareto.ISenderPareto;
import communication.algorithms.pareto.rmi.ReceiverParetoRMI;
import communication.general.IReceiver;
import communication.general.ISender;
import farm.objects.ReceiverState;
import farm.worker.FarmWorker;


/**
 * Pareto - SimWorker
 * ----------------------
 * connects via RMI to the SimMaster.
 * starts the WorkerCore, who calculates Pareto-Data
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
public class SimWorker implements ISender, IReceiver, ISenderPareto, IReceiverPareto{

	private ReceiverParetoRMI mySimReceiver;
	private FarmWorker myFarmWorker;
	private farm.worker.pareto.WorkerCore workerCore;	
	private int receiverID; //id for horizontal rmi communication;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimWorker(FarmWorker myFarmWorker, String ip) {
		super();
		this.myFarmWorker = myFarmWorker;
		try {
			mySimReceiver = new ReceiverParetoRMI(this, ip);
			receiverID = this.register();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub		
	}


	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		workerCore = new farm.worker.pareto.WorkerCore(tr, this, this.receiverID);
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
		workerCore.start();
		this.startedSim(receiverID, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		// TODO Auto-generated method stub		
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#register()
	 */
	public int register() {		
		return mySimReceiver.register();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#unregister(int)
	 */
	public void unregister(int receiverID) {
		// TODO Auto-generated method stub		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		// TODO Auto-generated method stub		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult) {
		// TODO Auto-generated method stub		
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
		//nothing to do here..	
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
		myFarmWorker.startedSim(receiverID, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		// TODO Auto-generated method stub		
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#getParetoDataToCalculate()
	 */
	public ParetoTransportData getParetoDataToCalculate() {
		return mySimReceiver.getParetoDataToCalculate();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#setCalculatedPareto(java.util.Vector, simobjects.algorithms.pareto.ParetoPoint, simobjects.Server)
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server) {
		mySimReceiver.setCalculatedPareto(ppVec, bestParetoPoint, server);		
	}


}//end of class
