package simobjects.transport.gui;

import java.io.PrintStream;
import java.io.Serializable;



public class SimJobGui implements Serializable{
	
	
	
	private static final long serialVersionUID = 7347446430668145162L;
	private int id;	
	private int simFarmId;	
	private int testrunId;	
	private int simResultId;
	private int simStatisticId;	
	private boolean finished = false;
	private boolean running = false;	
	private long queuedMs;
	private long startedMs;
	private long finishedMS;	
	private int queuePosition;
	
	
	public SimJobGui() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSimFarmId() {
		return simFarmId;
	}


	public void setSimFarmId(int simFarmId) {
		this.simFarmId = simFarmId;
	}


	public int getTestrunId() {
		return testrunId;
	}


	public void setTestrunId(int testrunId) {
		this.testrunId = testrunId;
	}


	public int getSimResultId() {
		return simResultId;
	}


	public void setSimResultId(int simResultId) {
		this.simResultId = simResultId;
	}


	public int getSimStatisticId() {
		return simStatisticId;
	}


	public void setSimStatisticId(int simStatisticId) {
		this.simStatisticId = simStatisticId;
	}


	public boolean isFinished() {
		return finished;
	}


	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public long getQueuedMs() {
		return queuedMs;
	}


	public void setQueuedMs(long queuedMs) {
		this.queuedMs = queuedMs;
	}


	public long getStartedMs() {
		return startedMs;
	}


	public void setStartedMs(long startedMs) {
		this.startedMs = startedMs;
	}


	public long getFinishedMS() {
		return finishedMS;
	}


	public void setFinishedMS(long finishedMS) {
		this.finishedMS = finishedMS;
	}


	public int getQueuePosition() {
		return queuePosition;
	}


	public void setQueuePosition(int queuePosition) {
		this.queuePosition = queuePosition;
	}
	
	public void dump (PrintStream stream) {
		stream.println("id="+id);	
		stream.println("simFarmId="+simFarmId);	
		stream.println("testrunId="+testrunId);	
		stream.println("simResultId="+simResultId);
		stream.println("simStatisticId="+simStatisticId);	
		stream.println("finished="+finished);
		stream.println("running="+running);
		stream.println("queuedMs="+queuedMs);
		stream.println("startedMs="+startedMs);
		stream.println("finishedMS="+finishedMS);	
		stream.println("queuePosition="+queuePosition);
	}
	
	
	

}
