package farm.master.pareto;

import java.util.Random;
import java.util.Vector;

import communication.algorithms.pareto.IReceiverPareto;
import communication.algorithms.pareto.ISenderPareto;
import communication.algorithms.pareto.rmi.SenderParetoRMI;
import communication.general.IReceiver;
import communication.general.ISender;
import simobjects.Server;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;
import farm.objects.ReceiverList;
import farm.objects.ReceiverState;


/**
 * Pareto - SimMaster
 * ----------------------
 * opens a RMI-channel to the SimWorker and is the master for them.
 * starts the MasterCore, which provides the pareto-data to calculate for the workers
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
public class SimMaster implements ISender, IReceiver, ISenderPareto, IReceiverPareto{

	private ISender myFarmMaster;
	private ReceiverList myWorker;	
	private MasterCore masterCore;
	private SenderParetoRMI senderParetoRMI;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimMaster(ISender myFarmMaster) {
		super();
		this.myFarmMaster = myFarmMaster;
		senderParetoRMI = new SenderParetoRMI(this);
		myWorker = new ReceiverList();
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
		myFarmMaster.abortedSim(receiverID, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		myFarmMaster.addSimLogMessage(receiverID, trID, message);		
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
		senderParetoRMI.stopServer();
		myFarmMaster.finishedSim(receiverID, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		myFarmMaster.loadedSim(receiverID, trID);		
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
		myFarmMaster.setReceiverState(receiverID, receiverState);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		myFarmMaster.setSimState(receiverID, trID, simState);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		myFarmMaster.startedSim(0, trID);		
	}


	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		myWorker.unregister(receiverID, initID);		
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
		masterCore = new MasterCore(this, tr);	
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
		this.startedSim(receiverID, trID);			
	}

	//*****************************
	// from simWorker
	//*****************************


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#register()
	 */
	public int register() {
		return myWorker.register(new Random().nextInt());
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#unregister(int)
	 */
	public void unregister(int receiverID) {
		// TODO Auto-generated method stub		
	}



	//*****************************
	// to simWorker
	//*****************************

	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#getParetoDataToCalculate()
	 */
	public ParetoTransportData getParetoDataToCalculate() {
		return masterCore.getParetoDataToCalculate();
	}


	/* (non-Javadoc)
	 * @see communication.algorithms.pareto.ISenderPareto#setCalculatedPareto(java.util.Vector, simobjects.algorithms.pareto.ParetoPoint, simobjects.Server)
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server) {
		masterCore.setCalculatedPareto(ppVec, bestParetoPoint, server);		
	}


}//end of class
