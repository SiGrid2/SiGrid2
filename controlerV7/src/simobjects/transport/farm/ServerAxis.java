/**
 * ServerAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class ServerAxis  implements java.io.Serializable {
    private int cost;

    private int dasd;

    private int id;

    private int relCat;

    private int speedCat;

    public ServerAxis() {
    }

    public ServerAxis(
           int cost,
           int dasd,
           int id,
           int relCat,
           int speedCat) {
           this.cost = cost;
           this.dasd = dasd;
           this.id = id;
           this.relCat = relCat;
           this.speedCat = speedCat;
    }


    /**
     * Gets the cost value for this ServerAxis.
     * 
     * @return cost
     */
    public int getCost() {
        return cost;
    }


    /**
     * Sets the cost value for this ServerAxis.
     * 
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }


    /**
     * Gets the dasd value for this ServerAxis.
     * 
     * @return dasd
     */
    public int getDasd() {
        return dasd;
    }


    /**
     * Sets the dasd value for this ServerAxis.
     * 
     * @param dasd
     */
    public void setDasd(int dasd) {
        this.dasd = dasd;
    }


    /**
     * Gets the id value for this ServerAxis.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this ServerAxis.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the relCat value for this ServerAxis.
     * 
     * @return relCat
     */
    public int getRelCat() {
        return relCat;
    }


    /**
     * Sets the relCat value for this ServerAxis.
     * 
     * @param relCat
     */
    public void setRelCat(int relCat) {
        this.relCat = relCat;
    }


    /**
     * Gets the speedCat value for this ServerAxis.
     * 
     * @return speedCat
     */
    public int getSpeedCat() {
        return speedCat;
    }


    /**
     * Sets the speedCat value for this ServerAxis.
     * 
     * @param speedCat
     */
    public void setSpeedCat(int speedCat) {
        this.speedCat = speedCat;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServerAxis)) return false;
        ServerAxis other = (ServerAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cost == other.getCost() &&
            this.dasd == other.getDasd() &&
            this.id == other.getId() &&
            this.relCat == other.getRelCat() &&
            this.speedCat == other.getSpeedCat();
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
        _hashCode += getCost();
        _hashCode += getDasd();
        _hashCode += getId();
        _hashCode += getRelCat();
        _hashCode += getSpeedCat();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServerAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "ServerAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cost");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dasd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "dasd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relCat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "relCat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("speedCat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "speedCat"));
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
