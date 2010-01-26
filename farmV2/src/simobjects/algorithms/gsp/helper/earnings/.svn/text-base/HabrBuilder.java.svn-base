package simobjects.algorithms.gsp.helper.earnings;

import simobjects.Layout;

/**
 * @author Christoph Beck
 * calculates the habr matrix, considering the different job running times. 
 * the best - aka lowest - entry is taken
 */
public class HabrBuilder {
	private Layout 	layout;
	private DMatrix dm;
	private DMatrix habrAll;
	private int 	numServers;
	private int 	numJobs;
	private int 	startTimes[]; 
	private String 	name;
	private boolean DEBUG=false;
	
	/**
	 * constructor for class HabrBuilder
	 * @param layout
	 * @param dm
	 * @param name
	 */
	public HabrBuilder(Layout layout, DMatrix dm, String name) {
		super();
		this.name 	= name;
		this.layout = layout;
		numServers 	= layout.getNumServer();
		numJobs		= layout.getNumJobs();
		this.dm = dm;
		
		init();
	}

	/**
	 * inits the HabrBuilder
	 */
	private void init() {
		double maskRows[] = new double [numServers];
		for (int i = 0; i < maskRows.length; i++) {
			maskRows[i] = 1.0;
		}
		
		
		// go through all start times
		double habrMatrix[][]  = new double [numServers][numJobs] ;
		for (int iServers = 0; iServers < numServers; iServers++) {
			for (int iJobs = 0; iJobs < numJobs; iJobs++) {
				habrMatrix[iServers][iJobs] = Double.MAX_VALUE;
			}
		}
		habrAll = new DMatrix(habrMatrix,name);
		
		startTimes = layout.getStartTimes();
		
		
		DMatrix excerpt;
		DMatrix habr;
		
		double jobsRunning[];
		for (int i = 0; i < startTimes.length; i++) {
	
			
			jobsRunning = convertToDouble(layout.getJobsRunningAtT(startTimes[i]));
			if(DEBUG ){
				dumJobsRunning(jobsRunning, i);
			}
			
			
			// Build an excerpt DMatrix wiht all the jobs running at startTimes[i]
			excerpt = new DMatrix (dm.collapseMatrix(maskRows, jobsRunning),name);
			
			// Build the habr - Matrix of the excerpt
			habr = new DMatrix (excerpt.getHabrMatrix(),name);
			
			// now akkumulate it
			habrAll.minimizeBy(habr.expandMatrix(habrMatrix, maskRows, jobsRunning));
			
			
		}
		
		// re - calc  getMinTotal (will still be Double.MAX_VALUE
		habrAll.init();
		
		// ready!
		habrAll = new DMatrix(habrAll.raiseBy((-1 * habrAll.getMinTotal())),name);
		
		
		
	}

	/**
	 * @param jobsRunning
	 * @param i
	 */
	private void dumJobsRunning(double[] jobsRunning, int i) {
		System.out.print("Well. simT="+startTimes[i]+"\t");
		for (double element : jobsRunning) {
			if(element>0.0) {
				System.out.print("*\t");
			}
			else {
				System.out.print(".\t");
			}
			
		}
		System.out.println();
	}
	
	/**
	 * @param jobsRunning
	 * @return
	 */
	private double [] convertToDouble (boolean jobsRunning[]) {
		double d [] = new double [jobsRunning.length];
		for (int i = 0; i < jobsRunning.length; i++) {
			if(jobsRunning[i]) {
				d [i] =  1.0;
			}
			else {
				d [i] =  0.0;
			}
		}
		return d;
	}

	/**
	 * @return the habrAll Matirx
	 */
	public DMatrix getHabr() {
		return habrAll;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
