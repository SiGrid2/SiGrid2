package communication.general.noAxis;

import communication.general.axis.ISenderAxis;
import controler.communication.gui.axis.GuiWebservice;

/**
 * @author Christoph Beck
 * Querries the controler for new simJobs to run
 */
public class GetActionThread extends Thread {

	
	private ISenderAxis sender;
	private SenderNoAxis senderNo;
	private int farmId;
	private GuiWebservice guiWS = new GuiWebservice();
	

	/**
	 * @param sender	the sender to ask
	 * @param farmId	the id of this farm
	 */
	public GetActionThread(ISenderAxis sender, int farmId) {
		super();
		this.farmId = farmId;
		
	}

	

	/**
	 * @param senderNoAxis 	the sender to ask
	 * @param i				the id of this farm
	 */
	public GetActionThread (SenderNoAxis senderNoAxis, int i) {
		senderNo = senderNoAxis;
		farmId = i;
	}



	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run(){
		// register farm, 
		registerSimFarm();
		
		// then loop untill stopped
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			senderNo.getActionFromControler(farmId);
		}
	}

	/**
	 *  method used for registering
	 */
	private void registerSimFarm() {
		senderNo.register(16);
		
	}



	
}
