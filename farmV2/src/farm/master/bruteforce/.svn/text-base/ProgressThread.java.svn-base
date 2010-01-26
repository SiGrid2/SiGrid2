package farm.master.bruteforce;


/**
 * The ProgressThread lets the MasterCore set the SimStatistics in a certain interval[ms]
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class ProgressThread extends Thread {

	private MasterCore mc;
	private int interval;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * The ProgressThread lets the MasterCore set the SimStatistics in a certain interval[ms]
	 * 
	 * @param mc		the Mastercore
	 * @param interval	the interval in [ms] this Thread lets the MsterCore set the SimStatistics
	 */
	public ProgressThread(MasterCore mc, int interval) {
		super();
		this.mc = mc;
		this.interval = interval;

	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while(true){
			mc.setProgress();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}//end of class
