package simobjects.transport.gui;

import java.io.Serializable;





public class LayoutGui implements Serializable{
	
	
	private static final long serialVersionUID = 8363449018392898036L;
	private JobGui[]  jobs;
	private ServerGui[] server;	
	private int id;
	private String name;
	private int numJobs  = 0;
	private int numServer  = 0;
	
	
	public LayoutGui() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JobGui[] getJobs() {
		return jobs;
	}


	public void setJobs(JobGui[] jobs) {
		this.jobs = jobs;
	}


	public ServerGui[] getServer() {
		return server;
	}


	public void setServer(ServerGui[] server) {
		this.server = server;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumJobs() {
		return numJobs;
	}


	public void setNumJobs(int numJobs) {
		this.numJobs = numJobs;
	}


	public int getNumServer() {
		return numServer;
	}


	public void setNumServer(int numServer) {
		this.numServer = numServer;
	}
	
	
	
	

}
