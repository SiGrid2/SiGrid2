package farm.objects;


import java.util.Vector;



/**
 * @author Christoph Beck
 *
 * Class to hold a list of IReceiver that an ISender is connected to
 */
public class ReceiverList  {

	private Vector<ReceiverState> myWorker;
	private ReceiverState myMaster = new ReceiverState(); //for example simMaster
	private int insertID = 0;

	public ReceiverList() {
		super();
		myWorker = new Vector<ReceiverState>();

	}



	/**
	 * @param receiverID the reciever which should start the simulation
	 * @param trID the simulation to be started
	 */
	public synchronized void startedSim(int receiverID, int trID) {
		
		if (receiverID == 0){
			myMaster.setState(trID);
		}
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getId()==receiverID) {

					aktWorker.setState(trID);

			}
		}
		
		

	}

	/**
	 * @param receiverID id of the farm that has finished loading a simulation
	 */
	public synchronized void loadedSim(int receiverID){

		if (receiverID == 0){
			myMaster.setState(ReceiverState.SIM_LOADED);
		}
		else{
			for (ReceiverState aktWorker : myWorker) {
				if (aktWorker.getId()==receiverID) {

						aktWorker.setState(ReceiverState.SIM_LOADED);

				}
			}
		}

	}

	/**
	 * @param receiverID id of the farm that has finished simulation
	 */
	public synchronized void finishedSim(int receiverID) {
		if (receiverID == 0){
			myMaster.setState(ReceiverState.SIM_FINISHED);
		}
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getId()==receiverID) {

				aktWorker.setState(ReceiverState.SIM_FINISHED);
			}
		}
	}


	/**
	 * @param initID a challenge id from the farm that wants to register
	 * @return
	 */
	public synchronized int register(long initID) {
		// check if Sender had beem registerd before...
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getInitID()==initID) {
				aktWorker.setState(ReceiverState.IDLE);
				return aktWorker.getId();
			}
		}

		// Still here? New Sender!

		myWorker.add(new ReceiverState(initID, ++insertID ,ReceiverState.IDLE));
		return insertID;
	}


	/**
	* @param receiverID the farm that unregistering
	  * @param initID the id which was the initial challege id when first registering
	 */
	public void unregister(int receiverID, long initID) {
		// only unregister what has been registered before!
		for (ReceiverState aktWorker : myWorker) {
			if ((aktWorker.getId()==receiverID) && (aktWorker.getInitID()==initID)) {
				aktWorker.setState(ReceiverState.UNREGISTERED);
				break;
			}
		}
	}


	/**
	 * @return the recievers associated with the iSender
	 */
	public synchronized ReceiverState[] getWorkingReciever () {
		Vector<ReceiverState> tmp = new Vector<ReceiverState>();

		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.isWorking()) {
				tmp.add(aktWorker);
			}
		}

		if (tmp.size()<0){
			return null;
		}

		return (ReceiverState[]) tmp.toArray();

	}

	/**
	 * @return an array of the recievers in idle state
	 */
	public synchronized ReceiverState[] getIdleReciever () {
		Vector<ReceiverState> tmp = new Vector<ReceiverState>();

		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.isIdle() ) {
				tmp.add(aktWorker);
			}
		}

		if (tmp.size()==0){
			return null;
		}

		return (ReceiverState[]) tmp.toArray();

	}

	/**
	 * @return an array of the recievers associated with this ISender
	 */
	public synchronized Vector<ReceiverState> getRegisteredReciever () {
		Vector<ReceiverState> tmp = new Vector<ReceiverState>();

		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getState()>ReceiverState.UNREGISTERED ) {
				tmp.add(aktWorker);
			}
		}

		if (tmp.size()==0){
			return null;
		}

		return  tmp;

	}

	/**
	 * @return true if all recievers have submitted results, false otherwise
	 */
	public synchronized boolean isStatsComplete () {
		for (ReceiverState aktWorker : myWorker) {
			System.out.println("state worker #" +aktWorker.getId() + " : " +aktWorker.getState());
			if (!aktWorker.isStatsSubmited() ) {

				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if all receivers have finished
	 */
	public synchronized boolean allReceiverFinished () {
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.isWorking() ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if all receivers have started
	 */
	public synchronized boolean allReceiverSimStarted() {
		if (myMaster.getState() <= ReceiverState.IDLE ) return false;
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getState() <= ReceiverState.IDLE ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return true if all receivers have loaded the simulation
	 */
	public synchronized boolean allReceiverLoadedSim () {
		if (myMaster.getState() != ReceiverState.SIM_LOADED ) return false;
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getState() != ReceiverState.SIM_LOADED ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param receiverID the receiver to be querried about simStatistics
	 * @return true if the call was successful
	 */
	public synchronized boolean addSimStatistics(int receiverID) {
		boolean updated=false;


		// ensure that the each sender is submitting only once
		for (ReceiverState aktWorker : myWorker) {
			if (aktWorker.getId()==receiverID) {


					aktWorker.setState(ReceiverState.RESULTS_SEND);
					updated = true;

				break;
			}
		}

		return updated;
	}



}
