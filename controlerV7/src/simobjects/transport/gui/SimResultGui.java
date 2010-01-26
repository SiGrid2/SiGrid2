package simobjects.transport.gui;

import java.io.Serializable;


public class SimResultGui implements Serializable{
	
	
	private static final long serialVersionUID = -2319874703250575735L;
	private int id;	
	private MatrixGui matrix;	
	private long earning;
	
	
	public SimResultGui() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public MatrixGui getMatrix() {
		return matrix;
	}


	public void setMatrix(MatrixGui matrix) {
		this.matrix = matrix;
	}


	public long getEarning() {
		return earning;
	}


	public void setEarning(long earning) {
		this.earning = earning;
	}
	
	
	
	

}
