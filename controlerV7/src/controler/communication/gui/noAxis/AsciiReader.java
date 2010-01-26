package controler.communication.gui.noAxis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import simobjects.Job;
import simobjects.Layout;
import simobjects.Server;
import simobjects.transport.gui.SimJobGui;
import simobjects.transport.gui.TestrunGui;


/**
 * Reads an Ascii Format file and converts it the appropriate objects
 * @author Christoph Beck
 * 
 * assumptions:
 * this should be able to read whatever it gets 
 * getLayout 	gives a layout, or null 
 * getTestRun 	gives a TestRun	(with a layout or a layoutID) or null 
 * getSimJob 	gives a SimJob	(with a TestRun or a TestRunID) or null
 * 
 * it is possible to read a Simjob and get only the Layout
 * it is possible to read a Layout and get a Simjob with sensible Defaults
 * 
 */
public class AsciiReader {
	private String filename;
	private LineNumberReader is;

	private Vector<Server> servers;
	private Vector<Job> jobs;

	private int 		serverId = -1;
	private int 		jobId = -1;

	private Layout 		l;
	private TestrunGui 	trg;
	private SimJobGui	sjg;
	private Long 		fileEarning = 0L;
	private int			algrithmID  = 0;

	private boolean 	simStatsSet = false;


	/**
	 * @param filename the file to read
	 */
	public AsciiReader(String filename) {
		super();

		this.filename = filename;

		// empty layout
		l 	= new  Layout();
		l.setName("tr-"+filename+"-"+System.currentTimeMillis());
		servers 	= l.getServerVector();
		jobs 		= l.getJobVector();

		// bf testrun by default
		trg		= new TestrunGui() ;
		trg.setAlgorithmId(1);
		trg.setName("tr-"+filename+"-"+System.currentTimeMillis());
		trg.setStatsEnd(1000);
		trg.setStatsStart(0);
		trg.setStatsNumFields(100);
		trg.setAlgorithmId(0);
		// trg.setLayoutId(0);

		// farm 1 by default
		sjg		= new SimJobGui();
		sjg.setSimFarmId(1);
		// sjg.setTestrunId(testrunId)



		try {
			this.is  = new LineNumberReader(new BufferedReader (new FileReader(filename)));
		} catch (FileNotFoundException e) {
			System.out.println("sorry, could not find the file '"+filename+"'");
		}
	}


	/**
	 * @param layout the layout to be set
	 */
	public AsciiReader (Layout layout){
		this.l = layout;
		servers 	= layout.getServerVector();
		jobs 		= layout.getJobVector();
	}

	/**
	 * reads the file
	 * @throws IOException
	 * @throws NoSuchElementException
	 * 
	 */
	public void readFile () throws IOException, NoSuchElementException {
		String s = null;
		int lineNo = 0;
		String token = null;


		while ((s = is.readLine() )!=null) {
			lineNo++;
			//System.out.print("line # "+lineNo+"\t"+s);

			if (s.matches(".*:.*")) {
				StringTokenizer st = new StringTokenizer (s,":", false);
				int serial = 0;
				while (st.hasMoreTokens()) {
					serial ++;
					token=st.nextToken();

					if(matches(token,"Server")) {
						//System.out.println("// Server found (loop="+serial+"). Token='"+token+"'");
						addServer(st.nextToken());
						continue;
					}
					if(matches(token,"Job")) {
						//System.out.println("// Job found (loop="+serial+"). Token='"+token+"'");
						addJob(st.nextToken());
					}
					if(matches(token,"TestRun")) {
						//System.out.println("// TestRun found. Token='"+token+"'");
						addTestrun(st.nextToken());
					}
					if(matches(token,"SimJob")) {
						//System.out.println("// SimJob found. Token='"+token+"'");
						addSimJob(st.nextToken());
					}
					if(matches(token,"Layout")) {
						//System.out.println("// Layout found. Token='"+token+"'");
						confLayout(st.nextToken());
					}
				}
			}
			if (s.matches("#SimResult Earning[0-9]*")) {
				getEarning(s);
			}
		}
	}


	/**
	 * @param jobString string token to be parsed to a job (will be addes immedeately)
	 */
	private void addJob(String jobString) {
		jobId++;

		//System.out.println("Job sub started with string '"+jobString+"'");
		Job job = new Job();
		job.setId(jobId);

		String token = null;
		StringTokenizer st = new StringTokenizer (jobString," \t;", false);
		String name;
		String value;
		int 	intValue = 0;
		long	longValue = 0;
		String names[] = {
				"id",
				"earning",
				"dasd",
				"startTime",
				"duration",
				"relCat",
				"speedCat",
				"penalty"/*,
				"penaltyType"*/
		};

		while (st.hasMoreTokens()) {
			token = st.nextToken();

			StringTokenizer st2  = new StringTokenizer (token,"=",false) ;
			while (st2.hasMoreTokens()) {
				name = st2.nextToken();
				value = st2.nextToken();
				intValue = 0;

				for (int i = 0; i < names.length; i++) {
					if (matches(names[i],name)) {
						name=names[i];
						try {
							intValue = Integer.valueOf(value);
							longValue = Long.valueOf(value);
						} catch (NumberFormatException e) {

						}

						switch (i) {
						case 0:	
							job.setId(intValue);
							break;
						case 1:
							job.setEarning(intValue);
							break;
						case 2:
							job.setDasd(intValue);
							break;
						case 3:
							job.setStartTime(intValue);
							break;
						case 4:
							job.setDuration(intValue);
							break;
						case 5:
							job.setRelCat(intValue);
							break;
						case 6:
							job.setSpeedCat(intValue);
							break;
						case 7:
							job.setPenalty(intValue);
							break;
							//case 8:
							//	job.setPenaltyType(intValue);
							//	break;

						default:
							break;
						}
					}
				}

			}

		}
		jobs.add(job);
		//System.out.println();
	}

	/**
	 * @param serverString string token to be parsed to a server (will be addes immedeately)
	 */
	private void addServer(String serverString) {
		serverId ++;

		Server server = new Server ();
		server.setId(serverId);


		//System.out.println("Server sub started with string '"+serverString+"'");
		StringTokenizer st = new StringTokenizer (serverString," \t;", false);
		String name;
		String value;
		int 	intValue 	= 0;
		long 	longValue 	= 0;
		String names[] = {
				"id",				
				"cost",
				"dasd",
				"relCat",
				"speedCat"
		};


		String token = null;

		while (st.hasMoreTokens()) {
			token = st.nextToken();


			StringTokenizer st2  = new StringTokenizer (token,"=",false) ;
			while (st2.hasMoreTokens()) {
				name = st2.nextToken();

				value = st2.nextToken();
				try {
					intValue = Integer.valueOf(value);
					longValue = Long.valueOf(value);
				} catch (NumberFormatException e) {

				}

				for (int i = 0; i < names.length; i++) {
					if (matches(names[i],name)) {
						name=names[i];
						intValue = Integer.valueOf(value);
						//System.out.print(""+name+"="+intValue+"(save)\t");

						switch (i) {
						case 0:	
							server.setId(intValue);
							break;
						case 1:
							server.setCost(intValue);
							break;
						case 2:
							server.setDasd(intValue);
							break;
						case 3:
							server.setRelCat(intValue);
							break;
						case 4:
							server.setSpeedCat(intValue);
							break;

						default:
							break;
						}
					}
				}

			}

		}
		servers.add(server);
		//System.out.println();
	}

	/**
	 * @param testRunString string token to be parsed to a server (will be addes immedeately)
	 */
	private void addTestrun(String testRunString) {
		String token = null;
		StringTokenizer st = new StringTokenizer (testRunString," \t;", false);
		String name;
		String value;
		int intValue = 0;
		long longValue = 0;

		String names[] = {
				"algorithmid",
				"statsstart",
				"statsend",
				"statsnumfields",	
				"layoutid",
				"name"
		};

		trg = new TestrunGui();
		trg.setName(this.filename);
		while (st.hasMoreTokens()) {
			token = st.nextToken();

			StringTokenizer st2  = new StringTokenizer (token,"=",false) ;
			while (st2.hasMoreTokens()) {
				name = st2.nextToken();
				value = st2.nextToken();
				intValue = 0;

				for (int i = 0; i < names.length; i++) {
					if (matches(names[i],name)) {
						name=names[i];
						try {
							intValue = Integer.valueOf(value);
							longValue = Long.valueOf(value);
						} catch (NumberFormatException e) {

						}


						switch (i) {
						case 0:	
							trg.setAlgorithmId(intValue);
							algrithmID=intValue;
							//System.out.println("trg.setAlgorithmId("+intValue+")");
							break;
						case 1:
							trg.setStatsStart(longValue);
							simStatsSet = true;
							//System.out.println("trg.setStatsStart("+longValue+")");
							break;
						case 2:
							trg.setStatsEnd(longValue);
							simStatsSet = true;
							//System.out.println("trg.setStatsEnd("+longValue+")");
							break;
						case 3:
							trg.setStatsNumFields(intValue);
							simStatsSet = true;
							//System.out.println("trg.setStatsNumFields("+intValue+")");
							break;
						case 4:
							trg.setLayoutId(intValue);
							//System.out.println("trg.setLayoutId("+intValue+")");
							break;
						case 5:
							trg.setName(value);
							//System.out.println("trg.setLayoutId("+value+")");
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	/**
	 * @param simJobString string token to be parsed to a SimJobGui (will be addes immedeately)
	 */
	private void addSimJob(String simJobString) {
		String token = null;
		StringTokenizer st = new StringTokenizer (simJobString," \t;", false);
		String name;
		String value;
		int 	intValue = 0;
		long 	longValue = 0;

		String names[] = {
				"testrunid",
				"farmid"
		};

		sjg = new SimJobGui();

		TestrunGui trg = new TestrunGui();
		while (st.hasMoreTokens()) {
			token = st.nextToken();

			StringTokenizer st2  = new StringTokenizer (token,"=",false) ;
			while (st2.hasMoreTokens()) {
				name = st2.nextToken();
				value = st2.nextToken();
				intValue = 0;

				for (int i = 0; i < names.length; i++) {
					if (matches(names[i],name)) {
						name=names[i];
						try {
							intValue = Integer.valueOf(value);
							longValue = Long.valueOf(value);
						} catch (NumberFormatException e) {

						}

						switch (i) {
						case 0:	
							trg.setLayoutId(intValue);
							//System.out.println("trg.setLayoutId("+intValue+")");
							break;
						case 1:	
							sjg.setSimFarmId(intValue);
							//System.out.println("sjg.setSimFarmId("+intValue+")");
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * @param jobString string token to be parsed to change a Laout SimJobGui (will be addes immedeately)
	 */
	private void confLayout(String jobString) {
		String token = null;
		StringTokenizer st = new StringTokenizer (jobString," \t;", false);
		String name;
		String value;

		int 	intValue = 0;
		long 	longValue = 0;

		String names[] = {
				"name"
		};

		TestrunGui trg = new TestrunGui();
		while (st.hasMoreTokens()) {
			token = st.nextToken();

			StringTokenizer st2  = new StringTokenizer (token,"=",false) ;
			while (st2.hasMoreTokens()) {
				name = st2.nextToken();
				value = st2.nextToken();

				try {
					intValue = Integer.valueOf(value);
					longValue = Long.valueOf(value);
				} catch (NumberFormatException e) {

				}

				for (int i = 0; i < names.length; i++) {
					if (matches(names[i],name)) {
						name=names[i];
						try {
							intValue = Integer.valueOf(value);
							longValue = Long.valueOf(value);
						} catch (NumberFormatException e) {

						}

						switch (i) {
						case 0:	
							l.setName(name);
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	
	
	/** helper function to compare case insensitive
	 * @param str1
	 * @param str2
	 * @return true if str1 matches str2
	 */
	private boolean matches (String str1, String str2) {
		try {
			Pattern pat = Pattern.compile(str1, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			return pat.matcher(str2).matches();
		} catch (Exception e) {
			return false;
		}
	}

	
	/** dumps the read file to an PrintStream
	 * @param stream PrintStream to  dump to
	 */
	public void dump (PrintStream stream) {
		//System.out.println("## "+filename);
		//sjg.dump(stream);
		//trg.dump(stream);
		l.dump(stream);
	}

	/** dumps the read testRun to an PrintStream
	 * @param stream PrintStream to  dump to
	 */
	public void dumptr (PrintStream stream) {
		//System.out.println("## "+filename);
		//sjg.dump(stream);
		trg.dump(stream);
		l.dump(stream);
	}

	/** dumps the read SimJob to an PrintStream
	 * @param stream PrintStream to  dump to
	 */
	public void dumpsj (PrintStream stream) {
		System.out.println("## "+filename);
		sjg.dump(stream);
		trg.dump(stream);
		l.dump(stream);
	}

	/** dumps the read layout to an PrintStream
	 * @param stream PrintStream to  dump to
	 */
	
	public void dumpCSV (PrintStream stream) {
		System.out.println("## "+filename);
		l.dumpCSV(stream);
	}

	/**
	 * @param layout the layout to be set
	 */
	public void setLayout(Layout layout) {
		this.l = layout;
	}

	
	/**
	 *  closes the file
	 */
	public void closeFile () {
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * @return the read layout
	 */
	public Layout getLayout () {
		if(!simStatsSet) {
			trg.setStatsStart(0);
			trg.setStatsEnd(l.getIdealEarning());			
			trg.setStatsNumFields(100);
		}
		return l;
	}

	/**
	 * @return the TestRun read from file 
	 */
	public TestrunGui getTestRunGui () {
		return trg;
	}

	/**
	 * @return the SimJobGui read from file 
	 */
	public SimJobGui getSimJobGui () {
		return sjg;
	}

	/**
	 * @param s string token to be parsed to the earning
	 */
	private void getEarning(String s) {
		Pattern p = Pattern.compile("#SimResult Earning");
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
		try {
			fileEarning=Long.valueOf(s);
		} catch (NumberFormatException e) {
			fileEarning=0L;
		}
	}


	/**
	 * @return the eraning read from file 
	 */
	public Long getFileEarning() {
		return fileEarning;
	}


	/**
	 * @return the AlgrithmID read from file 
	 */
	public int getAlgrithmID() {
		return algrithmID;
	}


}

