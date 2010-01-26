package controler.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import controler.communication.gui.noAxis.AsciiReader;

import simobjects.Layout;
import simobjects.LayoutEvaluation;
import simobjects.SimJob;

public class SimjobGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 10 ;
		
		int dasdmin = 10 ;
		int dasdmax = 1000 ;
		
		int jobs = 6400 ;
		
		int earning = 10 ;
		
		int startmin= 1 ;
		int startmax= 12 ;
		
		int durationmin= 1 ;
		int durationmax= 12 ;
		
		int relcatmin= 1 ;
		int relcatmax= 5 ;
		
		int speedcatmin= 1 ;
		int speedcatmax= 5 ;
		
		int penaltymin= 36240 ;
		int penaltymax= 12845628 ;
		
		int server= 25 ;

		int min = 150;
		int max = 3000;
		
		int penalty=penaltymin;

		PrintStream stream;
		Layout l;
		long t = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			
			try {
				stream = new PrintStream(new FileOutputStream(""+server+"-"+jobs+"-"+t+"-"+i+"-uncorrelated.txt"));
				stream.println("SimJob:	farmid=1");	
				stream.println("TestRun: algorithmid=2");	
				l = LayoutEvaluation.getUncorrelatedInstances(min, max, jobs, startmin, startmax, 
						durationmin, durationmax, relcatmin, relcatmax, speedcatmin, speedcatmax, penaltymin, 
						penaltymax, server);
				l.setName(""+server+"-"+jobs+"-"+System.currentTimeMillis());
				l.dumpWithoutIds(stream);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stream = new PrintStream(new FileOutputStream(""+server+"-"+jobs+"-"+t+"-"+i+"-stronglycorrelated.txt"));
				stream.println("SimJob:	farmid=1");	
				stream.println("TestRun: algorithmid=2");	
				l = LayoutEvaluation.getStronglyCorrelatedInstances(dasdmin, dasdmax, jobs, earning,
						startmin, startmax, durationmin, durationmax, relcatmin, relcatmax, speedcatmin, speedcatmax, 
						penalty, server);
				l.setName(""+server+"-"+jobs+"-"+System.currentTimeMillis());
				l.dumpWithoutIds(stream);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stream = new PrintStream(new FileOutputStream(""+server+"-"+jobs+"-"+t+"-"+i+"-weaklycorrelated.txt"));
				stream.println("SimJob:	farmid=1");	
				stream.println("TestRun: algorithmid=2");	
				
				l = LayoutEvaluation.getWeaklyCorrelatedInstances(dasdmin, dasdmax, jobs, earning, 
						startmin, startmax, durationmin, durationmax, relcatmin, relcatmax, speedcatmin, speedcatmax,
						penaltymin, penaltymax, server);
				l.setName(""+server+"-"+jobs+"-"+System.currentTimeMillis());
				l.dumpWithoutIds(stream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stream = new PrintStream(new FileOutputStream(""+server+"-"+jobs+"-"+t+"-"+i+"-staticearning.txt"));
				stream.println("SimJob:	farmid=1");	
				stream.println("TestRun: algorithmid=2");	
				
				l = LayoutEvaluation.getSlaWithStaticEarningInstances(dasdmin, dasdmax, jobs, earning,
						startmin, startmax, durationmin, durationmax, relcatmin, relcatmax, speedcatmin, speedcatmax,
						penalty, server);
				l.setName(""+server+"-"+jobs+"-"+System.currentTimeMillis());
				l.dumpWithoutIds(stream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
