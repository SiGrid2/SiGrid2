/**
 * SimStatisticsAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class SimStatisticsAxis  implements java.io.Serializable {
    private long end;

    private int fields;

    private int id;

    private int numStatisticsAcc;

    private long start;

    private long[] statistic;

    private double tick;

    public SimStatisticsAxis() {
    }

    public SimStatisticsAxis(
           long end,
           int fields,
           int id,
           int numStatisticsAcc,
           long start,
           long[] statistic,
           double tick) {
           this.end = end;
           this.fields = fields;
           this.id = id;
           this.numStatisticsAcc = numStatisticsAcc;
           this.start = start;
           this.statistic = statistic;
           this.tick = tick;
    }


    /**
     * Gets the end value for this SimStatisticsAxis.
     * 
     * @return end
     */
    public long getEnd() {
        return end;
    }


    /**
     * Sets the end value for this SimStatisticsAxis.
     * 
     * @param end
     */
    public void setEnd(long end) {
        this.end = end;
    }


    /**
     * Gets the fields value for this SimStatisticsAxis.
     * 
     * @return fields
     */
    public int getFields() {
        return fields;
    }


    /**
     * Sets the fields value for this SimStatisticsAxis.
     * 
     * @param fields
     */
    public void setFields(int fields) {
        this.fields = fields;
    }


    /**
     * Gets the id value for this SimStatisticsAxis.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this SimStatisticsAxis.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the numStatisticsAcc value for this SimStatisticsAxis.
     * 
     * @return numStatisticsAcc
     */
    public int getNumStatisticsAcc() {
        return numStatisticsAcc;
    }


    /**
     * Sets the numStatisticsAcc value for this SimStatisticsAxis.
     * 
     * @param numStatisticsAcc
     */
    public void setNumStatisticsAcc(int numStatisticsAcc) {
        this.numStatisticsAcc = numStatisticsAcc;
    }


    /**
     * Gets the start value for this SimStatisticsAxis.
     * 
     * @return start
     */
    public long getStart() {
        return start;
    }


    /**
     * Sets the start value for this SimStatisticsAxis.
     * 
     * @param start
     */
    public void setStart(long start) {
        this.start = start;
    }


    /**
     * Gets the statistic value for this SimStatisticsAxis.
     * 
     * @return statistic
     */
    public long[] getStatistic() {
        return statistic;
    }


    /**
     * Sets the statistic value for this SimStatisticsAxis.
     * 
     * @param statistic
     */
    public void setStatistic(long[] statistic) {
        this.statistic = statistic;
    }


    /**
     * Gets the tick value for this SimStatisticsAxis.
     * 
     * @return tick
     */
    public double getTick() {
        return tick;
    }


    /**
     * Sets the tick value for this SimStatisticsAxis.
     * 
     * @param tick
     */
    public void setTick(double tick) {
        this.tick = tick;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimStatisticsAxis)) return false;
        SimStatisticsAxis other = (SimStatisticsAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.end == other.getEnd() &&
            this.fields == other.getFields() &&
            this.id == other.getId() &&
            this.numStatisticsAcc == other.getNumStatisticsAcc() &&
            this.start == other.getStart() &&
            ((this.statistic==null && other.getStatistic()==null) || 
             (this.statistic!=null &&
              java.util.Arrays.equals(this.statistic, other.getStatistic()))) &&
            this.tick == other.getTick();
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
        _hashCode += new Long(getEnd()).hashCode();
        _hashCode += getFields();
        _hashCode += getId();
        _hashCode += getNumStatisticsAcc();
        _hashCode += new Long(getStart()).hashCode();
        if (getStatistic() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatistic());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatistic(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Double(getTick()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimStatisticsAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStatisticsAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("end");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "end"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "fields"));
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
        elemField.setFieldName("numStatisticsAcc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "numStatisticsAcc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("start");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "start"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statistic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "statistic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tick");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "tick"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
