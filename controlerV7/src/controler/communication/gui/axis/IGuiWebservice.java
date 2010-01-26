package controler.communication.gui.axis;

import simobjects.transport.gui.AlgorithmGui;
import simobjects.transport.gui.LayoutGui;
import simobjects.transport.gui.SimFarmGui;
import simobjects.transport.gui.SimJobGui;
import simobjects.transport.gui.TestrunGui;



/**
 * Interface for communication between GUI and Controller
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public interface IGuiWebservice {

	//Layout

	/**
	 * creates a layout in DB
	 * 
	 * @param layoutGui
	 * @return
	 */
	public LayoutGui createLayout(LayoutGui layoutGui);


	/**
	 * updates a layout in DB
	 * 
	 * @param layoutGui
	 * @return
	 */
	public boolean updateLayout(LayoutGui layoutGui);


	/**
	 * deletes a layout from DB
	 * 
	 * @param idLayout
	 * @return
	 */
	public boolean deleteLayout(int idLayout);


	/**
	 * get a layout from DB
	 * 
	 * @param idLayout
	 * @return
	 */
	public LayoutGui getLayoutById(int idLayout);


	/**
	 * get all layouts from DB
	 * 
	 * @return
	 */
	public LayoutGui[] getAllLayout();


	/**
	 * get all layouts within some constraints from DB
	 * 
	 * @param param1 constraint 1
	 * @param param2 constraint 2
	 * @param param3 constraint 3
	 * @return
	 */
	public LayoutGui[] getAllLayout(String param1, String param2, String param3);




	//Testrun

	/**
	 * create a testrun in DB
	 * 
	 * @param testrunGui
	 * @return
	 */
	public TestrunGui createTestrun(TestrunGui testrunGui);
	
	
	/**
	 * update a testrun in DB
	 * 
	 * @param testrunGui
	 * @return
	 */
	public boolean updateTestrun(TestrunGui testrunGui);
	
	
	/**
	 * delete testrun from DB
	 * 
	 * @param idTestrun
	 * @return
	 */
	public boolean deleteTestrun(int idTestrun);

	
	/**
	 * get testrun from DB
	 * 
	 * @param idTestrun
	 * @return
	 */
	public TestrunGui getTestrunById(int idTestrun);
	
	
	/**
	 * get all testruns from DB
	 * 
	 * @return
	 */
	public TestrunGui[] getAllTestrun();
	
	
	/**
	 * get all testruns within some constraints from DB
	 * 
	 * @param param1 constraint 1
	 * @param param2 constraint 2
	 * @param param3 constraint 3
	 * @return
	 */
	public TestrunGui[] getAllTestrun(String param1, String param2, String param3);

	
	
	
	//SimJob

	/**
	 * create simjob in DB
	 * 
	 * @param simJobGui
	 * @return
	 */
	public SimJobGui createSimJob(SimJobGui simJobGui);
	
	
	/**
	 * update simjob in DB
	 * 
	 * @param simJobGui
	 * @return
	 */
	public boolean updateSimJob (SimJobGui simJobGui);
	
	
	/**
	 * delete simjob from DB
	 * 
	 * @param idSimjob
	 * @return
	 */
	public boolean deleteSimJob (int idSimjob);

	
	/**
	 * get simjob from DB
	 * 
	 * @param idSimjob
	 * @return
	 */
	public SimJobGui getSimJobById(int idSimjob);	
	
	
	/**
	 * get all simjobs from db
	 * 
	 * @return
	 */
	public SimJobGui[] getAllSimJob();

	
	/**
	 * get all simjobs within some constraints from DB
	 * 
	 * @param param1 constraint 1
	 * @param param2 constraint 2
	 * @param param3 constraint 3
	 * @return
	 */
	public SimJobGui[] getAllSimJob(String param1, String param2, String param3);






	//SimFarm

	/**
	 * get simfarm from DB
	 * 
	 * @param idSimFarm
	 * @return
	 */
	public SimFarmGui getSimFarmById(int idSimFarm);
	
	
	/**
	 * get all simfarms from DB
	 * 
	 * @return
	 */
	public SimFarmGui[] getAllSimFarm();
	
	
	/**
	 * get all simfarms within some constraints from DB
	 * 
	 * @param param1 constraint 1
	 * @param param2 constraint 2
	 * @param param3 constraint 3
	 * @return
	 */
	public SimFarmGui[] getAllSimFarm(String param1, String param2, String param3);



	
	//Algorithms

	/**
	 * get algorithm from DB
	 * 
	 * @param idAlgorithm
	 * @return
	 */
	public AlgorithmGui getAlgorithmById(int idAlgorithm);
	
	
	/**
	 * get all algorithms from DB
	 * 
	 * @return
	 */
	public AlgorithmGui[] getAllAlgorithm();
	
	
	/**
	 * get all algorithms within some constraints from DB
	 * 
	 * @param param1 constraint 1
	 * @param param2 constraint 2
	 * @param param3 constraint 3
	 * @return
	 */
	public AlgorithmGui[] getAllAlgorithm(String param1, String param2, String param3);

	
	
	// Batch Dir
	
	/**
	 * set the directory for looking for batch-simjobs
	 * 
	 * @param dir
	 */
	public void SetBatchJobDir (String dir);

	
	
	
	// orders:
	
	/**
	 * a simjob is queued for execution on a farm
	 * 
	 * @param idSimJob
	 */
	public void queueSimJob(int idSimJob);
	
	
	/**
	 * a simjob is removed from queue
	 * 
	 * @param idSimJob
	 * @return
	 */
	public boolean deleteSimJobFromQueue(int idSimJob);
	
	
	/**
	 * delivers all simlogmessages from a certain farm
	 * 
	 * @param idSimFarm
	 * @return
	 */
	public String[] getSimLogMessages(int idSimFarm);
	
	
	/**
	 * sets the position of a simjob in the queue
	 * 
	 * @param idSimJob
	 * @param queuePos
	 */
	public void setQueuePosition(int idSimJob, int queuePos);
	
	
	/**
	 * aborts the execution of a simjob on a simfarm
	 * 
	 * @param idSimJob
	 */
	public void abortSim(int idSimJob);

	
	/**
	 * unregisters a simfarm from system
	 * 
	 * @param idSimFarm
	 */
	public void shutDownSimFarm(int idSimFarm);
	
	
	
	/**
	 * pause ON/OFF on a simfarm
	 * 
	 * @param idSimFarm
	 * @param pause
	 */
	public void pauseSimFarm(int idSimFarm, boolean pause);	
	

}//end of interface
