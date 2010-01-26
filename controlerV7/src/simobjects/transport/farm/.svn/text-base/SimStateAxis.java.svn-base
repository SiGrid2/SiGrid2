/**
 * SimStateAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class SimStateAxis  implements java.io.Serializable {
    private int progress;

    private long runtime;

    public SimStateAxis() {
    }

    public SimStateAxis(
           int progress,
           long runtime) {
           this.progress = progress;
           this.runtime = runtime;
    }


    /**
     * Gets the progress value for this SimStateAxis.
     * 
     * @return progress
     */
    public int getProgress() {
        return progress;
    }


    /**
     * Sets the progress value for this SimStateAxis.
     * 
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }


    /**
     * Gets the runtime value for this SimStateAxis.
     * 
     * @return runtime
     */
    public long getRuntime() {
        return runtime;
    }


    /**
     * Sets the runtime value for this SimStateAxis.
     * 
     * @param runtime
     */
    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimStateAxis)) return false;
        SimStateAxis other = (SimStateAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.progress == other.getProgress() &&
            this.runtime == other.getRuntime();
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
        _hashCode += getProgress();
        _hashCode += new Long(getRuntime()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimStateAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStateAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "progress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runtime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "runtime"));
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
