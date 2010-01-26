package farm.master;

import communication.general.IReceiver;
import communication.general.ISender;
import communication.general.noAxis.SenderNoAxis;
import farm.objects.ReceiverState;
import org.apache.log4j.PropertyConfigurator;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;

/**
 * @author Christoph Beck
 * 
 * Class to start the simFarmMaster process
 *
 */
public class MasterStarter implements ISender, IReceiver{

	private IReceiver farmMaster;
	private SenderNoAxis axisMaster;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		PropertyConfigurator.configureAndWatch( "log4j.properties", 60*1000 );
		MasterStarter myself = new MasterStarter();
		myself.init();
	}

	/**
	 *  used by constructor to init FarmMaster
	 */
	private void init() {
		farmMaster = new FarmMaster(this);
				
		axisMaster = new SenderNoAxis(this);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		axisMaster.abortedSim(receiverID, trID);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		axisMaster.addSimLogMessage(receiverID, trID, message);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simReult) {
		axisMaster.addSimResult(receiverID, trID, simReult);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		axisMaster.addSimStatistics(receiverID, trID, stats);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		axisMaster.finishedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		axisMaster.loadedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#register(long)
	 */
	public int register(long initID) {
		return axisMaster.register(initID);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState farmState) {
		axisMaster.setReceiverState(receiverID, farmState);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		axisMaster.setSimState(receiverID, trID, simState);
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		axisMaster.startedSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		axisMaster.unregister(receiverID, initID);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		farmMaster.abortSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		farmMaster.loadSim(receiverID, tr);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		farmMaster.shutDown(receiverID);
	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		farmMaster.startSim(receiverID, trID);
	}
	
	

}
