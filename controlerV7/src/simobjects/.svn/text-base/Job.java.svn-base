package simobjects;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import simobjects.transport.farm.JobAxis;
import simobjects.transport.gui.JobGui;


/**
 * @author Christoph Beck
 *
 */
@Embeddable
public class Job implements Serializable{


	private static final long serialVersionUID = 1L;

	private int id;
	private int earning;
	private int dasd;
	private int startTime;
	private int duration;
	private int relCat;
	private int speedCat;
	private int penalty;





	/**
	 * default constructor
	 */
	public Job() {
		super();
	}





	/** qualified constructor
	 * @param id
	 * @param earning
	 * @param dasd
	 * @param startTime
	 * @param duration
	 * @param relCat
	 * @param speedCat
	 * @param penalty
	 */
	public Job(int id, int earning, int dasd, int startTime, int duration, int relCat, int speedCat, int penalty) {
		super();
		this.id = id;
		this.earning = earning;
		this.dasd = dasd;
		this.startTime = startTime;
		this.duration = duration;
		this.relCat = relCat;
		this.speedCat = speedCat;
		this.penalty = penalty;

	}





	/**
	 * @return the job's
	 */
	public int getDasd() {
		return dasd;
	}
	/**
	 * @return the job's
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @return the job's
	 */
	public int getEarning() {
		return earning;
	}

	/**
	 * @return the job's
	 */
	public int getRelCat() {
		return relCat;
	}
	/**
	 * @return the job's
	 */
	public int getSpeedCat() {
		return speedCat;
	}
	/**
	 * @return the job's
	 */
	public int getStartTime() {
		return startTime;
	}
	/**
	 * @return the job's
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the job's
	 */
	public int getPenalty() {
		return penalty;
	}


	/**
	 * @param the job's dasd 
	 */
	public void setDasd(int dasd) {
		this.dasd = dasd;
	}
	/**
	 * @param duration the job's 
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @param earning the job's 
	 */
	public void setEarning(int earning) {
		this.earning = earning;
	}
	/**
	 * @param jobNumber the job's 
	 */
	public void setId(int jobNumber) {
		this.id = jobNumber;
	}
	/**
	 * @param penalty the job's 
	 */
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	/**
	 * @param relCat the job's 
	 */
	public void setRelCat(int relCat) {
		this.relCat = relCat;
	}
	/**
	 * @param speedCat the job's 
	 */
	public void setSpeedCat(int speedCat) {
		this.speedCat = speedCat;
	}
	/**
	 * @param startTime the job's 
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * Dumps a job to console
	 */
	public void printToConsole(){
		System.out.println("Job #" + id);
		System.out.println("dasd: " + dasd);
		System.out.println("earning "+ earning);
		System.out.println("start " + startTime);
		System.out.println("duration " + duration);
		System.out.println("class: " + getServerClass());
		System.out.println("relcat: " + getRelCat());
		System.out.println("speedcat: " + getSpeedCat());
		System.out.println("penalty: " + getPenalty());
		System.out.println();
	}

	/**
	 * @return the combined speedCat relCat class
	 */
	public int getServerClass(){
		return RelSpeedMapping.getInstance().getServerClass(speedCat, relCat);
	}
	
	/**
	 * @return the job converted to a JobAxis
	 */
	public JobAxis getJobAxis(){
		JobAxis ja = new JobAxis(dasd, duration, earning, id, penalty, relCat, speedCat, startTime);
		return ja;
	}
	
	/**
	 * @return the job converted to a JobGui
	 */
	public JobGui getJobGui(){
		JobGui jg = new JobGui();
		jg.setDasd(dasd);
		jg.setDuration(duration);
		jg.setEarning(earning);
		jg.setId(id);
		jg.setPenalty(penalty);
		jg.setRelCat(relCat);
		jg.setSpeedCat(speedCat);
		jg.setStartTime(startTime);
		
		return jg;
	}
	
	/**
	 * @param jobAxis the JobAxis to be converted
	 * @return job jobAxis converted to a Job
	 */
	public static Job getJob (JobAxis jobAxis){
		Job job = new Job();
		job.setDasd(jobAxis.getDasd());
		job.setDuration(jobAxis.getDuration());
		job.setEarning(jobAxis.getEarning());
		job.setId(jobAxis.getId());
		job.setPenalty(jobAxis.getPenalty());
		job.setRelCat(jobAxis.getRelCat());
		job.setSpeedCat(jobAxis.getSpeedCat());
		job.setStartTime(jobAxis.getStartTime());
		
		return job;
	}
	
	/**
	 * @param jobGui the JobGui to be converted
	 * @return job jobAxis converted to a jobGui
	 */
	public static Job getJob(JobGui jobGui){
		Job job = new Job();
		job.setDasd(jobGui.getDasd());
		job.setDuration(jobGui.getDuration());
		job.setEarning(jobGui.getEarning());
		job.setId(jobGui.getId());
		job.setPenalty(jobGui.getPenalty());
		job.setRelCat(jobGui.getRelCat());
		job.setSpeedCat(jobGui.getSpeedCat());
		job.setStartTime(jobGui.getStartTime());
		
		return job;
		
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Job other = (Job) obj;
		if (id != other.id)
			return false;
		return true;
	}





	@Override
	public int hashCode() {
		return id;
	}
	
	
	

}
