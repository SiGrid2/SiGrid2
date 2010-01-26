package controler.persistence.simfarm;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import simobjects.SimFarm;
import controler.persistence.GenericJPADAO;
import controler.persistence.JPAUtil;


/**
 * JPA implementation of ISimFarmDAO
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class SimFarmDAOJPA extends GenericJPADAO <SimFarm, Integer> implements ISimFarmDAO {

	/* (non-Javadoc)
	 * @see controler.persistence.simfarm.ISimFarmDAO#getSimFarmByInitId(long)
	 */
	public SimFarm getSimFarmByInitId(long initId) {
		
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery("SELECT x FROM SimFarm x WHERE x.initID = '"+initId+"'");
		SimFarm sf = null;
		try{
			sf = (SimFarm) query.getSingleResult();
		}catch(NoResultException e){
			System.out.println("exception: simfarm with that initid not found");			
		}	
		
		return sf;
	}

}//end of class
