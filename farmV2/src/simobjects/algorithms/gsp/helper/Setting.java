package simobjects.algorithms.gsp.helper;

/**
 * @author Christoph Beck
 * Settings for a subrun
 */
public class Setting {
	private double[][] sMatrix;
	private double[][] jMatrix;
	
	private boolean sSimple;
	private boolean jSimple;
	
	private String name;
	
	
	/**
	 * constructor for class Setting
	 * @param sMatrix
	 * @param jMatrix
	 * @param sSimple
	 * @param jSimple
	 * @param name
	 */
	public Setting(double[][] sMatrix, double[][] jMatrix, boolean sSimple, boolean jSimple, String name) {
		super();
		this.sMatrix = sMatrix;
		this.jMatrix = jMatrix;
		this.sSimple = sSimple;
		this.jSimple = jSimple;
		this.name	 = name;
	}


	/**
	 * @return
	 */
	public double[][] getJMatrix() {
		return jMatrix;
	}


	/**
	 * @param matrix
	 */
	public void setJMatrix(double[][] matrix) {
		jMatrix = matrix;
	}


	/**
	 * @return
	 */
	public boolean isJSimple() {
		return jSimple;
	}


	/**
	 * @param simple
	 */
	public void setJSimple(boolean simple) {
		jSimple = simple;
	}


	/**
	 * @return
	 */
	public double[][] getSMatrix() {
		return sMatrix;
	}


	/**
	 * @param matrix
	 */
	public void setSMatrix(double[][] matrix) {
		sMatrix = matrix;
	}


	/**
	 * @return
	 */
	public boolean isSSimple() {
		return sSimple;
	}


	/**
	 * @param simple
	 */
	public void setSSimple(boolean simple) {
		sSimple = simple;
	}


	/**
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
