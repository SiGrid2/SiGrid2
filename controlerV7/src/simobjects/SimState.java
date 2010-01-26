package simobjects;

import simobjects.transport.farm.SimStateAxis;


/**
 * with a simstate the progress of a simulation can be reported to the controller
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SimState {

	int progress;
	long runtime; //simulation time in millis



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimState() {
		super();		
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * mapping SimStateAxis to SimState
	 * 
	 * @param simStateAxis
	 * @return
	 */
	public static SimState getSimState(SimStateAxis simStateAxis){
		SimState s = new SimState();
		s.setProgress(simStateAxis.getProgress());
		s.setRuntime(simStateAxis.getRuntime());
		return s;
	}

	/**
	 * mapping because of web service from SimState to SimStateAxis
	 * 
	 * @return
	 */
	public SimStateAxis getSimStateAxis(){
		SimStateAxis ssa = new SimStateAxis();
		ssa.setProgress(progress);
		ssa.setRuntime(runtime);

		return ssa;
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @return the progress 
	 */
	public int getProgress() {
		return progress;
	}


	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}


	/**
	 * @return the runtime 
	 */
	public long getRuntime() {
		return runtime;
	}


	/**
	 * @param runtime the runtime to set
	 */
	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}


}//end of class
