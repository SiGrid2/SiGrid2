package controler.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * generic CRUD superinterface
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 *
 * @param <T> entity-instance of the DAO
 * @param <ID> type of the primary key in the DB
 */
public interface IGenericDAO <T, ID extends Serializable> {

	/**
	 * returns all instances of a certain entity from DB 
	 * 
	 * @return list of entities
	 */
	public List<T> getAll();


	/**
	 * returns the instance of a certain entity with the ID id from DB 
	 * 
	 * @param id
	 * @return the entity
	 */
	public T getById(ID id);


	/**
	 * creates/updates an instance of a certain entity in DB
	 * 
	 * @param entity
	 * @return the created/updated entity
	 */
	public T persist(T entity);


	/**
	 * deletes an instance of a certain entity in DB
	 * 
	 * @param entity
	 */
	public void delete(T entity);



	public void clear();		

	public void flush();


}//end of interface
