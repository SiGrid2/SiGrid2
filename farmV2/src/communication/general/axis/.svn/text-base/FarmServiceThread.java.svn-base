package communication.general.axis;



/**
 * Thread calls getActionFromControler-method every second
 * 
 * @author Dirk Holzapfel
 * @version 1.0 
 */
public class FarmServiceThread extends Thread {

	private ISenderAxis senderAXIS;
	private int farmId;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	public FarmServiceThread(ISenderAxis senderAXIS, int farmId) {
		super();
		this.senderAXIS = senderAXIS;
		this.farmId = farmId;		
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
			senderAXIS.getActionFromControler(farmId);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}//end of class
