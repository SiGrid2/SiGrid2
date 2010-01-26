package simobjects.algorithms.bruteforce;

import java.io.Serializable;
import simobjects.Matrix;


/**
 * A BfMatrix holds a certain number of matrizes to calculate.
 *
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class BfMatrix implements Serializable{

	private static final long serialVersionUID = 1790054258465556414L;
	private Matrix matrix;
	private double numMatrix; // num of matrizes still to give back
	private double combinations; // num of matrizes wanted

	private int rows;
	private int columns;

	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public BfMatrix(Matrix startMatrix, double combinations) {
		super();
		this.matrix = startMatrix;
		this.numMatrix = combinations;
		this.combinations = combinations;
		this.rows = matrix.getNumJobs();
		this.columns = matrix.getNumServer();
	}

	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	/**
	 * @return the actual matrix to calculate
	 */
	public Matrix getMatrixToCalculate(){
		if (numMatrix == combinations){
			numMatrix--;
			return matrix;
		}
		else if(numMatrix == 0){
			return null;
		}

		else{
			numMatrix--;
			return getNextMatrix(rows);
		}
	}
	

	/**
	 * @return the number of matrix's still to calculate
	 */
	public double getNumMatrix() {
		return numMatrix;
	}

	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * recursive method that calculates the next Matrix
	 * 
	 * @param row
	 * @return the next hold matrix
	 */
	private Matrix getNextMatrix(int row){

		int  column = getLastTrueIndex(row);

		if ( column == 0){
			matrix.getMatrix()[row-1][0] = true;
		}

		else if ( column < columns){
			matrix.getMatrix()[row-1][column-1] = false;
			matrix.getMatrix()[row-1][column] = true;
		}

		else{
			matrix.getMatrix()[row-1][column-1] = false;
			getNextMatrix(row-1);
		}

		return matrix;
	}
	

	/**
	 * @param row
	 * @return the last "true" index of a certain row in the matrix
	 * example: - x - -    Index = 2
	 * 			1 2 3 4
	 */
	private int getLastTrueIndex(int row){

		for (int column = (matrix.getMatrix()[row-1].length)-1; column >= 0; column--) {
			if (matrix.getMatrix()[row-1][column] == true){
				return column + 1;
			}
		}
		return 0;
	}
	

}//end of class
