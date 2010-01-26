package controler.communication.gui.noAxis;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import simobjects.Layout;

import simobjects.transport.gui.SimJobGui;
import simobjects.transport.gui.TestrunGui;

import controler.communication.gui.axis.GuiWebservice;



public class BatchControlerThread extends Thread {
	private GuiWebservice guiWS; 
	private String batchDirPath;
	private String slash;

	public BatchControlerThread(GuiWebservice guiWS, String batchDirPath){
		this.guiWS=guiWS;
		this.batchDirPath=batchDirPath;
		this.slash = File.separator;
	}

	public BatchControlerThread(GuiWebservice guiWS){
		this(guiWS,"."+File.separator+"batch");
	}

	public void run(){
		File batchDir = new File (batchDirPath);
		System.out.println("Batch thread started. Base spool Dir is "+batchDir.getAbsolutePath());
		File 	layoutsDir;
		File[] 	layouts;
		File 	testrunsDir;
		File[] 	testruns;
		File 	simjobsDir;
		File[] 	simjobs;
		File	processedDir;


		int uid = 0;

		while (true){


			layoutsDir = new File(batchDirPath+slash+"layouts");
			testrunsDir = new File(batchDirPath+slash+"testruns");
			simjobsDir = new File(batchDirPath+slash+"simjobs");
			processedDir = new File(batchDirPath+slash+"processed");


			if(layoutsDir.exists() && processedDir.exists()) {
				layouts=layoutsDir.listFiles();
				processLayouts(layouts, uid);
			}

			if(testrunsDir.exists() && processedDir.exists()) {
				testruns=testrunsDir.listFiles();
				processTestRuns(testruns, uid);
			}

			if(simjobsDir.exists() && processedDir.exists()) {
				simjobs=simjobsDir.listFiles();
				processSimJobs(simjobs, uid);
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
	private void processSimJobs(File[] simjobs, int uid) {
		File renameTo;

		for (int i = 0; i < simjobs.length; i++) {
			System.out.println(simjobs[i].getName());
			System.out.println(simjobs[i].getAbsolutePath());

			processSimJob(simjobs[i]);

			while((renameTo=new File(batchDirPath+slash+"processed"+slash+System.currentTimeMillis()+"-"+uid+"-"+simjobs[i].getName())).exists()){
				uid ++;
			}
			
			simjobs[i].renameTo(renameTo);
		}
	}

	private void processSimJob(File f) {
		AsciiReader reader = new AsciiReader(f.getAbsolutePath());

		SimJobGui	sjg;
		TestrunGui 	trg;
		Layout 		l;
		try {
			reader.readFile();
			
			sjg		=	reader.getSimJobGui();
			sjg.dump(System.out);
			
			trg 	= 	reader.getTestRunGui();
			trg.dump(System.out);
			
			l		=	reader.getLayout();
			l.dump(System.out);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reader.closeFile();
			return;
		} catch (IOException e) {
			reader.closeFile();
			e.printStackTrace();
			return;
		}

		
//		 PLEASE ENTER CODE TO SAVE LAYOUT HERE
		System.out.println("read Layout from file. Name:"+l.getName());
		System.out.println("read TestRun from file. Name:"+trg.getName());
		System.out.println("read TestRun from file. Testrun Name:"+trg.getName()+" SimFarm Name:"+sjg.getSimFarmId());
		

		reader.closeFile();
		
		l = Layout.getLayout(guiWS.createLayout(l.getLayoutGui()));
		System.out.println("read Layout from hibernate. Name:"+l.getName());
		
		trg.setLayoutId(l.getId());
		trg=guiWS.createTestrun(trg);
		System.out.println("read TestRun from hibernate. Name:"+trg.getName());
		
		sjg.setTestrunId(trg.getId());
		sjg=guiWS.createSimJob(sjg);
		System.out.println("read SimJob from hibernate. Testrun Name:"+trg.getName()+" SimFarm Name:"+sjg.getSimFarmId());
		
		guiWS.queueSimJob(sjg.getId());
		System.out.println("SimJob with Testrun Name: "+trg.getName()+" SimFarm Name: "+sjg.getSimFarmId()+" queued");
	}

	private void processTestRuns(File[] testruns, int uid) {
		File renameTo;

		for (int i = 0; i < testruns.length; i++) {
			System.out.println(testruns[i].getName());
			System.out.println(testruns[i].getAbsolutePath());

			processTestRun(testruns[i]);

			while((renameTo=new File(batchDirPath+slash+"processed"+slash+System.currentTimeMillis()+"-"+uid+"-"+testruns[i].getName())).exists()){
				uid ++;
			}
			
			testruns[i].renameTo(renameTo);
		}
	}

	private void processTestRun(File f) {
		AsciiReader reader = new AsciiReader(f.getAbsolutePath());

		TestrunGui trg;
		Layout l;
		try {
			reader.readFile();
			
			trg 	= 	reader.getTestRunGui();
			trg.dump(System.out);
			
			l		=	reader.getLayout();
			l.dump(System.out);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reader.closeFile();
			return;
		} catch (IOException e) {
			reader.closeFile();
			e.printStackTrace();
			return;
		}

		// PLEASE ENTER CODE TO SAVE LAYOUT HERE
		System.out.println("read Layout from file. Name:"+trg.getName());

		reader.closeFile();
		
		l = Layout.getLayout(guiWS.createLayout(l.getLayoutGui()));
		System.out.println("read Layout from hibernate. Name:"+l.getName());
		
		trg.setLayoutId(l.getId());
		trg=guiWS.createTestrun(trg);
		System.out.println("read TestRun from hibernate. Name:"+trg.getName());

	}

	private void processLayouts(File[] layouts, int uid) {
		File renameTo;

		for (int i = 0; i < layouts.length; i++) {
			System.out.println(layouts[i].getName());
			System.out.println(layouts[i].getAbsolutePath());

			processLayout(layouts[i]);

			while((renameTo=new File(batchDirPath+slash+"processed"+slash+System.currentTimeMillis()+"-"+uid+"-"+layouts[i].getName())).exists()){
				uid ++;
			}
		
			layouts[i].renameTo(renameTo);
		}
	}

	private void processLayout(File f) {
		AsciiReader reader = new AsciiReader(f.getAbsolutePath());

		Layout l;
		try {
			reader.readFile();
			l = reader.getLayout();
			l.dump(System.out);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reader.closeFile();
			return;
		} catch (IOException e) {
			reader.closeFile();
			e.printStackTrace();
			return;
		}

		// PLEASE ENTER CODE TO SAVE LAYOUT HERE
		System.out.println("read Layout from file. Name:"+l.getName());

		reader.closeFile();
		l = Layout.getLayout(guiWS.createLayout(l.getLayoutGui()));
		System.out.println("read Layout from hibernate. Name:"+l.getName());

	}

	public String getBatchDirPath() {
		return batchDirPath;
	}

	public void setBatchDirPath(String batchDirPath) {
		this.batchDirPath = batchDirPath;
	}

}

