package simobjects;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * this class generates layouts according to the four defined
 * layout-testcases:
 * 
 * 1. uncorrelated SLAs
 * 2. weakly correlated SLAs
 * 3. strongly correlated SLAs
 * 4. SLAs with static Earning
 * 
 * important for comparative analysis
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class LayoutEvaluation {

	/**
	 * @param min
	 * @param max
	 * @param jobs
	 * @param startmin
	 * @param startmax
	 * @param durationmin
	 * @param durationmax
	 * @param relcatmin
	 * @param relcatmax
	 * @param speedcatmin
	 * @param speedcatmax
	 * @param penaltymin
	 * @param penaltymax
	 * @param server
	 * @return
	 * 
	 * Den Parametern der SLAs der Layout-Testklasse „unkorrelierte SLAs“ werden zufällige
	 * Werte aus den einzelnen, gleichverteilten, Intervallen zugewiesen. Soweit möglich, werden
     * Werte aus den gleichverteilten Intervallen nicht doppelt vergeben. 
     
     * Die einzelnen Intervalle der SLA-Parameter sind folgendermaßen definiert:
     * 		• Speicherplatzanforderungen dasdsla und Gewinne earningsla in [min, max]
     * 		• Startzeiten starttimesla in [startmin, startmax]
     * 		• Laufzeiten durationsla in [durationmin, durationmax]
     * 		• Verlässlichkeitsforderungen relcatsla in [relcatmin, relcatmax]
     * 		• Geschwindigkeitsforderungen speedcatsla in [speedcatmin, speedcatmax]
     * 		• Strafzahlungen penaltysla in [penaltymin, penaltymax]
	 */
	@SuppressWarnings("unchecked")
	public static Layout getUncorrelatedInstances(
			int min, int max, 					/*earning and dasd*/
			int jobs, 
			int startmin, int startmax, 
			int durationmin, int durationmax, 
			int relcatmin, int relcatmax, 
			int speedcatmin, int speedcatmax, 
			int penaltymin, int penaltymax, 
			int server) {

		Layout l = new Layout();
		int allJobsDasd = 0;

		//create vector of uniformly distributed earnings
		Vector<Integer> jobEarningVec = getUniformlyDistributedVector(min, max,
				jobs);
		Collections.shuffle(jobEarningVec);

		//create vector of uniformly distributed dasd
		Vector<Integer> jobDasdVec = (Vector<Integer>) jobEarningVec.clone();
		Collections.shuffle(jobDasdVec);

		//create vector of uniformly distributed starttimes
		Vector<Integer> jobStarttimeVec = getUniformlyDistributedVector(startmin, startmax,
				jobs);
		Collections.shuffle(jobStarttimeVec);

		//create vector of uniformly distributed durations
		Vector<Integer> jobDurationVec = getUniformlyDistributedVector(durationmin, durationmax,
				jobs);
		Collections.shuffle(jobDurationVec);

		//create vector of uniformly distributed relcats
		Vector<Integer> jobRelcatVec = getUniformlyDistributedVector(relcatmin, relcatmax,
				jobs);
		Collections.shuffle(jobRelcatVec);

		//create vector of uniformly distributed speedcats
		Vector<Integer> jobSpeedcatVec = getUniformlyDistributedVector(speedcatmin, speedcatmax,
				jobs);
		Collections.shuffle(jobSpeedcatVec);

		//create vector of uniformly distributed penalties
		Vector<Integer> jobPenaltyVec = getUniformlyDistributedVector(penaltymin, penaltymax,
				jobs);
		Collections.shuffle(jobPenaltyVec);



		//make jobs
		for (int i = 0; i < jobs; i++) {
			Job job = new Job();
			job.setId(i);
			job.setEarning(jobEarningVec.get(i));
			job.setDasd(jobDasdVec.get(i));
			job.setStartTime(jobStarttimeVec.get(i));
			job.setDuration(jobDurationVec.get(i));
			job.setRelCat(jobRelcatVec.get(i));
			job.setSpeedCat(jobSpeedcatVec.get(i));
			job.setPenalty(jobPenaltyVec.get(i));

			allJobsDasd += job.getDasd();
			l.addJob(job);
		}
		
		int minServerDasd  = (int)(0.4 * allJobsDasd / server);
		int maxServerDasd  = (int)(0.6 * allJobsDasd / server);

		//create vector of uniformly distributed dasd
		Vector<Integer> serverDasdVec = getUniformlyDistributedVector(minServerDasd, maxServerDasd,
				server-1); 
		
		//create vector of uniformly distributed classes
		Vector<Integer> serverClassVec = getUniformlyDistributedVector(1, 25,
				server);
		//Collections.shuffle(serverClassVec);
		
		
		//create vector of uniformly distributed cost
		Vector<Integer> serverCostVec = getUniformlyDistributedVector((int)Math.ceil(min/10), (int)Math.ceil(max/10),
				server);
		Collections.sort(serverCostVec, new IntComparator());
		//Collections.shuffle(serverCostVec);
		
		
		int allServerDasd = 0;
		//make server
		for (int i = 0; i < server-1; i++ ) {
			Server s = new Server();
			s.setId(i);
			s.setDasd(serverDasdVec.get(i));
			s.setCost(serverCostVec.get(i));
			s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(i)));
			s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(i)));
			
			allServerDasd += s.getDasd();
			l.addServer(s);
			
		}
		
		//make last server because of special dasd, so that the sum of servers dasd is half
		//of the total job dasd
		Server s = new Server();
		s.setId(server-1);
		s.setDasd( (allJobsDasd/2) - allServerDasd );
		s.setCost(serverCostVec.get(server-1));
		s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(server-1)));
		s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(server-1)));
		
		l.addServer(s);

		return l; 		
	}
	
	
	
	/**
	 * @param dasdmin
	 * @param dasdmax
	 * @param jobs
	 * @param earning
	 * @param startmin
	 * @param startmax
	 * @param durationmin
	 * @param durationmax
	 * @param relcatmin
	 * @param relcatmax
	 * @param speedcatmin
	 * @param speedcatmax
	 * @param penaltymin
	 * @param penaltymax
	 * @param server
	 * @return
	 * 
	 * Den Parametern der SLAs der Layout-Testklasse „schwach korrelierte SLAs“ werden zufällige
	 * Werte aus den einzelnen, gleichverteilten, Intervallen zugewiesen (soweit möglich,
	 * werden Werte aus den gleichverteilten Intervallen nicht doppelt vergeben). Ausnahme
	 * bildet der Parameter Gewinn earningsla: Dort wird zufällig ein Wert aus dem Intervall
	 * [(earning +- earning/10)] mit dem übergebenen Parameter earning gewählt und mit
	 * (11 - relcatsla - speedcatsla) multipliziert. Je niedriger die Werte der Verlässlichkeitsund
	 * Geschwindigkeitsforderungen des SLAs, desto mehr Gewinn wird ihm zugeordnet.
	 * 
	 * Die einzelnen Intervalle bzw. Werte der SLA-Parameter sind folgendermaßen definiert:
	 * 		• Speicherplatzanforderungen dasdsla in [dasdmin, dasdmax]
	 * 		• Gewinnen earningsla = (earning +- earning/10 ) * (11 - relcatsla - speedcatsla)
	 * 		• Startzeiten starttimesla in [startmin, startmax]
	 * 		• Laufzeiten durationsla in [durationmin, durationmax]
	 * 		• Verlässlichkeitsforderungen relcatsla in [relcatmin, relcatmax]
	 * 		• Geschwindigkeitsforderungen speedcatsla in [speedcatmin, speedcatmax]
	 * 		• Strafzahlungen penaltysla in [penaltymin, penaltymax]
	 */
	public static Layout getWeaklyCorrelatedInstances(
			int dasdmin, int dasdmax, 
			int jobs,
			int earning, 
			int startmin, int startmax, 
			int durationmin, int durationmax, 
			int relcatmin, int relcatmax, 
			int speedcatmin, int speedcatmax, 
			int penaltymin, int penaltymax, 
			int server) {

		Layout l = new Layout();
		int allJobsDasd = 0;

		//create vector of uniformly distributed dasd
		Vector<Integer> jobDasdVec = getUniformlyDistributedVector(dasdmin, dasdmax,
				jobs);
		Collections.shuffle(jobDasdVec);

		//create vector of uniformly distributed starttimes
		Vector<Integer> jobStarttimeVec = getUniformlyDistributedVector(startmin, startmax,
				jobs);
		Collections.shuffle(jobStarttimeVec);

		//create vector of uniformly distributed durations
		Vector<Integer> jobDurationVec = getUniformlyDistributedVector(durationmin, durationmax,
				jobs);
		Collections.shuffle(jobDurationVec);

		//create vector of uniformly distributed relcats
		Vector<Integer> jobRelcatVec = getUniformlyDistributedVector(relcatmin, relcatmax,
				jobs);
		Collections.shuffle(jobRelcatVec);

		//create vector of uniformly distributed speedcats
		Vector<Integer> jobSpeedcatVec = getUniformlyDistributedVector(speedcatmin, speedcatmax,
				jobs);
		Collections.shuffle(jobSpeedcatVec);

		//create vector of uniformly distributed penalties
		Vector<Integer> jobPenaltyVec = getUniformlyDistributedVector(penaltymin, penaltymax,
				jobs);
		Collections.shuffle(jobPenaltyVec);

		int earningMin = (int)Math.ceil(earning - earning/10);
		int earningMax = (int)Math.ceil(earning + earning/10);

		//make jobs
		for (int i = 0; i < jobs; i++) {
			Job job = new Job();
			job.setId(i);
			job.setEarning((new Random().nextInt(earningMax-earningMin+1)+earningMin ) * (11 - jobRelcatVec.get(i) - jobSpeedcatVec.get(i)));
			job.setDasd(jobDasdVec.get(i));
			job.setStartTime(jobStarttimeVec.get(i));
			job.setDuration(jobDurationVec.get(i));
			job.setRelCat(jobRelcatVec.get(i));
			job.setSpeedCat(jobSpeedcatVec.get(i));
			job.setPenalty(jobPenaltyVec.get(i));

			allJobsDasd += job.getDasd();
			l.addJob(job);
		}
		
		int minServerDasd  = (int)(0.4 * allJobsDasd / server);
		int maxServerDasd  = (int)(0.6 * allJobsDasd / server);

		//create vector of uniformly distributed dasd
		Vector<Integer> serverDasdVec = getUniformlyDistributedVector(minServerDasd, maxServerDasd,
				server-1); 
		
		//create vector of uniformly distributed classes
		Vector<Integer> serverClassVec = getUniformlyDistributedVector(1, 25,
				server);
		//Collections.shuffle(serverClassVec);
		
		
		//create vector of uniformly distributed cost
		Vector<Integer> serverCostVec = getUniformlyDistributedVector(earning/10, earning,
				server);
		Collections.sort(serverCostVec, new IntComparator());
		//Collections.shuffle(serverCostVec);
		
		
		int allServerDasd = 0;
		//make server
		for (int i = 0; i < server-1; i++ ) {
			Server s = new Server();
			s.setId(i);
			s.setDasd(serverDasdVec.get(i));
			s.setCost(serverCostVec.get(i));
			s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(i)));
			s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(i)));
			
			allServerDasd += s.getDasd();
			l.addServer(s);
			
		}
		
		//make last server because of special dasd, so that the sum of servers dasd is half
		//of the total job dasd
		Server s = new Server();
		s.setId(server-1);
		s.setDasd( (allJobsDasd/2) - allServerDasd );
		s.setCost(serverCostVec.get(server-1));
		s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(server-1)));
		s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(server-1)));
		
		l.addServer(s);

		return l; 		
	}
	
	
	/**
	 * @param dasdmin
	 * @param dasdmax
	 * @param jobs
	 * @param earning
	 * @param startmin
	 * @param startmax
	 * @param durationmin
	 * @param durationmax
	 * @param relcatmin
	 * @param relcatmax
	 * @param speedcatmin
	 * @param speedcatmax
	 * @param penalty
	 * @param server
	 * @return
	 * 
	 * Den Parametern der SLAs der Layout-Testklasse „stark korrelierte SLAs“ werden zufällige
	 * Werte aus den einzelnen, gleichverteilten, Intervallen zugewiesen (soweit möglich,
	 * werden Werte aus den gleichverteilten Intervallen nicht doppelt vergeben). Ausnahmen
	 * bilden die Parameter Gewinn earningsla und Strafzahlung penaltysla. Beim Gewinn wird
	 * der übergebene Parameter earning mit einem Zehntel des dem SLA zugeordneten dasdsla
	 * multipliziert und dieser Wert mit (11 - relcatsla - speedcatsla) multipliziert. Je höher
	 * die Speicheranforderung und je niedriger die Werte der Verlässlichkeits- und Geschwindigkeitsforderungen
	 * des SLAs, desto mehr Gewinn wird ihm zugeordnet. Bei der Strafzahlung
	 * berechnet sich der Wert aus dem übergebenen Parameter penalty, multipliziert
	 * mit dem (dem SLA zugeordneten) dasdsla und earningsla.
	 * 
	 * Die einzelnen Intervalle bzw. Werte der SLA-Parameter sind folgendermaßen definiert:
	 * 		• Speicherplatzanforderungen dasdsla in [dasdmin, dasdmax]
	 * 		• Gewinnen earningsla = (earning + dasdsla/10 ) * (11 - relcatsla - speedcatsla)
	 * 		• Startzeiten starttimesla 2 [startmin, startmax]
	 * 		• Laufzeiten durationsla in [durationmin, durationmax]
	 * 		• Verlässlichkeitsforderungen relcatsla in [relcatmin, relcatmax]
	 * 		• Geschwindigkeitsforderungen speedcatsla in [speedcatmin, speedcatmax]
	 * 		• Strafzahlungen penaltysla = earningsla * dasdsla * penalty
	 */
	public static Layout getStronglyCorrelatedInstances(
			int dasdmin, int dasdmax, 
			int jobs,
			int earning, 
			int startmin, int startmax, 
			int durationmin, int durationmax, 
			int relcatmin, int relcatmax, 
			int speedcatmin, int speedcatmax, 
			int penalty, 
			int server) {

		Layout l = new Layout();
		int allJobsDasd = 0;

		//create vector of uniformly distributed dasd
		Vector<Integer> jobDasdVec = getUniformlyDistributedVector(dasdmin, dasdmax,
				jobs);
		Collections.shuffle(jobDasdVec);

		//create vector of uniformly distributed starttimes
		Vector<Integer> jobStarttimeVec = getUniformlyDistributedVector(startmin, startmax,
				jobs);
		Collections.shuffle(jobStarttimeVec);

		//create vector of uniformly distributed durations
		Vector<Integer> jobDurationVec = getUniformlyDistributedVector(durationmin, durationmax,
				jobs);
		Collections.shuffle(jobDurationVec);

		//create vector of uniformly distributed relcats
		Vector<Integer> jobRelcatVec = getUniformlyDistributedVector(relcatmin, relcatmax,
				jobs);
		Collections.shuffle(jobRelcatVec);

		//create vector of uniformly distributed speedcats
		Vector<Integer> jobSpeedcatVec = getUniformlyDistributedVector(speedcatmin, speedcatmax,
				jobs);
		Collections.shuffle(jobSpeedcatVec);

		

		

		//make jobs
		for (int i = 0; i < jobs; i++) {
			Job job = new Job();
			job.setId(i);
			job.setEarning((earning + jobDasdVec.get(i)/10) * (11 - jobRelcatVec.get(i) - jobSpeedcatVec.get(i)));
			job.setDasd(jobDasdVec.get(i));
			job.setStartTime(jobStarttimeVec.get(i));
			job.setDuration(jobDurationVec.get(i));
			job.setRelCat(jobRelcatVec.get(i));
			job.setSpeedCat(jobSpeedcatVec.get(i));
			job.setPenalty(penalty * job.getEarning() * job.getDasd());

			allJobsDasd += job.getDasd();
			l.addJob(job);
		}
		
		int minServerDasd  = (int)(0.4 * allJobsDasd / server);
		int maxServerDasd  = (int)(0.6 * allJobsDasd / server);

		//create vector of uniformly distributed dasd
		Vector<Integer> serverDasdVec = getUniformlyDistributedVector(minServerDasd, maxServerDasd,
				server-1); 
		
		//create vector of uniformly distributed classes
		Vector<Integer> serverClassVec = getUniformlyDistributedVector(1, 25,
				server);
		//Collections.shuffle(serverClassVec);
		
		
		//create vector of uniformly distributed cost
		Vector<Integer> serverCostVec = getUniformlyDistributedVector(earning/10, earning,
				server);
		Collections.sort(serverCostVec, new IntComparator());
		//Collections.shuffle(serverCostVec);
		
		
		int allServerDasd = 0;
		//make server
		for (int i = 0; i < server-1; i++ ) {
			Server s = new Server();
			s.setId(i);
			s.setDasd(serverDasdVec.get(i));
			s.setCost(serverCostVec.get(i));
			s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(i)));
			s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(i)));
			
			allServerDasd += s.getDasd();
			l.addServer(s);
			
		}
		
		//make last server because of special dasd, so that the sum of servers dasd is half
		//of the total job dasd
		Server s = new Server();
		s.setId(server-1);
		s.setDasd( (allJobsDasd/2) - allServerDasd );
		s.setCost(serverCostVec.get(server-1));
		s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(server-1)));
		s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(server-1)));
		
		l.addServer(s);

		return l; 		
	}
	
	
	/**
	 * @param dasdmin
	 * @param dasdmax
	 * @param jobs
	 * @param earning
	 * @param startmin
	 * @param startmax
	 * @param durationmin
	 * @param durationmax
	 * @param relcatmin
	 * @param relcatmax
	 * @param speedcatmin
	 * @param speedcatmax
	 * @param penalty
	 * @param server
	 * @return
	 * 
	 * Den Parametern der SLAs der Layout-Testklasse „stark korrelierte SLAs“ werden zufällige
	 * Werte aus den einzelnen, gleichverteilten, Intervallen zugewiesen (soweit möglich, werden
	 * Werte aus den gleichverteilten Intervallen nicht doppelt vergeben). Ausnahmen bilden die
	 * Parameter Gewinn earningsla und Strafzahlung penaltysla. Der Gewinn entspricht bei
	 * jedem SLA dem übergebenen Parameter earning, die Strafzahlung immer dem Wert
	 * penalty.
	 * 
	 * Die einzelnen Intervalle bzw. Werte der SLA-Parameter sind folgendermaßen definiert:
	 * 		• Speicherplatzanforderungen dasdsla in [dasdmin, dasdmax]
	 * 		• Gewinnen earningsla = earning
	 * 		• Startzeiten starttimesla in [startmin, startmax]
	 * 		• Laufzeiten durationsla in [durationmin, durationmax]
	 * 		• Verlässlichkeitsforderungen relcatsla in [relcatmin, relcatmax]
	 * 		• Geschwindigkeitsforderungen speedcatsla in [speedcatmin, speedcatmax]
	 * 		• Strafzahlungen penaltysla = penalty
	 */
	public static Layout getSlaWithStaticEarningInstances(
			int dasdmin, int dasdmax, 
			int jobs,
			int earning, 
			int startmin, int startmax, 
			int durationmin, int durationmax, 
			int relcatmin, int relcatmax, 
			int speedcatmin, int speedcatmax, 
			int penalty, 
			int server) {

		Layout l = new Layout();
		int allJobsDasd = 0;

		//create vector of uniformly distributed dasd
		Vector<Integer> jobDasdVec = getUniformlyDistributedVector(dasdmin, dasdmax,
				jobs);
		Collections.shuffle(jobDasdVec);

		//create vector of uniformly distributed starttimes
		Vector<Integer> jobStarttimeVec = getUniformlyDistributedVector(startmin, startmax,
				jobs);
		Collections.shuffle(jobStarttimeVec);

		//create vector of uniformly distributed durations
		Vector<Integer> jobDurationVec = getUniformlyDistributedVector(durationmin, durationmax,
				jobs);
		Collections.shuffle(jobDurationVec);

		//create vector of uniformly distributed relcats
		Vector<Integer> jobRelcatVec = getUniformlyDistributedVector(relcatmin, relcatmax,
				jobs);
		Collections.shuffle(jobRelcatVec);

		//create vector of uniformly distributed speedcats
		Vector<Integer> jobSpeedcatVec = getUniformlyDistributedVector(speedcatmin, speedcatmax,
				jobs);
		Collections.shuffle(jobSpeedcatVec);

		

		

		//make jobs
		for (int i = 0; i < jobs; i++) {
			Job job = new Job();
			job.setId(i);
			job.setEarning(earning);
			job.setDasd(jobDasdVec.get(i));
			job.setStartTime(jobStarttimeVec.get(i));
			job.setDuration(jobDurationVec.get(i));
			job.setRelCat(jobRelcatVec.get(i));
			job.setSpeedCat(jobSpeedcatVec.get(i));
			job.setPenalty(penalty);

			allJobsDasd += job.getDasd();
			l.addJob(job);
		}
		
		int minServerDasd  = (int)(0.4 * allJobsDasd / server);
		int maxServerDasd  = (int)(0.6 * allJobsDasd / server);

		//create vector of uniformly distributed dasd
		Vector<Integer> serverDasdVec = getUniformlyDistributedVector(minServerDasd, maxServerDasd,
				server-1); 
		
		//create vector of uniformly distributed classes
		Vector<Integer> serverClassVec = getUniformlyDistributedVector(1, 25,
				server);
		//Collections.shuffle(serverClassVec);
		
		
		//create vector of uniformly distributed cost
		Vector<Integer> serverCostVec = getUniformlyDistributedVector(earning/10, earning,
				server);
		Collections.sort(serverCostVec, new IntComparator());
		//Collections.shuffle(serverCostVec);
		
		
		int allServerDasd = 0;
		//make server
		for (int i = 0; i < server-1; i++ ) {
			Server s = new Server();
			s.setId(i);
			s.setDasd(serverDasdVec.get(i));
			s.setCost(serverCostVec.get(i));
			s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(i)));
			s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(i)));
			
			allServerDasd += s.getDasd();
			l.addServer(s);
			
		}
		
		//make last server because of special dasd, so that the sum of servers dasd is half
		//of the total job dasd
		Server s = new Server();
		s.setId(server-1);
		s.setDasd( (allJobsDasd/2) - allServerDasd );
		s.setCost(serverCostVec.get(server-1));
		s.setRelCat(RelSpeedMapping.getInstance().getRelClass(serverClassVec.get(server-1)));
		s.setSpeedCat(RelSpeedMapping.getInstance().getSpeedClass(serverClassVec.get(server-1)));
		
		l.addServer(s);

		return l; 		
	}
	

	/**
	 * @param min
	 * @param max
	 * @param jobs
	 * @return
	 */
	private static Vector<Integer> getUniformlyDistributedVector(int min,
			int max, int jobs) {
		Vector<Integer> intVec = new Vector<Integer>();
		if (jobs > (max-min)) {
			for (int i = 0; i < jobs; i++) {	
				intVec.add(min + (int)Math.floor((((double)(max-min+1)/(jobs)))*i));				
			}
		}
		else {
			for (int i = 0; i < jobs; i++) {	
				intVec.add(min + (int)Math.floor((((double)(max-min)/(jobs-1)))*i));				
			}
			
		}		
		return intVec;
	}

}
