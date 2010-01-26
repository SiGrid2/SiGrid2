package controler.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import simobjects.Algorithm;
import simobjects.Layout;
import simobjects.SimJob;
import simobjects.SimResult;
import simobjects.TestRun;
import simobjects.transport.gui.LayoutGui;
import simobjects.transport.gui.SimJobGui;
import simobjects.transport.gui.TestrunGui;
import controler.communication.gui.axis.GuiWebservice;

public class LayoutExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GuiWebservice guiWS= new GuiWebservice();



		PrintStream 	stream 	= null;
		String 			filename="default";
		if (args.length == 0) {
			SimJobGui [] sjs = guiWS.getAllSimJob();
			for (int i = 0; i < sjs.length; i++) {

				try {
					filename = "sjID-"+sjs[i].getId()+"-";
					filename = filename + "trID-"+sjs[i].getId()+"-";
					filename = filename + "layID-"+sjs[i].getId()+".log";
					stream = new PrintStream(new FileOutputStream(filename));
					dumpSJtoFile(stream, SimJob.getSimJob(sjs[i]));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}





			}
		}




	}
	public static void dumpSJtoFile (PrintStream stream, SimJob sj) {
		TestRun 		tr 	= sj.getTestrun();
		Layout			l  	= tr.getLayout();
		SimResult		sr 	= sj.getSimResult();



		l.dump(stream);

		stream.println("#Simjob id"+sj.getId());
		stream.println("#Simjob queuedMs"+sj.getQueuedMs());
		stream.println("#Simjob startedMs"+sj.getStartedMs());
		stream.println("#Simjob finishedMS"+sj.getFinishedMS());
		stream.println("#####");
		stream.println("#Testrun id"+tr.getId());
		stream.println("#Testrun name"+tr.getName());

		stream.println("#Testrun algorithm"+tr.getAlgorithm().getName());
		
		try {
			stream.println("#SimResult Earning"+sr.getEarning());
		} catch (java.lang.NullPointerException e) {
			stream.println("#SimResult -not present-");
		}


		stream.println("#Testrun statsStart"+tr.getStatsStart());
		stream.println("#Testrun statsStart"+tr.getStatsEnd());
		stream.println("#Testrun statsNumFields"+tr.getStatsNumFields());

		try {
			long stats[] = sj.getSimStatistic().getStatistics();
			for (int i = 0; i < stats.length; i++) {
				stream.println("#Testrun Statsfield (id,value) "+i+","+stats[i]);
			}


			stream.println("#####");


		} catch (java.lang.NullPointerException e) {
			stream.println("#Statsfield -not present-");
		}
		try {
			sr.getJobServerMapping().printMatrixToConsoleInt(stream);
		} catch (java.lang.NullPointerException e) {
			stream.println("#Mapping sr.getJobServerMapping() failed");
		}
		stream.close();
	}
}
