package simobjects;

import java.io.Serializable;
import javax.persistence.Embeddable;

import simobjects.transport.farm.ServerAxis;
import simobjects.transport.gui.ServerGui;

/**
 * @author Christoph Beck
 *
 */
@Embeddable
public class Server implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
	private int cost;
	private int dasd;
	private int relCat;
	private int speedCat;



	/**
	 * Default Constructor
	 */
	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}




	
	/** 
	 * @param id	
	 * @param cost
	 * @param dasd
	 * @param relCat
	 * @param speedCat
	 */
	public Server(int id, int cost, int dasd, int relCat, int speedCat) {
		super();
		this.id = id;
		this.cost = cost;
		this.dasd = dasd;
		this.relCat = relCat;
		this.speedCat = speedCat;
	}





	/**
	 * @return the server cost
	 */
	public int getCost() {
		return cost;
	}



	/**
	 * @param cost  the server cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}



	/**
	 * @return  the server dasd
	 */
	public int getDasd() {
		return dasd;
	}



	/**
	 * @param dasd  the server dasd
	 */
	public void setDasd(int dasd) {
		this.dasd = dasd;
	}



	/**
	 * @return  the server id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id  the server id
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return  the server relCat
	 */
	public int getRelCat() {
		return relCat;
	}



	/**
	 * @param relCat  the server relCat
	 */
	public void setRelCat(int relCat) {
		this.relCat = relCat;
	}



	/**
	 * @return  the server speedCat
	 */
	public int getSpeedCat() {
		return speedCat;
	}



	/**
	 * @param speedCat  the server speedCat
	 */
	public void setSpeedCat(int speedCat) {
		this.speedCat = speedCat;
	}



	/**
	 * Dumps job to console
	 */
	public void printToConsole(){
		System.out.println("server #" + id);
		System.out.println("dasd: " + dasd);
		System.out.println("cost: " + cost);
		System.out.println("class: " + getServerClass());
		System.out.println("relcat: " + getRelCat());
		System.out.println("speedcat: " + getSpeedCat());
		System.out.println();
	}

	/**
	 * @return  the server combined speedCat relCat class
	 */
	public int getServerClass(){
		return RelSpeedMapping.getInstance().getServerClass(speedCat, relCat);
	}

	/**
	 * @return the server converted to a ServerAxis
	 */
	public ServerAxis getServerAxis(){
		ServerAxis sa = new ServerAxis(cost, dasd, id, relCat, speedCat);
		return sa;
	}

	/**
	 * @return the server converted to a ServerGui
	 */
	public ServerGui getServerGui(){
		ServerGui sg = new ServerGui();
		sg.setCost(cost);
		sg.setDasd(dasd);
		sg.setId(id);
		sg.setRelCat(relCat);
		sg.setSpeedCat(speedCat);

		return sg;
	}

	/**
	 * @param serverAxis
	 * @return the serverAxis converted to a Server
	 */
	public static Server getServer (ServerAxis serverAxis){
		Server server = new Server();
		server.setCost(serverAxis.getCost());
		server.setDasd(serverAxis.getDasd());
		server.setId(serverAxis.getId());
		server.setRelCat(serverAxis.getRelCat());
		server.setSpeedCat(serverAxis.getSpeedCat());

		return server;
	}

	/**
	 * @param serverGui
	 * @return the serverAxis converted to a Server
	 */
	public static Server getServer (ServerGui serverGui){
		Server server = new Server();
		server.setCost(serverGui.getCost());
		server.setDasd(serverGui.getDasd());
		server.setId(serverGui.getId());
		server.setRelCat(serverGui.getRelCat());
		server.setSpeedCat(serverGui.getSpeedCat());

		return server;
	}






	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if((obj == null) || (obj.getClass() != this.getClass())) return false;
		Server s = (Server)obj;
		if (this.id == s.getId()) return true;
		return false;
	}





	@Override
	public int hashCode() {
		return id;
	}
	
	









}
