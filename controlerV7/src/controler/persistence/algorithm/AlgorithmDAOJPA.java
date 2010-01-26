package controler.persistence.algorithm;

import simobjects.Algorithm;
import controler.persistence.GenericJPADAO;


/**
 * JPA implementation of IAlgorithmDAO
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class AlgorithmDAOJPA extends GenericJPADAO <Algorithm, Integer> implements IAlgorithmDAO {

}//end of class
