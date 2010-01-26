package simobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import controler.Controler;
import simobjects.transport.farm.TestrunAxis;
import simobjects.transport.gui.TestrunGui;


/**
 * annotated class, representing a testrun in sigrid and the DB
 * a testrun is a layout + algorithm
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class TestRun implements Serializable {

	private static final long serialVersionUID = 624789335902631485L;

	@Id @GeneratedValue
	@Column(name="testrun_id")
	private int 	id;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)	
	private Layout 	layout;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)	
	private Algorithm algorithm;

	private String  name;
	private long 	statsStart;
	private long 	statsEnd;
	private int  	statsNumFields;


	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public TestRun() {
		
	}



	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	
	
	/**
	 * mapping because of web service from TestRun to TestrunAxis
	 * 
	 * @return
	 */
	public TestrunAxis getTestrunAxis(){
		TestrunAxis tra = new TestrunAxis();
		tra.setAlgorithm(algorithm.getAlgorithmAxis());
		tra.setId(id);
		tra.setLayout(layout.getLayoutAxis());
		tra.setName(name);
		tra.setStatsEnd(statsEnd);
		tra.setStatsNumFields(statsNumFields);
		tra.setStatsStart(statsStart);
		return tra;
	}
	
	/**
	 * mapping because of web service from TestRun to TestrunGui
	 * 
	 * @return
	 */
	public TestrunGui getTestrunGui(){
		TestrunGui trg = new TestrunGui();
		trg.setAlgorithmId(algorithm.getId());
		trg.setId(id);
		trg.setLayoutId(layout.getId());
		trg.setName(name);
		trg.setStatsEnd(statsEnd);
		trg.setStatsNumFields(statsNumFields);
		trg.setStatsStart(statsStart);
		
		return trg;
	}
	
	/**
	 * mapping TestrunAxis to TestRun
	 * 
	 * @param testRunAxis
	 * @return
	 */
	public static TestRun getTestRun(TestrunAxis testRunAxis){
		TestRun tr = new TestRun();
		tr.setAlgorithm(Algorithm.getAlgorithm(testRunAxis.getAlgorithm()));
		tr.setId(testRunAxis.getId());
		tr.setLayout(Layout.getLayout(testRunAxis.getLayout()));
		tr.setName(testRunAxis.getName());
		tr.setStatsEnd(testRunAxis.getStatsEnd());
		tr.setStatsNumFields(testRunAxis.getStatsNumFields());
		tr.setStatsStart(testRunAxis.getStatsStart());
		
		return tr;
	}
	
	/**
	 * mapping TestrunGui to TestRun
	 * 
	 * @param testrunGui
	 * @return
	 */
	public static TestRun getTestRun(TestrunGui testrunGui){
		TestRun tr = new TestRun();
		tr.setAlgorithm(Controler.getInstance().getDaoFactory().getAlgorithmDAO().getById(testrunGui.getAlgorithmId()));
		tr.setId(testrunGui.getId());
		tr.setLayout(Controler.getInstance().getDaoFactory().getLayoutDAO().getById(testrunGui.getLayoutId()));
		tr.setName(testrunGui.getName());
		tr.setStatsEnd(testrunGui.getStatsEnd());
		tr.setStatsNumFields(testrunGui.getStatsNumFields());
		tr.setStatsStart(testrunGui.getStatsStart());
		
		return tr;		
	}	

	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter/ and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public Layout getLayout() {
		return layout;
	}

	
	public void setLayout(Layout layout) {
		if (layout != null){
			this.layout = layout;
			this.statsStart = 0;
			this.statsEnd = layout.getIdealEarning();
			this.statsNumFields = 100;
		}
	}

	
	public long getStatsEnd() {
		return statsEnd;
	}

	
	public void setStatsEnd(long statsEnd) {
		this.statsEnd = statsEnd;
	}

	
	public int getStatsNumFields() {
		return statsNumFields;
	}

	
	public void setStatsNumFields(int statsNumFields) {
		this.statsNumFields = statsNumFields;
	}

	
	public long getStatsStart() {
		return statsStart;
	}
	

	public void setStatsStart(long statsStart) {
		this.statsStart = statsStart;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Algorithm getAlgorithm() {
		return algorithm;
	}



	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	

}//end of class
