package controler.persistence.testrun;

import simobjects.TestRun;
import controler.persistence.GenericJPADAO;


/**
 * JPA implementation of ITestrunDAO
 * 
 * @author Dirk Holzapfel
 * @version 1.0
 */
public class TestrunDAOJPA extends GenericJPADAO <TestRun, Integer> implements ITestrunDAO {

}//end of class
