package controler;


import simobjects.ReceiverState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Vector;
import controler.communication.farm.axis.IFarmWS;
import controler.helper.LayoutExtractor;
import controler.queue.QueueComparator;
import simobjects.SimFarm;
import simobjects.SimJob;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;
import simobjects.transport.farm.ReceiverStateAxis;
import simobjects.transport.farm.SimResultAxis;
import simobjects.transport.farm.SimStateAxis;
import simobjects.transport.farm.SimStatisticsAxis;
import simobjects.transport.farm.TestrunAxis;

/**
 * every registered farm has an own microcontroller.
 * a microcontroller schedules the not done simjobs for that farm in a queue.
 * orders from the gui are delegated to the farm,
 * the results of simjobs are persisted in DB
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class MicroControler implements IFarmWS{

	public static final int NOTHING_TO_DO = 0;
	public static final int LOAD_SIM = 1;
	public static final int START_SIM = 2;
	public static final int ABORT_SIM = 3;
	public static final int SHUTDOWN = 4;


	private Vector<SimJob> queue;
	private int actAction = NOTHING_TO_DO;
	private SimJob actSimJob = null;
	private Vector<String> simLogMessages;
	private SimState simState;
	private ReceiverState receiverState = new ReceiverState();
	private SimFarm simFarm;

	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public MicroControler(SimFarm sf) {
		super();
		this.simFarm = sf;
		this.queue = new Vector<SimJob>();
		this.simLogMessages = new Vector<String>();
	}






	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	


	/**
	 * a simjob is queued for execution at the end of queue
	 * 
	 * @param simJob
	 */
	public synchronized void queueSimJob(SimJob simJob){
		System.out.println("FarmActionQueue queuesimJob");
		if (actSimJob == null){ //no testrun running...
			System.out.println("no testrun running - set actjob = simjob");
			actSimJob = simJob;
			actAction = LOAD_SIM;
		}
		else{ //simjob running - new one into queue!
			System.out.println("another testrun is running - queuing new one");
			queue.add(simJob);
			Collections.sort(queue, new QueueComparator());
		}
	}
	
	/**
	 * a farm is told to unregister and shutdown
	 */
	public void shutDownSimFarm() {
		actAction = SHUTDOWN;
	}


	// IFarmWS
	//
	//******************************************


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		simFarm.getSimJob().remove(actSimJob);
		actSimJob.setRunning(false);
		Controler.getInstance().getDaoFactory().getSimFarmDAO().persist(simFarm);
		actSimJob = null;
		simLogMessages.clear();
		scheduleNextSimJob();
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		if (trID == actSimJob.getTestrun().getId()){
			simLogMessages.add(message);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimResult(int, int, simobjects.transport.farm.SimResultAxis)
	 */
	public void addSimResult(int receiverID, int trID, SimResultAxis simResult) {
		if (trID == actSimJob.getTestrun().getId()){
			SimResult simResultNorm = SimResult.getSimResult(simResult);
			actSimJob.setSimResult(simResultNorm);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimStatistics(int, int, simobjects.transport.farm.SimStatisticsAxis)
	 */
	public void addSimStatistics(int receiverID, int trID,
			SimStatisticsAxis stats) {
		if (trID == actSimJob.getTestrun().getId()){
			actSimJob.setSimStatistic(SimStatistics.getSimStatistics(stats));
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#finishedSim(int, int)
	 */
	public synchronized void finishedSim(int receiverID, int trID) {
		//save actSimJob to DB
		String filename =  "."+File.separator+"log"+File.separator;
		PrintStream 	stream 	= null;

		actSimJob.setFinished(true);
		actSimJob.setRunning(false);
		actSimJob.setFinishedMS(System.currentTimeMillis());
		actSimJob.setQueuedMs(actSimJob.getStartedMs() - actSimJob.getQueuedMs());
		Controler.getInstance().getDaoFactory().getSimJobDAO().persist(actSimJob);
		simFarm.getSimJob().remove(actSimJob);
		try {
			filename = "sjID-"+actSimJob.getId()+"-";
			filename = filename + "trID-"+actSimJob.getId()+"-";
			filename = filename + "layID-"+actSimJob.getId()+".log";
			stream = new PrintStream(new FileOutputStream(filename));
			LayoutExtractor.dumpSJtoFile(stream, actSimJob);

		} catch (FileNotFoundException e) {

		}
		Controler.getInstance().getDaoFactory().getSimFarmDAO().persist(simFarm);
		actSimJob = null;
		simLogMessages.clear();
		scheduleNextSimJob();	
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#getActionFromControler(int)
	 */
	public synchronized int getActionFromControler(int receiverID) {
		return actAction;
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#getTestRun(int)
	 */
	public TestrunAxis getTestRun(int receiverID) {
		TestRun tr = actSimJob.getTestrun();
		actAction = START_SIM;
		return tr.getTestrunAxis();
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		receiverState.setState(ReceiverState.SIM_LOADED);
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#register(long, java.lang.String)
	 */
	public int register(long initID, String name) {
		receiverState.setState(ReceiverState.IDLE);
		return 0;
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#setReceiverState(int, simobjects.transport.farm.ReceiverStateAxis)
	 */
	public void setReceiverState(int receiverID, ReceiverStateAxis receiverState) {
		this.receiverState.setState(receiverState.getState());
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#setSimState(int, int, simobjects.transport.farm.SimStateAxis)
	 */
	public void setSimState(int receiverID, int trID, SimStateAxis simState) {
		this.simState = SimState.getSimState(simState);
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		receiverState.setState(ReceiverState.SIM_STARTED);
		actAction = NOTHING_TO_DO;
		actSimJob.setRunning(true);
		actSimJob.setStartedMs(System.currentTimeMillis());
		Controler.getInstance().getDaoFactory().getSimJobDAO().persist(actSimJob);
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		receiverState.setState(ReceiverState.UNREGISTERED);
	}	
	
	
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * schedules the next simjob(first simjob in queue), so that a farm can request it
	 */
	private void scheduleNextSimJob(){
		if (queue.size() > 0){
			actSimJob = queue.remove(0);
			actAction = LOAD_SIM;
			System.out.println("actSimJob has algo id " + actSimJob.getTestrun().getAlgorithm().getId());
		}
	}


}//end of class