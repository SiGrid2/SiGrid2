package farm.master;

import communication.general.IReceiver;
import communication.general.ISender;
import communication.general.rmi.SenderRMI;
import farm.master.bruteforce.SimMaster;
import farm.objects.ReceiverList;
import farm.objects.ReceiverState;
import java.util.Vector;
import org.apache.log4j.Logger;

import simobjects.Algorithm;
import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;


/**
 * @author Christoph Beck
 * 
 * FarmMaster manages the connection to simControllPortal and controles the farm worker
 *
 */
public class FarmMaster implements ISender, IReceiver {

	private int myReceiverID = 1; //hard coded at the moment

    private SenderRMI myFarmSender;
    private ISender myStarter;
    private IReceiver mySimMaster;
    private int lastReceiverID = 0;

    private ReceiverList myWorker;
    private static Logger logger = Logger.getLogger( FarmMaster.class );
    private TestRun tr;


   


    /**
     * @param myStarter the starting instace 
     */
    public FarmMaster(ISender myStarter) {
        super();
        this.myStarter = myStarter;
        this.myWorker = new ReceiverList();

        myFarmSender = new SenderRMI(this);
    }



    //*****************************
	// SenderRMI -> ISender -> 
	//*****************************

    

    /* (non-Javadoc)
     * @see communication.general.ISender#register(long)
     */
    public synchronized int register(long initID) {
    	int i = myWorker.register(initID);
    	logger.info("Receiver registered. Gave him ID #"+i);
    	System.out.println("worker #"+i+"registered");
        return i;
    }

    /* (non-Javadoc)
     * @see communication.general.ISender#unregister(int, long)
     */
    public synchronized void unregister(int receiverID, long initID) {
    	myWorker.unregister(receiverID, initID);
        logger.info("Receiver unregistered. ID #"+receiverID);


    }



    /* (non-Javadoc)
     * @see communication.general.ISender#addSimResult(int, int, simobjects.SimResult)
     */
    public void addSimResult(int receiverID, int trID, SimResult simResult) {
    	logger.info("addSimResult(). receiver #" + receiverID);
    	System.out.println("****************");
    	System.out.println("earning: " + simResult.getEarning());;
    	//tr.getLayout().printToConsole();
    	//simResult.getJobServerMapping().printMatrixToConsoleInt();
    	myStarter.addSimResult(myReceiverID, trID, simResult);
    	
    	

    }


    /* (non-Javadoc)
     * @see communication.general.ISender#addSimStatistics(int, int, simobjects.SimStatistics)
     */
    public void addSimStatistics(int receiverID, int trID, SimStatistics stats) {
    	logger.info("addSimStatistics(). receiver #" + receiverID);
    	//stats.printToConsole();
    	myStarter.addSimStatistics(myReceiverID, trID, stats);


    }


    /* (non-Javadoc)
     * @see communication.general.ISender#setReceiverState(int, farm.objects.ReceiverState)
     */
    public void setReceiverState(int receiverID, ReceiverState receiverState) {
    	logger.info("setFarmState(). receiver #" + receiverID);
    	myStarter.setReceiverState(myReceiverID, receiverState);

    }

    /* (non-Javadoc)
     * @see communication.general.ISender#setSimState(int, int, simobjects.SimState)
     */
    public void setSimState(int receiverID, int trID, SimState simState) {
    	logger.info("setSimState(). receiver #" + receiverID);
    	myStarter.setSimState(myReceiverID, trID, simState);

    }

    /* (non-Javadoc)
     * @see communication.general.ISender#addSimLogMessage(int, int, java.lang.String)
     */
    public void addSimLogMessage(int receiverID, int trID, String message) {
    	logger.info("addSimLogMessage(). receiver #" + receiverID + " message: " + message);
    	myStarter.addSimLogMessage(myReceiverID, trID, message);
    }

    /* (non-Javadoc)
     * @see communication.general.ISender#loadedSim(int, int)
     */
    public synchronized void loadedSim(int receiverID, int trID) {
        logger.info("loadedSim(). receiver #" + receiverID);
        myWorker.loadedSim(receiverID);
        if (myWorker.allReceiverLoadedSim()||tr.getAlgorithm().getId()==2||tr.getAlgorithm().getId()==4){
        	logger.info("all receivers simloaded()!!!");
        	myStarter.loadedSim(myReceiverID, trID);

        }

    }

    /* (non-Javadoc)
     * @see communication.general.ISender#startedSim(int, int)
     */
    public synchronized void startedSim(int receiverID, int trID) {
    	logger.info("startedSim(). receiver #" + receiverID);

    	myWorker.startedSim(receiverID, trID);
        if (myWorker.allReceiverSimStarted()||tr.getAlgorithm().getId()==2||tr.getAlgorithm().getId()==4){
        	logger.info("all receivers simstarted()!!!");
        	myStarter.startedSim(myReceiverID, trID);

        }
    }


    /* (non-Javadoc)
     * @see communication.general.ISender#finishedSim(int, int)
     */
    public synchronized void finishedSim(int receiverID, int trID) {
    	logger.info("finishedSim(). receiver #" + receiverID);
    	if (myWorker.allReceiverSimStarted()||tr.getAlgorithm().getId()==2||tr.getAlgorithm().getId()==4) {
    		System.out.println("finishedSim, receiverID="+receiverID);
    		if (receiverID == 0){ //from simMaster
    			
        		mySimMaster = null;
        		myStarter.finishedSim(myReceiverID, trID);    		
        	}
    	}
    	

    }

    /* (non-Javadoc)
     * @see communication.general.ISender#abortedSim(int, int)
     */
    public void abortedSim(int receiverID, int trID) {
    	 logger.info("finishedSim(). receiver #" + receiverID);
    	 myStarter.abortedSim(myReceiverID, trID);
    }




    //*****************************
	// ? - > IReceiver - > SenderRMI
    //*****************************

    /* (non-Javadoc)
     * @see communication.general.IReceiver#abortSim(int, int)
     */
    public void abortSim(int receiverID, int trID) {
        //abort sim on all receivers
        for(ReceiverState x : myWorker.getRegisteredReciever()){
            myFarmSender.abortSim(x.getId(), trID);
        }
    }

    /* (non-Javadoc)
     * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
     */
    public void loadSim(int receiverID, TestRun tr) {
    	logger.info("loadSim()");
    	this.tr = tr;
    	
    	
    	 if (tr.getAlgorithm().getId() == Algorithm.BRUTEFORCE){	//BRUTEFORCE
    		 System.out.println("starting bruteforce infrastructure");
    		 mySimMaster = new SimMaster(this);
    		//load sim on all receivers
    	    	for(ReceiverState x : myWorker.getRegisteredReciever()){
    	            myFarmSender.loadSim(x.getId(), tr);
    	        }
    	 }
    	 else if (tr.getAlgorithm().getId() == Algorithm.GREEDY){ 	//GREEDY
    		 System.out.println("starting greedy infrastructure");
    		 mySimMaster = new farm.master.greedy.SimMaster(this);
    	 }
    	 else if (tr.getAlgorithm().getId() == Algorithm.PARETO){ 	//PARETO
    		 System.out.println("starting pareto infrastructure");
    		 mySimMaster = new farm.master.pareto.SimMaster(this);
//    		load sim on all receivers
 	    	for(ReceiverState x : myWorker.getRegisteredReciever()){
 	            myFarmSender.loadSim(x.getId(), tr);
 	        }
    	 }
    	 else if (tr.getAlgorithm().getId() == Algorithm.GSP){ 		//GSP
    		 System.out.println("starting gsp infrastructure");
    		 mySimMaster = new farm.master.gsp.SimMaster(this);
    	 }
    	 
    	 mySimMaster.loadSim(0, tr);
        
    }

    /* (non-Javadoc)
     * @see communication.general.IReceiver#shutDown(int)
     */
    public void shutDown(int receiverID) {
        //shutdown all receivers
    	for(ReceiverState x : myWorker.getRegisteredReciever()){
            myFarmSender.shutDown(x.getId());
        }
    }

    /* (non-Javadoc)
     * @see communication.general.IReceiver#startSim(int, int)
     */
    public void startSim(int receiverID, int trID) {
    	logger.info("startSim()");
    	mySimMaster.startSim(receiverID, trID);

        //start sim on all receivers, except algo id = 2 = greedy
    	if ((tr.getAlgorithm().getId() != 2)&&(tr.getAlgorithm().getId() != 4)){
    		System.out.println(myWorker.getRegisteredReciever().size());
        	for(ReceiverState x : myWorker.getRegisteredReciever()){
                myFarmSender.startSim(x.getId(), trID);
            }
    	}
    	
    }












}
