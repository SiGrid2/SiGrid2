package simobjects;


/**
 * class holds the relcat-speedcat->class-matrix
 * singleton.
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class RelSpeedMapping {

	private static RelSpeedMapping instance = null;

	private int[][] mapping;

	/**
	 * @return singleton instance
	 */
	public static RelSpeedMapping getInstance(){
		if(instance == null) {
			instance = new RelSpeedMapping();
		}
		return instance;
	}

	/*
	 a mapping-matrix looks like this:

	 		reliability
	 		  1  2  3  4  5
	 		------------------------
	 	s 1|
	 	p  |
	 	e 2|
	    e  |
   	 	d 3|
	 	   |
	 	  4|
	 	   |
	 	  5|
	 */
	
	private RelSpeedMapping() {
		mapping = new int[5][5];

		mapping[0][0] = 1;
		mapping[0][1] = 2;
		mapping[0][2] = 3;
		mapping[0][3] = 4;
		mapping[0][4] = 5;

		mapping[1][0] = 6;
		mapping[1][1] = 7;
		mapping[1][2] = 8;
		mapping[1][3] = 9;
		mapping[1][4] = 10;

		mapping[2][0] = 11;
		mapping[2][1] = 12;
		mapping[2][2] = 13;
		mapping[2][3] = 14;
		mapping[2][4] = 15;

		mapping[3][0] = 16;
		mapping[3][1] = 17;
		mapping[3][2] = 18;
		mapping[3][3] = 19;
		mapping[3][4] = 20;

		mapping[4][0] = 21;
		mapping[4][1] = 22;
		mapping[4][2] = 23;
		mapping[4][3] = 24;
		mapping[4][4] = 25;
	}
	

	/**
	 * @param speed
	 * @param reliabilty
	 * @return the class consisting of that relcat and speedcat
	 */
	public int getServerClass(int speed, int reliabilty){
		if(speed>5) speed = 5;
		if(reliabilty>5) reliabilty = 5;
		return mapping[speed-1][reliabilty-1];
	}
	
	/**
	 * @param classnr
	 * @return the relcat of that class
	 */
	public int getRelClass(int classnr) {
		for (int speed = 0; speed < mapping.length; speed++) {
			for (int rel = 0; rel < mapping[0].length; rel++) {
				if (mapping[speed][rel] == classnr) {
					return rel+1;
				}
			}
		}
		return -1;
	}
	
	
	/**
	 * @param classnr
	 * @return the speedcat of that class
	 */
	public int getSpeedClass(int classnr) {
		for (int speed = 0; speed < mapping.length; speed++) {
			for (int rel = 0; rel < mapping[0].length; rel++) {
				if (mapping[speed][rel] == classnr) {
					return speed+1;
				}
			}
		}
		return -1;
	}


}//end of class
