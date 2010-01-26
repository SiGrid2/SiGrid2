/**
 * SimResultAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class SimResultAxis  implements java.io.Serializable {
    private long earning;

    private int id;

    private simobjects.transport.farm.MatrixAxis matrix;

    public SimResultAxis() {
    }

    public SimResultAxis(
           long earning,
           int id,
           simobjects.transport.farm.MatrixAxis matrix) {
           this.earning = earning;
           this.id = id;
           this.matrix = matrix;
    }


    /**
     * Gets the earning value for this SimResultAxis.
     * 
     * @return earning
     */
    public long getEarning() {
        return earning;
    }


    /**
     * Sets the earning value for this SimResultAxis.
     * 
     * @param earning
     */
    public void setEarning(long earning) {
        this.earning = earning;
    }


    /**
     * Gets the id value for this SimResultAxis.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this SimResultAxis.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the matrix value for this SimResultAxis.
     * 
     * @return matrix
     */
    public simobjects.transport.farm.MatrixAxis getMatrix() {
        return matrix;
    }


    /**
     * Sets the matrix value for this SimResultAxis.
     * 
     * @param matrix
     */
    public void setMatrix(simobjects.transport.farm.MatrixAxis matrix) {
        this.matrix = matrix;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimResultAxis)) return false;
        SimResultAxis other = (SimResultAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.earning == other.getEarning() &&
            this.id == other.getId() &&
            ((this.matrix==null && other.getMatrix()==null) || 
             (this.matrix!=null &&
              this.matrix.equals(other.getMatrix())));
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
        _hashCode += new Long(getEarning()).hashCode();
        _hashCode += getId();
        if (getMatrix() != null) {
            _hashCode += getMatrix().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimResultAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimResultAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("earning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "earning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matrix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "matrix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "MatrixAxis"));
        elemField.setNillable(true);
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
