package simobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import simobjects.transport.farm.SimResultAxis;


/**
 * annotated class, representing a simresult in sigrid and the DB.
 * a simresult contains the monetary result of simulation of a testrun.
 * the matrix causing the earning is also contained
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class SimResult implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -3990126957884589648L;

	@Id @GeneratedValue
	@Column(name="simresult_id")
	private int id;
	
	@Embedded
	private Matrix jobServerMapping;
	
	private long earning;


	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public SimResult() {
		super();		
	}
	
	
	public SimResult(Matrix jobServerMapping, long earning) {
		super();
		this.jobServerMapping = jobServerMapping;
		this.earning = earning;
	}
	
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
		
	/**
	 * mapping SimResultAxis to SimResult
	 * 
	 * @param simResultAxis
	 * @return
	 */
	public static SimResult getSimResult(SimResultAxis simResultAxis){
		SimResult s = new SimResult();
		s.setEarning(simResultAxis.getEarning());
		Matrix m = Matrix.getMatrix(simResultAxis.getMatrix());
		s.setJobServerMapping(m);
		return s;
	}
	
	/**
	 * mapping because of web service from SimResult to SimResultAxis
	 * 
	 * @return
	 */
	public SimResultAxis getSimResultAxis(){
		SimResultAxis sra = new SimResultAxis();
		sra.setEarning(earning);
		sra.setId(id);
		sra.setMatrix(jobServerMapping.getMatrixAxis());
		
		return sra;
	}


	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	
	/**
	 * @return the earning
	 */
	public long getEarning() {
		return earning;
	}


	/**
	 * @param earning the earning to set
	 */
	public void setEarning(long earning) {
		this.earning = earning;
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
	 * @return the jobServerMapping
	 */
	public Matrix getJobServerMapping() {
		return jobServerMapping;
	}


	/**
	 * @param jobServerMapping the jobServerMapping to set
	 */
	public void setJobServerMapping(Matrix jobServerMapping) {
		this.jobServerMapping = jobServerMapping;
	}	


}//end of class
