package simobjects.algorithms.gsp;

import java.util.ArrayList;
import simobjects.Job;
import simobjects.algorithms.gsp.helper.server.ServerGSP;


/**
 * @author Christoph Beck
 *
 */
public class Request {
	private int 		requestJobIDs[];

	private boolean 	request[];
	private boolean 	runing[];
	private boolean 	result[];

	private ServerGSP 	serverGSP;
	private int 		jobStartTimes [];
	private int			minFreeDasd;

	private boolean accepted[];
	private boolean unassigned[];
	private boolean rejected[];

	/**
	 * constructor for class Request
	 * @param requestJobIDs
	 * @param serverGSP
	 */
	public Request(int[] requestJobIDs, ServerGSP serverGSP) {

		this.requestJobIDs 	= requestJobIDs;
		this.serverGSP 		= serverGSP;
		this.request 		= mapRequest();
		this.runing 		= mapRuning();
		this.result			= mapResult();
		this.jobStartTimes	= serverGSP.getJobStartTimes();
		accepted			= new boolean [runing.length];
		unassigned 			= new boolean [runing.length];
		rejected			= new boolean [runing.length];
		doRequest();
	}

	/**
	 * handles the request
	 */
	private void doRequest() {
		if(result==null) {

		}
		else {
			minFreeDasd = serverGSP.getServer().getDasd();
			int dasdT;
			
			
			for (int element : jobStartTimes) {
				if ((dasdT = doRequestAtTsim(element, result))<minFreeDasd){
					minFreeDasd = dasdT;
				}
			}
		}
	}


	/**
	 * handle the result.
	 * @return number of accepted jobs
	 */
	private int analyseResult() {
		int count				= 0;

		for (int iRequest = 0; iRequest < accepted.length; iRequest++) {
			if(request[iRequest] && result[iRequest]) {
				accepted[iRequest] = true;
				count ++;
			}
			else {
				accepted[iRequest] = false;
			}

			unassigned[iRequest] 	= runing[iRequest] 	&& (!result[iRequest]);
			rejected[iRequest] 		= request[iRequest] && (!result[iRequest]);
		}

		return count;

	}

	/**
	 * @param header
	 * @param toDump
	 */
	public void dumpRequest(String header, boolean[] toDump) {
		if(request != null) {
			System.out.print(""+header+"\t");
			for (boolean element : toDump) {
				if(element) {
					System.out.print(" X ");
				}
				else {
					System.out.print(" . ");
				}
			}
			System.out.println();
		}
		
	}

	public void dumpRequest(String header) {
		boolean[] toDump = mapRequest();	
		if(request != null) {
			System.out.print(""+header+"\t");
			for (boolean element : toDump) {
				if(element) {
					System.out.print(" X ");
				}
				else {
					System.out.print(" . ");
				}
			}
			System.out.println();
		}
		
	}

	/**
	 * @param tSim
	 * @param requestT
	 * @return
	 */
	private int doRequestAtTsim(int tSim, boolean requestT[]) {
		int dasdT = serverGSP.getServer().getDasd();

		Job aJob;

		int startT;
		int endT;
		//System.out.println();
		for (int i = 0; i < requestT.length; i++) {
			
			// if a Job is requested
			if(requestT[i]) {
				// then,
				aJob=serverGSP.getJobs().get(i).getJob();

				// And if it should run at tsim
				startT 	= 	aJob.getStartTime();
				endT	=	startT + aJob.getDuration();


				if ((endT>tSim)&&(startT<=tSim)) {

					// then, if it does not fit
					if(aJob.getDasd() > dasdT) {
						System.out.println("Tsim="+tSim+" Job "+aJob.getId()+" too big. avail:"+dasdT+", required:"+aJob.getDasd());
						// wipe it from the request
						requestT[i]=false;
					}
					// or, if it is fit, leave him assigned.
					else {
						System.out.println("Tsim="+tSim+" Job "+aJob.getId()+" accepted. avail:"+dasdT+", required:"+aJob.getDasd());
						dasdT = dasdT - aJob.getDasd(); 
					}
				}
			}
		}

		return dasdT;
	}

	private int doRequestAtTsimMT(int tSim, boolean requestT[]) {
		int dasdT = serverGSP.getServer().getDasd();

		Job aJob;

		int startT;
		int endT;
		//System.out.println();
		for (int i = 0; i < requestT.length; i++) {
			
			// if a Job is requested
			if(requestT[i]) {
				// then,
				aJob=serverGSP.getJobs().get(i).getJob();

				// And if it should run at tsim
				startT 	= 	aJob.getStartTime();
				endT	=	startT + aJob.getDuration();


				if ((endT>tSim)&&(startT<=tSim)) {
					// if job could be assigned
					// get earning with job assigned
						
					// and without it
					// whats better ?

				}
			}
		}

		return dasdT;
	}
	
	private long getearning (int tSim, boolean requestT[], int iJob) {
		int dasdT = serverGSP.getServer().getDasd();

		Job aJob;

		int startT;
		int endT;
		//System.out.println();
		for (int i = iJob; i < requestT.length; i++) {
			
			// if a Job is requested
			if(requestT[i]) {
				// then,
				aJob=serverGSP.getJobs().get(i).getJob();

				// And if it should run at tsim
				startT 	= 	aJob.getStartTime();
				endT	=	startT + aJob.getDuration();


				if ((endT>tSim)&&(startT<=tSim)) {
					// if job could be assigned
					// get earning with job assigned
						
					// and without it
					// whats better ?

				}
			}
		}
		
		return 0;
	}
	/**
	 * @return
	 */
	private boolean[] mapRuning() {
		return serverGSP.getJobStatus();
	}

	/**
	 * @return
	 */
	private boolean[] mapRequest() {
		int counter = 0;
		int	jobIDs[] 	= serverGSP.getJobIds();
		boolean tmp[] 	= new boolean [jobIDs.length];
		for (int j = 0; j < jobIDs.length; j++) {
			tmp[j] = false;
			for (int element : requestJobIDs) {
				if(element==jobIDs[j]) {
					tmp[j] = true;
					counter ++;
				}
			}
		}
		if (counter==0) {
			return null;
		}
		return tmp;
	}

	/**
	 * maps result to a boolean array
	 * @return result
	 */
	private boolean[] mapResult() {
		if(request!=null){
			result 	= new boolean [runing.length];
			for (int i = 0; i < result.length; i++) {
				result[i] = request[i] || runing[i];
			}
		}
		return result;
	}

	/**
	 * 
	 * @return true if at least one job of request is taken
	 */
	public boolean isSuccess() {
		if(request==null){
			return false;
		}
		if (analyseResult()==0){
			return false;
		}
		return true;
	}

	/**
	 * @return 
	 */
	public boolean[] getResult() {
		return result;
	}

	/**
	 * @return
	 */
	public int getMinFreeDasd() {
		return minFreeDasd;
	}

	/**
	 * @return
	 */
	public boolean[] getAcceptedNative() {
		return accepted;
	}

	/**
	 * @return
	 */
	public boolean[] getUnassignedNative() {
		return unassigned;
	}

	/**
	 * @return
	 */
	public boolean[] getRejectedNative() {
		return rejected;
	}

	/**
	 * @return
	 */
	public int[] getAcceptedJobIds() {
		return nativeToIds(accepted);
	}

	/**
	 * @return
	 */
	public int[] getUnassignedJobIds() {
		return nativeToIds(unassigned);
	}

	/**
	 * @return
	 */
	public int[] getRejectedJobIds() {
		return nativeToIds(rejected);
	}

	/**
	 * maps the boolean request to an array of ids
	 * @param nativeRequest
	 * @return
	 */
	public int[] nativeToIds (boolean nativeRequest[]) {
		ArrayList <Integer> idsV = new  ArrayList <Integer>();
		for (int i = 0; i < accepted.length; i++) {
			if(nativeRequest[i]) {
				idsV.add((serverGSP.getJobs().get(i).getJob()).getId());
			}
		}
		int iDs[] = new int [idsV.size()];
		for (int i = 0; i < iDs.length; i++) {
			iDs[i] = idsV.get(i);
		}
		return iDs;
	}
}
