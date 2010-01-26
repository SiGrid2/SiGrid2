package simobjects;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import controler.Controler;
import simobjects.transport.gui.SimJobGui;


/**
 * annotated class, representing a simjob in sigrid and the DB.
 * a simjob is a testrun assigned to a simfarm.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class SimJob implements Serializable{

	private static final long serialVersionUID = -5293723252804701796L;

	@Id @GeneratedValue
	@Column(name="simjob_id")
	private int id;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private SimFarm simFarm;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private TestRun testrun;

	@OneToOne( cascade = CascadeType.ALL, optional=true, fetch=FetchType.EAGER)
	private SimResult simResult;

	@OneToOne( cascade = CascadeType.ALL, optional=true, fetch=FetchType.EAGER)
	private SimStatistics simStatistic;

	private boolean finished = false;
	private boolean running = false;

	private long queuedMs;
	private long startedMs;
	private long finishedMS;

	private int queuePosition;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimJob() {

	}


	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/



	/**
	 * mapping SimJobGui to SimJob
	 * 
	 * @param simJobGui
	 * @return
	 */
	public static SimJob getSimJob(SimJobGui simJobGui){
		SimJob sj = new SimJob();
		sj.setFinished(simJobGui.isFinished());
		sj.setFinishedMS(simJobGui.getFinishedMS());
		sj.setId(simJobGui.getId());		
		sj.setQueuedMs(simJobGui.getQueuedMs());
		sj.setQueuePosition(simJobGui.getQueuePosition());
		sj.setRunning(simJobGui.isRunning());
		sj.setSimFarm(Controler.getInstance().getDaoFactory().getSimFarmDAO().getById(simJobGui.getSimFarmId()));

		sj.setSimResult(Controler.getInstance().getDaoFactory().getSimResultDAO().getById(simJobGui.getId()));
		sj.setSimStatistic(Controler.getInstance().getDaoFactory().getSimStatisticDAO().getById(simJobGui.getSimStatisticId()));
		sj.setStartedMs(simJobGui.getStartedMs());
		sj.setTestrun(Controler.getInstance().getDaoFactory().getTestrunDAO().getById(simJobGui.getTestrunId()));

		return sj;
	}
	

	/**
	 * mapping because of web service from SimJob to SimJobGui
	 * 
	 * @return
	 */
	public SimJobGui getSimJobGui(){
		SimJobGui sjg = new SimJobGui();
		sjg.setFinished(finished);
		sjg.setFinishedMS(finishedMS);
		sjg.setId(id);
		sjg.setQueuedMs(queuedMs);
		sjg.setQueuePosition(queuePosition);
		sjg.setRunning(running);
		sjg.setSimFarmId(simFarm.getId());
		if (simResult != null){
			sjg.setSimResultId(simResult.getId());
		}
		if (simStatistic != null){
			sjg.setSimStatisticId(simStatistic.getId());
		}

		sjg.setStartedMs(startedMs);
		sjg.setTestrunId(testrun.getId());

		return sjg;
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/



	/**
	 * @return the finished 
	 */
	public boolean isFinished() {
		return finished;
	}



	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}



	/**
	 * @return the finishedMS 
	 */
	public long getFinishedMS() {
		return finishedMS;
	}



	/**
	 * @param finishedMS the finishedMS to set
	 */
	public void setFinishedMS(long finishedMS) {
		this.finishedMS = finishedMS;
	}



	/**
	 * @return the id 
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the queuedMs 
	 */
	public long getQueuedMs() {
		return queuedMs;
	}



	/**
	 * @param queuedMs the queuedMs to set
	 */
	public void setQueuedMs(long queuedMs) {
		this.queuedMs = queuedMs;
	}



	/**
	 * @return the queuePosition 
	 */
	public int getQueuePosition() {
		return queuePosition;
	}



	/**
	 * @param queuePosition the queuePosition to set
	 */
	public void setQueuePosition(int queuePosition) {
		this.queuePosition = queuePosition;
	}



	/**
	 * @return the running 
	 */
	public boolean isRunning() {
		return running;
	}



	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}



	/**
	 * @return the simFarm 
	 */
	public SimFarm getSimFarm() {
		return simFarm;
	}



	/**
	 * @param simFarm the simFarm to set
	 */
	public void setSimFarm(SimFarm simFarm) {
		this.simFarm = simFarm;
	}



	/**
	 * @return the simResult 
	 */
	public SimResult getSimResult() {
		return simResult;
	}



	/**
	 * @param simResult the simResult to set
	 */
	public void setSimResult(SimResult simResult) {
		this.simResult = simResult;
	}



	/**
	 * @return the simStatistic 
	 */
	public SimStatistics getSimStatistic() {
		return simStatistic;
	}



	/**
	 * @param simStatistic the simStatistic to set
	 */
	public void setSimStatistic(SimStatistics simStatistic) {
		this.simStatistic = simStatistic;
	}



	/**
	 * @return the startedMs 
	 */
	public long getStartedMs() {
		return startedMs;
	}



	/**
	 * @param startedMs the startedMs to set
	 */
	public void setStartedMs(long startedMs) {
		this.startedMs = startedMs;
	}



	/**
	 * @return the testrun 
	 */
	public TestRun getTestrun() {
		return testrun;
	}



	/**
	 * @param testrun the testrun to set
	 */
	public void setTestrun(TestRun testrun) {
		this.testrun = testrun;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SimJob other = (SimJob) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}//end of class


