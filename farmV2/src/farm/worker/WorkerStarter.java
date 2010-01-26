package farm.worker;

/**
 * @author Christoph Beck
 *
 * Class to start FarmWorker
 */
public class WorkerStarter {



	/**
	 * @param args the ip of the mashine the farmMaster runns on
	 */
	public static void main(String[] args) {

		FarmWorker worker = new FarmWorker(args[0]);
		worker.register(0);


	}

}
