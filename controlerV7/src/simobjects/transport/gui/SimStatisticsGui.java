package simobjects.transport.gui;

import java.io.Serializable;



public class SimStatisticsGui implements Serializable{
	

	private static final long serialVersionUID = 2326843343046981459L;

	private int id;	
	private long start;
	private long end;
	private int fields;
	private double tick;	
	private long[] statistic;
	private int numStatisticsAcc = 0;
	
	
	public SimStatisticsGui() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getStart() {
		return start;
	}


	public void setStart(long start) {
		this.start = start;
	}


	public long getEnd() {
		return end;
	}


	public void setEnd(long end) {
		this.end = end;
	}


	public int getFields() {
		return fields;
	}


	public void setFields(int fields) {
		this.fields = fields;
	}


	public double getTick() {
		return tick;
	}


	public void setTick(double tick) {
		this.tick = tick;
	}


	public long[] getStatistic() {
		return statistic;
	}


	public void setStatistic(long[] statistic) {
		this.statistic = statistic;
	}


	public int getNumStatisticsAcc() {
		return numStatisticsAcc;
	}


	public void setNumStatisticsAcc(int numStatisticsAcc) {
		this.numStatisticsAcc = numStatisticsAcc;
	}
	
	
	
	
	

}
