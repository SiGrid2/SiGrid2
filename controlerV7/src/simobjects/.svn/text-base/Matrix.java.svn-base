package simobjects;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Vector;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Transient;
import simobjects.transport.farm.MatrixAxis;


/**
 * annotated class, representing an matrix in sigrid an the DB.
 * a matrix is a mapping of SLAs to storage networks.
 * 
 * a SLA can be mapped to 1 or 0 storage network.
 * 
 * Eine Matrix ist valide, wenn der Gesamtspeicherbedarf der zugeordneten SLAs pro Grid-
 * Speichernetzwerk zu keiner Zeit die Speicherkapazitätsschranke dasdsn des Speichernetzwerks
 * überschreitet und jedes SLA maximal einem Grid-Speichernetzwerk zugeordnet
 * ist.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
@Embeddable
public class Matrix implements Serializable{

	private static final long serialVersionUID = -3489446478524871674L;

	@Transient
	private boolean[][] matrix = new boolean[0][0];

	private int numJobs; //numJobs
	private int numServer; //numServer

	@Lob
	private String matrixForDB; //csv-string, representing the mapping in DB, because boolean[][] not mappable



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public Matrix() {
		super();		
	}

	public Matrix (int numJobs, int numServer){
		matrix = new boolean[numJobs][numServer];
		this.numJobs = numJobs;
		this.numServer = numServer;
	}

	public Matrix(Matrix m){
		//this.matrix = m.matrix.clone();

		this.numJobs = m.getNumJobs();
		this.numServer = m.getNumServer();
		this.matrix = new boolean[numJobs][numServer];
		for(int rows = 0; rows < matrix.length; rows++){
			for (int columns = 0; columns < matrix[0].length; columns++){
				matrix[rows][columns] = m.matrix[rows][columns];
			}
		}
	}






	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/



	/**
	 * schedules the SLA to the storage network
	 * 
	 * @param jobID ID of SLA
	 * @param serverID ID of storage network
	 */
	public void assignJobToServer (int jobID, int serverID)  {
		for (int i = 0; i < numServer; i++) {
			matrix[jobID][i] = (i==serverID);
		}
	}


	/**
	 * @param jobID
	 * @return the ID of sthe storage network where the SLA is scheduled. if not not scheduled -1.
	 */
	public int jobRunningOnServer (int jobID) {

		for (int i = 0; i < numServer; i++) {
			if (matrix[jobID][i]==true) {
				return i;
			}
		}
		return -1;
	}


	/**
	 * @param jobID ID of SLA
	 * @return boolean if the SLA is scheduled to a storage network
	 */
	public boolean isJobRunningOnServer (int jobID) {

		for (int i = 0; i < numServer; i++) {
			if (matrix[jobID][i]==true) {
				return true;
			}
		}
		return false;
	}


	/**
	 * @return the total number of scheduled SLAs
	 */
	public int getJobsRunning () {

		int running = 0;

		for (int i = 0; i < numServer; i++) {
			for (int j = 0; j < numJobs; j++) {
				if (matrix[j][i]==true) running++;
			}
		}
		return running;
	}


	/**
	 * resets the matrix. after that no SLAs are scheduled to any storage network
	 */
	public void clear () {
		for (int i = 0; i < numJobs; i++) {
			assignJobToServer (i, -1);
		}
	}


	/**
	 * @param serverId ID of storage network
	 * @return int[] with the IDs of the SLAs scheduled to that storage network
	 */
	public int[] getJobsOnServer (int serverId) {

		int [] jobsRunning = null;
		Vector<Integer> jobsRunningVec = new Vector<Integer>();
		for (int aktJobID = 0; aktJobID < numJobs; aktJobID++) {

			if (matrix[aktJobID][serverId]) {
				jobsRunningVec.add(aktJobID);
			}
		}

		if (jobsRunningVec.size() == 0) {
			return null;
		}

		jobsRunning = new int [jobsRunningVec.size()];
		for (int i = 0; i < jobsRunning.length; i++) {
			jobsRunning[i] = jobsRunningVec.elementAt(i);
		}
		return jobsRunning;
	}


	/**
	 * @param jobID ID of SLA
	 * @param serverID ID of storage network
	 * @return boolean if this SLA is scheduled to this storage network
	 */
	public boolean isJobRunningOnServer (int jobID, int serverID) {
		return matrix[jobID][serverID];
	}



	/**
	 * the internal data is transfered to another internal schema.
	 * has to be called before the matrix can be saved in DB!
	 */
	public void createMatrixForDB(){
		matrixForDB = "";

		for (int job = 0; job < numJobs; job++){
			boolean breaked = false;
			for (int server = 0; server < numServer; server++){
				if (matrix[job][server] == true){
					matrixForDB += server + ",";
					breaked = true;
					break;
				}				
			}
			if (!breaked){
				matrixForDB += "-1,";
			}			
		}	
	}


	/**
	 * when the matrix is load from DB, this method transferes the
	 * DB representation into the normal internal schema
	 * has to be called after deserializing from DB!
	 */
	public void restoreInternalMatrix(){	
		matrix = new boolean[numJobs][numServer];
		String[] result = matrixForDB.split(",");
		for (int x=0; x<result.length; x++){			
			if (!result[x].equalsIgnoreCase("-1") ){ //job is scheduled
				matrix[x][Integer.parseInt(result[x])] = true;
			}
		}
	}



	/**
	 * mapping from MatrixAxis to Matrix
	 * 
	 * @param matrixAxis
	 * @return
	 */
	public static Matrix getMatrix(MatrixAxis matrixAxis){
		Matrix m = new Matrix();
		m.numJobs = matrixAxis.getNumJobs();
		m.numServer = matrixAxis.getNumServer();
		m.matrixForDB = matrixAxis.getMatrixForDB();
		m.restoreInternalMatrix();
		return m;
	}


	/**
	 * mapping because of web service from Matrix to MatrixAxis
	 * 
	 * @return
	 */
	public MatrixAxis getMatrixAxis(){
		MatrixAxis ma = new MatrixAxis();
		createMatrixForDB();
		ma.setMatrixForDB(matrixForDB);
		ma.setNumJobs(numJobs);
		ma.setNumServer(numServer);

		return ma;
	}


	/**
	 * @param stream
	 */
	public void printMatrixToConsole(PrintStream stream){
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				stream.print(matrix[x][y]+ "  ");
			}
			stream.println();
		}
		stream.println();
	}

	/**
	 * prints the matrix to the console
	 */
	public void printMatrixToConsole(){
		printMatrixToConsole(System.out);
	}

	/**
	 * prints the matrix integer-coded to the console
	 */
	public void printMatrixToConsoleInt(){
		printMatrixToConsoleInt(System.out);
	}

	/**
	 * @param stream
	 */
	public void printMatrixToConsoleInt(PrintStream stream){
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				if (matrix[x][y]) {
					stream.print("x ");
				}
				else {
					stream.print("- ");
				}
			}
			stream.println();
		}
		stream.println();
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * getter- and setter Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * @return the matrix 
	 */
	public boolean[][] getMatrix() {
		return matrix;
	}


	/**
	 * @return the numJobs 
	 */
	public int getNumJobs() {
		return numJobs;
	}


	/**
	 * @return the numServer (bare_field_name}
	 */
	public int getNumServer() {
		return numServer;
	}	


}//end of class
