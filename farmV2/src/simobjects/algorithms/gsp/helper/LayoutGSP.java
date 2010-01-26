package simobjects.algorithms.gsp.helper;

import java.util.Iterator;
import java.util.Vector;
import simobjects.Job;
import simobjects.Layout;
import simobjects.Matrix;
import simobjects.Server;
import simobjects.algorithms.gsp.helper.jobs.JobGSP;
import simobjects.algorithms.gsp.helper.jobs.JobPref;
import simobjects.algorithms.gsp.helper.server.ServerGSP;
import simobjects.algorithms.gsp.helper.server.ServerPref;


/**
 * @author Christoph Beck
 *
 */
public class LayoutGSP {


	private double [][] serverValues;
	private double [][] jobValues;
	private double[][]  totalEarningMatrix;
	private double[][]  relEarningMatrix;

	private Layout layout;
	private Vector<JobGSP> 		gspJobs;
	private Vector<ServerGSP>	gspServers; 

	private boolean serverSimple;
	private boolean jobSimple;
	private boolean speedup;

	

	/**
	 * constructor for class LayoutGSP
	 * @param layout
	 * @param totalEarningMatrix
	 * @param relEarningMatrix
	 * @param serverValues
	 * @param jobValues
	 * @param serverSimple
	 * @param jobSimple
	 */
	public LayoutGSP(
			Layout layout, 
			double[][] totalEarningMatrix, 
			double[][] relEarningMatrix, 
			double[][] serverValues,  
			double[][] jobValues,
			boolean serverSimple,
			boolean jobSimple) {

		this.layout = layout;

		gspJobs 	= new Vector<JobGSP>();
		gspServers	= new Vector<ServerGSP>();

		this.serverValues 		= serverValues;
		this.jobValues 			= jobValues;
		this.totalEarningMatrix	= totalEarningMatrix;
		this.relEarningMatrix	= relEarningMatrix;
		this.serverSimple=serverSimple;
		this.jobSimple=jobSimple;
		this.speedup = false;
		init ();
	}
	
	public LayoutGSP(
			Layout layout, 
			double[][] totalEarningMatrix, 
			double[][] relEarningMatrix, 
			double[][] serverValues,  
			double[][] jobValues,
			boolean serverSimple,
			boolean jobSimple,
			boolean speedup) {

			this( layout, 
					totalEarningMatrix, 
					relEarningMatrix, 
					serverValues,  
					jobValues,
					serverSimple,
					jobSimple);
			this.setSpeedup(speedup);
			
	}

	public boolean isSpeedup() {
		return speedup;
	}

	public void setSpeedup(boolean speedup) {
		this.speedup = speedup;
	}
	/**
	 *  inits the layout
	 */
	public void init() {
		initGSPJobs();
		initGSPServer();
	}

	/**
	 *  inits the GSPServers
	 */
	private void initGSPServer() {
		ServerGSP gspServer;

		int iServer = 0;
		for (Server aktServer : layout.getServer()) {
			gspServer = new ServerGSP(aktServer);

			for (int iJob = 0; iJob < layout.getNumJobs(); iJob++) {
				if(totalEarningMatrix[iServer][iJob]>0) {
					if(serverSimple) {
						gspServer.add(new JobPref(layout.getJob(iJob),serverValues[iServer][iJob]));
					}
					else {
						gspServer.add(new JobPref(
								layout.getJob(iJob),
								serverValues		[iServer][iJob],
								relEarningMatrix	[iServer][iJob],
								totalEarningMatrix	[iServer][iJob])
						);
					}
				}
			}

			gspServers.add(gspServer);
			iServer ++;
		}

	}

	/**
	 * inits the JobGSPs
	 */
	private void initGSPJobs() {
		JobGSP gspJob;

		int iJob = 0;
		for (Job aktJob : layout.getJobs()) {
			gspJob = new JobGSP(aktJob);

			for (int iServer = 0; iServer < layout.getNumServer(); iServer++) {
				if(totalEarningMatrix[iServer][iJob]>0) {
					if(jobSimple) {
						gspJob.add(new ServerPref(layout.getServer(iServer),-1,(int)Math.round(jobValues[iServer][iJob])));
					}
					else {
						gspJob.add(new ServerPref(
								layout.getServer(iServer),
								-1,
								jobValues[iServer][iJob],
								relEarningMatrix	[iServer][iJob],
								totalEarningMatrix	[iServer][iJob]
						)
						);
					}
				}
			}
			gspJob.finalizePref();

			gspJobs.add(gspJob);
			iJob ++;
		}
	}

	/**
	 * @return
	 */
	public Vector<JobGSP> getGspJobs() {
		return gspJobs;
	}

	/**
	 * @return
	 */
	public Vector<ServerGSP> getGspServers() {
		return gspServers;
	}

	/**
	 * gets ServerGSP By ID
	 * @param id to look for
	 * @return ServerGSP (if found)
	 */
	public ServerGSP getServerGSPByID (int id) {
		for (Object element : gspServers) {
			ServerGSP serverGSP = (ServerGSP) element;
			if(serverGSP.getServer().getId()==id) {
				return serverGSP;
			}
		}
		return null;
	}

	/**
	 * gets JobGSP By ID
	 * @param id
	 * @return
	 */
	public JobGSP getJobGSPByID(int id) {
		for (Object element : gspJobs) {
			JobGSP jobGSP = (JobGSP) element;
			if(jobGSP.getJob().getId()==id) {
				return jobGSP;
			}
		}
		return null;
	}

	/**
	 * dumps everything to sysout
	 */
	public void dump() {
		long earningTotal = 0;
		for (int iJob=0; iJob < gspJobs.size(); iJob ++ ) {
			boolean map [] = map(gspJobs.get(iJob).getAssignedTo());
			for (int iServer = 0; iServer < map.length; iServer++) {
				if(map[iServer]) {
					System.out.print("x ");
					earningTotal = earningTotal + ((long)totalEarningMatrix[iServer][iJob]);
				}
				else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
		System.out.println("Earning total="+earningTotal);
	}
	
	/**
	 * @return the actual assignment
	 */
	public Matrix getMatrix (){
		Matrix matrix = new Matrix(layout.getNumJobs(),layout.getNumServer());
	
		for (int iJob=0; iJob < gspJobs.size(); iJob ++ ) {
			boolean map [] = map(gspJobs.get(iJob).getAssignedTo());
			for (int iServer = 0; iServer < map.length; iServer++) {
				if(map[iServer]) {
					matrix.assignJobToServer(iJob, iServer);
				}
			}
			
		}
		return matrix;
	}

	/**
	 * dums the actual Preflists
	 */
	public void dumpPreflists() {
		System.out.println("Jobs");
		for (Object element : gspJobs) {
			((JobGSP) element).dumpPreflist();			
		}
		System.out.println("Server");
		for (Object element : gspServers) {
			((ServerGSP) element).dumpPreflist();			
		}
	}
	
	/**
	 * @return the actual earning
	 */
	public long getEarning () {
		long earningTotal = 0;
		for (int iJob=0; iJob < gspJobs.size(); iJob ++ ) {
			boolean map [] = map(gspJobs.get(iJob).getAssignedTo());
			for (int iServer = 0; iServer < map.length; iServer++) {
				if(map[iServer]) {
					System.out.println("job #"+iJob+" on Server #"+iServer+" brings:"+((long)totalEarningMatrix[iServer][iJob]));
					earningTotal = earningTotal + ((long)totalEarningMatrix[iServer][iJob]);
					System.out.println("earningTotal="+earningTotal);
				}
			}
		}
		return earningTotal;
	}


	/**
	 * maps from job id to boolean array
	 * @param runningOnServer
	 * @return
	 */
	private boolean[] map (int runningOnServer) {
		boolean map [] = new boolean [gspServers.size()];
		for (int i = 0; i < map.length; i++) {
			if(gspServers.get(i).getServer().getId()==runningOnServer) {
				map[i] = true;
			}
			else {
				map[i] = false;
			}
		}
		return map;
	}
}
