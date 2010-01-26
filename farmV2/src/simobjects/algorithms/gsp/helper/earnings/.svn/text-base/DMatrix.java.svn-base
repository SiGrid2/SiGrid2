package simobjects.algorithms.gsp.helper.earnings;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Christoph Beck
 * Class to calculate and store the matrices of earnnigs
 */
public class DMatrix {
	private double [][] theMatrix;



	private double [] 	sumRows;
	private double [] 	sumCols;
	private double 		sumTotal;

	private double [] 	minRows;
	private double [] 	minCols;
	private double 		minTotal;

	private double [] 	maxRows;
	private double [] 	maxCols;
	private double 		maxTotal;

	private int 		numRows;
	private int 		numCols;

	private String		name;

	/**
	 * constructor for class DMatrix
	 * @param theMatrix
	 * @param name
	 */
	public DMatrix(double[][] theMatrix, String name) {
		this.theMatrix = theMatrix;
		this.name = name;
		// Row, Fields or Cells can be masked from calculation if the mask cell is zero or below

		init();
	}

	/**
	 * analyse the given set of data (min, max, average ...)
	 */
	public void init() {
		// Build the various fields we might use for statistics

		numRows 	= theMatrix.length;
		numCols 	= theMatrix[0].length;

		sumRows 	= new double [numRows];
		sumCols 	= new double [numCols];
		sumTotal 	= 0;

		minRows 	= new double [numRows];
		minCols 	= new double [numCols];
		minTotal 	= Double.MAX_VALUE;

		maxRows 	= new double [numRows];
		maxCols 	= new double [numCols];
		maxTotal 	= Double.MIN_VALUE;


		// init column sums
		for (int iRow = 0; iRow < numRows; iRow++) {
			sumRows[iRow] = 0;
			minRows[iRow] = Double.MAX_VALUE;
			maxRows[iRow] = Double.MIN_VALUE;

		}

		// init row stats
		for (int iCol = 0; iCol < numCols; iCol++) {
			sumCols[iCol] = 0;
			minCols[iCol] = Double.MAX_VALUE;
			maxCols[iCol] = Double.MIN_VALUE;
		}

		// here we are, ready to go
		for (int iRow = 0; iRow < numRows; iRow++) {

			for (int iCol = 0; iCol < numCols; iCol++) {

				// the sums;
				sumRows[iRow] = sumRows[iRow] + theMatrix[iRow][iCol];
				sumCols[iCol] = sumCols[iCol] + theMatrix[iRow][iCol];
				sumTotal = sumTotal + theMatrix[iRow][iCol];

				// min max all
				if(theMatrix[iRow][iCol] < minTotal) {
					minTotal = theMatrix[iRow][iCol];
				} 

				if(theMatrix[iRow][iCol] > maxTotal) {
					maxTotal = theMatrix[iRow][iCol];
				}

				//	min max rows
				if(theMatrix[iRow][iCol] < minRows[iRow]) {
					minRows[iRow] = theMatrix[iRow][iCol];
				} 

				if(theMatrix[iRow][iCol] > maxRows[iRow]) {
					maxRows[iRow] = theMatrix[iRow][iCol];
				}

				//	min max cols
				if(theMatrix[iRow][iCol] < minCols[iCol]) {
					minCols[iCol] = theMatrix[iRow][iCol];
				} 

				if(theMatrix[iRow][iCol] > maxCols[iCol]) {
					maxCols[iCol] = theMatrix[iRow][iCol];
				}

			}
		}
	}



	/**
	 * @return theMatrix
	 */
	public double[][] getTheMatrix() {
		return theMatrix;
	}

	/**
	 * @param theMatrix theMatrix
	 */
	public void setTheMatrix(double[][] theMatrix) {
		this.theMatrix = theMatrix;
		init();
	}


	/**
	 * @return num Cols
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * @return num Rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * @return
	 */
	public double[] getSumCols() {
		return sumCols;
	}

	/**
	 * @return
	 */
	public double[] getSumRows() {
		return sumRows;
	}

	/**
	 * @return
	 */
	public double getSumTotal() {
		return sumTotal;
	}

	/**
	 * @param sumTotal
	 */
	public void setSumTotal(double sumTotal) {
		this.sumTotal = sumTotal;
	}


	/**
	 * can be used to map a smaller matrix on a bigger one. 
	 * @param canvaMatrix
	 * @param maskRows
	 * @param maskCols
	 * @return
	 */
	public double [][] expandMatrix 	(double  canvaMatrix[][], double  maskRows[], double  maskCols[]) {
		int rowOffset = 0;
		int colOffset = 0;

		for (int iRow = 0; iRow < numRows; iRow++) {
			while((maskRows[rowOffset]<=0)&&(rowOffset<canvaMatrix.length)) {
				rowOffset ++;
			}
			for (int iCol = 0; iCol < numCols; iCol++) {
				while((maskCols[colOffset]<=0) && (colOffset< canvaMatrix[0].length)) {
					colOffset ++;
				}
				canvaMatrix[rowOffset][colOffset] = theMatrix[iRow][iCol];
				colOffset ++;
			}
			colOffset = 0;
			rowOffset ++;
		}
		return canvaMatrix;
	}
	/**
	 * columns or rows are blinded out unless their mask... param > 0
	 * @param maskRows
	 * @param maskCols
	 * @return
	 */
	public double [][] collapseMatrix 	(double [] maskRows, double [] maskCols) {

		int numMaskedRows = 0;
		int numMaskedCols = 0;	

		// find out size of masked matrix
		for (int iRow = 0; iRow < numRows; iRow++) {
			if(maskRows[iRow]>0) {
				numMaskedRows ++;
			}
		}

		for (int iCol = 0; iCol <numCols; iCol++) {
			if(maskCols[iCol]>0) {
				numMaskedCols ++;
			}
		}

		// reserve the space
		double maskedMatrix [][] = new double [numMaskedRows][numMaskedCols];

		// get values
		int rowOffset = 0;
		int colOffset = 0;
		for (int iRow = 0; iRow < numMaskedRows; iRow++) {
			while((maskRows[rowOffset]<=0)&&(rowOffset < numRows)) {
				rowOffset ++;
			}
			for (int iCol = 0; iCol < numMaskedCols; iCol++) {
				while((maskCols[colOffset]<=0)&&(colOffset < numCols)) {
					colOffset ++;
				}
				maskedMatrix[iRow][iCol] = theMatrix  [rowOffset][colOffset] ;
				colOffset ++;
			}
			colOffset = 0;
			rowOffset ++;
		}

		return maskedMatrix;
	}

	/**
	 * raise every entry by d
	 * @param d
	 * @return
	 */
	public double [][] raiseBy (double d) {
		double [][] raised = new double [numRows][numCols];

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				raised[iRow][iCol] = theMatrix[iRow][iCol] + d;
			}
		}

		return raised;
	}

	/**
	 * increment every entry by d
	 * @param d
	 * @return
	 */
	public double [][] sinkBy (double d) {
		double [][] sunken = new double [numRows][numCols];

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				sunken[iRow][iCol] = theMatrix[iRow][iCol] - d;
			}
		}
		return sunken;
	}

	/**
	 * every entry is substracted from d
	 * @param d
	 * @return
	 */
	public double [][] invertBy (double d) {
		double [][] inverted = new double [numRows][numCols];

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				inverted[iRow][iCol] = d - theMatrix[iRow][iCol];
			}
		}

		return inverted;
	}

	
	/**
	 * minimum entry of both matrici is taken
	 * @param aMatrix
	 * @return
	 */
	public double [][] minimizeBy (double [][] aMatrix) {
		double [][] minimized = new double [numRows][numCols];


		if (theMatrix.length != aMatrix.length) {
			return null;
		}
		if (theMatrix[0].length != aMatrix[0].length) {
			return null;
		}

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				if(aMatrix[iRow][iCol]<theMatrix[iRow][iCol]) {
					minimized[iRow][iCol] = aMatrix[iRow][iCol];
				}
				else {
					minimized[iRow][iCol] = theMatrix[iRow][iCol];
				}
			}
		}

		return minimized;
	}

	/**
	 * yes. devide by the other
	 * @param aMatrix
	 * @return
	 */
	public double [][] dividedBy (double [][] aMatrix) {
		double [][] divided = new double [numRows][numCols];


		if (theMatrix.length != aMatrix.length) {
			return null;
		}
		if (theMatrix[0].length != aMatrix[0].length) {
			return null;
		}

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				divided[iRow][iCol] = aMatrix[iRow][iCol] / aMatrix[iRow][iCol];
			}
		}

		return divided;
	}

	/**
	 * @return the habr Matrix
	 */
	public double [][] getHabrMatrix () {
		double [][] habr = new double [numRows][numCols];

		for (int iRow = 0; iRow < numRows; iRow++) {
			for (int iCol = 0; iCol < numCols; iCol++) {
				habr[iRow][iCol] = 	
					(
							theMatrix[iRow][iCol] - 
							(
									(sumRows[iRow]/numCols) +
									(sumCols[iCol]/numRows)
							) 
					)+ 
					(sumTotal/(numCols*numRows));
			}
		}

		return habr;
	}

	/**
	 * dump to stream
	 * @param stream
	 */
	public void dump (PrintStream stream) {
		NumberFormat formater = new DecimalFormat ("#####0.00");

		for (int iRow = 0; iRow < numRows; iRow++) {

			for (int iCol = 0; iCol < numCols; iCol++) {
				stream.print(""+formater.format(theMatrix[iRow][iCol])+"\t");
			}
			stream.println("| "+formater.format(minRows[iRow])+"\t"+formater.format(maxRows[iRow])
					+"\t"+formater.format((sumRows[iRow]/numCols))+"\t");
		}
		for (int iCol = 0; iCol < numCols; iCol++) {
			stream.print("---\t");
		}
		stream.println("+ ---\t---\t---");
		// min
		for (int iCol = 0; iCol < numCols; iCol++) {
			stream.print(""+formater.format(minCols[iCol])+"\t");
		}
		stream.println("| "+minTotal);

		// max
		for (int iCol = 0; iCol < numCols; iCol++) {
			stream.print(""+formater.format(maxCols[iCol])+"\t");
		}
		stream.println("| \t"+maxTotal);

		// av
		for (int iCol = 0; iCol < numCols; iCol++) {
			stream.print(""+formater.format((sumCols[iCol]/numRows))+"\t");
		}
		stream.println("| \t\t"+formater.format((sumTotal/(numRows*numCols))));
	}

	/**
	 * @return the  minimum value found in matrix
	 */
	public double getMinTotal() {
		return minTotal;
	}

	/**
	 * sets the  minimum value found in matrix
	 */
	public void setMinTotal(double minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * @return maxRows
	 */
	public double[] getMaxCols() {
		return maxCols;
	}

	/**
	 * @return maxRows
	 */
	public double[] getMaxRows() {
		return maxRows;
	}

	/**
	 * @return maxTotal
	 */
	public double getMaxTotal() {
		return maxTotal;
	}

	/**
	 * @return minCols
	 */
	public double[] getMinCols() {
		return minCols;
	}

	/**
	 * @return minRows
	 */
	public double[] getMinRows() {
		return minRows;
	}

	/**
	 * @return name
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