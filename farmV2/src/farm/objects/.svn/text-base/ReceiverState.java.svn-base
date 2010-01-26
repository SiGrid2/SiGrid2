package farm.objects;

/**
 * @author Christoph Beck
 * States a reciever can have
 */
public class ReceiverState {
	public static final int IDLE		 	= 0;
	// public final int SIM_RUNNING 	= 1;
	// REPLACED: If a Simultion is Running, the
	// id of the running simmulation is returned
	
	public static final int SIM_FINISHED 	= -1;
	public static final int RESULTS_SEND	= -2;
	public static final int SIM_LOADED		= -3;
	public static final int UNREGISTERED 	= -4;
	public static final int DEAD		 	= -8;

	private long	initID 		= -1;
	private int  	id			= -1;
	private int 	state		= IDLE;

	/**
	 * constuctor. takes initID, id and state of a reciever
	 * 
	 * @param initID
	 * @param id
	 * @param state
	 */
	public ReceiverState(long initID, int id, int state) {
		super();
		this.initID = initID;
		this.id = id;
		this.state = state;
	}

	/**
	 * empty constructor
	 */
	public ReceiverState() {
		this(0L,0,ReceiverState.IDLE);
	}

	/**
	 * returns the id 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * sets the id 
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * returns the initID
	 * @return the initID
	 */
	public long getInitID() {
		return initID;
	}
	/**
	 * @param initID
	 */
	public void setInitID(long initID) {
		this.initID = initID;
	}
	/**
	 * @return
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return
	 */
	public boolean isIdle () {
		return (state==IDLE);
	}

	/**
	 * @return
	 */
	public boolean isWorking () {
		return ((state>IDLE));
	}

	/**
	 * @return
	 */
	public boolean isStatsSubmited (){
		return (state==RESULTS_SEND);
	}

}
