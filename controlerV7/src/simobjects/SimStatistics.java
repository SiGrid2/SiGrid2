package simobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.IndexColumn;
import simobjects.transport.farm.SimStatisticsAxis;


/**
 * annotated class, representing a simstatistic in sigrid and the DB
 * a simstatistic is created while a bruteforce-simulation and holds the spreading
 * of earnings into several earning-intervals
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class SimStatistics implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 398701749199386542L;

	@Id @GeneratedValue
	@Column(name="simstatistic_id")
	private int id;

	private long start;
	private long end;
	private int fields;

	private double tick;
	@org.hibernate.annotations.CollectionOfElements
	@IndexColumn(name="arraypos")
	private long[] statistic;

	private int numStatisticsAcc = 0;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimStatistics() {
		super();		
	}


	public SimStatistics(long start, long end, int fields) {
		super();
		this.start = start;
		this.end = end;
		this.fields = fields;

		statistic = new long[fields+2];
		tick = (double) (end - start) / fields;

	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * the earning is registered in the statistic
	 * 
	 * @param earning
	 */
	public void addEarning(long earning){

		if (earning < start){
			statistic[0]++;
		}
		else if (earning > end){
			statistic[fields+1]++;
		}
		else{

			statistic[getArrayPosition(earning)]++;
		}
	}



	/**
	 * @return String[] with the boundaries of all intervals
	 */
	public String [] getBoundaries () {
		String boundaries [] = new String[fields+2];
		boundaries[0] = "]-oo, " + start + "[";

		for (int i = 1; i < boundaries.length-1; i++) {
			boundaries[i] = "[" + ((i-1)* tick + start) + ", " + ((i) * tick + start) + "[";
		}
		boundaries[fields] = "[" + ((fields-1)* tick + start) + ", " + ((fields) * tick + start) + "]";
		boundaries[fields + 1] = "]" + end + ", +oo[";
		return boundaries;
	}



	/**
	 * prints the statistic to console
	 */
	public void printToConsole(){
		String[] boundaries = getBoundaries();

		for (int i = 0; i < boundaries.length; i++) {
			System.out.println(boundaries[i] + " : " + statistic[i]);
		}
	}



	/**
	 * adds a statistic(same size!) to the statistic
	 * 
	 * @param stats
	 */
	public void add (SimStatistics stats) {
		numStatisticsAcc ++;
		for (int i = 0; i < statistic.length; i++) {
			statistic[i] = statistic[i] +  stats.statistic [i];
		}
	}



	/**
	 * mapping SimStatisticsAxis to SimStatistics
	 * 
	 * @param simStatisticsAxis
	 * @return
	 */
	public static SimStatistics getSimStatistics(SimStatisticsAxis simStatisticsAxis){
		SimStatistics s = new SimStatistics();
		s.end = simStatisticsAxis.getEnd();
		s.fields = simStatisticsAxis.getFields();
		s.numStatisticsAcc = simStatisticsAxis.getNumStatisticsAcc();
		s.id =  simStatisticsAxis.getId();
		s.start = simStatisticsAxis.getStart();
		s.statistic = simStatisticsAxis.getStatistic();
		s.tick = simStatisticsAxis.getTick();
		return s;
	}

	/**
	 * mapping because of web service from SimStatistics to SimStatisticsAxis
	 * 
	 * @return
	 */
	public SimStatisticsAxis getSimStatisticsAxis(){
		SimStatisticsAxis ssa = new SimStatisticsAxis();
		ssa.setEnd(end);
		ssa.setFields(fields);
		ssa.setId(id);
		ssa.setNumStatisticsAcc(numStatisticsAcc);
		ssa.setStart(start);
		ssa.setStatistic(statistic);
		ssa.setTick(tick);

		return ssa;
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getNumStatisticsAcc() {
		return numStatisticsAcc;
	}

	public long [] getStatistics () {
		return statistic;
	}




	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @param earning
	 * @return the number of the interval the earning is in
	 */
	private int getArrayPosition(long earning){

		int i = (int)((earning - start)/ tick);

		if (i < fields) {
			i++;
		}

		return i;
	}


}//end of class
