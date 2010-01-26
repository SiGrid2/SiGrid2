package communication.algorithms.pareto;

import java.util.Vector;

import simobjects.Server;
import simobjects.algorithms.pareto.ParetoPoint;
import simobjects.algorithms.pareto.ParetoTransportData;

/**
 * Interface for methods called from SimWorker on SimMaster 
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public interface ISenderPareto {


	/**
	 * the SimWorker registers at the SimMaster
	 *  
	 * @return ID of SimWorker
	 */
	public int 		register			();


	/**
	 * the SimWorker unregisters at the SimMaster
	 * 
	 * @param receiverID
	 */
	public void 	unregister			(int receiverID);	


	/**
	 * the SimWorker sends a SimLogMessage to the SimMaster
	 * 
	 * @param receiverID
	 * @param trID
	 * @param message
	 */
	public void 	addSimLogMessage	(int receiverID, int trID, String message);



	/**
	 * the SimWorker gets new pareto-data to calculate from the SimMaster
	 * 
	 * @return a ParetoTransportData-Object to calculate
	 */
	public ParetoTransportData getParetoDataToCalculate();	


	/**
	 * the SimWorker delivers the results of an optimisation to the SimMaster
	 * 
	 * @param ppVec vector of pareto-points
	 * @param bestParetoPoint the pareto-point with highest earning in ppVec
	 * @param server the storage network the optimization was made for
	 */
	public void setCalculatedPareto(Vector<ParetoPoint> ppVec, ParetoPoint bestParetoPoint, Server server);

}//end of interface
