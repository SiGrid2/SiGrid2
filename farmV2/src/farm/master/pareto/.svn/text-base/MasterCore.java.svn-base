package farm.master.pareto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import simobjects.Job;
import simobjects.Matrix;
import simobjects.Server;
import simobjects.SimResult;
import simobjects.TestRun;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;
import simobjects.algorithms.pareto.PreferenceList;
import simobjects.algorithms.pareto.PreferencePoint;
import simobjects.algorithms.pareto.comparator.PreferenceListComparator;

/**
 * MasterCore of Pareto-Algorithm. Delivers Pareto-Data to calculate to the workers.
 * Manages the results.
 * 
 * @author Dirk Holzapfel
 * @version 1.0 
 */
public class MasterCore {

	private TestRun tr;	
	private SimMaster simMaster;
	private PreferenceList preferenceList = new PreferenceList();
	private Map<Server, ParetoPoint> paretoMap = new HashMap<Server, ParetoPoint>();
	private Map<Server, ParetoPoint> paretoMapOld = new HashMap<Server, ParetoPoint>();
	private Vector<ParetoTransportData> paretoTransport = new Vector<ParetoTransportData>();
	private boolean updating = false;
	private boolean finished = false;


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	public MasterCore(SimMaster simMaster, TestRun tr) {
		super();
		this.simMaster = simMaster;
		this.tr = tr;		

		buildPreferenceList();		
		initParetoMap();
		initParetoLists();
		buildParetoTransport();		
	}


	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * method called from workers when they need new work
	 * 
	 * @return the Pareto-Data to calculate
	 */
	public synchronized ParetoTransportData getParetoDataToCalculate() {		
		if (paretoTransport.size() > 0) {			
			return paretoTransport.remove(0);
		}
		else {
			if (preferenceList.isEverythingFinished()) {
				return null;
			}
			else {
				System.out.println("masterCore: updating");
				while(updating) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (paretoTransport.size() > 0) {
					return paretoTransport.remove(0);
				}
				else return null;
			}
		}
	}


	/**
	 * workers set their calculated results
	 * 
	 * @param ppVec Vector of ParetoPoints
	 * @param bestParetoPoint the ParetoPoint with highest Earning in ppVec
	 * @param server the storage network, the optimisation was made for
	 */
	public synchronized void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server) {
		paretoMapOld.put(server, paretoMap.get(server));
		paretoMap.put(server, bestParetoPoint);
		preferenceList.paretoDiff(paretoMap.get(server), paretoMapOld.get(server));
		serializeParetoVector(ppVec, server);

		updating = true;
		if (paretoTransport.size() == 0 && !preferenceList.isEverythingFinished()) { //es geht weiter
			buildParetoTransport();
			updating = false;			
			notify();
		}
		else if (preferenceList.isEverythingFinished() && !finished){ //sim finished
			finished = true;
			updating = false;			
			notify();
			simFinished();
		}
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * private Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/


	/**
	 * invoked when everything is calculated. deletes the serialized-data on harddisk, 
	 * sends the result to the SimMaster 
	 */
	private void simFinished() {				

		Matrix matrix = buildActMatrix();

		SimResult sr = new SimResult();
		sr.setEarning(tr.getLayout().getEarning(matrix));
		sr.setJobServerMapping(matrix);

		int a = 0;
		for(Server server: tr.getLayout().getServerVector()) {
			a += deSerializeParetoVector(server).size();
			File file = new File("D:\\pareto\\pareto"+server.getId()+".ser");			

			if(file.exists()){
				file.delete();
			}
		}

		System.out.println("PARETOPOINTS IN SOLUTION: " + a);
		simMaster.addSimResult(0, tr.getId(), sr);
		simMaster.finishedSim(0, tr.getId());			
	}


	/**
	 * builds the Pareto-Data-Packages to calculate.
	 */
	private void buildParetoTransport() {

		for (Server server: tr.getLayout().getServerVector()) {
			Vector<Job> jobVec = preferenceList.getAllJobsPreferingServer(server);
			if (jobVec.size() > 0){
				paretoTransport.add(new ParetoTransportData(jobVec,deSerializeParetoVector(server),server));				
			}
		}
	}


	/**
	 * inits all storage networks in the local ParetoMap with the Start-Pareto-Points (0,0)
	 */
	private void initParetoMap() {
		//(0,0) paretopoints
		for (Server server: tr.getLayout().getServerVector()) {
			paretoMap.put(server, new ParetoPoint(server));
		}		
	}


	/**
	 * helper-method: prints state-information to console.
	 * 
	 * @param a the cycle
	 */
	@SuppressWarnings("unused")
	private void printActMatrixToConsole(int a) {
		Matrix m = buildActMatrix();
		System.out.println("matrix nach durchlauf " + a);
		m.printMatrixToConsoleInt();
		System.out.println();
		printPreferenceListToConsole();
		System.out.println("earning: " + tr.getLayout().getEarning(m));		
	}


	/**
	 * @return the actual solution-matrix
	 */
	private Matrix buildActMatrix() {
		Matrix m = new Matrix(tr.getLayout().getNumJobs(), tr.getLayout().getNumServer());
		for (Server server: tr.getLayout().getServerVector()) {
			for (Job job: paretoMap.get(server).getJobs()) {
				m.assignJobToServer(job.getId(), server.getId());
			}				
		}
		return m;
	}


	/**
	 * For each SLA a PreferenceList for storage networks is built
	 */
	private void buildPreferenceList() {
		Matrix matrix = new Matrix(tr.getLayout().getNumJobs(), tr.getLayout().getNumServer());
		PreferenceListComparator preferenceListComparator = new PreferenceListComparator();
		for (Job job: tr.getLayout().getJobVector()) {			
			preferenceList.getPreferenceList().put(job, new Vector<PreferencePoint>());			
			preferenceList.getPreferenceListDump().put(job, new Vector<PreferencePoint>());
			for (Server server: tr.getLayout().getServerVector()) {
				matrix.assignJobToServer(job.getId(), server.getId());
				long earning = tr.getLayout().getEarning(matrix);
				if ( tr.getLayout().isMatrixValid(matrix) &&  earning > 0 ) {
					PreferencePoint prefpoint = new PreferencePoint(server, job, earning);
					preferenceList.getPreferenceList().get(job).add(prefpoint);
					preferenceList.getPreferenceListDump().get(job).add(prefpoint);
				}
				matrix.clear();
			}
			Collections.sort(preferenceList.getPreferenceList().get(job), preferenceListComparator);
			Collections.sort(preferenceList.getPreferenceListDump().get(job), preferenceListComparator);
		}	
		//printPreferenceListToConsole();		
	}


	/**
	 * helper-method: prints the PrefernceLists to the console
	 */
	private void printPreferenceListToConsole() {
		for (Job job: tr.getLayout().getJobVector()) {
			System.out.print("Job" + job.getId()+": ");
			for (PreferencePoint pp: preferenceList.getPreferenceList().get(job)) {
				//System.out.print("Job #"+job.getId()+": ");
				//System.out.print("Server #" + pp.getServer().getId() + ", earning: " + pp.getEarning()+" *** ");
				System.out.print(pp.getServer().getId()+"("+pp.getEarning()+")");
				if (!pp.isAvailable())System.out.print("f");
				System.out.print(" * ");
			}
			System.out.println();
		}
	}


	/**
	 * serializes a Vector of ParetoPoints to harddisk
	 * 
	 * @param pp the Vector of ParetoPoints
	 * @param server the storage network the pp belongs to
	 */
	private void serializeParetoVector(Vector<ParetoPoint> pp, Server server) {
		try {
			FileOutputStream fs = new FileOutputStream("D:\\pareto\\pareto"+server.getId()+".ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(pp);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * deserializes a Vector of ParetoPoints from harddisk
	 * 
	 * @param server the storage network the data is wanted from 
	 * @return Vector of ParetoPoints
	 */
	@SuppressWarnings("unchecked")
	private Vector<ParetoPoint> deSerializeParetoVector(Server server){
		try {
			FileInputStream fs = new FileInputStream("D:\\pareto\\pareto"+server.getId()+".ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			Vector<ParetoPoint> pp = (Vector<ParetoPoint>)os.readObject();
			os.close();
			return pp;						

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * called in initialisation. For each storage network a vector with the Start-Pareto-Point
	 * (0,0) is serialized to harddisk.
	 */
	private void initParetoLists() {
		// add point (0,0) to every server

		for (Server s: tr.getLayout().getServerVector()) {
			ParetoPoint pp = new ParetoPoint(s);
			Vector<ParetoPoint> ppvec = new Vector<ParetoPoint>();
			ppvec.add(pp);
			serializeParetoVector(ppvec, s);
		}
	}


}//end of class
