package simobjects.transport.gui;

import java.io.Serializable;

public class AlgorithmGui implements Serializable{
	
	
	private static final long serialVersionUID = -4254051206328301038L;
	private int id;	
	private String name;
	
	
	public AlgorithmGui() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
