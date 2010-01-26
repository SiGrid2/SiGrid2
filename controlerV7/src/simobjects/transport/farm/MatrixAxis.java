/**
 * MatrixAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class MatrixAxis  implements java.io.Serializable {
    private java.lang.String matrixForDB;

    private int numJobs;

    private int numServer;

    public MatrixAxis() {
    }

    public MatrixAxis(
           java.lang.String matrixForDB,
           int numJobs,
           int numServer) {
           this.matrixForDB = matrixForDB;
           this.numJobs = numJobs;
           this.numServer = numServer;
    }


    /**
     * Gets the matrixForDB value for this MatrixAxis.
     * 
     * @return matrixForDB
     */
    public java.lang.String getMatrixForDB() {
        return matrixForDB;
    }


    /**
     * Sets the matrixForDB value for this MatrixAxis.
     * 
     * @param matrixForDB
     */
    public void setMatrixForDB(java.lang.String matrixForDB) {
        this.matrixForDB = matrixForDB;
    }


    /**
     * Gets the numJobs value for this MatrixAxis.
     * 
     * @return numJobs
     */
    public int getNumJobs() {
        return numJobs;
    }


    /**
     * Sets the numJobs value for this MatrixAxis.
     * 
     * @param numJobs
     */
    public void setNumJobs(int numJobs) {
        this.numJobs = numJobs;
    }


    /**
     * Gets the numServer value for this MatrixAxis.
     * 
     * @return numServer
     */
    public int getNumServer() {
        return numServer;
    }


    /**
     * Sets the numServer value for this MatrixAxis.
     * 
     * @param numServer
     */
    public void setNumServer(int numServer) {
        this.numServer = numServer;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MatrixAxis)) return false;
        MatrixAxis other = (MatrixAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.matrixForDB==null && other.getMatrixForDB()==null) || 
             (this.matrixForDB!=null &&
              this.matrixForDB.equals(other.getMatrixForDB()))) &&
            this.numJobs == other.getNumJobs() &&
            this.numServer == other.getNumServer();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMatrixForDB() != null) {
            _hashCode += getMatrixForDB().hashCode();
        }
        _hashCode += getNumJobs();
        _hashCode += getNumServer();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MatrixAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "MatrixAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matrixForDB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "matrixForDB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "numJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "numServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
