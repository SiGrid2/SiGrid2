package communication.general.axis;

import simobjects.SimResult;
import simobjects.SimState;
import simobjects.SimStatistics;
import simobjects.TestRun;

import farm.objects.ReceiverState;

/**
 * Same interface as the one from the Web Service (FarmWS), just without MappingObjects like TestRunAxis.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public interface ISenderAxis {

	public int getActionFromControler(int receiverID);
	public TestRun getTestRun(int receiverID);


	public void loadedSim   (int receiverID, int trID);
	public void startedSim  (int receiverID, int trID);
	public void abortedSim  (int receiverID, int trID);
	public void finishedSim (int receiverID, int trID);

	public void addSimResult(int receiverID, int trID, SimResult simResult);
	public void addSimLogMessage (int receiverID, int trID, String message);
	public void addSimStatistics(int receiverID, int trID, SimStatistics stats);

	public void setSimState(int receiverID, int trID, SimState simState);

	public void setReceiverState(int receiverID, ReceiverState receiverState);

	public int register(long initID);
	public void unregister(int receiverID, long initID);

}//end of interface
