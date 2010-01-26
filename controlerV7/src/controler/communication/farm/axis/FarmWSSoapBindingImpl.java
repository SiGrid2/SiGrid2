/**
 * FarmWSSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package controler.communication.farm.axis;

import java.rmi.RemoteException;
import controler.Controler;
import simobjects.transport.farm.ReceiverStateAxis;
import simobjects.transport.farm.SimResultAxis;
import simobjects.transport.farm.SimStateAxis;
import simobjects.transport.farm.SimStatisticsAxis;
import simobjects.transport.farm.TestrunAxis;

public class FarmWSSoapBindingImpl implements controler.communication.farm.axis.FarmWS{

	private Controler controler = Controler.getInstance();

	public TestrunAxis getTestRun(int receiverID) throws java.rmi.RemoteException {
		System.out.println("FarmWebservice: getTestRun");
		return controler.getTestRun(receiverID);
	}





	public void loadedSim(int receiverID, int trID) throws RemoteException {
		System.out.println("FarmWebservice: loadedSim");
		controler.loadedSim(receiverID, trID);

	}





	public void abortedSim(int receiverID, int trID) throws RemoteException {
		System.out.println("FarmWebservice: abortedSim");
		controler.abortedSim(receiverID, trID);

	}





	public void addSimLogMessage(int receiverID, int trID, String message)
	throws RemoteException {
		System.out.println("FarmWebservice: addSimLogMessage");
		controler.addSimLogMessage(receiverID, trID, message);

	}





	public void addSimResult(int receiverID, int trID, SimResultAxis simResult)
	throws RemoteException {
		System.out.println("FarmWebservice: addSimResult");
		controler.addSimResult(receiverID, trID, simResult);

	}





	public void addSimStatistics(int receiverID, int trID,
			SimStatisticsAxis stats) throws RemoteException {
		System.out.println("FarmWebservice: addSimStatistics");
		controler.addSimStatistics(receiverID, trID, stats);

	}





	public void finishedSim(int receiverID, int trID) throws RemoteException {
		System.out.println("FarmWebservice: finishedSim");

		controler.finishedSim(receiverID, trID);

	}





	public int getActionFromControler(int receiverID) throws RemoteException {

		return controler.getActionFromControler(receiverID);
	}





	public int register(long initID, String name) throws RemoteException {
		System.out.println("FarmWebservice: register");
		return controler.register(initID, name);
	}





	public void setReceiverState(int receiverID, ReceiverStateAxis receiverState)
	throws RemoteException {
		System.out.println("FarmWebservice: setReceiverState");
		controler.setReceiverState(receiverID, receiverState);

	}





	public void setSimState(int receiverID, int trID, SimStateAxis simState)
	throws RemoteException {
		//System.out.println("FarmWebservice: setSimState");
		controler.setSimState(receiverID, trID, simState);

	}





	public void startedSim(int receiverID, int trID) throws RemoteException {
		System.out.println("FarmWebservice: startedSim");
		controler.startedSim(receiverID, trID);

	}





	public void unregister(int receiverID, long initID) throws RemoteException {
		System.out.println("FarmWebservice: unregister");
		controler.unregister(receiverID, initID);

	}

}
