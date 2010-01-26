package controler.communication.gui.axis;


import java.util.List;
import controler.Controler;
import controler.communication.gui.noAxis.BatchControlerThread;
import controler.persistence.DAOFactory;
import simobjects.Layout;
import simobjects.SimJob;
import simobjects.TestRun;
import simobjects.transport.gui.*;


/**
 * very prototypic implementation of IGuiWebservice
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class GuiWebservice implements IGuiWebservice {	
	
	private DAOFactory daoFactory = Controler.getInstance().getDaoFactory();
	

	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * Constructor
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
	
	public GuiWebservice() {
		super();
		System.out.println("starting thread");
		new BatchControlerThread(this).start();
		System.out.println("Thread started?");
	}
	
	
	
	
	
	/* ***********************************************************************************************************
	 * ***********************************************************************************************************
	 * public Methods
	 * ***********************************************************************************************************
	 *************************************************************************************************************/
		
	
	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#createLayout(simobjects.transport.gui.LayoutGui)
	 */
	public LayoutGui createLayout(LayoutGui layoutGui) {
		System.out.println("Layout l = Layout.getLayout(layoutGui);");
		Layout l = Layout.getLayout(layoutGui);
		System.out.println( "l = daoFactory.getLayoutDAO().persist(l);");
		l = daoFactory.getLayoutDAO().persist(l);
		System.out.println ( "return l.getLayoutGui();");
		return l.getLayoutGui();
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#createSimJob(simobjects.transport.gui.SimJobGui)
	 */
	public SimJobGui createSimJob(SimJobGui simJobGui) {
		SimJob sj = SimJob.getSimJob(simJobGui);
		sj = daoFactory.getSimJobDAO().persist(sj);
		return sj.getSimJobGui();
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#createTestrun(simobjects.transport.gui.TestrunGui)
	 */
	public TestrunGui createTestrun(TestrunGui testrunGui) {
		TestRun tr = TestRun.getTestRun(testrunGui);		
		tr = daoFactory.getTestrunDAO().persist(tr);
		return tr.getTestrunGui();
	}
	

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#queueSimJob(int)
	 */
	public void queueSimJob(int idSimJob) {
		SimJob simJob = daoFactory.getSimJobDAO().getById(idSimJob);
		if (simJob != null){
			System.out.println("Queued Success! (SimJobId="+simJob.getId()+")");
			Controler.getInstance().queueSimJob(simJob);
		}
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#setQueuePosition(int, int)
	 */
	public void setQueuePosition(int idSimJob, int queuePos) {
		// TODO Auto-generated method stub		
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#shutDownSimFarm(int)
	 */
	public void shutDownSimFarm(int idSimFarm) {
		Controler.getInstance().shutDownSimFarm( idSimFarm);		
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#updateLayout(simobjects.transport.gui.LayoutGui)
	 */
	public boolean updateLayout(LayoutGui layoutGui) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#updateSimJob(simobjects.transport.gui.SimJobGui)
	 */
	public boolean updateSimJob(SimJobGui simJobGui) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#updateTestrun(simobjects.transport.gui.TestrunGui)
	 */
	public boolean updateTestrun(TestrunGui testrunGui) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#abortSim(int)
	 */
	public void abortSim(int idSimJob) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#deleteLayout(int)
	 */
	public boolean deleteLayout(int idLayout) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#deleteSimJob(int)
	 */
	public boolean deleteSimJob(int idSimjob) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#deleteSimJobFromQueue(int)
	 */
	public boolean deleteSimJobFromQueue(int idSimJob) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#deleteTestrun(int)
	 */
	public boolean deleteTestrun(int idTestrun) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAlgorithmById(int)
	 */
	public AlgorithmGui getAlgorithmById(int idAlgorithm) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllAlgorithm()
	 */
	public AlgorithmGui[] getAllAlgorithm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllAlgorithm(java.lang.String, java.lang.String, java.lang.String)
	 */
	public AlgorithmGui[] getAllAlgorithm(String param1, String param2, String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllLayout()
	 */
	public LayoutGui[] getAllLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllLayout(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LayoutGui[] getAllLayout(String param1, String param2, String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllSimFarm()
	 */
	public SimFarmGui[] getAllSimFarm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllSimFarm(java.lang.String, java.lang.String, java.lang.String)
	 */
	public SimFarmGui[] getAllSimFarm(String param1, String param2, String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllSimJob()
	 */
	public SimJobGui[] getAllSimJob() {
		List sjl = daoFactory.getSimJobDAO().getAll();
		SimJobGui sjg[] = new SimJobGui[sjl.size()];
		
		for (int i = 0; i < sjg.length; i++) {
			sjg[i] = ((SimJob)sjl.get(i)).getSimJobGui();
		}
		return sjg;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllSimJob(java.lang.String, java.lang.String, java.lang.String)
	 */
	public SimJobGui[] getAllSimJob(String param1, String param2, String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllTestrun()
	 */
	public TestrunGui[] getAllTestrun() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getAllTestrun(java.lang.String, java.lang.String, java.lang.String)
	 */
	public TestrunGui[] getAllTestrun(String param1, String param2, String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getLayoutById(int)
	 */
	public LayoutGui getLayoutById(int idLayout) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getSimFarmById(int)
	 */
	public SimFarmGui getSimFarmById(int idSimFarm) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getSimJobById(int)
	 */
	public SimJobGui getSimJobById(int idSimjob) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getSimLogMessages(int)
	 */
	public String[] getSimLogMessages(int idSimFarm) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#getTestrunById(int)
	 */
	public TestrunGui getTestrunById(int idTestrun) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#SetBatchJobDir(java.lang.String)
	 */
	public void SetBatchJobDir(String dir) {		
		
	}

	/* (non-Javadoc)
	 * @see controler.communication.gui.axis.IGuiWebservice#pauseSimFarm(int, boolean)
	 */
	public void pauseSimFarm(int idSimFarm, boolean pause) {
		// TODO Auto-generated method stub		
	}	
	

}//end of class
