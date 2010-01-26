package farm.master.layoutreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import simobjects.Job;
import simobjects.Layout;
import simobjects.Server;

/**
 * @author Christoph Beck
 * Simple Class to read and dunp ascii layouts
 * main objective:
 * try to read how
 */
public class AsciiReader {
	private String filename;
	private LineNumberReader is;
	private Layout layout;
	private Vector<Server> servers;
	private Vector<Job> jobs;

	private int serverId = 0;
	private int jobId = 0;

	/**
	 * @param filename
	 */
	public AsciiReader(String filename) {
		super();

		this.filename = filename;
		layout = new  Layout();
		servers 	= layout.getServerVector();
		jobs 		= layout.getJobVector();

		try {
			this.is  = new LineNumberReader(new BufferedReader (new FileReader(filename)));
		} catch (FileNotFoundException e) {
			System.out.println("sorry, could not find the file '"+filename+"'");
		}
		
		
	}
	
	
	/**
	 * to set a layout to be dumped
	 * @param layout layout to be dumped
	 */
	public AsciiReader (Layout layout){
		this.layout = layout;
		servers 	= layout.getServerVector();
		jobs 		= layout.getJobVector();
	}

	/**
	 * well, it reads the file
	 * @return Layout read
	 */
	public Layout readFile () {
		String s = null;
		int lineNo = 0;
		String token = null;

		try{

			while ((s = is.readLine() )!=null) {
				lineNo++;
				//System.out.print("line # "+lineNo+"\t"+s);

				if (s.matches(".*:.*")) {
					StringTokenizer st = new StringTokenizer (s,":", false);
					while (st.hasMoreTokens()) {

						if(matches((token=st.nextToken()),"Server")) {
							//System.out.println("// Server found token='"+token+"'");
							addServer(st.nextToken());
						}
						if(matches(token,"Job")) {
							//System.out.println("// Job token='"+token+"'");
							addJob(st.nextToken());
						}

					}
				}
			}

		}
		catch (NoSuchElementException e) {
			System.out.println();
			System.out.println("Diese Datei scheint nicht gültig zu sein!!");
			e.printStackTrace();
			System.exit(0);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return layout;
	}

	/**
	 * used if a Job-String was found, adds a job
	 * @param jobString the Job-String
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
		int intValue = 0;

		// trigger on these tokens
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
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
	 * used if aserverString was found, adds a server
	 * @param jobString the Job-String
	 */
	private void addServer(String serverString) {
		serverId ++;

		Server server = new Server ();
		server.setId(serverId);


		//System.out.println("Server sub started with string '"+serverString+"'");
		StringTokenizer st = new StringTokenizer (serverString," \t;", false);
		String name;
		String value;
		int intValue = 0;
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
				intValue = 0;

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
	 * case insestive matcher
	 * @param str1
	 * @param str2
	 * @return
	 */
	private boolean matches (String str1, String str2) {
		try {
			Pattern pat = Pattern.compile(str1, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			return pat.matcher(str2).matches();
		} catch (Exception e) {
			return false;
		}
				
	}
	
	
	/**
	 * @param stream
	 */
	public void dump (PrintStream stream) {
		System.out.println("## "+filename);
		layout.dump(stream);
	}
	
	/**
	 * @param stream
	 */
	public void dumpCSV (PrintStream stream) {
		System.out.println("## "+filename);
		layout.dumpCSV(stream);
	}

	/**
	 * @return
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @param layout
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}

