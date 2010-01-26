package simobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import simobjects.transport.farm.AlgorithmAxis;

/**
 * annotated class, representing an algorithm in sigrid and the DB
 * at the moment implemented:
 * 
 * id 1: bruteforce
 * id 2: greedy
 * id 3: pareto-optimisation
 * id 4: gale-shapley
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Entity
public class Algorithm implements Serializable{
		
	private static final long serialVersionUID = -7500075478903973197L;

	public static final int BRUTEFORCE 	= 1;
	public static final int GREEDY 		= 2;
	public static final int PARETO 		= 3;
	public static final int GSP 		= 4;

	@Id @GeneratedValue
	@Column(name="algorithm_id")
	private int id;
	
	private String name;
	

	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public Algorithm() {
		super();
	}
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * mapping because of web service from Algorithm to AlgorithmAxis
	 * 
	 * @return
	 */
	public AlgorithmAxis getAlgorithmAxis(){
		AlgorithmAxis aa = new AlgorithmAxis();
		aa.setId(id);
		aa.setName(name);
		return aa;
	}
	
	/**
	 * mapping from AlgorithmAxis to Algorithm
	 * 
	 * @param algorithmAxis
	 * @return
	 */
	public static Algorithm getAlgorithm(AlgorithmAxis algorithmAxis){
		Algorithm algo = new Algorithm();
		algo.setId(algorithmAxis.getId());
		algo.setName(algorithmAxis.getName());
		
		return algo;
	}


	
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}	
	
	

}//end of class
