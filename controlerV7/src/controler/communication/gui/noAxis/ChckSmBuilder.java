package controler.communication.gui.noAxis;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ChckSmBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("filename, please...");
			System.exit(0);
		}

		boolean dump = false;
		// still here ?

		AsciiReader ascii = new AsciiReader (args[0]);
		
		if(args.length > 1 && args[1].equals("-d")) {
			dump = true;
		}
		try {
			ascii.readFile();
			
			long earn 	= ascii.getFileEarning();
			int alg  	= ascii.getAlgrithmID();
			int nSrv	= ascii.getLayout().getNumServer();
			int nJb		= ascii.getLayout().getNumJobs();
			
			
			//System.err.println("TakeMeHome\tnsrv-njob-csrv-cjob-csum-earn-alg\tfile");
			System.err.println("TakeMeHomeV\t"+ 
					nSrv+"-"+nJb+"-"+
					ascii.getLayout().getUID()+"-"+
					+earn+"-"+alg+"\t"+args[0]);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.exit(0);
		}
		if(dump) {
			ascii.dump(System.out);
		}
		
	}
	

}
