package controler.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


/**
 * implementation of the generic superinterface IGenericDAO with JPA
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 *
 * @param <T>
 * @param <ID>
 */
public class GenericJPADAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	private Class<T> persistentClass;
	private EntityManager entityManager;

	public GenericJPADAO(){
		this.persistentClass = (Class<T>)
		((ParameterizedType) getClass().getGenericSuperclass())
		.getActualTypeArguments()[0];
	}


	/* (non-Javadoc)
	 * @see controler.persistence.IGenericDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getEntityManager().createQuery("from " + getPersistentClass().getName()).getResultList();
	}


	/* (non-Javadoc)
	 * @see controler.persistence.IGenericDAO#getById(java.io.Serializable)
	 */
	public T getById(ID id) {		
		return (T) getEntityManager().find(getPersistentClass(), id);	

	}


	/* (non-Javadoc)
	 * @see controler.persistence.IGenericDAO#persist(java.lang.Object)
	 */
	public T persist(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		T obj = em.merge(entity);

		tx.commit();

		return obj;
	}


	/* (non-Javadoc)
	 * @see controler.persistence.IGenericDAO#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();

		getEntityManager().remove(entity);

		tx.commit();
	}


	/**
	 * @return class of the generic argument T
	 */
	public Class<T> getPersistentClass(){
		return persistentClass;
	}



	/**
	 * @return JPA EntityManager
	 */
	public EntityManager getEntityManager() {
		if (entityManager == null){
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	/**
	 * @param entityManager JPA EntityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void clear() {
		getEntityManager().clear();
	}

	public void flush() {
		getEntityManager().flush();

	}	


}//end of class
