/**
 * GetWorkItemIDResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.longfor.itserver.esi.bpm.bpm;

public class GetWorkItemIDResponse  implements java.io.Serializable {
    private String getWorkItemIDResult;

    public GetWorkItemIDResponse() {
    }

    public GetWorkItemIDResponse(
           String getWorkItemIDResult) {
           this.getWorkItemIDResult = getWorkItemIDResult;
    }


    /**
     * Gets the getWorkItemIDResult value for this GetWorkItemIDResponse.
     * 
     * @return getWorkItemIDResult
     */
    public String getGetWorkItemIDResult() {
        return getWorkItemIDResult;
    }


    /**
     * Sets the getWorkItemIDResult value for this GetWorkItemIDResponse.
     * 
     * @param getWorkItemIDResult
     */
    public void setGetWorkItemIDResult(String getWorkItemIDResult) {
        this.getWorkItemIDResult = getWorkItemIDResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetWorkItemIDResponse)) return false;
        GetWorkItemIDResponse other = (GetWorkItemIDResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getWorkItemIDResult==null && other.getGetWorkItemIDResult()==null) || 
             (this.getWorkItemIDResult!=null &&
              this.getWorkItemIDResult.equals(other.getGetWorkItemIDResult())));
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
        if (getGetWorkItemIDResult() != null) {
            _hashCode += getGetWorkItemIDResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetWorkItemIDResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetWorkItemIDResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getWorkItemIDResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetWorkItemIDResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
