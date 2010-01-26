package simobjects;


/**
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ReceiverState {
	public static final int IDLE		 	= 0;
	// public final int SIM_RUNNING 	= 1;
	// REPLACED: If a Simultion is Running, the
	// id of the running simmulation is returned
	public static final int SIM_FINISHED 	= -1;
	public static final int RESULTS_SEND	= -2;
	public static final int SIM_LOADED		= -3;
	public static final int SIM_STARTED     = -5;
	public static final int UNREGISTERED 	= -4;
	
	public static final int DEAD		 	= -8;


	private long	initID 		= -1;
	private int  	id			= -1;
	private int 	state		= IDLE;


	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	
	public ReceiverState(long initID, int id, int state) {
		super();
		this.initID = initID;
		this.id = id;
		this.state = state;
	}

	public ReceiverState() {
		this(0L,0,ReceiverState.IDLE);
	}

	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getInitID() {
		return initID;
	}
	public void setInitID(long initID) {
		this.initID = initID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public boolean isIdle () {
		return (state==IDLE);
	}

	public boolean isWorking () {
		return ((state!=IDLE)&&(state>=RESULTS_SEND));
	}

	public boolean isStatsSubmited (){
		return (state==IDLE||state==RESULTS_SEND);
	}

}
