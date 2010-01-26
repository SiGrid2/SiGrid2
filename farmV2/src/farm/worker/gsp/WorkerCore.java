package farm.worker.gsp;

import simobjects.TestRun;
import communication.general.IReceiver;
import communication.general.ISender;




public class WorkerCore implements IReceiver{
	private ISender myMaster;
	
	public WorkerCore(ISender myMaster) {
		super();
		this.myMaster = myMaster;
	}

	public void abortSim(int receiverID, int trID) {
		myMaster.abortedSim(receiverID, trID);
	}

	public void loadSim(int receiverID, TestRun tr) {
		myMaster.loadedSim(receiverID, tr.getId());
	}

	public void shutDown(int receiverID) {
		// not  applicable
	}

	public void startSim(int receiverID, int trID) {
		myMaster.startedSim(receiverID, trID);
	}

	


	
}
