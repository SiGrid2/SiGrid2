package controler.persistence;

import controler.persistence.algorithm.AlgorithmDAOJPA;
import controler.persistence.algorithm.IAlgorithmDAO;
import controler.persistence.layout.ILayoutDAO;
import controler.persistence.layout.LayoutDAOJPA;
import controler.persistence.simfarm.ISimFarmDAO;
import controler.persistence.simfarm.SimFarmDAOJPA;
import controler.persistence.simjob.ISimJobDAO;
import controler.persistence.simjob.SimJobDAOJPA;
import controler.persistence.simresult.ISimResultDAO;
import controler.persistence.simresult.SimResultDAOJPA;
import controler.persistence.simstatistic.ISimStatisticDAO;
import controler.persistence.simstatistic.SimStatisticDAOJPA;
import controler.persistence.testrun.ITestrunDAO;
import controler.persistence.testrun.TestrunDAOJPA;

/**
 * implementation of the abstract factory with JPA
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class JPADAOFactory extends DAOFactory {

	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getLayoutDAO()
	 */
	public ILayoutDAO getLayoutDAO(){
		return (ILayoutDAO) instantiateDAO (LayoutDAOJPA.class);		
	}	


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getAlgorithmDAO()
	 */
	public IAlgorithmDAO getAlgorithmDAO() {
		return (IAlgorithmDAO) instantiateDAO (AlgorithmDAOJPA.class);
	}


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getSimJobDAO()
	 */
	public ISimJobDAO getSimJobDAO() {
		return (ISimJobDAO) instantiateDAO (SimJobDAOJPA.class);
	}


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getTestrunDAO()
	 */
	public ITestrunDAO getTestrunDAO() {
		return (ITestrunDAO) instantiateDAO (TestrunDAOJPA.class);
	}


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getSimFarmDAO()
	 */
	public ISimFarmDAO getSimFarmDAO() {
		return (ISimFarmDAO) instantiateDAO (SimFarmDAOJPA.class);
	}	


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getSimResultDAO()
	 */
	public ISimResultDAO getSimResultDAO() {
		return (ISimResultDAO) instantiateDAO (SimResultDAOJPA.class);
	}


	/* (non-Javadoc)
	 * @see controler.persistence.DAOFactory#getSimStatisticDAO()
	 */
	public ISimStatisticDAO getSimStatisticDAO() {
		return (ISimStatisticDAO) instantiateDAO (SimStatisticDAOJPA.class);
	}


	/**
	 * instantiates the JPA DAOs
	 * 
	 * @param daoClass
	 * @return
	 */
	private GenericJPADAO instantiateDAO(Class daoClass){
		try{
			GenericJPADAO dao = (GenericJPADAO) daoClass.newInstance();
			return dao;
		} catch (Exception e){
			throw new RuntimeException("Can't instantiate DAO: " + daoClass, e);
		}
	}

	
}//end of class
