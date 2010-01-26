/**
 * LayoutAxis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package simobjects.transport.farm;

public class LayoutAxis  implements java.io.Serializable {
    private int id;

    private simobjects.transport.farm.JobAxis[] jobs;

    private java.lang.String name;

    private simobjects.transport.farm.ServerAxis[] server;

    public LayoutAxis() {
    }

    public LayoutAxis(
           int id,
           simobjects.transport.farm.JobAxis[] jobs,
           java.lang.String name,
           simobjects.transport.farm.ServerAxis[] server) {
           this.id = id;
           this.jobs = jobs;
           this.name = name;
           this.server = server;
    }


    /**
     * Gets the id value for this LayoutAxis.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this LayoutAxis.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the jobs value for this LayoutAxis.
     * 
     * @return jobs
     */
    public simobjects.transport.farm.JobAxis[] getJobs() {
        return jobs;
    }


    /**
     * Sets the jobs value for this LayoutAxis.
     * 
     * @param jobs
     */
    public void setJobs(simobjects.transport.farm.JobAxis[] jobs) {
        this.jobs = jobs;
    }


    /**
     * Gets the name value for this LayoutAxis.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this LayoutAxis.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the server value for this LayoutAxis.
     * 
     * @return server
     */
    public simobjects.transport.farm.ServerAxis[] getServer() {
        return server;
    }


    /**
     * Sets the server value for this LayoutAxis.
     * 
     * @param server
     */
    public void setServer(simobjects.transport.farm.ServerAxis[] server) {
        this.server = server;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LayoutAxis)) return false;
        LayoutAxis other = (LayoutAxis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.jobs==null && other.getJobs()==null) || 
             (this.jobs!=null &&
              java.util.Arrays.equals(this.jobs, other.getJobs()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.server==null && other.getServer()==null) || 
             (this.server!=null &&
              java.util.Arrays.equals(this.server, other.getServer())));
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
        _hashCode += getId();
        if (getJobs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJobs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJobs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getServer() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServer());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServer(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LayoutAxis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "LayoutAxis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "jobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "JobAxis"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("server");
        elemField.setXmlName(new javax.xml.namespace.QName("http://farm.transport.simobjects", "server"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "ServerAxis"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item"));
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
