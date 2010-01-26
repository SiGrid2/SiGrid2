package simobjects;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CollectionOfElements;

import simobjects.transport.farm.JobAxis;
import simobjects.transport.farm.LayoutAxis;
import simobjects.transport.farm.ServerAxis;
import simobjects.transport.gui.JobGui;
import simobjects.transport.gui.LayoutGui;
import simobjects.transport.gui.ServerGui;

/**
 * @author Christoph Beck
 * 
 * Class to hold Servers and Jobs
 * Also provides some generation methods for layouts
 *
 */
/**
 * @author Admin
 *
 */
/**
 * @author Admin
 *
 */
@Entity
public class Layout implements Serializable{



	private static final long serialVersionUID = 1L;

	@CollectionOfElements
	private List <Job> jobs;

	@CollectionOfElements
	private List <Server> server;

	@Id @GeneratedValue
	@Column(name="layout_id")
	private int id;

	private String name;

	private int numJobs  = 0;
	private int numServer  = 0;


	/**
	 * Default constructor
	 */
	public Layout() {
		super();
		jobs   = new Vector<Job>();
		server = new Vector<Server>();
	}






	/**
	 * @param job the job to be added
	 */
	public void addJob(Job job){
		jobs.add(job);
		numJobs++;
	}

	/**
	 * @param server the server to be added
	 */
	public void addServer(Server server){
		this.server.add(server);
		numServer++;
	}

	/**
	 * @param index index position of the element in the jobs Vector
	 * @return returns the job at the given index position
	 */
	public Job getJob (int index) {

		return jobs.get(index);
	}

	/**
	 * @param index index position of the element in the server Vector
	 * @return returns the server at the given index position
	 */
	public Server getServer (int index) {
		return server.get(index);
	}

	/**
	 * @return the number of servers currently attached
	 */
	public int getNumServer () {
		return server.size();
	}

	/**
	 * @return the number of jobs currently attached
	 */
	public int getNumJobs () {
		return jobs.size();
	}

	
	/**
	 * @return the jobs List as a vector
	 */
	public Vector<Job> getJobVector(){
		return (Vector<Job>)jobs;
	}
	/**
	 * @return the jobs  List
	 */
	public List<Job> getJobList(){
		return jobs;
	}
	/**
	 * @return the jobs  List
	 */
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * @return the server List as a vector
	 */
	public Vector<Server> getServerVector(){
		return (Vector<Server>)server;
	}
	/**
	 * @return the server  List
	 */
	public List<Server> getServerList(){
		return server;
	}
	/**
	 * @return the server  List
	 */
	public List<Server> getServer() {
		return server;
	}
	/**
	 * @return the name of a layout
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name of a layout
	 */
	public void setName(String name) {
		this.name = name;
	}






	/**
	 * @param jobs a List with jobs
	 */
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}





	/**
	 * @param server a List with servers
	 */
	public void setServer(List<Server> server) {
		this.server = server;
	}







	/**
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}





	/**
	 * @param matrix a mapping of jobs to servers in matrix form
	 * @return the total earning of the provided mapping
	 */
	public long getEarning (Matrix matrix) {

		int runnungOnServer = 0;
		long earning = 0;
		long jobEarning = 0;

		for (int i = 0; i < getNumJobs(); i++) {
			jobEarning = 0;
			runnungOnServer = matrix.jobRunningOnServer(i);
			if (runnungOnServer>=0) {
				jobEarning = getJob(i).getEarning() - getServer(runnungOnServer).getCost();
				jobEarning = jobEarning * getJob(i).getDasd();
				jobEarning = jobEarning * getJob(i).getDuration();

				earning = earning + jobEarning - getPenalty (i, runnungOnServer);
			}
		}

		return earning;
	}

	/**
	 * @param iVec id of job
	 * @param runningOnServer id of server
	 * @return the penalty caused by this mapping (if there is any)
	 */
	private long getPenalty(int iVec, int runningOnServer) {
		if (getServer(runningOnServer).getRelCat() > getJob(iVec).getRelCat() || getServer(runningOnServer).getSpeedCat() > getJob(iVec).getSpeedCat()){
			return getJob(iVec).getPenalty();
		}
		return 0;
	}

	
	/**
	 * @param matrix tests if a matrix ist valid, which means if all jobs assingned can run.
	 * tests without any optimization, e.g. runs through all for loops even if the matrix has already proven to be invalid
	 * @return true if the matrix is valid, false otherwise.
	 */
	public boolean isMatrixValidUnopt (Matrix matrix) {
		int jobsRunningOnServer[] = null;
		int serverLoad;
		boolean valid = true;
		for (int aktServer = 0; aktServer < getNumServer(); aktServer++) {
			jobsRunningOnServer = matrix.getJobsOnServer(aktServer);
			if (jobsRunningOnServer != null) {
				serverLoad = 0;
				for (int j = 0; j < jobsRunningOnServer.length; j++) {
					serverLoad = serverLoad + getJob(j).getDasd();
				}
				getEarning(matrix);
				if (serverLoad > getServer(aktServer).getDasd()) {
					valid = false;
				}
			}
		}
		return valid;
	}


	/**
	 * @param matrix tests if a matrix ist valid, which means if all jobs assingned can run
	 * @return if the matrix is valid, false otherwise.
	 */
	public boolean isMatrixValid (Matrix matrix) {

		Vector<Integer> startTimes = new Vector<Integer>();

		for (int aktJob = 0; aktJob < getNumJobs(); aktJob++) {
			if (matrix.isJobRunningOnServer(aktJob)) {				
				int startTime = jobs.get(aktJob).getStartTime();
				if (!startTimes.contains(startTime)){
					startTimes.add(startTime);
				}
			}
		}


		int serverLoad []  = new int[getNumServer()];
		for (int startTime : startTimes) {
			for (int i = 0; i < serverLoad.length; i++) {
				serverLoad[i]=0;
			}

			for (int aktJob = 0; aktJob < getNumJobs(); aktJob++) {
				for (int aktServer = 0; aktServer < serverLoad.length; aktServer++) {
					if (matrix.isJobRunningOnServer(aktJob, aktServer)) {
						if(jobs.get(aktJob).getStartTime() == startTime ||
								(jobs.get(aktJob).getStartTime() < startTime && jobs.get(aktJob).getStartTime() + jobs.get(aktJob).getDuration() > startTime)){

							serverLoad[aktServer] = serverLoad[aktServer] + ((Vector<Job>)jobs).elementAt(aktJob).getDasd();
							if (serverLoad[aktServer]> ((Vector<Server>)server).elementAt(aktServer).getDasd()) {
								return false;
							}

						}
						break;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @param jobVec the mapping planned for a server
	 * @param ServerNumber the number of the server
	 * @return
	 */
	public boolean isJobVectorValid (Vector<Job> jobVec, int ServerNumber) {

		Vector<Integer> startTimes = new Vector<Integer>();

		for (Job job:jobVec) {
			int startTime = job.getStartTime();
			if (!startTimes.contains(startTime)){
				startTimes.add(startTime);
			}
		}




		for (int startTime : startTimes) {
			int serverLoad = 0;
			for (Job job:jobVec) {
				if(job.getStartTime() == startTime ||
						(job.getStartTime() < startTime && job.getStartTime() + job.getDuration() > startTime)){

					serverLoad = serverLoad + job.getDasd();
					if (serverLoad> ((Vector<Server>)server).elementAt(ServerNumber).getDasd()) {
						return false;
					}
				}
			}
		}
		return true;
	}


	
	/**
	 * creates a Layout for performance testing purposes
	 * @param numJobs number of jobs the layout shall have
	 * @param numServer number of jobs the layout shall have
	 * @param numJobsPossible how many job shall be able to run simultanously
	 * @return the requested layout
	 */
	public static Layout createLayout (int numJobs, int numServer, int numJobsPossible) {
		Layout layout = new Layout ();


		int jobDasd		= 10;
		int serverDasd  = jobDasd * numJobsPossible ;

		int jobIncome	= 10;
		int serverCost	= 1;
		int jobEarning	= jobIncome + serverCost;


		for (int i = 0; i < numJobs; i++) {
			int speedcat = (int) (Math.random()*5 + 1);
			int relcat = (int) (Math.random()*5 + 1);
			layout.addJob(new Job(i,jobEarning,jobDasd,0,1,speedcat,relcat,0));

		}
		for (int i = 0; i < numServer; i++) {
			layout.addServer(new Server(i,serverCost,serverDasd,1,1));
		}

		return layout;
	}

	public static Layout createParetoLayout1() {
		Layout l = new Layout();
		l.addServer(new Server(0,10,100,5,5));
		l.addServer(new Server(1, 100,100,1,1));

		l.addJob(new Job(0,1000,10,1,1,5,5,10000));
		l.addJob(new Job(1,10000,1,1,1,1,1,100000));

		return l;
	}

	/**
	 * creates a Layout for performance testing purposes
	 * @param numJobs number of jobs the layout shall have
	 * @param numServer number of jobs the layout shall have
	 * @param numJobsPossible how many job shall be able to run simultanously
	 * @return the requested layout
	 */
	public static Layout createRandomLayout (int numJobs, int numServer, int numJobsPossible) {
		Layout layout = new Layout ();


		for (int i = 0; i < numJobs; i++) {
			int speedcat = (int) (Math.random()*5 + 1);
			int relcat = (int) (Math.random()*5 + 1);
			int jobIncome	= (int) (Math.random()*15 + 1);

			int jobDasd		= (int)(Math.random()*10 + 1);

			int starttime = (int)(Math.random()*10 + 1);
			int duration = (int)(Math.random()*10 + 1);

			layout.addJob(new Job(i,jobIncome,jobDasd,starttime,duration,speedcat,relcat,10000));

		}
		for (int i = 0; i < numServer; i++) {
			int serverDasd  = (int)(Math.random()*20 + 1);
			int serverCost	= (int)(Math.random()*3 + 1);
			layout.addServer(new Server(i,serverCost,serverDasd,1,1));
		}

		return layout;
	}

	
	/** A very simple estimation of the maximum earning a Layout might produce.
	 * @return the earning the layout can under no circumstances exceed (all job on cheapest server, no penalties)
	 */
	public long getIdealEarning () {
		int cost = Integer.MAX_VALUE;
		long earning = 0;
		for (Server s : server) {
			if(s.getCost() < cost) {
				cost = s.getCost();
			}
		}

		for (Job j : jobs) {
			earning = earning + j.getDasd() * j.getDuration() * (j.getEarning() - cost);
		}

		return earning;
	}

	public Vector<Job> getAllJobsWithServerClass(int serverClass){
		Vector<Job> temp = new Vector<Job>();
		for(Job job:jobs){
			if(job.getServerClass() == serverClass){
				temp.add(job);
			}
		}
		return temp;

	}

	public Vector<Job> getAllJobsWithServerClassInRange(int serverClassMin, int serverClassMax){
		Vector<Job> temp = new Vector<Job>();
		for(Job job:jobs){
			if(job.getServerClass() >= serverClassMin && job.getServerClass() <= serverClassMax){
				temp.add(job);
			}
		}
		return temp;

	}



	/**
	 * @param serverClass 
	 * @return returns the server with the resquested class if there is one, false otherwise
	 */
	public Server getServerWithServerClass(int serverClass){

		for(Server s:server){
			if(s.getServerClass() == serverClass){
				return s;
			}
		}
		return null;

	}
	/**
	 * @return the layout id
	 */
	public int getId() {
		return id;
	}

	/**
	 * creates a Layout with 5 server classes for performance testing purposes
	 * @param layout an empty layout
	 * @return the requested layout
	 */
	public static Layout add25ServerClassestoLayout(Layout layout){
		Server s1 = new Server(0,25,160,1,1);
		Server s2 = new Server(1,24,160,2,1);
		Server s3 = new Server(2,23,160,3,1);
		Server s4 = new Server(3,22,160,4,1);
		Server s5 = new Server(4,21,160,5,1);

		Server s6 = new Server(5,20,160,1,2);
		Server s7 = new Server(6,19,160,2,2);
		Server s8 = new Server(7,18,160,3,2);
		Server s9 = new Server(8,17,160,4,2);
		Server s10 = new Server(9,16,160,5,2);

		Server s11= new Server(10,15,160,1,3);
		Server s12 = new Server(11,14,160,2,3);
		Server s13 = new Server(12,13,160,3,3);
		Server s14 = new Server(13,12,160,4,3);
		Server s15 = new Server(14,11,160,5,3);

		Server s16 = new Server(15,10,160,1,4);
		Server s17 = new Server(16,9,160,2,4);
		Server s18 = new Server(17,8,160,3,4);
		Server s19 = new Server(18,7,160,4,4);
		Server s20 = new Server(19,6,160,5,4);

		Server s21= new Server(20,5,160,1,5);
		Server s22 = new Server(21,4,160,2,5);
		Server s23 = new Server(22,3,160,3,5);
		Server s24 = new Server(23,2,160,4,5);
		Server s25 = new Server(24,1,160,5,5);

		layout.addServer(s1);
		layout.addServer(s2);
		layout.addServer(s3);
		layout.addServer(s4);
		layout.addServer(s5);

		layout.addServer(s6);
		layout.addServer(s7);
		layout.addServer(s8);
		layout.addServer(s9);
		layout.addServer(s10);

		layout.addServer(s11);
		layout.addServer(s12);
		layout.addServer(s13);
		layout.addServer(s14);
		layout.addServer(s15);

		layout.addServer(s16);
		layout.addServer(s17);
		layout.addServer(s18);
		layout.addServer(s19);
		layout.addServer(s20);

		layout.addServer(s21);
		layout.addServer(s22);
		layout.addServer(s23);
		layout.addServer(s24);
		layout.addServer(s25);

		return layout;
	}

	/**
	 * creates a Layout with 10 server classes for performance testing purposes
	 * @param layout an empty layout
	 * @return the requested layout
	 */
	public static Layout add10ServerClassestoLayout(Layout layout){
		Server s1 = new Server(0,25,100,1,1);
		Server s5 = new Server(1,21,100,5,1);

		Server s6 = new Server(2,20,100,1,2);
		Server s10 = new Server(3,16,100,5,2);

		Server s11= new Server(4,15,100,1,3);
		Server s15 = new Server(5,11,100,5,3);

		Server s16 = new Server(6,10,100,1,4);
		Server s20 = new Server(7,6,100,5,4);

		Server s21= new Server(8,5,100,1,5);
		Server s25 = new Server(9,1,100,5,5);

		layout.addServer(s1);		
		layout.addServer(s5);

		layout.addServer(s6);		
		layout.addServer(s10);

		layout.addServer(s11);

		layout.addServer(s15);

		layout.addServer(s16);

		layout.addServer(s20);

		layout.addServer(s21);

		layout.addServer(s25);

		return layout;
	}

	/**
	 * creates a Layout  for performance testing purposes
	 * @return the requested layout
	 */
	public static Layout get25erAllLAyout(){
		Layout layout = new Layout ();

		layout = addJobsToLayout(4000, layout);
		layout = add25ServerClassestoLayout(layout);

		return layout;
	}
	
	/**
	 * creates a Layout  for performance testing purposes
	 * @param layout an empty layout
	 * @return the requested layout
	 */
	public static Layout get25ServerGreedyRuntime(int jobs) {
		Layout layout = new Layout ();


		layout = add25ServerClassestoLayout(layout);
		for (int i=0; i<jobs; i++) {
			//Job job = new Job(i, 100, 1,1,1,5,5, 2000);
			Job job = new Job(i, 100, 1,1,1,5,5, i);
			layout.addJob(job);
		}

		return layout;
	}

	
	/**
	 * Adds a number of random jobs to a Layout	 
	 * @param numJobs number of jobs to add
	 * @param layout the layout the jobs shall be added to
	 * @return the requested layout
	 */
	public static Layout addJobsToLayout (int numJobs, Layout layout) {



		for (int i = 0; i < numJobs; i++) {
			int speedcat = (int) (Math.random()*5 + 1);
			int relcat = (int) (Math.random()*5 + 1);
			int jobIncome	= (int) (Math.random()*100 + 1);

			int jobDasd		= (int)(Math.random()*10 + 1);

			int starttime = (int)(Math.random()*10 + 1);
			int duration = (int)(Math.random()*10 + 1);

			layout.addJob(new Job(i,jobIncome,jobDasd,starttime,duration,relcat, speedcat,10000));

		}


		return layout;
	}

	/**
	 * creates a LayoutAxis from the current layout
	 * @return the requested layout
	 */
	public LayoutAxis getLayoutAxis(){
		LayoutAxis la= new LayoutAxis();


		JobAxis[] jobsAxis = new JobAxis[jobs.size()];
		int i = 0;
		for(Job j: jobs){
			jobsAxis[i] = j.getJobAxis();
			i++;
		}

		ServerAxis[] serverAxis = new ServerAxis[server.size()];
		i = 0;
		for(Server s : server){
			serverAxis[i] = s.getServerAxis();
			i++;
		}

		la.setId(id);
		la.setName(name);
		la.setJobs(jobsAxis);
		la.setServer(serverAxis);


		return la;

	}
	/**
	 * creates a LayoutGui from the current layout
	 * @return the requested layout
	 */
	public LayoutGui getLayoutGui(){
		LayoutGui lgui = new LayoutGui();

		JobGui[] jobsGui = new JobGui[jobs.size()];
		int i = 0;
		for(Job j: jobs){
			jobsGui[i] = j.getJobGui();
			i++;
		}

		ServerGui[] serverGui = new ServerGui[server.size()];
		i = 0;
		for(Server s : server){
			serverGui[i] = s.getServerGui();
			i++;
		}

		lgui.setId(id);
		lgui.setName(name);
		lgui.setJobs(jobsGui);
		lgui.setServer(serverGui);
		lgui.setNumJobs(numJobs);
		lgui.setNumServer(numServer);

		return lgui;
	}
	/**
	 * creates a Layout from a LayoutAxis
	 * @param layoutAxis
	 * @return the requested layout
	 */
	
	public static Layout getLayout(LayoutAxis layoutAxis){
		Layout l = new Layout();		


		for(JobAxis jobAxis: layoutAxis.getJobs()){
			l.addJob(Job.getJob(jobAxis));
		}


		for(ServerAxis serverAxis: layoutAxis.getServer()){
			l.addServer(Server.getServer(serverAxis));
		}




		l.setId(layoutAxis.getId());
		l.setName(layoutAxis.getName());

		return l;
	}

	/**
	 * creates a Layout from a layoutGui
	 * @param layoutAxis
	 * @return the requested layout
	 */
	public static Layout getLayout (LayoutGui layoutGui){
		Layout l = new Layout();


		for(JobGui jobGui: layoutGui.getJobs()){
			l.addJob((Job.getJob(jobGui)));
		}


		for(ServerGui serverGui: layoutGui.getServer()){
			l.addServer(Server.getServer(serverGui));
		}	

		l.setId(layoutGui.getId());
		l.setName(layoutGui.getName());		


		return l;
	}

	/** creates a layout with 9 servers for performance testing
	 * @param jobs the number of jobs wanted
	 * @return the requested layout
	 */
	public static Layout getLayoutWith9Server(int jobs) {
		Layout layout = new Layout();		

		Server s5 = new Server(0,21,100,5,1);

		Server s6 = new Server(1,20,100,1,2);
		Server s10 = new Server(2,16,100,5,2);

		Server s11= new Server(3,15,100,1,3);
		Server s15 = new Server(4,11,100,5,3);

		Server s16 = new Server(5,10,100,1,4);
		Server s20 = new Server(6,6,100,5,4);

		Server s21= new Server(7,5,100,1,5);
		Server s25 = new Server(8,1,100,5,5);

		layout.addServer(s5);
		layout.addServer(s6);		
		layout.addServer(s10);		
		layout.addServer(s11);		
		layout.addServer(s15);
		layout.addServer(s16);		
		layout.addServer(s20);
		layout.addServer(s21);		
		layout.addServer(s25);



		return layout;



	}
	/** creates a layout for performance testing
	 * @param jobs the number of jobs wanted
	 * @param jobs the number of servers wanted
	 * @return the requested layout
	 */
	public static Layout getRandomLayout(int server, int jobs) {

		Layout l = new Layout();

		for (int i=0; i<server; i++) {
			Server s = new Server();
			s.setId(i);
			s.setCost((int) ((7-i)*5));
			s.setDasd((int) (Math.random() * 30));
			s.setRelCat(1+i);
			s.setSpeedCat(1+i);
			l.addServer(s);
		}

		for (int j=0; j<jobs; j++) {
			Job job = new Job();
			job.setId(j);
			job.setDasd((int) (Math.random() * 10));
			job.setEarning((int) (Math.random() * 1000));
			job.setStartTime((int) (Math.random() * 10)+1);
			job.setDuration((int) (Math.random() * 10)+1);
			job.setRelCat((int) (Math.random() * 5)+1);
			job.setSpeedCat((int) (Math.random() * 5)+1);
			job.setPenalty((int) (Math.random() * 10000));
			l.addJob(job);
		}

		return l;
	}
	
	/** creates a layout for performance Pareto testing
	 * @return the requested layout
	 */
	public static Layout getParetoErrorLayout() {
		Layout l = new Layout();

		Server s = new Server();
		s.setId(0);
		s.setCost(35);
		s.setDasd(17);
		s.setRelCat(1);
		s.setSpeedCat(1);
		l.addServer(s);

		Server s2 = new Server();
		s2.setId(1);
		s2.setCost(30);
		s2.setDasd(12);
		s2.setRelCat(2);
		s2.setSpeedCat(2);
		l.addServer(s2);

		Job job = new Job();
		job.setId(0);
		job.setDasd(9);
		job.setEarning(320);
		job.setStartTime(2);
		job.setDuration(10);
		job.setRelCat(4);
		job.setSpeedCat(2);
		job.setPenalty(6842);
		l.addJob(job);

		Job job2 = new Job();
		job2.setId(1);
		job2.setDasd(1);
		job2.setEarning(806);
		job2.setStartTime(10);
		job2.setDuration(5);
		job2.setRelCat(5);
		job2.setSpeedCat(3);
		job2.setPenalty(5637);
		l.addJob(job2);

		Job job3 = new Job();
		job3.setId(2);
		job3.setDasd(2);
		job3.setEarning(345);
		job3.setStartTime(6);
		job3.setDuration(6);
		job3.setRelCat(3);
		job3.setSpeedCat(3);
		job3.setPenalty(93);
		l.addJob(job3);

		Job job4 = new Job();
		job4.setId(3);
		job4.setDasd(0);
		job4.setEarning(542);
		job4.setStartTime(1);
		job4.setDuration(7);
		job4.setRelCat(3);
		job4.setSpeedCat(2);
		job4.setPenalty(7134);
		l.addJob(job4);

		Job job5 = new Job();
		job5.setId(4);
		job5.setDasd(0);
		job5.setEarning(372);
		job5.setStartTime(7);
		job5.setDuration(3);
		job5.setRelCat(2);
		job5.setSpeedCat(4);
		job5.setPenalty(1092);
		l.addJob(job5);

		Job job6 = new Job();
		job6.setId(5);
		job6.setDasd(2);
		job6.setEarning(656);
		job6.setStartTime(4);
		job6.setDuration(8);
		job6.setRelCat(1);
		job6.setSpeedCat(4);
		job6.setPenalty(2643);
		l.addJob(job6);

		Job job7 = new Job();
		job7.setId(6);
		job7.setDasd(8);
		job7.setEarning(651);
		job7.setStartTime(6);
		job7.setDuration(10);
		job7.setRelCat(5);
		job7.setSpeedCat(2);
		job7.setPenalty(5897);
		l.addJob(job7);

		Job job8 = new Job();
		job8.setId(7);
		job8.setDasd(2);
		job8.setEarning(465);
		job8.setStartTime(1);
		job8.setDuration(3);
		job8.setRelCat(1);
		job8.setSpeedCat(3);
		job8.setPenalty(1826);
		l.addJob(job8);

		return l;

	}

	/** creates a layout for performance Pareto testing
	 * @return the requested layout
	 */
	public static Layout getParetoErrorLayout2() {
		Layout l = new Layout();

		Server s = new Server();
		s.setId(0);
		s.setCost(2);
		s.setDasd(1);
		s.setRelCat(5);
		s.setSpeedCat(5);
		l.addServer(s);

		Server s2 = new Server();
		s2.setId(1);
		s2.setCost(1);
		s2.setDasd(1);
		s2.setRelCat(1);
		s2.setSpeedCat(1);
		l.addServer(s2);

		Job job = new Job();
		job.setId(0);
		job.setDasd(1);
		job.setEarning(4);
		job.setStartTime(1);
		job.setDuration(1);
		job.setRelCat(5);
		job.setSpeedCat(5);
		job.setPenalty(1000);
		l.addJob(job);



		Job job7 = new Job();
		job7.setId(1);
		job7.setDasd(1);
		job7.setEarning(3);
		job7.setStartTime(1);
		job7.setDuration(1);
		job7.setRelCat(1);
		job7.setSpeedCat(1);
		job7.setPenalty(1000);
		l.addJob(job7);



		return l;

	}
	
	

	/**
	 * dumps a Layout to console
	 */
	public void printToConsole() {
		for (Server s:server) {
			s.printToConsole();
		}

		for(Job j:jobs) {
			j.printToConsole();
		}
	}

	/** creates a layout for performance testing
	 * @param jobs the number of jobs wanted
	 * @return the requested layout
	 */
	public static Layout getBruteforceRuntimeLayout1to10jobs4server(int jobs) {
		Layout l = new Layout();

		Server s1 = new Server(0,25,100,1,1);
		Server s2 = new Server(1,24,100,2,1);
		Server s3 = new Server(2,23,100,3,1);
		Server s4 = new Server(3,22,100,4,1);

		l.addServer(s1);
		l.addServer(s2);
		l.addServer(s3);
		l.addServer(s4);

		for (int i=0; i<jobs; i++) {
			Job job = new Job(i, 100, 1,1,1,5,5, 2000);
			l.addJob(job);
		}

		return l;		
	}

	/** creates a layout for performance testing
	 * @param jobs the number of jobs wanted
	 * @param jobs the number of servers wanted
	 * @return the requested layout
	 */
	public static Layout getGreedyBad() {
		Layout l = new Layout();

		Server s1 = new Server(0,1,12,1,1);



		l.addServer(s1);




		Job job1 = new Job(0,5,1,5,8,1,3,2363636);
		Job job2 = new Job(1,1,6,3,7,4,4,3272727);
		Job job3 = new Job(2,2,8,4,9,4,4,2909090);
		Job job4 = new Job(3,6,2,5,6,1,1,3090909);
		Job job5 = new Job(4,7,1,6,2,2,1,2000000);
		Job job6 = new Job(5,3,9,2,1,5,3,3818181);
		Job job7 = new Job(6,9,3,1,5,2,3,4000000);
		Job job8 = new Job(7,4,10,4,3,5,2,3636363);
		Job job9 = new Job(8,1,6,2,10,1,5,2181818);
		Job job10 = new Job(9,6,7,3,11,3,5,2545454);
		Job job11= new Job(10,8,4,1,4,3,1,2727272);
		Job job12 = new Job(11,10,5,6,12,3,2,3454545);

		l.addJob(job1);
		l.addJob(job2);
		l.addJob(job3);
		l.addJob(job4);
		l.addJob(job5);
		l.addJob(job6);
		l.addJob(job7);
		l.addJob(job8);
		l.addJob(job9);
		l.addJob(job10);
		l.addJob(job11);
		l.addJob(job12);

		return l;

	}

	/**
	 * dumps a Layout to a PrintStrem
	 * @param stream PrintStrem to dump to
	 */
	public void dump (PrintStream stream) {
		stream.println("## Layout id="+id);
		dumpServers(stream, true);
		dumpJobs(stream, true);

	}

	/**
	 * dumps a Layout to a PrintStrem using tab seperated values
	 * @param stream PrintStrem to dump to
	 */
	public void dumpCSV (PrintStream stream) {
		stream.println("## Layout id="+id);
		dumpServersCSV(stream);
		dumpJobsCSV(stream);

	}

	/**
	 * dumps the list of servers to a PrintStream using
	 * @param stream PrintStrem to dump to
	 */
	private void dumpServers (PrintStream stream, boolean withIDs) {

		for (Iterator iServer = server.iterator(); iServer.hasNext();) {
			Server element = (Server) iServer.next();
			stream.print("Server:\t");
			if (withIDs) {
				stream.print("id=" + element.getId() + "\t");
			}			
			stream.print("cost="+element.getCost()+"\t");
			stream.print("dasd="+element.getDasd()+"\t");
			stream.print("relCat="+element.getRelCat()+"\t");
			stream.print("speedCat="+element.getSpeedCat()+"\t");
			stream.println();
		}
	}
	

	/**
	 * dumps the list of jobs to a PrintStream
	 * @param stream PrintStrem to dump to
	 */
	private void dumpJobs (PrintStream stream, boolean withIDs) {

		for (Iterator iJob = jobs.iterator(); iJob.hasNext();) {
			Job job = (Job) iJob.next();
			stream.print("Job:\t");

			if (withIDs) {
				stream.print("id=" + job.getId() + "\t");
			}			
			stream.print("earning="+job.getEarning()+"\t");
			stream.print("dasd="+job.getDasd()+"\t");

			stream.print("startTime="+job.getStartTime()+"\t");			
			stream.print("duration="+job.getDuration()+"\t");

			stream.print("relCat="+job.getRelCat()+"\t");
			stream.print("speedCat="+job.getSpeedCat()+"\t");


			stream.print("penalty="+job.getPenalty()+"\t");		
			//System.out.print("penaltyType="+job.getPenaltyType()+"\t");

			stream.println();
		}
	}

	/**
	 * dumps the list of servers to a PrintStrem using tab seperated values
	 * @param stream PrintStrem to dump to
	 */
	private void dumpServersCSV (PrintStream stream) {
		stream.println("\t-\t-\t-\tServer:\t-\t-\t-");
		stream.println("id\tcost\tdasd\trelCat\tspeedCat");
		for (Iterator iServer = server.iterator(); iServer.hasNext();) {
			Server element = (Server) iServer.next();			
			stream.print(""+element.getId()+"\t");			
			stream.print(""+element.getCost()+"\t");
			stream.print(""+element.getDasd()+"\t");
			stream.print(""+element.getRelCat()+"\t");
			stream.print(""+element.getSpeedCat()+"\t");
			stream.println();
		}
	}

	/**
	 * dumps the list of jobs to a PrintStream using tab seperated values
	 * @param stream PrintStream to dump to
	 */
	
	private void dumpJobsCSV (PrintStream stream) {
		stream.println("\t-\t-\t-\tJobs\t-\t-\t-");
		stream.println("id\tearning\tdasd\tstartTime\tduration\trelCat\tspeedCat\tpenalty\tpenaltyType");
		for (Iterator iJob = jobs.iterator(); iJob.hasNext();) {
			Job job = (Job) iJob.next();

			stream.print(""+job.getId()+"\t");

			stream.print(""+job.getEarning()+"\t");
			stream.print(""+job.getDasd()+"\t");

			stream.print(""+job.getStartTime()+"\t");			
			stream.print(""+job.getDuration()+"\t");

			stream.print(""+job.getRelCat()+"\t");
			stream.print(""+job.getSpeedCat()+"\t");


			stream.print(""+job.getPenalty()+"\t");		
			//stream.print(""+job.getPenaltyType()+"\t");

			stream.println();
		}
	}





	/**
	 * dumps the list of jobs to a PrintStream without job or server ids
	 * @param stream PrintStream to dump to
	 */
	public void dumpWithoutIds(PrintStream stream) {


		dumpServers(stream, false);
		dumpJobs(stream, false);

	}

	
	/** 
	 * @return an array of the job start times 
	 */
	public int [] getStartTimes () {
		TreeSet <Integer> startTimesV = new TreeSet<Integer>();
		getJobs();
		Job aJob;

		for (Iterator iter = getJobs().iterator(); iter.hasNext();) {
			aJob = (Job) iter.next();
			startTimesV.add(aJob.getStartTime());
		}

		Integer startTimesI[] 	= startTimesV.toArray(new Integer[0]);
		int 	startTimes[]	= new int[startTimesI.length];

		for (int i = 0; i < startTimes.length; i++) {
			startTimes[i] = startTimesI[i].intValue();
		}
		return startTimes;
	}

	
	/** 
	 * @param simTime a simulation time for wich the check should be made
	 * @return a boolean array where runningAt[i]=true indicates that job i is running 
	 */
	public boolean [] getJobsRunningAtT (long simTime) {
		boolean [] running = new boolean[jobs.size()];
		long startT;
		long endT;

		for (int i = 0; i < running.length; i++) {
			startT 		= jobs.get(i).getStartTime();
			endT		= startT + jobs.get(i).getDuration();

			if((simTime>=startT)&&(simTime<endT)) {
				running [i] = true;
			}
			else {
				running [i] = false;
			}
		}


		return running;
	}


	/**
	 * @return returns an md5 checksum over a Layout.
	 */
	public String getUID () {
		long jearningChkSm	= 0;
		long jdasdChkSm		= 0;
		long jstartTimeChkSm	= 0;
		long jdurationChkSm	= 0;
		long jrelCatChkSm	= 0;
		long jspeedCatChkSm	= 0;
		long jpenaltyChkSm	= 0;
		long scostChkSm	= 0;
		long sdasdChkSm		= 0;
		long srelCatChkSm	= 0;
		long sspeedCatChkSm	= 0;
		//long sChkSmAll		= 0;
		//long jChkSmAll		= 0;

		String tmp="";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance( "SHA", "SUN" );
			
			for (Job jobC : jobs) {
				jearningChkSm	= jearningChkSm 		+ jobC.getEarning();
				jdasdChkSm		= jdasdChkSm 			+ jobC.getDasd();
				jstartTimeChkSm	= jstartTimeChkSm 		+ jobC.getStartTime();
				jdurationChkSm	= jdurationChkSm 		+ jobC.getDuration();
				jrelCatChkSm	= jrelCatChkSm 			+ jobC.getRelCat();
				jspeedCatChkSm	= jspeedCatChkSm 		+ jobC.getSpeedCat();
				jpenaltyChkSm	= jpenaltyChkSm 		+ jobC.getPenalty();
				
				tmp = ""+ jobC.getEarning()+"."+
						  jobC.getDasd()+"."+
						  jobC.getStartTime()+"."+
						  jobC.getDuration()+"."+
						  jobC.getRelCat()+"."+
						  jobC.getSpeedCat()+"."+
						  jobC.getPenalty();
				
				md.update(tmp.getBytes());
				
				

			}

			for (Server serverC : server) {
				scostChkSm		= scostChkSm 		+ serverC.getCost();
				sdasdChkSm		= sdasdChkSm 		+ serverC.getDasd();

				srelCatChkSm	= srelCatChkSm 		+ serverC.getRelCat();
				sspeedCatChkSm	= sspeedCatChkSm 	+ serverC.getSpeedCat();
				
				tmp = ""+ 	serverC.getCost()+"."+
							serverC.getDasd()+"."+
							serverC.getRelCat()+"."+
							serverC.getSpeedCat();
				
				md.update(tmp.getBytes());
				
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String UID = "";
		byte[] digest = md.digest(); 
		StringWriter buffer = new StringWriter();
		PrintWriter out = new PrintWriter( buffer );
		
		int i;
		for ( byte b : digest ) {
			i = b + 127;
		 out.printf( "%03d.", i );
		}
		 out.printf( "000");
		 
		
		
		return buffer.toString();
	}

}
