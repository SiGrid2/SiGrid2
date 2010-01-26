package controler.persistence.simfarm;

import simobjects.SimFarm;
import controler.persistence.IGenericDAO;


/**
 * DAO interface of entity simfarm, extending and parametrizes superinterface IGenericDAO
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public interface ISimFarmDAO extends IGenericDAO<SimFarm, Integer > {
	
	/**
	 * @param initId
	 * @return simfarm with a certain initID from DB
	 */
	public SimFarm getSimFarmByInitId(long initId);

}//end of interface
