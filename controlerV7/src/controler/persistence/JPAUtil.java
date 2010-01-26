package controler.persistence;

import javax.persistence.*;


/**
 * singleton class. provides the EntityManagerFactory
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class JPAUtil {

	private static EntityManagerFactory emFactory;

	private JPAUtil(){};
	
	static{
		try{
			emFactory = Persistence.createEntityManagerFactory("default");			
		}
		catch(Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory(){		
		return emFactory;
	}

	public static void shutdown(){
		getEntityManagerFactory().close();
	}

}//end of class
