package farm.master.gsp;

import org.apache.log4j.Logger;

import communication.general.IReceiver;

import simobjects.Layout;
import simobjects.Matrix;
import simobjects.SimResult;
import simobjects.TestRun;
import simobjects.algorithms.gsp.MatchMaker;
import simobjects.algorithms.gsp.helper.LayoutGSP;
import simobjects.algorithms.gsp.helper.Setting;
import simobjects.algorithms.gsp.helper.earnings.DMatrix;
import simobjects.algorithms.gsp.helper.earnings.EarningBuilder;
import simobjects.algorithms.gsp.helper.earnings.HabrBuilder;


/**
 * Adaption of the GSP Algorithm
 * 
 * Four matrices are build and each combination for server and jobs tested. 
 * 
 * The best one ist submitted  back
 * 
 * @author Christoph Beck
 * @version 1.0
 */
public class MasterCore implements IReceiver {
	private static Logger logger = Logger.getLogger( SimMaster.class );
	private SimMaster simMaster;
	private TestRun tr;
	private Layout layout;
	private EarningBuilder eb;
	private DMatrix total;
	private DMatrix relative;



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/

	/**
	 * Constructor, takes the testrun
	 * @param simMaster		the simMaster to talk to
	 * @param tr			the testrun
	 */
	public MasterCore(SimMaster simMaster, TestRun tr) {
		this.simMaster = simMaster;
		this.tr = tr;
		this.layout= tr.getLayout();
		init ();
	}



	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/



	/* (non-Javadoc)
	 * @see communication.general.IReceiver#startSim(int, int)
	 */
	public void startSim(int receiverID, int trID) {
		simMaster.startedSim(receiverID, trID);
		logger.info("GSP MasterCore started!");

		// the settings & data for each run
		Setting[] settings = getSettings();

		// store earnings and running times
		long earnings[] 		= new long [settings.length];
		long times[]			= new long [settings.length];

		long earningMax[]		= new long[2]; 		


		// for the best combination found
		SimResult simResultMax 	= null;

		// layout with preflist methods
		LayoutGSP layoutGSP;

		// the main loop
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < settings.length; i++) {

				// take times
				times[i] = System.currentTimeMillis();

				// build layout from settings
				layoutGSP = new LayoutGSP (
						layout, 
						total.getTheMatrix(),
						relative.getTheMatrix(),
						settings[i].getSMatrix(),
						settings[i].getJMatrix(),
						false,
						false,
						j==0);

				// action !!!!!!!!!!
				MatchMaker theMaker = new MatchMaker (layoutGSP );
				theMaker.match();


				// analyse results. the usual stuff
				earnings[i]=layoutGSP.getEarning();
				times[i]= System.currentTimeMillis() - times[i] ;

				if (earnings[i]>earningMax[j]) {
					earningMax[j] = earnings[i];
					simResultMax= new SimResult(layoutGSP.getMatrix(),earningMax[j]);
				}
				System.out.println("finished pass:"+j+"/"+i);
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}

		System.out.println("MaxEarning without speedup\t="+earningMax[0]);
		System.out.println("MaxEarning with speedup\t="+earningMax[1]);
		simMaster.addSimResult(0, tr.getId(), simResultMax);
		simMaster.finishedSim(0, tr.getId());
	}



	/**
	 * @return
	 */
	private Setting[] getSettings() {
		DMatrix matrices[]	= getSubruns ();



		Setting settings [] = new Setting [matrices.length * matrices.length]; 
		for (int i = 0; i < matrices.length; i++) {
			for (int j = 0; j < matrices.length; j++) {
				settings [i*matrices.length+j] = new Setting(matrices[i].getTheMatrix(), matrices[j].getTheMatrix(),
						false, false, "S"+matrices[i].getName()+"XJ"+matrices[j].getName());
			}
		}
		return settings;
	}

	/* (non-Javadoc)
	 * @see communication.general.IReceiver#abortSim(int, int)
	 */
	public void abortSim(int receiverID, int trID) {
		simMaster.abortedSim(receiverID, trID);
	}



	/* (non-Javadoc)
	 * @see communication.general.IReceiver#loadSim(int, simobjects.TestRun)
	 */
	public void loadSim(int receiverID, TestRun tr) {
		simMaster.loadedSim(receiverID, tr.getId());

	}



	/* (non-Javadoc)
	 * @see communication.general.IReceiver#shutDown(int)
	 */
	public void shutDown(int receiverID) {
		// n. a.
	}

	/**********************************************************
	 *  private functions
	 **********************************************************/


	/**
	 * builds the basic matrix of (total & relative) earnings
	 */
	private void init () {
		eb 				= new EarningBuilder (layout);
		total 			= new DMatrix (eb.getEarningMatrix(),"totalEarnings");
		relative 		= new DMatrix (eb.getRelEarningMatrix(),"realtiveEarnings");
	}


	/*
	 * builds the subruns: total, relative, habr of total, habr of realtive
	 */
	private DMatrix[] getSubruns() {

		// invert to be used by gsp
		DMatrix rmTDM 	= new DMatrix (total.invertBy(total.getMaxTotal()),"invertedTotalEarnings");
		DMatrix rmRDM 	= new DMatrix (relative.invertBy(relative.getMaxTotal()),"invertedRealtiveEarnings") ;

		rmTDM.init();
		rmRDM.init();

		//		 habred to be used by gsp
		DMatrix habrTDMTA	= (new HabrBuilder (layout, rmTDM,"habrTotalEarnings")).getHabr();
		DMatrix habrRDMTA	= (new HabrBuilder (layout, rmRDM,"habrRealtiveEarnings")).getHabr();

		// wrap it up
		DMatrix [] getSubruns = new DMatrix []{
				rmTDM,
				rmRDM,
				habrTDMTA,
				habrRDMTA
		};

		return getSubruns;
	}






}//end of class
