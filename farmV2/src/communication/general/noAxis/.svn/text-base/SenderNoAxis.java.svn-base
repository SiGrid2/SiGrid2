package communication.general.noAxis;

import java.rmi.RemoteException;

import communication.general.IReceiver;
import communication.general.ISender;
import controler.MicroControler;
import controler.communication.farm.axis.FarmWSSoapBindingImpl;
import farm.objects.ReceiverState;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;


public class SenderNoAxis implements ISender, IReceiver{

	private FarmWSSoapBindingImpl controlerWS;
	private IReceiver myFarmReceiver;

	private int trID = -1;


	
	/**
	 * Communicates with simControllPortal
	 * @param receiver this receiver
	 */
	public SenderNoAxis(IReceiver receiver) {
		super();
		this.controlerWS = new FarmWSSoapBindingImpl();
		this.myFarmReceiver = receiver;
		new GetActionThread(this,1 ).start();
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		try {
			controlerWS.abortedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		try {
			controlerWS.addSimLogMessage(receiverID, trID, message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
	 */
	public void addSimResult(int receiverID, int trID, SimResult simResult) {
		try {
			controlerWS.addSimResult(receiverID, trID, simResult.getSimResultAxis() );
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
	 */
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
		try {
			controlerWS.addSimStatistics(receiverID, trID, stats.getSimStatisticsAxis());
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
			controlerWS.finishedSim(receiverID, trID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * asks controler what to do and starts appropriate action
	 * @param receiverID this recievers ID
	 * @return
	 */
	public int getActionFromControler(int receiverID) {

		int answer;
		try {
			answer = controlerWS.getActionFromControler(receiverID);

			if (answer == MicroControler.NOTHING_TO_DO){
				// well, .... what can i do ...
			}
			else if( answer == MicroControler.LOAD_SIM){
				TestRun tr = TestRun.getTestRun(controlerWS.getTestRun(receiverID));
				trID = tr.getId();
				loadSim(1, tr);
			}
			else if (answer == MicroControler.ABORT_SIM){
				abortSim(receiverID, trID);
			}
			else if (answer == MicroControler.SHUTDOWN){
				shutDown(receiverID);
			}
			else if (answer == MicroControler.START_SIM){
				startSim(receiverID, trID);
			}
			return 0;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	/*
	 *  Methods from ISender
	 */

	/* (non-Javadoc)
	 * @see communication.general.ISender#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		try {
			controlerWS.loadedSim(receiverID, trID);
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
			return controlerWS.register(initID, "Testfarm");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
	 */
	public void setReceiverState(int receiverID, ReceiverState receiverState) {
		//nop

	}

	/* (non-Javadoc)
	 * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
	 */
	public void setSimState(int receiverID, int trID, SimState simState) {
		try {
			controlerWS.setSimState(receiverID, trID, simState.getSimStateAxis());
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
			controlerWS.startedSim(receiverID, trID);
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
			controlerWS.unregister(receiverID, initID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 *  Methods from i Reciever
	 */

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		myFarmReceiver.abortSim(receiverID, trID);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		myFarmReceiver.loadSim(receiverID, tr);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		myFarmReceiver.shutDown(receiverID);

	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		myFarmReceiver.startSim(receiverID, trID);
	}

}
