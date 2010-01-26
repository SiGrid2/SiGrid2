package controler;

import controler.communication.farm.axis.IFarmWS;
import controler.persistence.DAOFactory;
import java.util.HashMap;
import java.util.Map;
import simobjects.SimFarm;
import simobjects.SimJob;
import simobjects.transport.farm.ReceiverStateAxis;
import simobjects.transport.farm.SimResultAxis;
import simobjects.transport.farm.SimStateAxis;
import simobjects.transport.farm.SimStatisticsAxis;
import simobjects.transport.farm.TestrunAxis;


/**
 * singleton class. the main controller of sigrid.
 * connection between gui and farms. 
 * access to persistence layer.
 * for every registered simfarm a microcontroller is started and calls are delegated
 * to that microcontroller.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class Controler implements IFarmWS{

	private static Controler instance = null;
	private Map<Integer, MicroControler> microControler = new HashMap<Integer, MicroControler>();
	private DAOFactory daoFactory = DAOFactory.instance(DAOFactory.JPA);




	private Controler() {
		super();		
		// Exists only to defeat instantiation.
	}


	/**
	 * onliest possibilty to gain access to the controller 
	 * 
	 * @return singleton-Controler
	 */
	public static Controler getInstance() {
		if(instance == null) {
			instance = new Controler();
		}
		return instance;
	}

	/**
	 * @return the instantiated DAo Factory. In the moment: JPA
	 */
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}







	// Interface IFarmWS
	//
	//******************************************************************************


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#register(long, java.lang.String)
	 */
	public int register(long initID, String name) {		
		SimFarm sf = daoFactory.getSimFarmDAO().getSimFarmByInitId(initID);
		System.out.println("a  farm wants to register.");
		if ( sf != null){ //Farm already registered
			System.out.println("it is a known farm, is already in db");			

			if (microControler.get(sf.getId()) == null){ //no microController available, build new one.
				System.out.println("microcontroller not availbale, build new  one");
				microControler.put(sf.getId(), new MicroControler(sf));
			}
			else{
				System.out.println("microcontroller available, use old one");
			}

		}
		else{ //new farm, save to db
			System.out.println("it is a unknown farm, save to db, start new microcontroller");

			sf = new SimFarm();
			sf.setName(name);
			sf.setInitID(initID);
			sf.setFirstRegAtMs(System.currentTimeMillis());
			sf = daoFactory.getSimFarmDAO().persist(sf);

			microControler.put(sf.getId(), new MicroControler(sf));

		}

		microControler.get(sf.getId()).register(initID, name);

		return sf.getId();
	}



	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#unregister(int, long)
	 */
	public void unregister(int receiverID, long initID) {
		System.out.println("a farm wants to unregister.");
		if (receiverIDExists(receiverID)){
			//farmQueue.remove(receiverID); not necessary?!
			microControler.get(receiverID).unregister(receiverID, initID);
			System.out.println("farm removed successfully");
		}
		else{
			System.out.println("Couldnt remove-notpresent");
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#getActionFromControler(int)
	 */
	public int getActionFromControler(int receiverID) {
		if (receiverIDExists(receiverID)){
			return microControler.get(receiverID).getActionFromControler(receiverID);
		}
		else{
			return -1;
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#getTestRun(int)
	 */
	public TestrunAxis getTestRun(int receiverID) {
		if (receiverIDExists(receiverID)){
			return microControler.get(receiverID).getTestRun(receiverID);
		}
		else{
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#abortedSim(int, int)
	 */
	public void abortedSim(int receiverID, int trID) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).abortedSim(receiverID, trID);
		}

	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimLogMessage(int, int, java.lang.String)
	 */
	public void addSimLogMessage(int receiverID, int trID, String message) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).addSimLogMessage(receiverID, trID, message);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimResult(int, int, simobjects.transport.farm.SimResultAxis)
	 */
	public void addSimResult(int receiverID, int trID, SimResultAxis simResult) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).addSimResult(receiverID, trID, simResult);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#addSimStatistics(int, int, simobjects.transport.farm.SimStatisticsAxis)
	 */
	public void addSimStatistics(int receiverID, int trID,
			SimStatisticsAxis stats) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).addSimStatistics(receiverID, trID, stats);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#finishedSim(int, int)
	 */
	public void finishedSim(int receiverID, int trID) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).finishedSim(receiverID, trID);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#loadedSim(int, int)
	 */
	public void loadedSim(int receiverID, int trID) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).loadedSim(receiverID, trID);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#setReceiverState(int, simobjects.transport.farm.ReceiverStateAxis)
	 */
	public void setReceiverState(int receiverID, ReceiverStateAxis receiverState) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).setReceiverState(receiverID, receiverState);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#setSimState(int, int, simobjects.transport.farm.SimStateAxis)
	 */
	public void setSimState(int receiverID, int trID, SimStateAxis simState) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).setSimState(receiverID, trID, simState);
		}
	}


	/* (non-Javadoc)
	 * @see controler.communication.farm.axis.IFarmWS#startedSim(int, int)
	 */
	public void startedSim(int receiverID, int trID) {
		if (receiverIDExists(receiverID)){
			microControler.get(receiverID).startedSim(receiverID, trID);
		}		
	}


	// from GUI
	//
	//******************************************************************************


	/**
	 *  a simjob is queued for execution on a farm
	 * 
	 * @param simJob
	 */
	public void queueSimJob(SimJob simJob){
		if (receiverIDExists(simJob.getSimFarm().getId())){
			simJob.setQueuedMs(System.currentTimeMillis());
			simJob = daoFactory.getSimJobDAO().persist(simJob);

			simJob.getSimFarm().addSimJob(simJob);
			daoFactory.getSimFarmDAO().persist(simJob.getSimFarm());

			microControler.get(simJob.getSimFarm().getId()).queueSimJob(simJob);
		}
	}


	/**
	 * unregisters a simfarm from system
	 * 
	 * @param idSimFarm
	 */
	public void shutDownSimFarm(int idSimFarm) {
		if (receiverIDExists(idSimFarm)){
			microControler.get(idSimFarm).shutDownSimFarm();
		}
	}




	//helper methods:
	//
	//************************************

	/**
	 * checks if a simfarm with that ID is registered in the system
	 * 
	 * @param receiverID
	 * @return boolean
	 */
	private boolean receiverIDExists(int receiverID){
		return (microControler.get(receiverID) != null);
	}


}//end of class