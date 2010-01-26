/**
 * TestrunAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class TestrunAxis  implements java.io.Serializable {
    private simobjects.transport.farm.AlgorithmAxis algorithm;

    private int id;

    private simobjects.transport.farm.LayoutAxis layout;

    private java.lang.String name;

    private long statsEnd;

    private int statsNumFields;

    private long statsStart;

    public TestrunAxis() {
    }

    public TestrunAxis(
           simobjects.transport.farm.AlgorithmAxis algorithm,
           int id,
           simobjects.transport.farm.LayoutAxis layout,
           java.lang.String name,
           long statsEnd,
           int statsNumFields,
           long statsStart) {
           this.algorithm = algorithm;
           this.id = id;
           this.layout = layout;
           this.name = name;
           this.statsEnd = statsEnd;
           this.statsNumFields = statsNumFields;
           this.statsStart = statsStart;
    }


    /**
     * Gets the algorithm value for this TestrunAxis.
     * 
     * @return algorithm
     */
    public simobjects.transport.farm.AlgorithmAxis getAlgorithm() {
        return algorithm;
    }


    /**
     * Sets the algorithm value for this TestrunAxis.
     * 
     * @param algorithm
     */
    public void setAlgorithm(simobjects.transport.farm.AlgorithmAxis algorithm) {
        this.algorithm = algorithm;
    }


    /**
     * Gets the id value for this TestrunAxis.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this TestrunAxis.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the layout value for this TestrunAxis.
     * 
     * @return layout
     */
    public simobjects.transport.farm.LayoutAxis getLayout() {
        return layout;
    }


    /**
     * Sets the layout value for this TestrunAxis.
     * 
     * @param layout
     */
    public void setLayout(simobjects.transport.farm.LayoutAxis layout) {
        this.layout = layout;
    }


    /**
     * Gets the name value for this TestrunAxis.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this TestrunAxis.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the statsEnd value for this TestrunAxis.
     * 
     * @return statsEnd
     */
    public long getStatsEnd() {
        return statsEnd;
    }


    /**
     * Sets the statsEnd value for this TestrunAxis.
     * 
     * @param statsEnd
     */
    public void setStatsEnd(long statsEnd) {
        this.statsEnd = statsEnd;
    }


    /**
     * Gets the statsNumFields value for this TestrunAxis.
     * 
     * @return statsNumFields
     */
    public int getStatsNumFields() {
        return statsNumFields;
    }


    /**
     * Sets the statsNumFields value for this TestrunAxis.
     * 
     * @param statsNumFields
     */
    public void setStatsNumFields(int statsNumFields) {
        this.statsNumFields = statsNumFields;
    }


    /**
     * Gets the statsStart value for this TestrunAxis.
     * 
     * @return statsStart
     */
    public long getStatsStart() {
        return statsStart;
    }


    /**
     * Sets the statsStart value for this TestrunAxis.
     * 
     * @param statsStart
     */
    public void setStatsStart(long statsStart) {
        this.statsStart = statsStart;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TestrunAxis)) return false;
        TestrunAxis other = (TestrunAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.algorithm==null && other.getAlgorithm()==null) || 
             (this.algorithm!=null &&
              this.algorithm.equals(other.getAlgorithm()))) &&
            this.id == other.getId() &&
            ((this.layout==null && other.getLayout()==null) || 
             (this.layout!=null &&
              this.layout.equals(other.getLayout()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.statsEnd == other.getStatsEnd() &&
            this.statsNumFields == other.getStatsNumFields() &&
            this.statsStart == other.getStatsStart();
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
        if (getAlgorithm() != null) {
            _hashCode += getAlgorithm().hashCode();
        }
        _hashCode += getId();
        if (getLayout() != null) {
            _hashCode += getLayout().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += new Long(getStatsEnd()).hashCode();
        _hashCode += getStatsNumFields();
        _hashCode += new Long(getStatsStart()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TestrunAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "TestrunAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("algorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "algorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "AlgorithmAxis"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "layout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "LayoutAxis"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statsEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "statsEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statsNumFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "statsNumFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statsStart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "statsStart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
