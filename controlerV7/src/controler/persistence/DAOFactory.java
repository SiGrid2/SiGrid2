package controler.persistence;

import controler.persistence.algorithm.IAlgorithmDAO;
import controler.persistence.layout.ILayoutDAO;
import controler.persistence.simfarm.ISimFarmDAO;
import controler.persistence.simjob.ISimJobDAO;
import controler.persistence.simresult.ISimResultDAO;
import controler.persistence.simstatistic.ISimStatisticDAO;
import controler.persistence.testrun.ITestrunDAO;

/**
 * abstract factory, that can instantiiate all DAOs
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public abstract class DAOFactory {

	public static final Class JPA = JPADAOFactory.class;

	public static DAOFactory instance (Class factory){
		try{
			return (DAOFactory) factory.newInstance();
		} catch (Exception e){
			throw new RuntimeException("Coulnd'nt create DAOFactory " + factory);
		}
	}

	//my DAO interfaces:
	/**
	 * @return ILayoutDAO
	 */
	public abstract ILayoutDAO getLayoutDAO();

	/**
	 * @return ITestrunDAO
	 */
	public abstract ITestrunDAO getTestrunDAO();

	/**
	 * @return IAlgorithmDAO
	 */
	public abstract IAlgorithmDAO  getAlgorithmDAO();

	/**
	 * @return ISimJobDAO
	 */
	public abstract ISimJobDAO getSimJobDAO();

	/**
	 * @return ISimFarmDAO
	 */
	public abstract ISimFarmDAO getSimFarmDAO();

	/**
	 * @return ISimResultDAO
	 */
	public abstract ISimResultDAO getSimResultDAO();

	/**
	 * @return ISimStatisticDAO
	 */
	public abstract ISimStatisticDAO getSimStatisticDAO();


}//end of class
